package com.example.yalantis.y1.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.ui.activity.task.TaskActivity;
import com.example.yalantis.y1.model.task.TaskModel;
import com.example.yalantis.y1.util.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.tvTaskLikes.setText(mTaskList.get(position).getLikes_counter());
        holder.tvTaskTitle.setText(mTaskList.get(position).getTitle());
        holder.tvTaskAddress.setText(getFullAddress(holder.tvTaskAddress.getContext(), position));
        holder.tvTaskDate.setText(DateUtils.getDateFromMillis(holder.tvTaskDate.getContext(), mTaskList.get(position).getCreated_date()));
        holder.tvTaskDuration.setText(DateUtils.getDaysDifference(holder.tvTaskDuration.getContext(), mTaskList.get(position).getCreated_date()));
        holder.rlTaskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), TaskActivity.class)
                .putExtra("position", mTaskList.get(position).getId()));
            }
        });
    }

    private StringBuilder getFullAddress(Context context, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        if (mTaskList.get(position).getUser().getAddress().getStreet() != null) {
            stringBuilder.append(mTaskList.get(position).getUser().getAddress().getStreet().getStreet_type().getShort_name());
            stringBuilder.append(mTaskList.get(position).getUser().getAddress().getStreet().getName());
            stringBuilder.append(", ");
            stringBuilder.append(mTaskList.get(position).getUser().getAddress().getHouse().getName());
            stringBuilder.append("/");
            stringBuilder.append(mTaskList.get(position).getUser().getAddress().getFlat());
        } else {
            stringBuilder.append(context.getString(R.string.task_no_address));
        }
        return stringBuilder;
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    /**
     * Class to hold recycleView items.
     */
    static class RecyclerViewHolder extends RecyclerView.ViewHolder/* implements View.OnClickListener*/ {
        // View holder for gridview recycler view as we used in listview
        @BindView(R.id.rlTaskCard)
        View rlTaskCard;
        @BindView(R.id.tvTaskLikes)
        TextView tvTaskLikes;
        @BindView(R.id.tvTaskTitle)
        TextView tvTaskTitle;
        @BindView(R.id.tvTaskAddress)
        TextView tvTaskAddress;
        @BindView(R.id.tvTaskDate)
        TextView tvTaskDate;
        @BindView(R.id.tvTaskDuration)
        TextView tvTaskDuration;

        public RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
