package com.timsterj.imgur.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.timsterj.imgur.R;
import com.timsterj.imgur.adapters.holders.GalleryViewHolder;
import com.timsterj.imgur.listeners.IItemClickListener;
import com.timsterj.imgur.model.Gallery;

public class GalleriesAdapter extends PagedListAdapter<Gallery, GalleryViewHolder> {

    private IItemClickListener galleryClickListener;

    public GalleriesAdapter(){
        super(Gallery.CALLBACK);
    }

    public void setGalleryClickListener(IItemClickListener galleryClickListener) {
        this.galleryClickListener = galleryClickListener;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        Gallery gallery = getItem(position);

        holder.setListener(galleryClickListener);
        holder.setData(gallery);
    }



}
