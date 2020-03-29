package com.ly.commom.utils;

import java.util.UUID;

public class UuUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
