package com.kunitskaya.base.utils;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;

public class StringUtil {

    /**
     * Generate a random string (a-z, A-Z, 0-9)
     *
     * @param stringLength - string max length
     * @return - a random string
     */
    public static String getRandomString(int stringLength) {
        return randomAlphanumeric(stringLength);
    }
}
