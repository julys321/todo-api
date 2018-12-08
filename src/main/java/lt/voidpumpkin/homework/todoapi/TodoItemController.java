package lt.voidpumpkin.homework.todoapi;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
}
