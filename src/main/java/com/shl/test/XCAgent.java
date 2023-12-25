package com.shl.test;

import com.shl.util.ApiClient;

import java.io.IOException;
import java.util.HashMap;

public class XCAgent {

    public static void main(String[] args) throws IOException {

        String appId = "e453a9a9cd7949f5ba9dcfef67a3be19";
        String screct = "8286b9f4a01f4ffcb021ea4216941dbf";

        String url = "http://yxxx.xckj.net";


        String v1 = "%s/xcoffice/api/v1/xckj/oauth/authorize?client_id=%s" +
                "&redirect_uri=%s&response_type=code";
        v1 = String.format(v1, url, appId, "http://ga33ne.natappfree.cc/Backstage/BackstageHome/ZtLoginLoading");

        System.out.println("v1 = " + v1);

        String s = ApiClient.sendGet(new HashMap<>(), v1, String.class, "");
        System.out.println(s);
    }
}
