package lt.voidpumpkin.homework.todoapi;

import java.sql.Timestamp;

public class TodoListItemResponse {
    private Integer id;

    private String text;

    private Timestamp date;

    public TodoListItemResponse() {
    }

    public TodoListItemResponse(Integer id, String text, Timestamp date) {
        this.id = id;
        this.text = text;
        this.date = date;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
