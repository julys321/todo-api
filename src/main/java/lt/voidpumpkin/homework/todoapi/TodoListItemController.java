package lt.voidpumpkin.homework.todoapi;

import lt.voidpumpkin.homework.generated.db.tables.Todoitem;
import lt.voidpumpkin.homework.generated.db.tables.records.TodoitemRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
public class TodoListItemController {

    @Autowired
    private DSLContext dsl;

    private Todoitem todoItemTable = Todoitem.TODOITEM;

    private List<TodoListItemResponse> getTodoListItemsFromDatabase() {
        TodoitemRecord[] todoitemRecords = dsl
                .selectFrom(todoItemTable)
                .fetchArray();
        return ClassTranslator.translateRecordsToResponses(todoitemRecords);
    }

    @CrossOrigin
    @RequestMapping(value = "/todoList", method = RequestMethod.GET)
    public List<TodoListItemResponse> getTodoList() {
        return getTodoListItemsFromDatabase();
    }

    @CrossOrigin
    @RequestMapping(value = "/todoListItem", method = RequestMethod.POST)
    //TODO make it return only the item that was added
    public List<TodoListItemResponse> addNewTodoItem(@RequestBody TodoListItemResponse todoListItemResponse) {
        dsl.insertInto(todoItemTable)
                .set(todoItemTable.TEXT, todoListItemResponse.getText())
                .set(todoItemTable.CREATIONDATE, new Timestamp(Instant.now().toEpochMilli()))
                //TODO fix bug where isArchived is always null and delete this hard code
                .set(todoItemTable.ISARCHIVED, false)
                .execute();
        return getTodoListItemsFromDatabase();
    }

    @CrossOrigin
    @RequestMapping(value = "/archiveTodoListItem/{todoListItemId}", method = RequestMethod.PUT)
    public List<TodoListItemResponse> archiveTodoItem(@PathVariable Integer todoListItemId) {
        dsl.fetchOne(todoItemTable, todoItemTable.ID.like(String.valueOf(todoListItemId)));
        return getTodoListItemsFromDatabase();
    }

}
