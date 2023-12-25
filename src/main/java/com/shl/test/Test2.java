package com.shl.test;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class Test2 {

    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, ParseException {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE - 1);

    }

    static void download() throws IOException {
        Map<String, Object> varMap = new HashMap<>();
        URL url = new URL("https://jymf.wuxing.gov.cn:37091/xcadmin/comp/attachment/download?fileid=e8aa300572014a51bdcfd32cdcf92485&ccc=c875e0529df0a4de08e9bb26e0e062ed");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/octet-stream;charset=utf-8");
        conn.setDoInput(true);
        conn.setDoOutput(true);
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
        int responseCode = conn.getResponseCode();
        System.out.println("response code = " + responseCode);
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        out.print(sb);
        out.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line = "";
        StringBuilder result = new StringBuilder();
        while (StringUtils.isNotBlank((line = bufferedReader.readLine()))) {
            result.append(line);
        }
        System.out.println(result);
    }

    public static void select(String path, long sTime) throws IOException {
        File[] fileArr = new File(path).listFiles();
        for (File f : fileArr) {
            String[] s = f.getName().split("_");
            if (s.length >= 3) {
                long time = Long.parseLong(s[0]);
                if (time >= sTime) {
                    System.out.println(f.getName());
                }
            }
        }


        String his = "E:\\IdeaProjects\\mysql\\createsql.properties";
        System.out.println(his);
        Properties p = new Properties();
        InputStream in = new FileInputStream(his);
        p.load(in);
        p.setProperty("cc", "cc");

        p.store(new BufferedWriter(new FileWriter(his)), "update aaa");
    }

    private static void pdfTest() {
        String srcPath = "https://test.xckj.net:9391/xcoffice/xcurl/printform?access_token=eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNqUV81u3DYQfpViz9pC0q69u7o5hosEiNui8SW9LChqtMtYIhWS2lgteuu96KGH3JJLbw1yKVqg6NvU7mt0SOqHUtZxc_BaooacmW9mvhl-P3uh2SyZhflpvMnzeJ6meTRfbrLFfB1COKeQL8Llcp1DtJoFM1WnKBzTTUhSulxHq5OYZCdhhE-n0XqZ43NMAAWZUihYNiLPGYWqIBrmWlwDnyuQB5BGhOhZEq3C-DQ-OVltghncVO3C6XplFoTcfUlKwINu3729e_Pn7fuf_v3rR9xKhayeZMaSaJNSGsWLTZwv0xgt2kC-XpNsGW7iFYlQtgJZnosMjD0c_0dhmBwYvErCMAqu9hLgfA_0OikJJzvA1Th4JeT1VkIlpN7WuN-KVkTqZltCmeJCLxwFgheMw8WBFDXRTHBvcVsAkZzx3Vb1x2ggdA8Sj6eyZroErr2vpZCkSDKiiTMxRrWNkek0viL8hllRDIViWWMNJloDzwinkCiNVijNqHLqWAlXJC1g8JnxXCQKCqDa6SSKqQSPLxrzYH0FqQTnUPiO7kUJiu6FGK12LiqMCWzdB89g49YiqFSDGwuxa_ytx8BfBE4FAq0ULm2n0NTauUxFLRUkRErCW41jAJNwcxPsJMkwLPBM15mH4uC2t2CifkXUdUIoFWVVMLWHzH6hBVEKbeUDfiVhXOOfXXlZk4Jhnrv4e0dK2GEsxvnSq-kjhQbbgJKCyCbRTeUEla4v3TZvdyZkuQdS6P0Q0OFE0BpDMUmPZY9CuyUK4IaU7mwZ2AplaAPudrk7XdCSNJ10JcWLS99BUyQuwTAG9UT1YrDN4QTKQMQJk_DMMzYl9LrT0EYQ_WRayMZ-R_hBKy-lIpMmkNXFCJy2VuYfZKGLIkjtooRpk5Vt7Iz9qK83fidMno9V9f6YdFdYGUkObTChoKYs2vQaeIXuvxnHvrPappFq8Es5-uKhzjOH_KIjiwQm7NLJIokAO0AnXgJYSB84d9kWzzPLAZOUTYmCJzbDO0SQlkEyi6aFLdB0b95U-0rrQteyxRQRgWs4DJ8d9KIsa86wdJ1QWpVfS0ERy0HNcaeWgcOqyw7D4PGAs2Wd4RUzlRkyGla-YJqjmrnGGI3JrFZd9pkzFz4_asCK0Vj8vXFGZOmp7TPipLXvXHBVl6YOVGcqyTKJqp9iFjgHEUYbw20qiMyOWTPxa-SSeckZtyQ_aQbx_2FhDC377rxWWpSDXwPRSzElqpt9dTn0RAw61r6xZoSjoRZwLPDJunpvJSZim3ay9ElUTojQjBGPkCvapMJAcd1xZKeGmoaCULDxIY5WaEOLjrkUFgGFD3tL2-p7-vN5o6pT0xeO1b43PxTo35bwbJuLGn-H9u562yPCOw_KCit2WoKfFSRNSIVwH7qqGtq41ymsp-4gC_Vj1xd8r0l2YAo-sV_HmO4Yh6JPZGRfpawQ45kBQvSBmdvep5v7Eifyd2PZjA9AHuj0OrRjX9x3xIeEkrKqVQvmOSnM6CM_0GW6azsEufB66eaY2fYlX4kdDwc5f5ZAZ9mO15Xvpw3NBbbSjuXahPdObNG5hIx86tS0RPYvqyM5PAHOzwh_CTm4nvSxruswd0iNo_gZz76SO693u-mrH9jsdPLAzDaOjqi0X4PDlHJPqzzWCyYsPyHoCRnfX4eTeE5BOzrlfowR72ew4wPuQ2B-pCMcv5gcR__eocJcssqdvSiZuxvcmIueebJ2XeGgiQs74GhFgctSFNDdlOysCSSQymrIiAqeP__2UaBe7IpW4VlhNpkkai9p__z9-u73P9o1dz17-KKId7yR6CpfrzabdBVG-ESj1QLoMsQrHorCy5pVpkpayyvq9j8VvLsnov7b9z_fvX5398vr299-vXvz9vPJ1ZGZJMGSbM-4ujg7f3zxjTvIOI9rat_6cIYXgZrjLXUmRDX74T8AAAD__w.xxgYSfbNUgGbbGcJLab7tLXorNzgBoN9S3ELaSOiZuOFITu-uQI_HhGRes6KeUii80BBJIW8KYbId_N08Y_Jng&formKey=402894ee8c6b3cc2018c6b4bee34003f&bpmBusinessKey=8ee110c5c0c5494db6db8556c88fce89";
//        srcPath = "http://116.62.48.123/xcoffice/static/aaa.html";
//        srcPath = "https://developer.aliyun.com/article/353699";
        String BASEDIR = "D:\\";
        String pdfName = "aaa.pdf";
        final String destPath = BASEDIR + pdfName;
        File file = new File(destPath);
        File parent = file.getParentFile();
        //如果pdf保存路径不存在，则创建路径
        if (!parent.exists()) {
            parent.mkdirs();
        }

        StringBuilder cmd = new StringBuilder();
        cmd.append("D:/wkhtmltopdf/wkhtmltopdf64/bin/wkhtmltopdf.exe").append(" ");

        cmd.append("--no-outline").append(" ");
        String jsDelay = "500";
        cmd.append("--javascript-delay").append(" ").append(jsDelay).append(" ");
        cmd.append("--no-stop-slow-scripts").append(" ");
        cmd.append(srcPath);
        cmd.append(" ");
        cmd.append(destPath);
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(cmd.toString());
            proc.waitFor();
            pdfName = destPath;
        } catch (Exception e) {
            // LOG.debug("pdfName = " + pdfName + " 没有成功生成pdf文件");
            pdfName = "";
            e.printStackTrace();
        } finally {
            if (proc != null) {
                // LOG.debug(">>>>>>>>>>>>结束process 线程");
                proc.destroy();
            }
            // LOG.debug(">>>>>>>生成pdf结束");
        }
        System.out.println(">>>>>>>>>>>>>>.aaa");
    }
}
