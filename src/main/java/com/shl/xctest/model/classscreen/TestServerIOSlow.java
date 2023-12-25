package com.shl.xctest.model.classscreen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestServerIOSlow {
    public static void main(String[] args) {
        String filePath = "D:\\xclog\\emqttd.zip";
        File f = new File(filePath);
        System.out.println(f.getTotalSpace() / (8 * 1024 * 1024.0 * 1024));
        readFile(f);
        copyFile(f, "D:\\xclog\\可删除.zip");
    }

    private static void readFile(File f) {
        long start = System.currentTimeMillis();
        try {

            FileInputStream in = new FileInputStream(f);
            int len = -1;
            byte[] buff = new byte[1024];
            while ((len = in.read(buff)) != -1) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("读取文件时间" + (System.currentTimeMillis() - start) / 1000.0 + "秒");
    }

    private static void copyFile(File f, String desPath) {
        long start = System.currentTimeMillis();
        try {

            FileInputStream in = new FileInputStream(f);
            FileOutputStream out = new FileOutputStream(desPath);
            int len = -1;
            byte[] buff = new byte[1024];
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("复制文件时间" + (System.currentTimeMillis() - start) / 1000.0 + "秒");
    }
}
