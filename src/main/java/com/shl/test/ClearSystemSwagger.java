package com.shl.test;

import java.io.File;

public class ClearSystemSwagger {

    private static int total = 0, success = 0;

    public static void main(String[] args) {
        File f = new File("E:\\IdeaProjects\\xcenv_local_shl_future");
        deleteLogFile(f);
        System.out.println(success + "/" + total);

        success = 0;
        total = 0;
        String[] logPath = new String[]{"D:\\bin\\service\\tomcat\\apache-tomcat-office\\logs",
        "D:\\bin\\service\\tomcat\\apache-tomcat-office\\temp",
        "D:\\bin\\service\\tomcat\\apache-tomcat-mobile\\logs",
        "D:\\bin\\service\\tomcat\\apache-tomcat-mobile\\temp",
        "D:\\bin\\service\\tomcat\\apache-tomcat-admin\\logs",
        "D:\\bin\\service\\tomcat\\apache-tomcat-admin\\temp"};
        for (String path : logPath) {
            deleteServerLogFile(new File(path));
        }
    }

    private static void deleteLogFile(File file) {
        if (file.isFile()) {
            String name = file.getName();
            if ("Swagger2Config.class".equals(name)) {
                long totalSpace = file.getTotalSpace();
                boolean b = file.delete();
                System.out.println(file.getAbsolutePath() + " 删除 " + (b ? "成功" : "失败") + " 大小:" + (totalSpace) + "");
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

    private static void deleteServerLogFile(File file) {
        if (file.isFile()) {
            String name = file.getName();
            long totalSpace = file.getTotalSpace();
            boolean b = file.delete();
            System.out.println(file.getAbsolutePath() + " 删除 " + (b ? "成功" : "失败") + " 大小:" + (totalSpace) + "");
            if (b) {
                success++;
            }
            total++;
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
