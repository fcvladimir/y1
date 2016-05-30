package com.example.yalantis.y1.ui.activity.task;

import android.os.Bundle;

public class TaskServiceImpl implements TaskService {

    private TaskRepositoryDiskImpl mTaskRepositoryDisk;

    public TaskServiceImpl() {
        mTaskRepositoryDisk = new TaskRepositoryDiskImpl();
    }

    @Override
    public void getTaskById(Bundle bundle) {
        mTaskRepositoryDisk.getTaskById(bundle);
    }

    @Override
    public void onDestroy() {
        mTaskRepositoryDisk.onDestroy();
        mTaskRepositoryDisk = null;
    }
}
