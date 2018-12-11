package lt.voidpumpkin.homework.todoapi;

import lt.voidpumpkin.homework.generated.db.tables.records.TodoitemRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoListItemService {
    static public List<TodoListItemResponse> translateRecordsToResponses(TodoitemRecord[] todoitemRecords) {
        List<TodoListItemResponse> todoListItemsFromDatabase = new ArrayList<>();
        for (TodoitemRecord todoitemRecord : todoitemRecords) {
            todoListItemsFromDatabase.add(new TodoListItemResponse(todoitemRecord));
        }
        return todoListItemsFromDatabase;
    }
}
