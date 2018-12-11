package lt.voidpumpkin.homework.todoapi;

import lt.voidpumpkin.homework.generated.db.tables.records.TodoitemRecord;

import java.util.ArrayList;
import java.util.List;

public class ClassTranslator {
    static public List<TodoListItemResponse> translateRecordsToResponses(TodoitemRecord[] todoitemRecords) {
        List<TodoListItemResponse> todoListItemsFromDatabase = new ArrayList<>();
        for (TodoitemRecord todoitemRecord : todoitemRecords) {
            todoListItemsFromDatabase.add(new TodoListItemResponse(todoitemRecord));
        }
        return todoListItemsFromDatabase;
    }
}
