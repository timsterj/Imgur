package com.timsterj.imgur.di.modules;

import com.timsterj.imgur.data.pagination.GalleryDataSourceFactory;
import com.timsterj.imgur.data.pagination.GallerySource;

import dagger.Module;
import dagger.Provides;

@Module
public class PaginationModule {

    @Provides
    GalleryDataSourceFactory provideGalleryDataSourceFactory(){
        return new GalleryDataSourceFactory();
    }


}
