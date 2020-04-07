package com.timsterj.imgur.model;

public class Comment {

    private String id;
    private String author;
    private String comment;
    private int points;
    private int datetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDatetime() {
        return datetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }
}
