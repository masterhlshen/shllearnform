package com.shl.test;

import java.io.*;

public class DependenceGet {
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\18256\\Desktop\\上虞门户");
        deleteDstore(f);
    }

    private static void deleteDstore(File file) {
        if (file.isFile()) {
            if (".DS_Store".equals(file.getName())) {
                boolean delete = file.delete();
                System.out.println(file.getName() + "删除 " + delete);

            }
            return;
        }
        File[] files = file.listFiles();
        for (File file1 : files) {
            deleteDstore(file1);
        }
    }
}
