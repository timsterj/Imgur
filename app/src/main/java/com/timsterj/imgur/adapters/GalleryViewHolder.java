package com.timsterj.imgur.adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.timsterj.imgur.base.BaseViewHolder;
import com.timsterj.imgur.databinding.GalleryItemBinding;
import com.timsterj.imgur.model.Gallery;
import com.timsterj.imgur.model.Image;

import static com.timsterj.imgur.utils.GlideUtil.getThumbnailLink;

public class GalleryViewHolder extends BaseViewHolder<Gallery> {

    private GalleryItemBinding binding;
    private ConstraintSet set = new ConstraintSet();
    private Context mContext;

    @Override
    public void setData(Gallery gallery, int position) {
        String link;
        int width;
        int height;

        if (gallery.getImages() != null) {
            Image image = gallery.getImages().get(0);
            link = image.getLink();
            width = image.getWidth();
            height = image.getHeight();
        } else {
            link = gallery.getLink();
            width = gallery.getWidth();
            height = gallery.getHeight();

        }

        loadPreview(link);

        set.clone(binding.parentConstraint);
        set.setDimensionRatio(binding.imgGalleryPreview.getId(), String.format("%d:%d", width, height));
        set.applyTo(binding.parentConstraint);

    }

    public GalleryViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = GalleryItemBinding.bind(itemView);
        mContext = itemView.getContext();
    }

    private void loadPreview(String link) {
        String thumbnailLink = getThumbnailLink(link);

        Glide.with(mContext).load(link)
                .thumbnail(
                        Glide.with(mContext)
                                .load(thumbnailLink)
                )
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.imgGalleryPreview);
    }

}
