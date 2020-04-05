package com.timsterj.imgur.base;

import com.timsterj.imgur.listeners.ILoadedCallback;

public interface BaseRepository {

    void init(ILoadedCallback loadedCallback);
    void init();

    void clear();
}
