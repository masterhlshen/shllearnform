package com.shl.xctest;

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
import java.util.*;

public class GetXcToken {
   static Map<String, Object> userMap = new HashMap<>();

   static {
       userMap.put("useraccount", "oop");
       userMap.put("userpassword", "123456");
   }

    public static void main(String[] args) throws IOException {


        String path = "http://localhost:8080/xcoffice/login";
        sendPostForm(userMap, path);

    }

    public static String sendPostForm(Map<String, Object> paraMap, String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

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
        Map<String, List<String>> headerFields = conn.getHeaderFields();
        List<String> cookieList = headerFields.get("Set-Cookie");
        Map<String, String> res = new HashMap<>();
        res.put("epname", userMap.get("useraccount") + "");
        for (String s : cookieList) {
            String[] arr = s.trim().split(";");
            for (String s1 : arr) {
                String[] split = s1.split("=");
                if (split.length > 1) {
                    res.put(split[0].trim(), split[1]);
                }
            }

        }

        res.put("JSESSIONID", "b0602fa1-370f-4f46-9473-b29aa8fba749");
        List<String> list = new LinkedList<>();
        for (String s : res.keySet()) {
            list.add(s + "=" + res.get(s));
        }
        String cookie = String.join(";", list);

        System.out.println(cookie);
        /*cookie = "epname=oop;" +
                " Path=/xcoffice;" +
                " Max-Age=0;" +
                " Expires=Thu, 18-May-2023 12:01:20 GMT;" +
                "isRemembered=deleteMe;" +
                "eptoken=deleteMe;" +
                "JSESSIONID=b0602fa1-370f-4f46-9473-b29aa8fba749;" +
                " HttpOnly=undefined;" +
                "rememberMe=deleteMe";
        System.out.println(cookie);*/
        Map map = sendGet(null, "http://localhost:8080/xcoffice/getContext?JSESSIONID=" + res.get("JSESSIONID"), cookie);
        System.out.println(JsonUtils.writeValueAsString(map));
        return cookie;
    }


    public static Map sendGet(Map<String, Object> paraMap, String path, String cookie) throws IOException {

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
        URL url = new URL(path);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Cookie", cookie);
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

        return JsonUtils.readValueByClass(result.toString(), Map.class);
    }

}
