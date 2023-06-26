package com.shl.test;

import com.shl.util.JsonUtils;
import com.shl.util.PDFtoImages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test3 {

    public static void main(String[] args) {
        String s = "232";
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n * 10 + (s.charAt(i) - '0');
        }
        System.out.println(n);


        PDFtoImages.pdftoimage("D:\\DingDing\\dingdingdownlaod\\去氧参比制剂\\10042A-Ass-24M.pdf");
        PDFtoImages.pdftoimage("D:\\DingDing\\dingdingdownlaod\\去氧参比制剂\\10042A-Ena-24M.pdf");
        PDFtoImages.pdftoimage("D:\\DingDing\\dingdingdownlaod\\去氧参比制剂\\10042A-RSub-24M.pdf");

    }

    static void searchYearAndClassNum(String s) {
        List<String> res = new ArrayList<>(4);
        Matcher matcher = compile.matcher(s);
        while (matcher.find()) {
            res.add(matcher.group());
        }
        System.out.println(JsonUtils.writeValueAsString(res));
    }

    static Pattern compile = Pattern.compile("\\d+");
}
