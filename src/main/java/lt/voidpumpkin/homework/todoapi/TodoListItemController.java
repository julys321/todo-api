package lt.voidpumpkin.homework.todoapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoListItemController {

    @Autowired
    TodoListItemService todoListItemService;


    @CrossOrigin
    @RequestMapping(value = "/todoList", method = RequestMethod.GET)
    public List<TodoListItemResponse> getTodoList() {
        return todoListItemService.fetchTodoListItemsFromDatabase();
    }

    @CrossOrigin
    @RequestMapping(value = "/todoListItem", method = RequestMethod.POST)
    //TODO make it return only the item that was added
    public TodoListItemResponse addNewTodoItem(@RequestBody TodoListItemResponse todoListItemResponse) {
        return todoListItemService.addNewTodoItemToDatabase(todoListItemResponse);
    }

    @CrossOrigin
    @RequestMapping(value = "/archiveTodoListItem/{todoListItemId}", method = RequestMethod.PUT)
    public TodoListItemResponse archiveTodoItem(@PathVariable Integer todoListItemId) {
        return todoListItemService.setDatabaseTodoItemAsArchived(todoListItemId);
    }

}
