package com.timsterj.imgur.contracts.mvvm;

import androidx.paging.PagedList;

import com.timsterj.imgur.model.Gallery;

public interface HomeFragmentContract {

    interface ViewModel {

    }

    interface Navigator {
        void updateList(PagedList<Gallery> galleries);
    }

}
