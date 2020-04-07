package com.timsterj.imgur.base;

import android.content.ClipData;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timsterj.imgur.listeners.IItemClickListener;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public abstract void setListener(IItemClickListener listener);

    public abstract void setData(T data);

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
