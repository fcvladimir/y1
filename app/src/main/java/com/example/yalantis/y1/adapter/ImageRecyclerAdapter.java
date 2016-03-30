package com.example.yalantis.y1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.model.Photo;
import com.example.yalantis.y1.util.DialogUtil;
import com.example.yalantis.y1.util.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.RecyclerViewHolder> {

    private List<Photo> persons; //[Comment] Google code style

    public ImageRecyclerAdapter (List<Photo> persons){
        this.persons = persons;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item_row, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(persons.get(position).getImageUrl(), holder.ivTaskPhoto, ImageUtils.UIL_USER_PHOTO_DISPLAY_OPTIONS);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    /**
     * Class to hold recycleView items.
     */
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // View holder for gridview recycler view as we used in listview
        private ImageView ivTaskPhoto;

        public RecyclerViewHolder(View view) {
            super(view);
            // Find all views ids
            ivTaskPhoto = (ImageView) view
                    .findViewById(R.id.ivTaskPhoto);
            ivTaskPhoto.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DialogUtil.show(v.getContext(), "Photo at position #" + getAdapterPosition()+" clicked");
        }
    }
}
