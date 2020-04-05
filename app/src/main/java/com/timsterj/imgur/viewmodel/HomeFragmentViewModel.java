package com.timsterj.imgur.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.timsterj.imgur.base.BaseViewModel;
import com.timsterj.imgur.contracts.mvvm.HomeFragmentContract;
import com.timsterj.imgur.data.pagination.GalleryDataSourceFactory;
import com.timsterj.imgur.model.Gallery;

import io.reactivex.disposables.CompositeDisposable;

public class HomeFragmentViewModel extends BaseViewModel<HomeFragmentContract.Navigator> implements HomeFragmentContract.ViewModel {

    private CompositeDisposable disposableBag = new CompositeDisposable();

    private LiveData<PagedList<Gallery>> listLiveData;
    private MutableLiveData<String> dataState;
    private GalleryDataSourceFactory factory;

    public HomeFragmentViewModel() {
        super();
        init();
    }

    @Override
    public void init() {
        initPagination();
    }

    private void initPagination() {
        factory = new GalleryDataSourceFactory();
        factory.init();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(60)
                .setPrefetchDistance(4)
                .build();

        listLiveData = new LivePagedListBuilder<>(factory, config)
                .build();

        dataState = factory.create().getDataState();
    }

    public LiveData<PagedList<Gallery>> getListLiveData() {
        return listLiveData;
    }

    public MutableLiveData<String> getDataState() {
        return dataState;
    }

    @Override
    public void setNavigator(HomeFragmentContract.Navigator navigator) {
        super.setNavigator(navigator);
    }

    @Override
    protected void onCleared() {
        disposableBag.clear();
        factory.clear();
        super.onCleared();
    }


}
