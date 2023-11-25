package com.shl.test;

import java.io.File;

/**
 * 清除电子班牌的无效文件
 *
 * @author shenhl
 */
public class ClearElectronicIgFile {

    public static void main(String[] args) {
        String path = "E:\\AndroidStudioProjects\\dev_out";
        searchFile(new File(path), 0);
    }

    static void searchFile(File f, int level) {
        if (f == null) {
            return;
        }

        if (f.isDirectory()) {
            if (level > 1 && ".svn".equals(f.getName())) {

                System.out.println(f.getName() + " " + level);
                System.out.println(f.getAbsolutePath());

                deleteFile(f);

            }
            File[] fs = f.listFiles();
            if (fs != null) {
                for (File file : fs) {
                    searchFile(file, level + 1);
                }
            }
        }

        if (f.isFile()) {
            String name = f.getName();
            String[] nameArr = name.split("\\.");
            if ("iml".equals(nameArr[nameArr.length - 1])) {
                boolean b = f.delete();
                System.out.println("删除" + f.getAbsolutePath() + " " + b);
            }
        }


    }

    static void deleteFile(File file) {
        if (file == null) {
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }

        }

        boolean b = file.delete();
        System.out.println("删除" + file.getAbsolutePath() + " " + b);

    }
}
