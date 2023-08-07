package com.shl.util;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class ApiClient {

    public static void main(String[] args) throws IOException {
        final String path = "https://test.xckj.net:9391/xcoffice";
        Map<String, Object> varMap = new HashMap<>();
        varMap.put("useraccount", "chentun");
        varMap.put("userpassword", "123456");
        Map res = sendPostForm(varMap, path + "/login", Map.class, "");
        System.out.println(JsonUtils.writeValueAsString(res));

        varMap.put("clientId", "8aa576ab914743b19147da78c0716f1a");
        varMap.put("clientSecret", "b6fcea8c92024819adbc4409f72d4edd");
         res = sendPostJson(varMap, path + "/api/clientToken/login/client", Map.class, "");
        System.out.println(JsonUtils.writeValueAsString(res));

    }


    public static <T> T sendGet(Map<String, Object> paraMap, String path, Class<T> clss, String author) throws IOException {

        if (paraMap != null) {
            StringBuilder params = new StringBuilder();
            for (Map.Entry<String, Object> entry : paraMap.entrySet()) {
                params.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            if (params.length() > 0) {
                params.deleteCharAt(params.length() - 1);
            }
            path += ("?" + params.toString());
        }
        System.out.println(path);
        URL url = new URL(path);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        if (StringUtils.isNotBlank(author)) {
            conn.setRequestProperty("Authorization", author);
        }
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
        return JsonUtils.readValueByClass(result.toString(), clss);
    }

    public static <T> T sendPostJson(Map<String, Object> paraMap, String path, Class<T> clss, String author) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        if (StringUtils.isNotBlank(author)) {
            conn.setRequestProperty("Authorization", author);
        }
        conn.setConnectTimeout(2 * 60 * 1000);
        // 不允许使用缓存
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.connect();
        if (paraMap != null) {
            conn.getOutputStream().write(JsonUtils.writeValueAsString(paraMap).getBytes(StandardCharsets.UTF_8));
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line = "";
        StringBuilder result = new StringBuilder();
        while (StringUtils.isNotBlank((line = bufferedReader.readLine()))) {
            result.append(line);
        }

        return JsonUtils.readValueByClass(result.toString(), clss);
    }

    public static <T> T sendPostForm(Map<String, Object> paraMap, String path, Class<T> clss, String author) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        if (StringUtils.isNotBlank(author)) {
            conn.setRequestProperty("Authorization", author);
        }
        conn.setConnectTimeout(2 * 60 * 1000);
        // 不允许使用缓存
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.connect();
        if (paraMap != null && !paraMap.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> entry : paraMap.entrySet()) {
                sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue() + "", "UTF-8")).append("&");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(sb);
            out.flush();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line = "";
        StringBuilder result = new StringBuilder();
        while (StringUtils.isNotBlank((line = bufferedReader.readLine()))) {
            result.append(line);
        }

        return JsonUtils.readValueByClass(result.toString(), clss);
    }


    public static <T> T sendGet2(Map<String, Object> paraMap, String path, Class<T> clss, String author) throws IOException {

        if (paraMap != null) {
            StringBuilder params = new StringBuilder();
            for (Map.Entry<String, Object> entry : paraMap.entrySet()) {
                params.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            if (params.length() > 0) {
                params.deleteCharAt(params.length() - 1);
            }
            path += ("?" + params.toString());
        }
        System.out.println(path);
        URL url = new URL(path);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        if (StringUtils.isNotBlank(author)) {
            conn.setRequestProperty("Authorization", author);
        }
        conn.setConnectTimeout(2 * 60 * 1000);
        // 不允许使用缓存
        conn.setUseCaches(false);
        conn.connect();
        byte[] b = IOUtils.toByteArray(conn.getInputStream());
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        GZIPInputStream gzis = new GZIPInputStream(bais);
        InputStreamReader reader = new InputStreamReader(gzis);
        BufferedReader in = new BufferedReader(reader);
        String readed;
        StringBuilder result = new StringBuilder();
        while ((readed = in.readLine()) != null) {
            result.append(readed);
        }
        return JsonUtils.readValueByClass(result.toString(), clss);
    }



}
