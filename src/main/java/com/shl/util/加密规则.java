package com.shl.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class 加密规则 {

    public static void main(String[] args) {
        SHA1("好吗，好的");
        System.out.println(md5String("好吗，好的"));

    }

    static void SHA1(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = data.getBytes();

            digest.update(bytes);
            byte[] hashedBytes = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            String hashedData = hexString.toString();
            System.out.println(hashedData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    static String md5String(String data) {
        String md5Str = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buf = md5.digest(data.getBytes("UTF-8"));
            for (byte element : buf) {
                md5Str += byte2Hex(element);
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // SKIP
        }
        return md5Str;
    }

    private static String byte2Hex(byte b) {
        String hex = Integer.toHexString(b);
        if (hex.length() > 2) {
            hex = hex.substring(hex.length() - 2);
        }
        while (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }
}
