package com.shl.deplayXc;

import com.shl.algorithm.ag4.util.StdIn;
import com.shl.deplayXc.fileopt.CatalinalogListener;
import com.shl.deplayXc.fileopt.FileMonitor;
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
        warList.add("xcadmin");
        warList.add("xcoffice");
        // warList.add("xcmobile");

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        DeplayedHelper helper = new DeplayedHelper();
        helper.loadCfg();
        helper.backDir = helper.cfg.getServerPath() + "副本(" + (new SimpleDateFormat("yyyyMMdd").format(new Date())) + ")";
        // TODO 备份原有服务
        helper.copyServer(new File(helper.cfg.getServerPath()));
        // TODO 删除
        for (String name : helper.warList) {
            warCgfVO warCgfVO = helper.cfg.getAppServer().get(name);
            helper.deleteXc(new File(helper.cfg.getServerPath() + "/" + warCgfVO.getServer() + warCgfVO.getWebApps() + "/" + name + ".war"));
            helper.deleteXc(new File(helper.cfg.getServerPath() + "/" + warCgfVO.getServer() + warCgfVO.getWebApps() + "/" + name));
        }


        // TODO 将war解压到对应服务
        for (String name : helper.warList) {
            warCgfVO warCgfVO = helper.cfg.getAppServer().get(name);
            helper.unzip(helper.cfg.getResourceDir() + "/" + name + ".war", helper.cfg.getServerPath() + "/" + warCgfVO.getServer() + warCgfVO.getWebApps() + "/" + name);

        }

        // TODO 删除swagger文件
        for (String name : helper.warList) {
            warCgfVO warCgfVO = helper.cfg.getAppServer().get(name);
            helper.deleteSwaggerFile(new File(helper.cfg.getServerPath() + "/" + warCgfVO.getServer() + warCgfVO.getWebApps() + "/" + name));
        }

        // TODO 复制配置文件
        helper.copyOldSeverProperties();

        helper.startServer(0);


        System.out.println(">>>>>>>输入q退出");
        String q = StdIn.readString();
        if ("q".equals(q)) {

        }
    }


    public void startServer(int index) throws IOException {
        if (index >= 0 && warList.size() > index) {
            warCgfVO warCgfVO = cfg.getAppServer().get(warList.get(index));
            startXcServer(cfg.getServerPath() + "/" + warCgfVO.getServer(), warCgfVO.getBinDir(), warCgfVO.getLogDir(), index);
        } else if (warList.size() == index) {
            copyNewWarAndStart();
        }
    }

    private void copyNewWarAndStart() throws IOException {
        // 复制文件
        File[] files = new File(cfg.getNewWar()).listFiles();
        if (files != null) {
            int len = -1;
            byte[] buff = new byte[1024];
            int success = 0;
            for (File file : files) {
                FileInputStream in = new FileInputStream(file);
                FileOutputStream out = new FileOutputStream(cfg.getNewWarBackup() + "/" + file.getName());
                while ((len = in.read(buff)) != -1) {
                    out.write(buff, 0, len);
                }
                out.flush();
                out.close();
                in.close();
                success++;
            }
            System.out.println("复制jar包完成 成功" + success + "个/共" + files.length + "个");
            File dir = new File(cfg.getNewWarBat());
            String[] cmd = {"cmd", "/c", "publish-test.bat"};
            Runtime.getRuntime().exec(cmd, null, dir);
        }
    }

    private void startXcServer(String serverPath, String binPath, String logFilePath, int index) throws IOException {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File catalinaLog = new File(serverPath + logFilePath + "/" + "catalina." + date + ".log");
        if (catalinaLog.exists()) {
            // 清空
            BufferedWriter writer = new BufferedWriter(new FileWriter(catalinaLog));
            writer.write("");
            writer.close();
        }
        File dir = new File(serverPath + binPath);
        String[] cmd = {"cmd", "/c", "startup.bat"};
        Runtime.getRuntime().exec(cmd, null, dir);

        FileMonitor m = null;
        try {
            CatalinalogListener listener = new CatalinalogListener(this, index);
            m = new FileMonitor(1000, serverPath + logFilePath, listener);
            listener.setMonitor(m);
        } catch (Exception e) {
            e.printStackTrace();
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

    private void copyOldSeverProperties() throws IOException {
        for (String name : warList) {
            warCgfVO warCgfVO = cfg.getAppServer().get(name);
            File file = new File(cfg.getServerPath() + "/" + warCgfVO.getServer() + warCgfVO.getWebApps() + "/" + name + "/WEB-INF/classes");
            File[] filess = file.listFiles();
            if (filess != null) {
                for (File f : filess) {
                    if (f.isFile()) {
                        boolean delete = f.delete();
                        System.out.println(f.getAbsolutePath() + " delete " + delete);
                    }

                }
                File srcFile = new File(
                        file.getAbsolutePath().replace(cfg.getServerPath(), backDir)
                );

                File[] files = srcFile.listFiles();
                if (files != null) {
                    for (File ff : files) {
                        if (ff.isFile()) {
                            FileInputStream in = new FileInputStream(ff);
                            FileOutputStream out = new FileOutputStream(ff.getAbsolutePath().replace(backDir, cfg.getServerPath()));
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

}

