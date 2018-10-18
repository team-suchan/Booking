package com.newboynb.bookingsystem.utils;

import java.util.Random;
import java.util.UUID;

public class KeyUtil {

    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static synchronized String getUUID(int number) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, number);
    }
}
