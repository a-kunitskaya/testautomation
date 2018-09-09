package com.kunitskaya.webservices.models;

import java.util.Objects;

public class ToDo {
    private String userId;
    private String id;
    private String title;
    private String completed;

    public ToDo() {
    }

    public ToDo(String userId, String id, String title, String completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(userId, toDo.userId) &&
                Objects.equals(id, toDo.id) &&
                Objects.equals(title, toDo.title) &&
                Objects.equals(completed, toDo.completed);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, id, title, completed);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", completed='" + completed + '\'' +
                '}';
    }
}
