package com.shl.test;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CTest {
    public static void main(String arg[]) throws IOException {
        String path = "E:\\IdeaProjects\\mysql";
        String pathw = "D:\\xclog\\打包sql\\syjyj20220522.sql";
        String envName = "测试";
        List<File> files = select(path, 20220522);
        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            }
        });

        File ff = new File(pathw);
        if (!ff.exists()) {
            FileOutputStream out = new FileOutputStream(pathw);
            out.close();
            ff = new File(pathw);
        }

        List<String> fileLines = new ArrayList<>();
        try {
            for (int i = 0, len = files.size(); i < len; i++) {
                File f = files.get(i);
                //String ggg = FileUtils.readFileToString(f, "UTF-8");
                fileLines.addAll(FileUtils.readLines(f, "UTF-8"));
                fileLines.add("\n\n");
                System.out.println(f.getName());
                if (i == (len - 1)) {
                    storeHistory(envName, f.getName());
                }
            }
            FileUtils.writeLines(ff, "UTF-8", fileLines);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String> newuserIds = new ArrayList<String>();
        newuserIds.add("baafafafffa123");
        newuserIds.add("abcdefgjfdsaf0000");
        System.out.println("newuserIds:" + newuserIds.toString());
        // System.out.println(isMessyCode(ff));
    }


    private static List<File> select(String path, long sTime) {
        File[] fileArr = new File(path).listFiles();
        List<File> res = new LinkedList<>();
        for (File f : fileArr) {
            String[] s = f.getName().split("_");
            if (s.length >= 3) {
                long time = Long.parseLong(s[0]);
                if (time >= sTime) {
                    res.add(f);
                }
            }
        }
        if (res.isEmpty()) {
            throw new IllegalArgumentException("未发现" + sTime + "之后的sql文件");
        }
        return res;
    }

    /**
     * 记录打包历史
     *
     * @param envName      环境名称
     * @param lastFileName 最后一个sql文件的名称
     * @throws IOException
     */
    private static void storeHistory(String envName, String lastFileName) throws IOException {
        String his = "E:\\IdeaProjects\\mysql\\patchsql.txt";
        File hisFile = new File(his);
        String hisContent = envName + "_" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()) + "---->" + lastFileName + "\n\n";
        List<String> hisContentList = new ArrayList<>(2);
        hisContentList.add(hisContent);

        FileUtils.writeLines(hisFile, "UTF-8", hisContentList, true);

    }
}