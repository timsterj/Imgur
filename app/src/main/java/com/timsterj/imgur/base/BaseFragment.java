package com.timsterj.imgur.base;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment implements OnBackPressed{

    public abstract void init();

    public abstract V getViewModel();


}


