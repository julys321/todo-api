package lt.voidpumpkin.homework.todoapi;

import lt.voidpumpkin.homework.generated.db.tables.Todoitem;
import lt.voidpumpkin.homework.generated.db.tables.records.TodoitemRecord;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Record4;
import org.jooq.Result;
import org.jooq.impl.DSL;
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
    private List<TodoItem> todoItems;
    @Autowired
    private DSLContext dsl;

    Todoitem testingItem = Todoitem.TODOITEM;

    public TodoItemController() {
        //TODO delete mocked list filling
        this.todoItems = new ArrayList<>();
        todoItems.add(new TodoItem("1","Get eggs","2018-12-08"));
        todoItems.add(new TodoItem("5","Get pants","2009-12-08"));
        todoItems.add(new TodoItem("2","Take a bath","2020-10-01"));
    }

    @RequestMapping(value = "/allTodoItems",method = RequestMethod.GET)
    public List<TodoItem> getAllTodoItems() {
        return todoItems;
    }

    @RequestMapping(value = "/addTodoItem",method = RequestMethod.POST)
    public List<TodoItem> addNewTodoItem(@RequestBody TodoItem todoItem) {
        dsl.insertInto(testingItem)
                .set(testingItem.TEXT,todoItem.getText())
                .set(testingItem.CREATIONDATE,new Timestamp(Instant.now().toEpochMilli()))
                .execute();

        //TODO delete mocked todoitem saving
        todoItem.setId("00");//TODO id generation
        todoItem.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));//TODO move this bad stuff to Service
        todoItems.add(todoItem);
        return todoItems;
    }

}
