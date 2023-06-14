package com.myapp;


import java.sql.Date;

public class Task {
    private String taskName;
    private Date taskDateAdded;
    private Date taskDateCompleted;

    private int taskDuration;
    private boolean taskComplete;

    private int taskId;
    private int taskCategoryId;

    public Task(){

    }

    public Task(String taskName, Date taskDateAdded){
        this.taskName = taskName;
        this.taskDateAdded = taskDateAdded;
        this.taskComplete = false;
    }

    public void setTaskCategoryId(int taskCategoryId){
        this.taskCategoryId = taskCategoryId;
    }

    public boolean getTaskComplete(){
        return taskComplete;
    }
    public String getTaskName(){return taskName;}

    public int getTaskCategoryId(){
        return taskCategoryId;
    }

    public Date getTaskDateAdded(){return taskDateAdded;}

    public void setId(int taskId) {
        this.taskId = taskId;
    }

    public void setCompletedDate(Date currentDate) {
        this.taskDateCompleted = currentDate;
    }

    public int getTaskId() {
        return taskId;
    }
}
