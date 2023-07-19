package com.example.demo;

import java.security.MessageDigest;

/**
 * @auther JunDeng
 * @Date 2020/2/20
 * @desc
 */
public class Md5Utils {
    /**
     * @param password
     * @param algorithm
     * @Deprecated md5加密
     * @return: java.lang.String
     * @Author: DengJun
     * @date: 2019/7/22
     */
    private static String encode(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            return password;
        }
        md.reset();
        md.update(unencodedPassword);
        byte[] encodedPassword = md.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xFF) < 16) {
                buf.append("0");
            }
            buf.append(Long.toString(encodedPassword[i] & 0xFF, 16));
        }
        return buf.toString();
    }

    public static String Md5(String s){
      return  encode(s,"MD5");
    }

    /**
     * @Deprecated 验证签名是否正确
     * @param a 签名
     * @param b 待校验字符串
     * @return: boolean
     * @Author: DengJun
     * @date: 2020/2/20
     */
    public static boolean march(String a,String b){
        return Md5(b).equals(a);
    }

    public static String Md5S(){
        String s =System.currentTimeMillis()+"";
        return  s;
    }
}
