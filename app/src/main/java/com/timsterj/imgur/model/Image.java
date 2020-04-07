package com.timsterj.imgur.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

    private String id;
    private String title;
    private String description;
    private int datetime;
    private String link;

    private int width;
    private int height;

    public Image() {
    }

    public Image(String link, int width, int height) {
        this.link = link;
        this.width = width;
        this.height = height;
    }

    private Image(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        datetime = in.readInt();
        link = in.readString();
        width = in.readInt();
        height = in.readInt();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(datetime);
        dest.writeString(link);
        dest.writeInt(width);
        dest.writeInt(height);
    }
}
