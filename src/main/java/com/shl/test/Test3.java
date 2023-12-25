package com.shl.test;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Test3 {


    public static void main(String[] args) throws IOException {
        URL url = new URL("https://school.careforeyou.com/third/student/rope/list?page=1&size=10&studentNo=001");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("appid", "wvoylz1h1lhf8v5g");
        conn.setRequestProperty("appsecret", "tkbtv4gj3z856nz52v41wvhlg1i2r2un");

        conn.setConnectTimeout(2 * 60 * 1000);
        // 不允许使用缓存
        conn.setUseCaches(false);
        conn.connect();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line = "";
        StringBuilder result = new StringBuilder();
        while (StringUtils.isNotBlank((line = bufferedReader.readLine()))) {
            result.append(line);
        }
        System.out.println(line);
    }

    static void deleteSomeFile(File file) {

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File file1 : files) {
                deleteSomeFile(file1);
            }
        }

        file.delete();

    }
}
