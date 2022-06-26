package com.shl.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    public static void main(String[] args) throws IOException {

        File f = new File("E:\\PDM\\影视片段\\我的团长我的团_1.mp4");
        System.out.println(f.length());
        RandomAccessFile rf = new RandomAccessFile(f, "r");
        System.out.println(rf.length());
        byte[] buffer = new byte[1024];
        int len = 0;
        FileOutputStream out = new FileOutputStream("D:/aa.mp4");
        System.out.println(rf.getFilePointer());
        while ((len = rf.read(buffer)) != -1) {

            out.write(buffer, 0, len);
        }
        System.out.println(rf.getFilePointer());

    }
}
