package com.shl.test;

import java.io.File;

public class ClearOverlays {

    public static void main(String[] args) {
        String root = "E:\\IdeaProjects\\xcenv_local_shl_future";
        listFile(new File(root));
    }

    static void listFile(File root) {
        boolean isDir = root.isDirectory();
        if (isDir) {
            String name = root.getName();
            if ("overlays".equals(name) || "target".equals(name)) {
                deleteFile(root);
            } else {
                for (File file : root.listFiles()) {

                    listFile(file);
                }
            }


        }
    }

    static void deleteFile(File node) {
        boolean isDir = node.isDirectory();
        if (isDir) {
            for (File file : node.listFiles()) {
                deleteFile(file);
            }
        }
        boolean success = node.delete();
        System.out.println(node.getAbsolutePath() + " 删除 " + (success ? "成功" : "失败"));

    }
}
