package lt.voidpumpkin.homework.todoapi;

import lt.voidpumpkin.homework.generated.db.tables.Todoitem;
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
public class TodoItemController {
    //TODO delete mocked list
    private List<TodoListItemResponse> todoListItemResponses;
    @Autowired
    private DSLContext dsl;

    Todoitem testingItem = Todoitem.TODOITEM;

    public TodoItemController() {
        //TODO delete mocked list filling
        this.todoListItemResponses = new ArrayList<>();
        todoListItemResponses.add(new TodoListItemResponse("1","Get eggs","2018-12-08"));
        todoListItemResponses.add(new TodoListItemResponse("5","Get pants","2009-12-08"));
        todoListItemResponses.add(new TodoListItemResponse("2","Take a bath","2020-10-01"));
    }

    @RequestMapping(value = "/allTodoItems",method = RequestMethod.GET)
    public List<TodoListItemResponse> getAllTodoItems() {
        return todoListItemResponses;
    }

    @RequestMapping(value = "/addTodoItem",method = RequestMethod.POST)
    public List<TodoListItemResponse> addNewTodoItem(@RequestBody TodoListItemResponse todoListItemResponse) {
        dsl.insertInto(testingItem)
                .set(testingItem.TEXT, todoListItemResponse.getText())
                .set(testingItem.CREATIONDATE,new Timestamp(Instant.now().toEpochMilli()))
                .execute();

        //TODO delete mocked todoitem saving
        todoListItemResponse.setId("00");//TODO id generation
        todoListItemResponse.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));//TODO move this bad stuff to Service
        todoListItemResponses.add(todoListItemResponse);
        return todoListItemResponses;
    }

}
