package com.timsterj.imgur.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class FormatUtils {


    public static String getDateFormatFromEpochTime(int epochTime) {
        if (epochTime == 0) {
            throw new IllegalArgumentException();
        }

        Date date = new Date(epochTime * 1000L);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

        return simpleDateFormat.format(date);
    }

}
