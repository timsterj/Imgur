package com.timsterj.imgur.data.network.api;

import com.timsterj.imgur.data.network.dto.CommentDTO;
import com.timsterj.imgur.data.network.dto.GalleryDTO;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImgurApi {

    @GET("gallery/{section}/{sort}/{window}/{page}")
    Observable<GalleryDTO> getGalleries(
            @Path("section") String section,
            @Path("sort") String sort,
            @Path("page") String page
    );


    @GET("gallery/{galleryHash}/comments/{commentSort}")
    Observable<CommentDTO> getComments(
            @Path("galleryHash") String galleryHash,
            @Path("commentSort") String commentSort
    );
}
