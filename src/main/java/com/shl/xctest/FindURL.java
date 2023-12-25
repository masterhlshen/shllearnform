package com.shl.xctest;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class FindURL {

    public static void main(String[] args) throws IOException, ParseException {
        //
        // traverse(new File("D:\\DingDing\\dingdingdownlaod\\xc-class\\xc-class\\src\\api"));
        List<VO> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            VO vo = new VO();
            vo.id = i + "";
            list.add(vo);
        }
        int index = 0;
        Iterator<VO> it = list.iterator();
        Map<Integer, VO> change = new HashMap<>();
        while (it.hasNext()) {
            VO next = it.next();
            if (next.id.equals("3")) {
                VO vo = new VO();
                vo.id = "1000";
                change.put(index, vo);
            }

            index++;
        }
        for (Integer id : change.keySet()) {
            list.set(id, change.get(id));
        }
        for (VO vo : list) {
            System.out.println(vo.id);
        }

    }

    static class VO {
        String id;
    }

    static void traverse(File file) throws IOException {
        if (file == null) {
            return;
        }

        if (file.isDirectory()) {
            if (file.getAbsolutePath().contains(".svn")) {
                return;
            }
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                traverse(f);
            }
        }

        if (file.isFile() && file.getAbsolutePath().contains(".js")) {
            List<String> lineList = FileUtils.readLines(file);
            boolean b = false;
            for (String line : lineList) {
                if (line.contains("url") && line.contains("/classcreen")) {
                    System.out.println(line);
                }


            }

        }
    }
}
