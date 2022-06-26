package com.shl.test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SlowSqlAnalysis {

    private static final String PL_DIR = "D:\\develop_soft\\strawberry-perl-5.32.1.1-64bit\\perl\\bin\\perl.exe";
    private static final String MYSQL_DUMP_SLOW_DIR = "D:\\develop_soft\\mysql-5.7.14-winx64\\bin\\mysqldumpslow.pl";
    private static final String EDIT_PLUS_DIR = "D:\\softusemanager\\editplus\\editplus.exe";
    private static String SRC_LOG_DIR = "D:/xclog/慢sql日志分析/testslow.log";

    public static void main(String[] args) throws IOException {
        String cmd = "-a -S t -t 10";
        writeAndOpen(cmd, "按照查询时间排序，查看前10条 SQL 语句");

        cmd = "-a -s r -t 20";
        writeAndOpen(cmd, "得到返回记录集最多的20个SQL");

        cmd = "-a -s c -t 10";
        writeAndOpen(cmd, "得到访问次数最多的10个SQL");

        cmd = "-a -s t -t 10 -g \"left join\"";
        writeAndOpen(cmd, "得到按照时间排序的前10条里面含有左连接的查询语句");
    }

    private static void writeAndOpen(String cmd, String suffix) throws IOException {
        cmd = PL_DIR + " " + MYSQL_DUMP_SLOW_DIR + " " + cmd + " " + SRC_LOG_DIR + "";
        System.out.println(cmd);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Runtime.getRuntime().exec(cmd).getInputStream(),
                        "GBK"
                )
        );

        String fileName = "D:/xclog/慢sql日志分析/" + suffix + ".txt";
        FileOutputStream fout = new FileOutputStream(fileName);
        OutputStreamWriter writer = new OutputStreamWriter(fout, StandardCharsets.UTF_8);
        String line;
        while ((line = br.readLine()) != null) {
            writer.write(line);
            writer.write("\n");
            System.out.println(">>>>>>>");
        }
        writer.flush();

        // Runtime.getRuntime().exec(EDIT_PLUS_DIR + " " + fileName);

    }


}
