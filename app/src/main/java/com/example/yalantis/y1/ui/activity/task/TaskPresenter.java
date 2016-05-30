package com.example.yalantis.y1.ui.activity.task;

import android.os.Bundle;

import com.example.yalantis.y1.events.TaskLoadByIdEvent;
import com.squareup.otto.Subscribe;

public interface TaskPresenter {

    void loadTaskById(Bundle bundle);

    @Subscribe
    void onLoadTask(TaskLoadByIdEvent taskLoadByIdEvent);

    void onDestroy();
}
