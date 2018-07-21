package com.kunitskaya.base.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static final String SUBJECT_TIMESTAMP_PATTERN = "yyyy.MM.dd HH:mm:ss:SS";

    /**
     * Get a timestamp formatted according to pattern
     *
     * @param pattern - pattern used to format
     * @return a formatted timestamp as String
     */
    public static String getFormattedTimestamp(String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
