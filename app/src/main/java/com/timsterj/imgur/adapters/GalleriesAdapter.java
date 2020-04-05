package com.timsterj.imgur.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.timsterj.imgur.R;
import com.timsterj.imgur.base.BaseViewHolder;
import com.timsterj.imgur.model.Gallery;

public class GalleriesAdapter extends PagedListAdapter<Gallery,BaseViewHolder> {


    public GalleriesAdapter(){
        super(Gallery.CALLBACK);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Gallery gallery = getItem(position);

        holder.setData(gallery, position);
    }

}
