package com.kunitskaya.base.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static final String SUBJECT_TIMESTAMP_PATTERN = "yyyy.MM.dd HH:mm:ss:SS";

    /**
     * Get a timestemp to use in email subject
     *
     * @return a timestamp as String
     */
    public static String getSubjectTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat(SUBJECT_TIMESTAMP_PATTERN);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
