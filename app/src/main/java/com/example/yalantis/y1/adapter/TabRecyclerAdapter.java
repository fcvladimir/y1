package com.example.yalantis.y1.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.activity.TaskActivity;
import com.example.yalantis.y1.model.TaskModel;
import com.example.yalantis.y1.util.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class TabRecyclerAdapter extends RecyclerView.Adapter<TabRecyclerAdapter.RecyclerViewHolder> {

    private List<TaskModel> mTaskList;

    public TabRecyclerAdapter(List<TaskModel> taskList){
        mTaskList = taskList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_main_item, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(mTaskList.get(position).getTaskUrl(), holder.ivTaskType, ImageUtils.UIL_USER_PHOTO_DISPLAY_OPTIONS);
        holder.tvTaskLikes.setText(mTaskList.get(position).getTaskLikes() + "");
        holder.tvTaskTitle.setText(mTaskList.get(position).getTaskTitle());
        holder.tvTaskAddress.setText(mTaskList.get(position).getTaskAddress());
        holder.tvTaskDate.setText(mTaskList.get(position).getTaskDate());
        holder.tvTaskDuration.setText(holder.tvTaskDuration.getContext().getString(R.string.task_duration_days, mTaskList.get(position).getTaskDuration()));
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    /**
     * Class to hold recycleView items.
     */
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // View holder for gridview recycler view as we used in listview
        private View rlTaskCard;
        private ImageView ivTaskType;
        private TextView tvTaskLikes;
        private TextView tvTaskTitle;
        private TextView tvTaskAddress;
        private TextView tvTaskDate;
        private TextView tvTaskDuration;

        public RecyclerViewHolder(View view) {
            super(view);
            // Find all views ids
            rlTaskCard = view.findViewById(R.id.rlTaskCard);
            ivTaskType = (ImageView) view.findViewById(R.id.ivTaskType);
            tvTaskLikes = (TextView) view.findViewById(R.id.tvTaskLikes);
            tvTaskTitle = (TextView) view.findViewById(R.id.tvTaskTitle);
            tvTaskAddress = (TextView) view.findViewById(R.id.tvTaskAddress);
            tvTaskDate = (TextView) view.findViewById(R.id.tvTaskDate);
            tvTaskDuration = (TextView) view.findViewById(R.id.tvTaskDuration);
            rlTaskCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(new Intent(v.getContext(), TaskActivity.class));
        }
    }
}
