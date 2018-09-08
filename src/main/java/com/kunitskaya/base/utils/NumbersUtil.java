package com.kunitskaya.base.utils;

import java.util.concurrent.ThreadLocalRandom;

public class NumbersUtil {

    public static int getRandomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
