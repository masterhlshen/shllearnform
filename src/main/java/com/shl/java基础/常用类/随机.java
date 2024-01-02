package com.shl.java基础.常用类;

import java.util.Random;

public class 随机 {

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            System.out.println(Math.random());
        }

        // 种子相同，随机序列就是相同
        Random r = new Random(12121212);
        for (int i = 0; i < 5; i++) {
            System.out.println(r.nextInt(100));
        }

        // OkHttpClient client = new OkHttpClient();
    }


}
