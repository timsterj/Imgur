package com.timsterj.imgur.base;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<N> extends ViewModel {

    private N navigator;

    public BaseViewModel() {
        super();
    }


    public abstract void init();

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public N getNavigator() {
        return navigator;
    }

    public void setNavigator(N navigator) {
        this.navigator = navigator;
    }
}
