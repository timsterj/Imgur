package com.timsterj.imgur.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timsterj.imgur.App;
import com.timsterj.imgur.adapters.CommentsAdapter;
import com.timsterj.imgur.adapters.ImagesAdapter;
import com.timsterj.imgur.base.BaseFragment;
import com.timsterj.imgur.contracts.Contracts;
import com.timsterj.imgur.databinding.FragmentGalleryInfoBinding;
import com.timsterj.imgur.model.Gallery;
import com.timsterj.imgur.model.Image;
import com.timsterj.imgur.utils.FormatUtils;
import com.timsterj.imgur.viewmodel.GalleryViewModel;

import java.text.Format;
import java.util.ArrayList;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class GalleryInfoFragment extends BaseFragment<GalleryViewModel> {

    private FragmentGalleryInfoBinding binding;
    private ImagesAdapter imagesAdapter;
    private CommentsAdapter commentsAdapter;

    @Inject
    Router router;

    public static Fragment getFragment(String extraName) {
        GalleryInfoFragment fragment = new GalleryInfoFragment();
        Bundle arguments = new Bundle();

        arguments.putString(Contracts.NavigationConstant.EXTRA_NAME, extraName);

        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void init() {
        binding.imageInfoToolbar.setTitle("Информация");
        initRvImages();
        intiRvComments();
        initViewModel();
    }

    private void initRvImages() {
        imagesAdapter = new ImagesAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.rvImages.setNestedScrollingEnabled(false);
        binding.rvImages.setLayoutManager(manager);
        binding.rvImages.setAdapter(imagesAdapter);
    }

    private void intiRvComments() {
        commentsAdapter = new CommentsAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.rvComments.setNestedScrollingEnabled(false);
        binding.rvComments.setLayoutManager(manager);
        binding.rvComments.setAdapter(commentsAdapter);
    }

    private void initViewModel() {
        getViewModel().getSelected().observe(getViewLifecycleOwner(), gallery -> {
            initImages(gallery);
            initGalleryInfo(gallery);
            initComments(gallery.getId());

        });
    }

    private void initComments(String galleryId) {

        getViewModel().getComments(galleryId).observe(this, comments -> {
            if (comments != null) {
                commentsAdapter.setCommentList(comments);
                hideLoading();
            } else {
                showLoading();
            }

        });

    }

    private void initGalleryInfo(Gallery gallery) {
        StringBuilder galleryInfo = new StringBuilder();
        if (gallery.getId() != null) {
            galleryInfo.append("id: ").append(gallery.getId()).append("\n");
        }
        if (gallery.getTitle() != null) {
            galleryInfo.append("Заголовок: ").append(gallery.getTitle()).append("\n");
        }
        if (gallery.getDescription() != null) {
            galleryInfo.append("Описание: ").append(gallery.getDescription()).append("\n");
        }

        galleryInfo.append("Дата: ").append(
                FormatUtils.getDateFormatFromEpochTime(gallery.getDatetime())
        ).append("\n");
        galleryInfo.append("Просмотры: ").append(gallery.getViews()).append("\n");
        galleryInfo.append("Отметки: ").append(gallery.getPoints()).append("\n");
        galleryInfo.append("Популярность: ").append(gallery.getScore()).append("\n");

        binding.txtGalleryInfo.setText(galleryInfo.toString());
    }

    private void initImages(Gallery gallery) {
        ArrayList<Image> images = gallery.getImages();

        if (images == null) {
            Image image = new Image(gallery.getLink(), gallery.getWidth(), gallery.getHeight());

            images = new ArrayList<>();
            images.add(image);

        }

        imagesAdapter.setImageList(images);
    }

    private void showLoading(){
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.rvComments.setVisibility(View.GONE);
    }

    private void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.rvComments.setVisibility(View.VISIBLE);
    }

    @Override
    public GalleryViewModel getViewModel() {
        return new ViewModelProvider(getActivity()).get(GalleryViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getINSTANCE().getHomeComponent()
                .inject(this);

        binding = FragmentGalleryInfoBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }
}
