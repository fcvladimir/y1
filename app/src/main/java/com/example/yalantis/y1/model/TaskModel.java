package com.example.yalantis.y1.model;

public class TaskModel {

    private String taskUrl;
    private int taskLikes;
    private String taskTitle;
    private String taskAddress;
    private String taskDate;
    private int taskDuration;

    public TaskModel(String taskUrl, int taskLikes, String taskTitle, String taskAddress, String taskDate, int taskDuration) {
        this.taskUrl = taskUrl;
        this.taskLikes = taskLikes;
        this.taskTitle = taskTitle;
        this.taskAddress = taskAddress;
        this.taskDate = taskDate;
        this.taskDuration = taskDuration;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public int getTaskLikes() {
        return taskLikes;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskAddress() {
        return taskAddress;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public int getTaskDuration() {
        return taskDuration;
    }
}
