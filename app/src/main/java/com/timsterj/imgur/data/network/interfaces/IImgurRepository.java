package com.timsterj.imgur.data.network.interfaces;

import com.timsterj.imgur.base.BaseRepository;

public interface IImgurRepository extends BaseRepository {

    void getGalleries(int page);

}
