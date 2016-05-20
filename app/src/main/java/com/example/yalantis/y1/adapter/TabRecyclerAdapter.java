package com.example.yalantis.y1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.activity.TaskActivity;
import com.example.yalantis.y1.model.TaskBean;
import com.example.yalantis.y1.util.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabRecyclerAdapter extends RecyclerView.Adapter<TabRecyclerAdapter.RecyclerViewHolder> {

    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private List<TaskBean> mTaskList;

    public TabRecyclerAdapter(List<TaskBean> taskList){
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
        if (mTaskList.get(position).getFiles().size() > 0) {
            ImageLoader.getInstance().displayImage("http://dev-contact.yalantis.com/files/ticket/" + mTaskList.get(position).getCategory().getImage(), holder.ivTaskType, ImageUtils.UIL_USER_PHOTO_DISPLAY_OPTIONS);
        }
        holder.tvTaskLikes.setText(mTaskList.get(position).getLikes_counter());
        holder.tvTaskTitle.setText(mTaskList.get(position).getTitle());
        holder.tvTaskAddress.setText(getFullAddress(holder.tvTaskAddress.getContext(), position));
        holder.tvTaskDate.setText(getTaskDate(position));
        holder.tvTaskDuration.setText(getDifferenceDays(holder.tvTaskDuration.getContext(), new Date(mTaskList.get(position).getCreated_date()), new Date(mTaskList.get(position).getCreated_date())));
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

    private String getTaskDate(int position) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mTaskList.get(position).getCreated_date());
        return formatter.format(calendar.getTime());
    }

    public static String getDifferenceDays(Context context, Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return context.getString(R.string.task_duration_days, TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    /**
     * Class to hold recycleView items.
     */
    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // View holder for gridview recycler view as we used in listview
        @BindView(R.id.rlTaskCard)
        View rlTaskCard;
        @BindView(R.id.ivTaskType)
        ImageView ivTaskType;
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
            rlTaskCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(new Intent(v.getContext(), TaskActivity.class));
        }
    }
}
