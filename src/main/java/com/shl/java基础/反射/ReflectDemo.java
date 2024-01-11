package com.shl.java基础.反射;

import java.io.*;

public class ReflectDemo {

    public static void main(String[] args) throws IllegalAccessException, IOException, InterruptedException {

       /* String fPath = "D:\\abc\\faceregist\\failed";
        File f = new File(fPath);
        for (File file : f.listFiles()) {
            String name = file.getName();
            String[] na = name.split("_");
            rename(file, new File("D:\\abc\\faceregist\\f" + "\\" + na[1] + ".jpg"));
        }*/

        /*String fPath = "D:\\abc\\faceregist\\register";
        File f = new File(fPath);
        for (File file : f.listFiles()) {
            String name = file.getName();
            String[] na = name.split("_");
            rename(file, new File("D:\\abc\\faceregist\\reg" + "\\" + na[0] + ".jpg"));
        }*/

        tansIllgal();

    }

    static void tansIllgal() throws IOException, InterruptedException {
        String a = "D:/abc/a.jpg";
        String b = "D:/abc/b.jpg";
        String format = String.format("D:/ImageMagick-7.1.1-Q16-HDRI/magick.exe convert -resize %s %s %s", "960x1280", a, b);
        Process exec = Runtime.getRuntime().exec(format);

        exec.waitFor();

        String c = "D:/abc/c.jpg";
        format = String.format("D:/ImageMagick-7.1.1-Q16-HDRI/magick.exe convert -rotate %s %s %s", 90, b, c);
        Process exec1 = Runtime.getRuntime().exec(format);

        exec1.waitFor();
    }


    static void rename(File s, File d) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        in = new FileInputStream(s);
        out = new FileOutputStream(d);
        int len = -1;
        byte[] buff = new byte[1024];
        while ((len = in.read(buff)) != -1) {
            out.write(buff, 0, len);
        }
        out.flush();
        in.close();
        out.close();
    }


}
