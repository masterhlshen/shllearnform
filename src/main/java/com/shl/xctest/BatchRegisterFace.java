package com.shl.xctest;

import com.shl.util.ApiClient;
import com.shl.util.JsonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 批量向测试环境注册人脸
 */
public class BatchRegisterFace {

    static String dir = "C:\\Users\\18256\\Desktop\\test";
    static StringBuilder token = new StringBuilder("");
    static Map<String, String> picMap = new HashMap<>(32);
    static Map<String, String> picSuffixMap = new HashMap<>(32);
    static String registerURL = "https://test.xckj.net:9391/xcvisualface/api/v1/api-face/addFaceInfo";
    static String searcURL = "https://test.xckj.net:9391/xcvisualface/api/v1/api-face/queryUserByFace";
    public static void main(String[] args) throws IOException {

        register();
        findPicUserId();


    }

    private static void findPicUserId() throws IOException {
        findPicInfo();

        for (String s : picMap.keySet()) {
            Map<String, Object> paraMap = new HashMap<>(8);
            paraMap.put("imgBase64", picMap.get(s));
            long start = System.currentTimeMillis();
            Map map = ApiClient.sendPostJson(paraMap, searcURL, Map.class, token.toString());
            System.out.println(JsonUtils.writeValueAsString(map));
            System.out.println(">>>>>>查询人脸耗时:" + ((System.currentTimeMillis() - start) / 1000.0) + "秒");
        }
    }

    private static void register() throws IOException {
        findPicInfo();

        for (String s : picMap.keySet()) {
            Map<String, Object> paraMap = new HashMap<>(8);
            paraMap.put("imgSuffix", picSuffixMap.get(s));
            paraMap.put("userId", s);
            paraMap.put("imgBase64", picMap.get(s));
            Map map = ApiClient.sendPostJson(paraMap, registerURL, Map.class, token.toString());
            System.out.println(s + "--->" + JsonUtils.writeValueAsString(map));

        }
    }

    private static void findPicInfo() throws IOException {
        FileInputStream in = new FileInputStream("E:\\opensource\\shllearnform\\src\\main\\java\\com\\shl\\xctest\\token.txt");
        byte[] buff = new byte[1024];
        int len = -1;

        while ((len = in.read(buff)) != -1) {
            token.append(new String(buff, 0, len));
        }
        // System.out.println(token);



        getPicInfo(new File(dir));
    }

    static void getPicInfo(File file) {
        if (file == null) {
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                getPicInfo(f);
            }
            return;
        }

        String path = file.getAbsolutePath();
        String name = file.getName();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        name = name.substring(0, name.lastIndexOf("."));

        picMap.put(name, base64Img(path));
        picSuffixMap.put(name, suffix);
    }

    static String base64Img(String filePath) {
        String encode = "";
        FileInputStream in = null;
        try {
            in = new FileInputStream(filePath);
            byte[] buff = new byte[in.available()];
            in.read(buff);
            encode = java.util.Base64.getEncoder().encodeToString(buff);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return encode;
    }
}
