package com.example.yalantis.y1.manager;

import android.content.Context;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.model.TaskModel;

import java.util.ArrayList;
import java.util.Random;

public class ContentManager {

    private Context mContext;

    public ContentManager(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<TaskModel> getTaskList() {
        ArrayList<TaskModel> taskModelList = new ArrayList<>();
        TaskModel taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/18/0005/1477/374213/13/b4b8e81ac2.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_utilities),
                mContext.getString(R.string.task_address_one),
                mContext.getString(R.string.task_date_one),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/19/0005/1477/374213/13/693daa3882.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_building),
                mContext.getString(R.string.task_address_two),
                mContext.getString(R.string.task_date_two),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/18/0005/1477/374213/13/b4b8e81ac2.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_utilities),
                mContext.getString(R.string.task_address_three),
                mContext.getString(R.string.task_date_three),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/19/0005/1477/374213/13/693daa3882.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_building),
                mContext.getString(R.string.task_address_one),
                mContext.getString(R.string.task_date_one),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/18/0005/1477/374213/13/b4b8e81ac2.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_utilities),
                mContext.getString(R.string.task_address_two),
                mContext.getString(R.string.task_date_two),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/19/0005/1477/374213/13/693daa3882.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_building),
                mContext.getString(R.string.task_address_three),
                mContext.getString(R.string.task_date_three),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/18/0005/1477/374213/13/b4b8e81ac2.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_utilities),
                mContext.getString(R.string.task_address_one),
                mContext.getString(R.string.task_date_one),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/19/0005/1477/374213/13/693daa3882.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_building),
                mContext.getString(R.string.task_address_two),
                mContext.getString(R.string.task_date_two),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/18/0005/1477/374213/13/b4b8e81ac2.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_utilities),
                mContext.getString(R.string.task_address_three),
                mContext.getString(R.string.task_date_three),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        taskModel = new TaskModel("http://dl1.joxi.net/drive/2016/04/19/0005/1477/374213/13/693daa3882.jpg",
                new Random().nextInt(8)+1,
                mContext.getString(R.string.task_type_building),
                mContext.getString(R.string.task_address_one),
                mContext.getString(R.string.task_date_one),
                new Random().nextInt(8)+1);
        taskModelList.add(taskModel);
        return taskModelList;
    }

}
