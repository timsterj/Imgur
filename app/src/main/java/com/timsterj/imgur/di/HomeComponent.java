package com.timsterj.imgur.di;

import com.timsterj.imgur.activities.HomeActivity;
import com.timsterj.imgur.fragments.GalleryInfoFragment;
import com.timsterj.imgur.fragments.HomeFragment;
import com.timsterj.imgur.viewmodel.GalleryViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface HomeComponent {

    // Activities
    void inject(HomeActivity homeActivity);

    // Fragments
    void inject(HomeFragment homeFragment);
    void inject(GalleryInfoFragment galleryInfoFragment);

    // ViewModels
    void inject(GalleryViewModel galleryViewModel);
}
