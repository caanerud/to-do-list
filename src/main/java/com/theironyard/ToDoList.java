package com.theironyard;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by chrisaanerud on 4/18/17.
 */
public class ToDoList {
    private Integer id;
    private boolean completed;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
    private Integer priority;
    @Size(min=2, max=30)
    @NotNull
    private String taskName;
    @Size(min=2, max=255)
    @NotNull
    private String details;
    private Integer user = 1;

    public ToDoList(Integer id, boolean completed, Date dueDate, Integer priority, String task, String details, Integer user) {
        this.id = id;
        this.completed = completed;
        this.dueDate = dueDate;
        this.priority = priority;
        this.taskName = task;
        this.details = details;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "id=" + id +
                ", completed=" + completed +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", taskName='" + taskName + '\'' +
                ", details='" + details + '\'' +
                ", user=" + user +
                '}';


}

    public ToDoList(){

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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String task) {
        this.taskName = task;
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
