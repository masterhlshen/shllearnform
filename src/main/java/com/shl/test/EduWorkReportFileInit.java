package com.shl.test;

import com.shl.util.Dbutil;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 构造作业上报附件
 *
 * @author shenhl
 */
public class EduWorkReportFileInit {
    public static void main(String[] args) throws IOException {

        copyFile();
        // createZip();
        // createNormalZip();

    }

    private static void copyFile() throws IOException {
        String filePath = "D:\\upload\\1.jpg";
        FileInputStream in = new FileInputStream(filePath);
        if (in.markSupported()) {
            in.mark(0);
        }
        FileOutputStream out;
        int len = 0;
        byte[] buff = new byte[1024];

        File dF = new File(filePath);

        String sql = "SELECT file_name, relative_path from xc_sys_attachment where relative_path like 'work-report%'";
        Dbutil db = new Dbutil();
        List<Map> data = db.findListMapByNativeSql(sql, new HashMap());
        for (Map ele : data) {
            String file = "D:/upload/" + ele.get("relative_path");
            File f = new File(file);
            if (!f.exists()) {
                f.mkdirs();
            }
            file += "/" + ele.get("file_name");
            out = new FileOutputStream(file);
            int size = 0;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
                size += len;
            }
            out.flush();
            if (in.markSupported()) {
                in.reset();
            } else {
                in = new FileInputStream(filePath);
            }
        }
    }

    private static void createZip() throws IOException {
        int len = 0;
        byte[] buff = new byte[1024];
        String sql = "SELECT file_name, relative_path from xc_sys_attachment where relative_path like 'work-report%'";
        Dbutil db = new Dbutil();
        List<Map> data = db.findListMapByNativeSql(sql, new HashMap());
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("D:\\upload\\ab.zip"));
        int i = 0;
        for (Map ele : data) {
            if (i > 10) {
                return;
            }
            String file = "D:/upload/" + ele.get("relative_path");
            file += "/" + ele.get("file_name");
            ZipEntry zipEntry = new ZipEntry(ele.get("file_name") + "");
            zipOutputStream.putNextEntry(zipEntry);
            FileInputStream in = new FileInputStream(file);
            while ((len = in.read(buff)) != -1) {
                zipOutputStream.write(buff, 0, len);
            }
            zipOutputStream.closeEntry();
            i++;
            in.close();

        }


        zipOutputStream.flush();
        zipOutputStream.close();

    }

    private static void createNormalZip() throws IOException {
        String pathname = "D:\\帮助文档与工作文件\\晓窗\\晓窗设计文档\\67晓窗设计文档_沈宏亮\\交接文档\\数据库设计";
        File file = new File(pathname);
        System.out.println(file.getAbsolutePath());
        String[] list = file.list();
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("D:\\upload\\ab.zip"));
        byte[] buff = new byte[1024];
        int len = 0;
        for (String s : list) {
            FileInputStream in = new FileInputStream(pathname + "\\" + s);
            ZipEntry zipEntry = new ZipEntry(s);
            zipOutputStream.putNextEntry(zipEntry);
            while ((len = in.read(buff)) != -1) {
                zipOutputStream.write(buff, 0, len);
            }
        }
        zipOutputStream.close();
    }
}
