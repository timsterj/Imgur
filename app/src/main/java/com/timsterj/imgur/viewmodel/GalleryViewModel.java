package com.timsterj.imgur.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.timsterj.imgur.App;
import com.timsterj.imgur.base.BaseViewModel;
import com.timsterj.imgur.contracts.mvvm.HomeFragmentContract;
import com.timsterj.imgur.data.network.api.ImgurApi;
import com.timsterj.imgur.data.pagination.GalleryDataSourceFactory;
import com.timsterj.imgur.model.Comment;
import com.timsterj.imgur.model.Gallery;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GalleryViewModel extends BaseViewModel<HomeFragmentContract.Navigator> implements HomeFragmentContract.ViewModel {

    private CompositeDisposable disposableBag = new CompositeDisposable();

    private LiveData<PagedList<Gallery>> pagedListLiveData;
    private MutableLiveData<List<Comment>> listComments = new MutableLiveData<>();

    private MutableLiveData<String> dataState;
    private MutableLiveData<Gallery> selected = new MutableLiveData<>();

    @Inject
    GalleryDataSourceFactory factory;

    @Inject
    ImgurApi imgurApi;

    public GalleryViewModel() {
        super();
    }

    public GalleryViewModel(boolean test) {
        super(test);
    }

    @Override
    public void init() {
        App.getINSTANCE().getHomeComponent()
                .inject(this);

        initPagination();
    }

    public void initPagination() {

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(60)
                .setPrefetchDistance(4)
                .build();

        pagedListLiveData = new LivePagedListBuilder<>(factory, config)
                .build();


        dataState = factory.create().getDataState();

    }


    public void getComments(String galleryId) {
        if (galleryId == null || galleryId.isEmpty()) {
            throw new IllegalArgumentException();
        }

        disposableBag.add(
                imgurApi.getComments(
                        galleryId,
                        "best"
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                commentDTO -> {
                                    listComments.setValue(commentDTO.getData());
                                },
                                t -> {
                                    listComments.setValue(null);
                                    getComments(galleryId);
                                }
                        )
        );

    }


    @Override
    public void setNavigator(HomeFragmentContract.Navigator navigator) {
        super.setNavigator(navigator);
    }

    public void select(Gallery gallery) {
        selected.setValue(gallery);
    }

    public MutableLiveData<Gallery> getSelected() {
        return selected;
    }

    @Override
    protected void onCleared() {
        disposableBag.clear();
        factory.clear();
        super.onCleared();
    }

    public MutableLiveData<List<Comment>> getListComments() {
        return listComments;
    }

    public LiveData<PagedList<Gallery>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public MutableLiveData<String> getDataState() {
        return dataState;
    }
}
