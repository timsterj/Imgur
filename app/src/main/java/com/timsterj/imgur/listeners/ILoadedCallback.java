package com.timsterj.imgur.listeners;

import com.timsterj.imgur.model.Response;

public interface ILoadedCallback {

    void onSuccessLoaded(Response response);

}
