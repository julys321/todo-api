package lt.voidpumpkin.homework.todoapi;

import lt.voidpumpkin.homework.generated.db.tables.Todoitem;
import lt.voidpumpkin.homework.generated.db.tables.records.TodoitemRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TodoListItemController {
    //TODO delete mocked list
    private List<TodoListItemResponse> todoListItemResponses;
    @Autowired
    private DSLContext dsl;

    Todoitem testingItem = Todoitem.TODOITEM;

    public TodoListItemController() {
        //TODO delete mocked list filling
        this.todoListItemResponses = new ArrayList<>();
        todoListItemResponses.add(new TodoListItemResponse(1,"Get eggs",new Timestamp(Instant.now().toEpochMilli())));
        todoListItemResponses.add(new TodoListItemResponse(5,"Get pants",new Timestamp(Instant.now().toEpochMilli())));
        todoListItemResponses.add(new TodoListItemResponse(2,"Take a bath",new Timestamp(Instant.now().toEpochMilli())));
    }

    @RequestMapping(value = "/TodoList",method = RequestMethod.GET)
    public List<TodoListItemResponse> getTodoList() {
        return todoListItemResponses;
    }

    @RequestMapping(value = "/addTodoItem",method = RequestMethod.POST)
    public List<TodoListItemResponse> addNewTodoItem(@RequestBody TodoListItemResponse todoListItemResponse) {
        dsl.insertInto(testingItem)
                .set(testingItem.TEXT, todoListItemResponse.getText())
                .set(testingItem.CREATIONDATE,new Timestamp(Instant.now().toEpochMilli()))
                .execute();

        //TODO delete mocked todoitem saving
        todoListItemResponse.setId(0);//TODO id generation
        todoListItemResponse.setDate(new Timestamp(Instant.now().toEpochMilli()));//TODO move this bad stuff to Service
        todoListItemResponses.add(todoListItemResponse);
        return todoListItemResponses;
    }

}
