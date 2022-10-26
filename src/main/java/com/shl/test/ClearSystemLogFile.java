package com.shl.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClearSystemLogFile {

    private static int total = 0, success = 0;

    private static final String CURRENT_YEAR = new SimpleDateFormat("yyyyMM").format(new Date());

    public static void main(String[] args) {
        File[] files = File.listRoots();
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
            File f = new File(file.getAbsolutePath());
            deleteLogFile(f);
            System.out.println(success + "/" + total);
        }


    }

    private static void deleteLogFile(File file) {
        if (file.isFile()) {
            String name = file.getName();
            String suffix = name.substring(name.lastIndexOf(".") + 1);
            if ("log".equals(suffix) || ("jar".equals(suffix) && name.startsWith(CURRENT_YEAR))) {
                boolean b = file.delete();
                System.out.println(file.getAbsolutePath() + " 删除 " + (b ? "成功" : "失败") + " 大小:" + (file.getTotalSpace() / 1000.0) + "kb");
                if (b) {
                    success++;
                }
                total++;
            }
            return;
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                deleteLogFile(f);
            }
        }

    }
}
