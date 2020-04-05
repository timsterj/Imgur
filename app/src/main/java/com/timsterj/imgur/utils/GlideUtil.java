package com.timsterj.imgur.utils;

public class GlideUtil {

    public static String getThumbnailLink(String link) {
        StringBuilder stringBuilder = new StringBuilder(link);

        stringBuilder.insert(link.length() - 4, "t");

        return stringBuilder.toString();
    }

}
