package com.timsterj.imgur.navigators;

import androidx.paging.PagedList;

import com.timsterj.imgur.model.Gallery;

public interface HomeFragmentNavigator {

    void updateList(PagedList<Gallery> galleries);

}
