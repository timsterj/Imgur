package com.timsterj.imgur.data.pagination;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class GalleryDataSourceFactory extends DataSource.Factory {

    private GallerySource gallerySource;

    private MutableLiveData<GallerySource> liveData;

    public void init() {
        liveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public GallerySource create() {
        if (gallerySource == null) {
            gallerySource = new GallerySource();
            gallerySource.init();
        }

        liveData.postValue(gallerySource);

        return gallerySource;
    }

    public MutableLiveData<GallerySource> getLiveData() {
        return liveData;
    }

    public void clear() {
        gallerySource.clear();
    }

}
