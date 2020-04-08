package com.timsterj.imgur.utils;

public class GlideUtil {

    public static String getThumbnailLink(String link) {
        if (link == null) {
            throw new NullPointerException();
        }

        StringBuilder stringBuilder = new StringBuilder(link);

        stringBuilder.insert(link.length() - 4, "s");

        return stringBuilder.toString();
    }

}
