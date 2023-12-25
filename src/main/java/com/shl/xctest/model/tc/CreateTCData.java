package com.shl.xctest.model.tc;

import com.shl.util.ApiClient;
import com.shl.util.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class CreateTCData {

    public static void main(String[] args) throws IOException {


        // 精度问题
        float f = 0.1f * 0.1f;
        // 输出的不是0.01，而是0.010000001
        System.out.println(f);
        // 换double试试
        double d = 0.1D * 0.1D;
        // 输出的依然不是0.01，而是0.010000000000000002
        System.out.println(d);

        boolean a = true;
        int b = 0;
        boolean flag = a | b++ > 0;
        System.out.println(flag + "  " + b);

        b = 0;
        flag = a || b++ > 0;
        System.out.println(flag + "  " + b);


        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> data = new HashMap<>();
        List testData = new LinkedList();
        data.put("testData", testData);
        for (String idCard : idCardArr) {
            Map e = new HashMap(4);
            e.put("idCard", idCard);
            List dataList = new ArrayList(7);
            e.put("dataList", dataList);
            for (int i = 0; i <= 6; i++) {
                Map e1 = new HashMap(8);
                e1.put("testItemCode", cTable[new Random().nextInt(cTable.length)]);
                e1.put("testTime", sdf.format(new Date()));
                e1.put("value", new Random().nextInt(300));
                dataList.add(e1);
            }
            testData.add(e);
        }
        // System.out.println(JsonUtils.writeValueAsString(data));
        sendData(data);*/
    }


    static void sendData(Map<String, Object> paraMap) throws IOException {
        String path = "https://test.xckj.net:9391/xcoffice/api/v1/api-personalized2/physical-health/data-sync/batch-upload";
        Map res = ApiClient.sendPostJson(paraMap, path, Map.class, token);
        System.out.println(JsonUtils.writeValueAsString(res));
    }

    static String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ4Y2FkbWluIiwic3ViIjoiOGFhNTc2YWI5MTQ3NDNiMTkxNDdkYTc4YzA3MTZmMWEiLCJleHAiOjE3MDMyOTI3MzAsIm5iZiI6MTcwMjQyODczMCwiaWF0IjoxNzAyNDI4NzMwLCJqdGkiOiI3OTBmNDM3NS0wOThlLTQ0NmQtOGVkMi0wZTc4NGIwMjQ3ZDEiLCJ0dHkiOiJhY2Nlc3NUb2tlbiJ9.NTWRzLDiZnyEkcrgaHHiM670kXxqGFgckIQIY31dDvg";

    static String[] idCardArr = new String[]{
            "340823199206125320",
            "340823199206125321",
            "340823199206125322",
            "340823199206125317",
            "340823199206125318",
            "340823199206125319"
    };

    static String[] cTable = new String[]{
            "0001001",
            "0001002",
            "0001003",
            "0002001",
            "0003001",
            "0004001",
            "0005001",
            "0006001",
            "0006002",
            "0007001",
            "0007002",
            "0007003",
            "0008001"
    };
}
