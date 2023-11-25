package com.shl.test;

import java.io.File;

/**
 * 清空空文件夹
 */
public class ClearEmptyDir {

    static int subTotal = 0;

    public static void main(String[] args) {
        String path = "D:\\";
        dealEmptyFile(new File(path));


    }

    static void dealEmptyFile(File file) {
        if (file == null) {
            return;
        }


        if (file.isDirectory()) {
            File[] fArr = file.listFiles();
            if (fArr != null) {
                for (File fe : fArr) {
                    dealEmptyFile(fe);
                }
                fArr = file.listFiles();
                if (fArr != null && fArr.length == 0) {
                    System.out.println(file.getAbsolutePath() + " 为空，开始删除");
                    boolean b = file.delete();
                    System.out.println(file.getAbsolutePath() + " 删除 " + (b ? "成功" : "失败"));

                }
            }
        }


    }
}
