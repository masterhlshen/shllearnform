package com.shl.test;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class Test2 {

    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, ParseException {
        Date date = new Date();
        int switchInt = 0;
        if (switchInt > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse("2020-01-01");
        }

       // System.out.println(date.getTime());

        System.out.println(Integer.MAX_VALUE);


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
        String srcPath = "http://yun.xckj.net/xcoffice/xcurl/printform?access_token=eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNp8Vc-P20QU_l9ydio7iZM4txKt-LXlACsBx-fxiz2b8Yw7Mw72Ig6gnkBa6KESCKRSqeqth3JCwJ_TbMt_wRvbydrsdg9RPJ_fr-97z2--Hp1bPlqNQsaiKYuS8TRcRuPZkvnjaLaZjONZOA38ZObHUzbyRqaMyXjCIh_ieD5PEpyFy9gP5uj7mxiSIPan4Ay5MWSY12qz4QwLARbHVm1Rjg3qHWpnAna0CuaT5WK2WMxn3girogMiP3KA0uknkCMFunryy_77Z_vnj8iPKV18mBDo06FAna9Vgi4blxu1yoFLS7-V7weeqY3F_AFISFF7UukcxOGkpOAST3YgSrBctQ6W53gGscDVjuNXDZSD4WZFTqJ2Dw6ipEZJiYKyuWA9ux7ABBijEdvIN4vTmHIqT_d9DAjQ9crWRXtONSRU3qGcwMMKjnwabTmIlUHbWN8ArIb6YF1odf7gOtPDEo2jLYFr_Ayt5TJteSA2z21VuqWiSm3ISiBrpOpXPJDYUHN62lF-Ll2wI5Kp0hwyta5rJU2ZO83NIQokiUZjTkmegdMxSptmkMEdqqw4Mpx4lmXE2RnYQZ-UJoVYzQR2bTDEjnVsWKaUeA_ktuOdF6TG_zlfT0lzYlmO0ppb-sgyZG0kSHbcYN9kOI3GluOHJQhu675Rn9GhujUIlAnoHpvrYW2OCVhww2J4KstiUDnmxaCzrYN7iot88Kaj0Id2KEscILd8L0Nx7qxvIP2Q6rCVN4kM398m07GJ7xyzu2LcMfG3fbeZyrFNO5iC4ddwPbXvju72Yp42663bstEiipNlDKEfLCIWAsJiEk3C5catY6y6PdgmP6O9QUCKEklngrUSeFiPVZUKmsOcSw-E8LZxKmqv0TK-qH1_4bFtKnRNf-aC_uLcva84qAxUQU7uMaZB59vSq4Af4RK26F2Y90-_9M7JxGTcO6-c7xcft7yojpK2frfK9__8_u_fl1c_X3Zwn-tdNwpdBkNZYBGizwKSBZZhMMVlGM6jzbQ1PVXycHespz5dH2-_--P1X4_vXb16-vaHH9_89u2bX3_aP718_efL_csX9_rXC08cR1t3Wp6d3F9_cPJpG9VJSVjNPvp83ZV_n1EzpXUoSOLuevjNfwAAAP__.qeHWSyjqPwj4VLGpuCL6hkwvZM1PCAIV3y5ZSMa21AIrI1hvpfRYi-G0fBfWO-SZkOXoKRhNdSTPLXny14zo_w&formKey=2c90abb975d19ba70175d5a378851a23&bpmBusinessKey=35406221d358469f96c97289add8c6b7&showPrintCommend=false&startUser=2c90abb66dde458b016e00fbad1b03ac&processId=8471251";
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
