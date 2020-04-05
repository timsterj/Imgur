package com.timsterj.imgur.di.modules;

import com.timsterj.imgur.contracts.Contracts;
import com.timsterj.imgur.data.network.RetrofitClient;
import com.timsterj.imgur.data.network.api.ImgurApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RetrofitModule {

    @Provides
    Retrofit provideRetrofit() {
        return RetrofitClient.getClient(Contracts.RetrofitConstant.BASE_URL);
    }

    @Provides
    ImgurApi provideImgurApi(Retrofit retrofit) {
        return retrofit.create(ImgurApi.class);
    }

}
