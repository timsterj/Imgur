package com.timsterj.imgur.adapters.holders;

import android.view.View;

import androidx.annotation.NonNull;

import com.timsterj.imgur.base.BaseViewHolder;
import com.timsterj.imgur.databinding.CommentItemBinding;
import com.timsterj.imgur.listeners.IItemClickListener;
import com.timsterj.imgur.model.Comment;
import com.timsterj.imgur.utils.FormatUtils;

import java.text.DateFormat;

public class CommentViewHolder extends BaseViewHolder<Comment> {

    private CommentItemBinding binding;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = CommentItemBinding.bind(itemView);
    }

    @Override
    public void setListener(IItemClickListener listener) {

    }

    @Override
    public void setData(Comment data) {

        commentInfo(data);
    }

    private void commentInfo(Comment data) {
        StringBuilder commentInfo = new StringBuilder();

        if (data.getId() != null) {
            commentInfo.append("id: ").append(data.getId()).append("\n");
        }

        if (data.getAuthor() != null) {
            commentInfo.append("Автор: ").append(data.getAuthor()).append("\n");
        }

        commentInfo.append("Дата: ").append(
                FormatUtils.getDateFormatFromEpochTime(data.getDatetime())
        ).append("\n");
        commentInfo.append("Отметки: ").append(data.getPoints()).append("\n");

        if (data.getComment() != null) {
            commentInfo.append("Комментарий: ").append(data.getComment()).append("\n");
        }

        binding.commentInfo.setText(commentInfo.toString());
    }

}
