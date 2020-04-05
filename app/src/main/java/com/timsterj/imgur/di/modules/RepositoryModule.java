package com.timsterj.imgur.di.modules;

import com.timsterj.imgur.data.network.interfaces.IImgurRepository;
import com.timsterj.imgur.data.network.repositories.ImgurRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract IImgurRepository bindImgurRepository(ImgurRepository imgurRepository);

}
