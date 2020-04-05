package com.timsterj.imgur.data.network.repositories;

import android.util.Log;

import com.timsterj.imgur.App;
import com.timsterj.imgur.contracts.Contracts;
import com.timsterj.imgur.data.network.api.ImgurApi;
import com.timsterj.imgur.data.network.interfaces.IImgurRepository;
import com.timsterj.imgur.listeners.ILoadedCallback;
import com.timsterj.imgur.model.Response;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Retrofit;

public class ImgurRepository implements IImgurRepository {

    private PublishSubject<Response> responsePublishSubject = PublishSubject.create();
    private CompositeDisposable disposableBag = new CompositeDisposable();

    @Inject
    ImgurRepository() {
    }

    @Inject
    Retrofit retrofit;
    @Inject
    ImgurApi imgurApi;

    @Override
    public void init(ILoadedCallback callback) {
        App.getINSTANCE().getHomeComponent().inject(this);

        disposableBag.add(
                responsePublishSubject.subscribe(callback::onSuccessLoaded)
        );

    }

    @Override
    public void init() {

    }

    @Override
    public void getGalleries(int page) {

        disposableBag.add(imgurApi.getGalleries(
                Contracts.RetrofitConstant.CLIENT_ID,
                "top",
                "top",
                String.valueOf(page)
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(value -> responsePublishSubject.onNext(value),
                                t -> {
                                    t.printStackTrace();
                                })
        );

    }


    @Override
    public void clear() {
        disposableBag.clear();
    }
}
