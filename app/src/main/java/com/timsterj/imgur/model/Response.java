package com.timsterj.imgur.model;

import java.util.ArrayList;

public class Response {
    private String success;

    private ArrayList<Gallery> data;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<Gallery> getData() {
        return data;
    }

    public void setData(ArrayList<Gallery> data) {
        this.data = data;
    }
}
