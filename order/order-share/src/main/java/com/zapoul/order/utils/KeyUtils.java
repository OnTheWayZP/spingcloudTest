package com.zapoul.order.utils;

import java.util.Random;

public class KeyUtils {

    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(90000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
