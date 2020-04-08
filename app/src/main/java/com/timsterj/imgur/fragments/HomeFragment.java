package com.timsterj.imgur.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.timsterj.imgur.App;
import com.timsterj.imgur.adapters.GalleriesAdapter;
import com.timsterj.imgur.base.BaseFragment;
import com.timsterj.imgur.contracts.Contracts;
import com.timsterj.imgur.contracts.mvvm.HomeFragmentContract;
import com.timsterj.imgur.databinding.FragmentHomeBinding;
import com.timsterj.imgur.listeners.IItemClickListener;
import com.timsterj.imgur.model.Gallery;
import com.timsterj.imgur.navigation.Screens;
import com.timsterj.imgur.viewmodel.GalleryViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.terrakok.cicerone.Router;

public class HomeFragment extends BaseFragment<GalleryViewModel> implements HomeFragmentContract.Navigator, IItemClickListener<Gallery> {

    private CompositeDisposable disposableBag = new CompositeDisposable();
    private FragmentHomeBinding binding;

    private GalleriesAdapter adapter;

    @Inject
    Router router;

    public static Fragment getFragment(String extraName) {
        HomeFragment fragment = new HomeFragment();

        Bundle arguments = new Bundle();
        arguments.putString(Contracts.NavigationConstant.EXTRA_NAME, extraName);

        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void init() {
        initRvGalleries();
        initViewModel();
    }

    private void initViewModel() {
        getViewModel().getListLiveData().observe(this, pagedList -> adapter.submitList(pagedList));

        getViewModel().getDataState().observe(this, this::showState);

    }



    private void initRvGalleries() {
        adapter = new GalleriesAdapter();
        adapter.setGalleryClickListener(this);

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, 1);

        binding.rvGalleries.setLayoutManager(manager);
        binding.rvGalleries.setAdapter(adapter);
    }

    private void showState(String state) {
        if (state.equals(Contracts.NetworkState.LOADING)) {
            showLoading();
        } else if (state.equals(Contracts.NetworkState.LOADED)) {
            hideLoading();
        }
    }

    private void showLoading() {
        binding.txtError.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateList(PagedList<Gallery> galleries) {
        adapter.submitList(galleries);
    }

    @Override
    public void onItemClick(Gallery data) {
        getViewModel().select(data);

        router.navigateTo(new Screens.GalleryFlow.GalleryInfoScreen(
                Contracts.NavigationConstant.GALLERY_INFO_FRAGMENT)
        );

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public GalleryViewModel getViewModel() {
        return new ViewModelProvider(getActivity()).get(GalleryViewModel.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        App.getINSTANCE().getHomeComponent()
                .inject(this);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }

    @Override
    public void onDestroy() {
        disposableBag.clear();
        super.onDestroy();
    }
}
