package com.timsterj.imgur.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<N> extends ViewModel {

    private N navigator;

    public BaseViewModel() {
        super();
        init();
    }

    public BaseViewModel(boolean test){

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
