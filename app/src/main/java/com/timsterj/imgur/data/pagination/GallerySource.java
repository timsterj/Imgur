package com.timsterj.imgur.data.pagination;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.timsterj.imgur.App;
import com.timsterj.imgur.contracts.Contracts;
import com.timsterj.imgur.data.network.api.ImgurApi;
import com.timsterj.imgur.model.Gallery;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GallerySource extends PageKeyedDataSource<Integer, Gallery> {

    private CompositeDisposable disposableBag = new CompositeDisposable();
    private MutableLiveData<String> dataState = new MutableLiveData<>();
    ;

    @Inject
    ImgurApi imgurApi;

    public GallerySource(boolean test) {

    }

    public GallerySource() {
        init();
    }

    void init() {
        App.getINSTANCE().getAppComponent().inject(this);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Gallery> callback) {
        dataState.postValue(Contracts.NetworkState.LOADING);

        disposableBag.add(imgurApi.getGalleries(
                "top",
                "top",
                String.valueOf(1)
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(value -> {
                            dataState.postValue(Contracts.NetworkState.LOADED);
                            callback.onResult(value.getData(), null, 2);
                        }, t -> {
                            clear();
                            loadInitial(params, callback);
                        })
        );

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Gallery> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Gallery> callback) {
        dataState.postValue(Contracts.NetworkState.LOADING);

        disposableBag.add(imgurApi.getGalleries(
                "top",
                "top",
                String.valueOf(params.key)
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(value -> {
                            dataState.postValue(Contracts.NetworkState.LOADED);
                            callback.onResult(value.getData(), params.key + 1);

                        }, t -> {
                            loadAfter(params, callback);

                        })
        );
    }

    public MutableLiveData<String> getDataState() {
        return dataState;
    }

    void clear() {
        disposableBag.clear();
    }

}
