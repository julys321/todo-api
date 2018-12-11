package lt.voidpumpkin.homework.todoapi;

import lt.voidpumpkin.homework.generated.db.tables.Todoitem;
import lt.voidpumpkin.homework.generated.db.tables.records.TodoitemRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoListItemService {

    @Autowired
    TodoListItemController todoListItemController;

    @Autowired
    private DSLContext dsl;

    private Todoitem todoItemTable = Todoitem.TODOITEM;

    List<TodoListItemResponse> addNewTodoItemToDatabase(TodoListItemResponse todoListItemResponse) {
        dsl.insertInto(todoItemTable)
                .set(todoItemTable.TEXT, todoListItemResponse.getText())
                .set(todoItemTable.CREATIONDATE, new Timestamp(Instant.now().toEpochMilli()))
                //TODO fix bug where isArchived is always null and delete this hard code
                .set(todoItemTable.ISARCHIVED, false)
                .execute();
        return fetchTodoListItemsFromDatabase();
    }

    List<TodoListItemResponse> setDatabaseTodoItemAsArchived(Integer todoListItemId) {
        dsl.update(todoItemTable)
                .set(todoItemTable.ISARCHIVED, true)
                .where(todoItemTable.ID.eq(todoListItemId))
                .execute();
        dsl.fetchOne(todoItemTable, todoItemTable.ID.like(String.valueOf(todoListItemId)))
                .setIsarchived(true);
        return fetchTodoListItemsFromDatabase();
    }

    List<TodoListItemResponse> fetchTodoListItemsFromDatabase() {
        TodoitemRecord[] todoitemRecords = dsl
                .selectFrom(todoItemTable)
                .fetchArray();
        return turnTodoItemRecordsToResponses(todoitemRecords);
    }

    private List<TodoListItemResponse> turnTodoItemRecordsToResponses(TodoitemRecord[] todoitemRecords) {
        List<TodoListItemResponse> todoListItemsFromDatabase = new ArrayList<>();
        for (TodoitemRecord todoitemRecord : todoitemRecords) {
            todoListItemsFromDatabase.add(new TodoListItemResponse(todoitemRecord));
        }
        return todoListItemsFromDatabase;
    }
}
