package com.shl.test;

import java.io.IOException;
import java.io.InputStream;

public class Test3 {

    public static void main(String[] args) throws IOException {
        Process pro = Runtime.getRuntime().exec("tasklist");
        InputStream input = pro.getInputStream();
        byte[] buff = new byte[512];
        int len = -1;
        StringBuilder out = new StringBuilder();
        while ((len = input.read(buff)) != -1) {
            out.append(new String(buff, 0, len));
        }
        System.out.println(out.toString() + " ----- ");
    }

}
