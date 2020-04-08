package com.timsterj.imgur.data.network;


import com.timsterj.imgur.contracts.Contracts;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseURL) {

        if (baseURL == null) {
            throw new NullPointerException();
        } else if (baseURL.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHttpClient())
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient getHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Authorization", Contracts.RetrofitConstant.CLIENT_ID)
                            .build();

                    return chain.proceed(request);
                })
                .build();
    }

}
