package com.example.yalantis.y1.ui.activity.task;

import android.os.Bundle;

import com.example.yalantis.y1.events.BusProvider;
import com.example.yalantis.y1.events.TaskLoadByIdEvent;
import com.squareup.otto.Subscribe;

public class TaskPresenterImpl implements TaskPresenter {

    private TaskContract mTaskView;
    private TaskServiceImpl mTaskService;

    public TaskPresenterImpl(TaskContract mainContract) {
        BusProvider.getInstance().register(this);
        mTaskView = mainContract;
        mTaskService = new TaskServiceImpl();
    }

    @Override
    public void loadTaskById(Bundle bundle) {
        mTaskService.getTaskById(bundle);
    }

    @Subscribe
    public void onLoadTask(TaskLoadByIdEvent taskLoadByIdEvent) {
        mTaskView.fillTaskDetailsById(taskLoadByIdEvent.getTaskBeanList());
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
        mTaskService.onDestroy();
        mTaskService = null;
    }
}
