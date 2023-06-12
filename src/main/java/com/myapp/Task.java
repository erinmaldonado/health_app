package com.myapp;

import java.sql.Date;

public class Task {
    private String taskName;
    private Date taskDate;
    private int taskDuration;
    private boolean taskComplete;

    private int taskId;
    private int taskCategoryId;

    public Task(String taskName){
        this.taskName = taskName;
        this.taskComplete = false;
    }

    public String getTaskName(){return taskName;}
}
