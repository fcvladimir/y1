package com.example.yalantis.y1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.activity.TaskActivity;
import com.example.yalantis.y1.model.TaskModel;
import com.example.yalantis.y1.util.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class TabListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<TaskModel> mTaskList;

    public TabListViewAdapter(Context context, List<TaskModel> taskList) {
        this.mContext = context;
        this.mTaskList = taskList;
    }

    @Override
    public int getCount() {
        return mTaskList.size();
    }

    @Override
    public TaskModel getItem(int position) {
        return mTaskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the layout
            convertView = LayoutInflater.from(mContext).inflate(R.layout.task_main_item, parent, false);
            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.rlTaskCard = convertView.findViewById(R.id.rlTaskCard);
            viewHolder.ivTaskType = (ImageView) convertView.findViewById(R.id.ivTaskType);
            viewHolder.tvTaskLikes = (TextView) convertView.findViewById(R.id.tvTaskLikes);
            viewHolder.tvTaskTitle = (TextView) convertView.findViewById(R.id.tvTaskTitle);
            viewHolder.tvTaskAddress = (TextView) convertView.findViewById(R.id.tvTaskAddress);
            viewHolder.tvTaskDate = (TextView) convertView.findViewById(R.id.tvTaskDate);
            viewHolder.tvTaskDuration = (TextView) convertView.findViewById(R.id.tvTaskDuration);
            // store the holder with the view
            convertView.setTag(viewHolder);
        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // get the ImageView and TextView from the ViewHolder and then set photo and text
        ImageLoader.getInstance().displayImage(getItem(position).getTaskUrl(), viewHolder.ivTaskType, ImageUtils.UIL_USER_PHOTO_DISPLAY_OPTIONS);
        viewHolder.tvTaskLikes.setText(getItem(position).getTaskLikes() + "");
        viewHolder.tvTaskTitle.setText(getItem(position).getTaskTitle());
        viewHolder.tvTaskAddress.setText(getItem(position).getTaskAddress());
        viewHolder.tvTaskDate.setText(getItem(position).getTaskDate());
        viewHolder.tvTaskDuration.setText(mContext.getString(R.string.task_duration_days, getItem(position).getTaskDuration()));
        viewHolder.rlTaskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(v.getContext(), TaskActivity.class));
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        public View rlTaskCard;
        public ImageView ivTaskType;
        public TextView tvTaskLikes;
        public TextView tvTaskTitle;
        public TextView tvTaskAddress;
        public TextView tvTaskDate;
        public TextView tvTaskDuration;
    }
}
