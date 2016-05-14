package hu.tomkom.deliveryapp.model;

import java.util.List;

public class Rent {

    private String name;

    private String start;

    private String end;

    private RentStatus status;

    private List<Comment> comments;

    public Rent() {
    }

    public Rent(String name, String start, String end, RentStatus status, List<Comment> comments) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.status = status;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
