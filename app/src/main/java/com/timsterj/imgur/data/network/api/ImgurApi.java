package com.timsterj.imgur.data.network.api;

import com.timsterj.imgur.model.Gallery;
import com.timsterj.imgur.model.Response;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ImgurApi {

    @GET("gallery/{section}/{sort}/{window}/{page}")
    Observable<Response> getGalleries(
        @Header("Authorization") String clientId,
        @Path("section") String section,
        @Path("sort") String sort,
        @Path("page") String page
    );


}
