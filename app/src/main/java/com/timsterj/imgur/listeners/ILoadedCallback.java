package com.timsterj.imgur.listeners;

import com.timsterj.imgur.data.network.dto.GalleryDTO;

public interface ILoadedCallback {

    void onSuccessLoaded(GalleryDTO galleryDTO);

}
