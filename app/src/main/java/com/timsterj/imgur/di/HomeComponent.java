package com.timsterj.imgur.di;

import com.timsterj.imgur.activities.HomeActivity;
import com.timsterj.imgur.data.network.repositories.ImgurRepository;
import com.timsterj.imgur.fragments.HomeFragment;

import dagger.Subcomponent;

@Subcomponent
public interface HomeComponent {

    // Activities
    void inject(HomeActivity homeActivity);

    // Fragments
    void inject(HomeFragment homeFragment);

    // Repositories
    void inject(ImgurRepository imgurRepository);
}
