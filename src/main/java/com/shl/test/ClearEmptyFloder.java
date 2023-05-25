package com.shl.test;

import java.io.File;

public class ClearEmptyFloder {

    public static void main(String[] args) {
        String path = "D:\\";
        int n = clearEmptyFloder(new File(path));
        System.out.println(n);

        File[] files = File.listRoots();
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

    static int clearEmptyFloder(File file) {
        if (file == null) {
            return 0;
        }

        if (file.isDirectory()) {
            int n = 0;
            File[] files = file.listFiles();
            if (files == null) {
                return 0;
            }
            for (File f : files) {
                n += clearEmptyFloder(f);
            }
            if (n == 0) {
                boolean b = file.delete();
                System.out.println(">>>删除" + file.getAbsolutePath() + " 结果 " + b);
            }
            return n;
        }

        return 1;

    }


}
