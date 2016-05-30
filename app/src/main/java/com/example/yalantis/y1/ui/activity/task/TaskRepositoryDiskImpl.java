package com.example.yalantis.y1.ui.activity.task;

import android.os.Bundle;
import android.util.Log;

import com.example.yalantis.y1.events.BusProvider;
import com.example.yalantis.y1.events.TaskLoadByIdEvent;
import com.example.yalantis.y1.model.task.TaskModel;

import io.realm.Realm;

public class TaskRepositoryDiskImpl implements TaskRepository {
    private static final String TAG = TaskRepositoryDiskImpl.class.getSimpleName();

    public TaskRepositoryDiskImpl() {
        BusProvider.getInstance().register(this);
    }

    @Override
    public void getTaskById(Bundle bundle) {
        try {
            long taskId = bundle.getLong("position");
            Realm mRealm = Realm.getDefaultInstance();
            TaskModel results = mRealm.where(TaskModel.class).equalTo("id", taskId).findFirst();
            BusProvider.getInstance().post(new TaskLoadByIdEvent(results));
        } catch (Exception exception) {
            Log.d(TAG, "Error Getting Task", exception);
        }
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
    }
}
