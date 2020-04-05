package com.timsterj.imgur.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public abstract void setData(T data, int position);

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
