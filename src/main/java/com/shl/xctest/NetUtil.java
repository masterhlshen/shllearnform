package com.shl.xctest;

import com.shl.util.HandleResult;
import com.shl.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class NetUtil {

    public static void main(String[] args) throws IOException {
        Map<String, Object> varMap = new HashMap<>(8);
        varMap.put("clientId", "8aa576ab914743b19147da78c0716f1a");
        varMap.put("clientSecret", "b6fcea8c92024819adbc4409f72d4edd");
        HandleResult handleResult = postJson(varMap, "https://test.xckj.net:9391/xcoffice/api/clientToken/login/client", HandleResult.class);
        System.out.println(handleResult.getData());

    }

    public static <T> T post(Map<String, Object> varMap, String urlPath, Class<T> c) throws IOException {
        URL url = new URL(urlPath);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setConnectTimeout(120000);
        // 不允许使用缓存
        conn.setUseCaches(false);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : varMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue() + "", "UTF-8")).append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        out.print(sb);
        out.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line = "";
        StringBuilder result = new StringBuilder();
        while (StringUtils.isNotBlank((line = bufferedReader.readLine()))) {
            result.append(line);
        }
        System.out.println(result);
        return JsonUtils.readValueByClass(result.toString(), c);
    }

    public static <T> T postJson(Map<String, Object> varMap, String urlPath, Class<T> c) throws IOException {
        Map<String, String> param = new HashMap<>(varMap.size());
        for (String s : varMap.keySet()) {
            param.put(s, URLEncoder.encode(varMap.get(s) + "", "utf-8"));
        }
        URL url = new URL(urlPath);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setConnectTimeout(120000);
        // 不允许使用缓存
        conn.setUseCaches(false);

        String jsonBody = JsonUtils.writeValueAsString(varMap);
        System.out.println(jsonBody);
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        out.print(jsonBody);
        out.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line = "";
        StringBuilder result = new StringBuilder();
        while (StringUtils.isNotBlank((line = bufferedReader.readLine()))) {
            result.append(line);
        }
        System.out.println(result);
        return JsonUtils.readValueByClass(result.toString(), c);
    }

    public static <T> T get(Map<String, Object> varMap, String urlPath, Class<T> c) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : varMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue() + "", "UTF-8")).append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        urlPath += "?" + sb.toString();
        System.out.println(urlPath);
        URL url = new URL(urlPath);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setConnectTimeout(120000);
        // 不允许使用缓存
        conn.setUseCaches(false);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line = "";
        StringBuilder result = new StringBuilder();
        while (StringUtils.isNotBlank((line = bufferedReader.readLine()))) {
            result.append(line);
        }
        System.out.println(result);
        return JsonUtils.readValueByClass(result.toString(), c);
    }
}
