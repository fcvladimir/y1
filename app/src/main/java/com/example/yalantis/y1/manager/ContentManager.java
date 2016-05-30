package com.example.yalantis.y1.manager;

import com.example.yalantis.y1.model.ProfileModel;
import com.example.yalantis.y1.model.task.TaskModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ContentManager {

    private final int TASK_STATE_WORK    = 0;
    private final int TASK_STATE_DONE    = 1;
    private final int TASK_STATE_PENDING = 2;

    private final String TASK_STATE_ID   = "state.id";

    private Realm mRealm;

    public ContentManager() {
        mRealm = Realm.getDefaultInstance();
    }

    public void saveTasksToDb(Realm realm, List<TaskModel> taskBeen) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(taskBeen);
        realm.commitTransaction();
    }

    public void saveProfileToDb(ProfileModel profileModel) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(profileModel);
        mRealm.commitTransaction();
    }

    public RealmResults<TaskModel> getTasksFromDb(int taskStatus) {
        RealmResults<TaskModel> taskBeen = null;
        switch (taskStatus) {
            case TASK_STATE_WORK:
                taskBeen = mRealm.where(TaskModel.class).equalTo(TASK_STATE_ID, 0).or().equalTo(TASK_STATE_ID, 5).or().equalTo(TASK_STATE_ID, 7).or().equalTo(TASK_STATE_ID, 8).or().equalTo(TASK_STATE_ID, 9).findAll();
                break;
            case TASK_STATE_DONE:
                taskBeen = mRealm.where(TaskModel.class).equalTo(TASK_STATE_ID, 6).or().equalTo(TASK_STATE_ID, 10).findAll();
                break;
            case TASK_STATE_PENDING:
                taskBeen = mRealm.where(TaskModel.class).equalTo(TASK_STATE_ID, 1).or().equalTo(TASK_STATE_ID, 3).or().equalTo(TASK_STATE_ID, 4).findAll();
                break;
        }
        return taskBeen;
    }

    public ProfileModel getUserProfile() {
        return mRealm.where(ProfileModel.class).findFirst();
    }

    public void deleteTasksFromDb(RealmResults<TaskModel> listToDelete) {
        mRealm.beginTransaction();
        listToDelete.deleteAllFromRealm();
        mRealm.commitTransaction();
    }

}
