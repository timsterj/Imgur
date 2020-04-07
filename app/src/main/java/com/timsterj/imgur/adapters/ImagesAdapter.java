package com.timsterj.imgur.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timsterj.imgur.R;
import com.timsterj.imgur.adapters.holders.ImageViewHolder;
import com.timsterj.imgur.model.Image;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private ArrayList<Image> imageList = new ArrayList<>();

    public void setImageList(ArrayList<Image> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image image = imageList.get(position);
        holder.setData(image);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
