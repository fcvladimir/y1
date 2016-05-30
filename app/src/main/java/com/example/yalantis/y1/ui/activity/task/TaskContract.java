package com.example.yalantis.y1.ui.activity.task;

import com.example.yalantis.y1.model.task.TaskModel;

public interface TaskContract {

    void initViews();

    void initPresenter();

    void loadTaskById();

    void fillTaskDetailsById(TaskModel listTaskModel);

    void initListeners();
}
