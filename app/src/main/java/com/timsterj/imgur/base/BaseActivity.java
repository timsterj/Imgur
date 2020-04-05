package com.timsterj.imgur.base;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {

    public abstract void init();

    public abstract V getViewModel();
}
