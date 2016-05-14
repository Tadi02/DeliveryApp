package hu.tomkom.deliveryapp.model;

public class Comment {

    private Long id;

    private String time;

    private String text;

    public Comment() {
    }

    public Comment(Long id, String time, String text) {
        this.id = id;
        this.time = time;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
