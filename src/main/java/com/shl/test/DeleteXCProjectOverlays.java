package com.shl.test;

import java.io.File;

public class DeleteXCProjectOverlays {
    public static void main(String[] args) {
        DeleteXCProjectOverlays dxc = new DeleteXCProjectOverlays();
        dxc.execute("E:\\IdeaProjects\\xcenv_local_shl_future");

    }

    void execute(String path) {
        File root = new File(path);
        if (!root.isDirectory()) {
            return;
        }
        File[] files = root.listFiles((dir, name) -> "overlays".equalsIgnoreCase(name) || "target".equalsIgnoreCase(name));
        if (files.length == 0) {
            files = root.listFiles();
            for (File f : files) {
                execute(f.getAbsolutePath());
            }
        } else {
            for (File f : files) {
                executeDelete(f);
                boolean delete = f.delete();
                increaCount();
                System.out.println(">>>>delete " + f.getAbsolutePath() + " " + delete);
            }
        }
        System.out.println("删除文件" + count + "个");
    }

    void executeDelete(File f) {
        if (f.exists()) {
            File[] files = f.listFiles();
            for (File s : files) {
                if (s.isDirectory()) {
                    executeDelete(s);
                    boolean b = s.delete();
                    if (b) {
                        increaCount();
                        System.out.println(s.getAbsolutePath());
                    }
                } else {
                    boolean b = s.delete();
                    if (b) {
                        increaCount();
                        System.out.println(s.getAbsolutePath());
                    }
                }
            }
        }

    }

    int count = 0;

    void increaCount() {
        count++;
    }
}
