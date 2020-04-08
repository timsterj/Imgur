package com.timsterj.imgur.di;

import android.content.Context;

import com.timsterj.imgur.data.pagination.GallerySource;
import com.timsterj.imgur.di.modules.NavigationModule;
import com.timsterj.imgur.di.modules.PaginationModule;
import com.timsterj.imgur.di.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(
        modules = {
                NavigationModule.class,
                RetrofitModule.class,
                PaginationModule.class
        }
)
public interface AppComponent {


    void inject(GallerySource gallerySource);


    HomeComponent plusHomeComponent();


    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder context(Context context);


    }

}
