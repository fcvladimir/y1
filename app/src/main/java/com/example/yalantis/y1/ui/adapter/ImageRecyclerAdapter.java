package com.example.yalantis.y1.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.model.task.TaskModel;
import com.example.yalantis.y1.retrofit.ApiUtils;
import com.example.yalantis.y1.util.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.RecyclerViewHolder> {

    private TaskModel mTaskList;

    public ImageRecyclerAdapter (TaskModel photoList){
        mTaskList = photoList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_detail_photo_item, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(ApiUtils.IMAGE_URL + mTaskList.getFiles().get(position).getFilename(), holder.ivTaskPhoto, ImageUtils.UIL_USER_PHOTO_DISPLAY_OPTIONS);
    }

    @Override
    public int getItemCount() {
        return mTaskList.getFiles().size();
    }

    /**
     * Class to hold recycleView items.
     */
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // View holder for gridview recycler view as we used in listview
        @BindView(R.id.ivTaskPhoto)
        ImageView ivTaskPhoto;

        public RecyclerViewHolder(View view) {
            super(view);
            // Find all views ids
            ButterKnife.bind(this, view);
            ivTaskPhoto.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Photo at position #" + getAdapterPosition()+" clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
