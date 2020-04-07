package com.timsterj.imgur.data.network.dto;

import com.timsterj.imgur.model.Comment;

import java.util.ArrayList;

public class CommentDTO {

    private String success;

    private ArrayList<Comment> data;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<Comment> getData() {
        return data;
    }

    public void setData(ArrayList<Comment> data) {
        this.data = data;
    }
}
