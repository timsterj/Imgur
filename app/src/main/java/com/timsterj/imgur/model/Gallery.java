package com.timsterj.imgur.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

public class Gallery {

    private String id;
    private String title;
    private String link;
    private int views;
    private int width;
    private int height;

    private ArrayList<Image> images;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }


    public static final DiffUtil.ItemCallback<Gallery> CALLBACK = new DiffUtil.ItemCallback<Gallery>() {
        @Override
        public boolean areItemsTheSame(@NonNull Gallery oldItem, @NonNull Gallery newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Gallery oldItem, @NonNull Gallery newItem) {
            return true;
        }
    };

}
