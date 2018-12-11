package lt.voidpumpkin.homework.todoapi;

import lt.voidpumpkin.homework.generated.db.tables.Todoitem;
import lt.voidpumpkin.homework.generated.db.tables.records.TodoitemRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoListItemController {

    @Autowired
    private DSLContext dsl;

    private Todoitem testingItem = Todoitem.TODOITEM;

    private List<TodoListItemResponse> getTodoListItemsFromDatabase() {
        TodoitemRecord[] todoitemRecords = dsl
                .selectFrom(testingItem)
                .fetchArray();
        //TODO find a better way to do this
        List<TodoListItemResponse> todoListItemsFromDatabase = new ArrayList<>();
        for (TodoitemRecord todoitemRecord : todoitemRecords) {
            todoListItemsFromDatabase.add(new TodoListItemResponse(todoitemRecord.getId(), todoitemRecord.getText(), todoitemRecord.getCreationdate()));
        }
        return todoListItemsFromDatabase;
    }

    @CrossOrigin
    @RequestMapping(value = "/TodoList", method = RequestMethod.GET)
    public List<TodoListItemResponse> getTodoList() {
        return getTodoListItemsFromDatabase();
    }

    @CrossOrigin
    @RequestMapping(value = "/addTodoItem", method = RequestMethod.POST)
    //TODO make it return only the item that was added
    public List<TodoListItemResponse> addNewTodoItem(@RequestBody TodoListItemResponse todoListItemResponse) {
        dsl.insertInto(testingItem)
                .set(testingItem.TEXT, todoListItemResponse.getText())
                .set(testingItem.CREATIONDATE, new Timestamp(Instant.now().toEpochMilli()))
                .execute();
        return getTodoListItemsFromDatabase();
    }

}
