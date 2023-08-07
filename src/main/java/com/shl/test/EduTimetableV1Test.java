package com.shl.test;

import com.shl.util.ApiClient;
import com.shl.util.JsonUtils;
import com.shl.util.Md5Utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

public class EduTimetableV1Test {

    public static void main(String[] args) throws IOException {

        findTimeTable();
        // findStudent();
    }

     static void findTimeTable() throws IOException {
        Map<String, Object> param = new TreeMap<>();
        String app_secret = "8834585d741cab7ebabfcb9301e64ba2";
        param.put("timestamp", System.currentTimeMillis() / 1000);
        param.put("app_id", "TYZXMK20230718");
        param.put("room_code", "yxg20210101");
        param.put("type", 2);
        StringBuilder p = new StringBuilder();
        for (String s : param.keySet()) {
            System.out.println("key = " + s);
            p.append(s).append("=").append(URLEncoder.encode(param.get(s) + "", "UTF-8")).append("&");
        }

        if (p.length() > 0) {
            p.deleteCharAt(p.length() - 1);
        }

        System.out.println(p.toString());

        String sign = Md5Utils.Md5(p.toString() + app_secret);
        System.out.println("sign = " + sign);

        param.put("sign", sign);
        Map data = ApiClient.sendGet2(param, "https://thirdapi.qingxiaolu.com/thirdapi/V1/schedule/room", Map.class, "");
        System.out.println(JsonUtils.writeValueAsString(data));
    }

    static void findStudent() throws IOException {
        Map<String, Object> param = new TreeMap<>();
        String app_secret = "348a78ec319f9f1b2a871562766bcf47";
        param.put("timestamp", System.currentTimeMillis() / 1000);
        param.put("app_id", "TYOVIA20220214");
        param.put("class_unique_code", "5iuedog8vog7876hvjst31ah8e");
        StringBuilder p = new StringBuilder();
        for (String s : param.keySet()) {
            System.out.println("key = " + s);
            p.append(s).append("=").append(URLEncoder.encode(param.get(s) + "", "UTF-8")).append("&");
        }

        if (p.length() > 0) {
            p.deleteCharAt(p.length() - 1);
        }

        System.out.println(p.toString());

        String sign = Md5Utils.Md5(p.toString() + app_secret);
        System.out.println("sign = " + sign);

        param.put("sign", sign);
        Map data = ApiClient.sendGet2(param, "http://dcollege.qljy.com:5183/thirdapi/V1/student/get-student-by-class", Map.class, "");
        System.out.println(JsonUtils.writeValueAsString(data));
    }

}
