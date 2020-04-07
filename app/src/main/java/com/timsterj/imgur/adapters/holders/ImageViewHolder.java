package com.timsterj.imgur.adapters.holders;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;

import com.bumptech.glide.Glide;
import com.timsterj.imgur.base.BaseViewHolder;
import com.timsterj.imgur.databinding.ImageItemBinding;
import com.timsterj.imgur.listeners.IItemClickListener;
import com.timsterj.imgur.model.Image;
import com.timsterj.imgur.utils.FormatUtils;

import java.util.Locale;

public class ImageViewHolder extends BaseViewHolder<Image> {

    private ImageItemBinding binding;
    private ConstraintSet set = new ConstraintSet();
    private Context mContext;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ImageItemBinding.bind(itemView);
        mContext = itemView.getContext();
    }

    @Override
    public void setListener(IItemClickListener listener) {

    }

    @Override
    public void setData(Image data) {
        String id = data.getId();
        String title = data.getTitle();
        String description = data.getDescription();
        int datetime = data.getDatetime();
        int width = data.getWidth();
        int height = data.getHeight();
        String link = data.getLink();

        if (id == null) {
            onlyGalleryInfo(link, width, height);
        } else {
            fullInfoImage(id, title, description, datetime, link, width, height);
        }

    }

    private void fullInfoImage(String id, String title, String description, int datetime, String link, int width, int height) {
        StringBuilder imageInfo = new StringBuilder();
        if (!id.isEmpty()) {
            imageInfo.append("id: ").append(id).append("\n");
        }
        if (title != null) {
            imageInfo.append("Заголовок: ").append(title).append("\n");
        }
        if (description != null) {
            imageInfo.append("Описание: ").append(description).append("\n");
        }
        imageInfo.append("Дата: ").append(
                FormatUtils.getDateFormatFromEpochTime(datetime)
        ).append("\n");


        binding.txtImageInfo.setText(imageInfo.toString());

        Glide.with(mContext)
                .load(link)
                .into(binding.imgImage);

        set.clone(binding.parentConstraint);
        set.setDimensionRatio(binding.imgImage.getId(), String.format(Locale.getDefault(),"%d:%d", width, height));
        set.applyTo(binding.parentConstraint);
    }

    private void onlyGalleryInfo(String link, int width, int height) {

        Glide.with(mContext)
                .load(link)
                .into(binding.imgImage);

        set.clone(binding.parentConstraint);
        set.setDimensionRatio(binding.imgImage.getId(), String.format(Locale.getDefault(),"%d:%d", width, height));
        set.applyTo(binding.parentConstraint);
    }
}
