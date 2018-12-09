package lt.voidpumpkin.homework.todoapi;

public class TodoListItemResponse {
    private String id;

    private String text;

    private String date;

    public TodoListItemResponse() {
    }

    public TodoListItemResponse(String id, String text, String date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
