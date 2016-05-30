package com.example.yalantis.y1.events;

import com.example.yalantis.y1.model.task.TaskModel;

public class TaskLoadByIdEvent {
    private TaskModel mListTaskModel;

    public TaskLoadByIdEvent(TaskModel listTaskModel) {
        this.mListTaskModel = listTaskModel;
    }

    public TaskModel getTaskBeanList() {
        return mListTaskModel;
    }

}
