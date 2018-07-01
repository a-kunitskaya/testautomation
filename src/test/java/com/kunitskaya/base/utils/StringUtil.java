package com.kunitskaya.base.utils;

import java.util.Random;

public class StringUtil {

    public String getRandomString(int stringLength) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(stringLength);

        for (int i = 0; i < stringLength; i++) {

            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }
}
