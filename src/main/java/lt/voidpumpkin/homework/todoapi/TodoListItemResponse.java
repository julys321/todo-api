package lt.voidpumpkin.homework.todoapi;

import lt.voidpumpkin.homework.generated.db.tables.records.TodoitemRecord;

import java.sql.Timestamp;

public class TodoListItemResponse {
    private Integer id;

    private String text;

    private Timestamp creationDate;

    public TodoListItemResponse() {
    }

    public TodoListItemResponse(Integer id, String text, Timestamp creationDate) {
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
    }

    public TodoListItemResponse(TodoitemRecord todoitemRecord) {
        this.id = todoitemRecord.getId();
        this.text = todoitemRecord.getText();
        this.creationDate = todoitemRecord.getCreationdate();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
