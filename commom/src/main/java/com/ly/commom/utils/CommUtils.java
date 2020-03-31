package com.ly.commom.utils;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

import static com.sun.javafx.font.FontResource.SALT;

public class CommUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    /**
     * 验证码生产方法
     *
     * @return
     */
    public static String initVerificationCode() {
        StringBuilder yzm = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int int1 = new Random().nextInt(10);
            yzm.append(int1);
        }
        return yzm.toString();

    }

    /**
     * 加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
