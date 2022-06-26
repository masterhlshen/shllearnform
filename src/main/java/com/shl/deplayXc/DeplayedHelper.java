package com.shl.deplayXc;

import com.shl.util.JsonUtils;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 盖包助手
 *
 * @author shenhl
 */
public class DeplayedHelper {

    DeplayCfgVO cfg;
    String backDir;
    private List<String> warList = new LinkedList<>();

    {
        warList.add("xcoffice");
        warList.add("xcadmin");
        warList.add("xcmobile");

    }

    public static void main(String[] args) throws IOException {

        DeplayedHelper helper = new DeplayedHelper();
        helper.loadCfg();
        helper.backDir = helper.cfg.getServerPath() + "副本(" + (new SimpleDateFormat("yyyyMMdd").format(new Date())) + ")";
        // TODO 备份原有服务
        helper.copyServer(new File(helper.cfg.getServerPath()));
        // TODO 删除
        for (String name : helper.warList) {
            helper.deleteXc(new File(helper.cfg.getServerPath() + "/" + helper.cfg.getAppServer().get(name).getServer() + "/" + name + ".war"));
            helper.deleteXc(new File(helper.cfg.getServerPath() + "/" + helper.cfg.getAppServer().get(name).getServer() + "/" + name));
        }


        // TODO 将war解压到对应服务
        for (String name : helper.warList) {

            helper.unzip(helper.cfg.getResourceDir() + "/" + name + ".war", helper.cfg.getServerPath() + "/" + helper.cfg.getAppServer().get(name).getServer() + "/" + name);

        }

        // TODO 删除swagger文件
        for (String name : helper.warList) {
            helper.deleteSwaggerFile(new File(helper.cfg.getServerPath() + "/" + helper.cfg.getAppServer().get(name).getServer() + "/" + name));
        }

        // TODO 复制配置文件
        for (String name : helper.warList) {
            File file = new File(helper.cfg.getServerPath() + "/" + helper.cfg.getAppServer().get(name).getServer() + "/" + name + "/WEB-INF/classes");
            File[] filess = file.listFiles();
            if (filess != null) {
                for (File f : filess) {
                    if (f.isFile()) {
                        boolean delete = f.delete();
                        System.out.println(f.getAbsolutePath() + " delete " + delete);
                    }

                }
                File srcFile = new File(
                        file.getAbsolutePath().replace(helper.cfg.getServerPath(), helper.backDir)
                );

                File[] files = srcFile.listFiles();
                if (files != null) {
                    for (File ff : files) {
                        if (ff.isFile()) {
                            FileInputStream in = new FileInputStream(ff);
                            FileOutputStream out = new FileOutputStream(ff.getAbsolutePath().replace(helper.backDir, helper.cfg.getServerPath()));
                            int len = -1;
                            byte[] buff = new byte[1024];
                            while ((len = in.read(buff)) != -1) {
                                out.write(buff, 0, len);
                            }
                            out.flush();
                            out.close();
                            in.close();
                        }
                    }
                }
            }
        }

    }

    private void deleteSwaggerFile(File file) {
        if (file.isFile()) {
            if ("Swagger2Config.class".equals(file.getName())) {
                boolean b = file.delete();
                System.out.println(file.getAbsolutePath() + " delete " + b);
            }
            return;
        }
        for (File listFile : file.listFiles()) {
            deleteSwaggerFile(listFile);
        }
    }

    private void deleteXc(File f) {

        if (f.isFile()) {
            boolean b = f.delete();
            System.out.println(f.getAbsolutePath() + " delete " + b);
            return;
        }

        File[] files = f.listFiles();
        if (f.isDirectory() && files != null && files.length > 0) {
            for (File file : files) {
                deleteXc(file);
            }
            boolean b = f.delete();
            System.out.println(f.getAbsolutePath() + " delete " + b);
        }
    }

    private void copyServer(File srcFile) throws IOException {
        if (!srcFile.isDirectory()) {
            // TODO 处理
            String backPath = srcFile.getAbsolutePath().replace(cfg.getServerPath(), backDir);
            System.out.println(backPath);
            File backFile = new File(backPath);
            FileInputStream input = new FileInputStream(srcFile);
            if (!backFile.getParentFile().exists()) {
                backFile.getParentFile().mkdirs();
            }
            FileOutputStream out = new FileOutputStream(backFile);
            int len = -1;
            byte[] buff = new byte[1024];
            while ((len = input.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            out.flush();
            input.close();
            out.close();
            return;
        }
        File[] files = srcFile.listFiles();
        for (File f : files) {
            copyServer(f);
        }
    }

    private void loadCfg() throws IOException {
        String cfgPath = "D:\\deplay_cfg\\cfg.json";
        BufferedReader reader = new BufferedReader(new FileReader(cfgPath));
        StringBuilder res = new StringBuilder();
        String line = "";
        while ((StringUtils.isNotBlank(line = reader.readLine()))) {
            res.append(line);
        }
        cfg = JsonUtils.readValueByClass(res.toString(), DeplayCfgVO.class);
        System.out.println(cfg.getServerPath());
    }


    private void unzip(String warPath, String unzipPath) {
        System.out.println(">>>>>war包位置" + warPath);
        System.out.println(">>>>>解压地址" + unzipPath);
        File warFile = new File(warPath);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(warFile));
            ArchiveInputStream in = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.JAR,
                    bufferedInputStream);

            JarArchiveEntry entry = null;
            while ((entry = (JarArchiveEntry) in.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    new File(unzipPath, entry.getName()).mkdir();
                } else {
                    OutputStream out = FileUtils.openOutputStream(new File(unzipPath, entry.getName()));
                    IOUtils.copy(in, out);
                    out.close();
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("未找到war文件");
        } catch (ArchiveException e) {
            System.err.println("不支持的压缩格式");
        } catch (IOException e) {
            System.err.println("文件写入发生错误");
        }
    }

}

