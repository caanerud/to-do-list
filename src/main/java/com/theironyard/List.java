package com.theironyard;

import java.util.Date;

/**
 * Created by chrisaanerud on 4/18/17.
 */
public class List {
    private Integer id;
    private boolean completed;
    private Date dueDate;
    private Integer priority;
    private String task;
    private String details;
    private Integer user;

    public List(Integer id, boolean completed, Date dueDate, Integer priority, String task, String details, Integer user) {
        this.id = id;
        this.completed = completed;
        this.dueDate = dueDate;
        this.priority = priority;
        this.task = task;
        this.details = details;
        this.user = user;
    }

    public List(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
