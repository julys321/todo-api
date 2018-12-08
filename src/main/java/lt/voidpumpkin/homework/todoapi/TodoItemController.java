package lt.voidpumpkin.homework.todoapi;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TodoItemController {
    private List<TodoItem> todoItems;

    public TodoItemController() {
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
        todoItem.setId("00");//TODO id generation
        todoItem.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));//TODO move this bad stuff to Service
        todoItems.add(todoItem);
        return todoItems;
    }

}
