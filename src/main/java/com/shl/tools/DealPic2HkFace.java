package com.shl.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 海康人脸识别图片处理工具
 *
 * @author shl
 */
public final class DealPic2HkFace {

    public static void main(String[] args) throws IllegalAccessException, IOException, InterruptedException {

        String imagePath = "D:/abc/f";
        String trans = imagePath + "/trans";
        String resPath = trans + "/res";

        File resPathFile = new File(resPath);
        if (!resPathFile.exists()) {
            resPathFile.mkdirs();
        }
        File imageDirFile = new File(imagePath);
        File[] imageFileArr = imageDirFile.listFiles();


        for (File imageFile : imageFileArr) {
            if (imageFile.isDirectory()) {
                continue;
            }
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            String[] propertyNames = bufferedImage.getPropertyNames();


            // 获取图片的宽度和高度
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width > height) {
                String a = imageFile.getAbsolutePath();
                String name = imageFile.getName();
                String b = trans + "/" + name;
                // 宽高反向，并且去掉图片的相关无用信息
                String format = String.format("D:/ImageMagick-7.1.1-Q16-HDRI/magick.exe convert +profile \"*\" -resize %s %s %s", height + "x" + width, a, b);
                Process exec = Runtime.getRuntime().exec(format);

                exec.waitFor();

                String c = resPath + "/" + name;
                // 更改宽高
                format = String.format("D:/ImageMagick-7.1.1-Q16-HDRI/magick.exe convert -resize 500x500> -rotate %s %s %s", 90, b, c);
                Process exec1 = Runtime.getRuntime().exec(format);

                exec1.waitFor();


                File tmpFile = new File(b);
                if (tmpFile.exists()) {
                    boolean db = tmpFile.delete();
                    System.out.println(">>>>>>>>>>>删除临时文件" + name + " 结果 " + db);
                }
            }
            System.out.println("width = " + width + " height = " + height);

        }
    }

    static void tansIllgal() throws IOException, InterruptedException {
        String a = "D:/abc/a.jpg";
        String b = "D:/abc/b.jpg";
        String format = String.format("D:/ImageMagick-7.1.1-Q16-HDRI/magick.exe convert -resize %s %s %s", "960x1280", a, b);
        Process exec = Runtime.getRuntime().exec(format);

        exec.waitFor();

        String c = "D:/abc/c.jpg";
        format = String.format("D:/ImageMagick-7.1.1-Q16-HDRI/magick.exe convert -rotate %s %s %s", 90, b, c);
        Process exec1 = Runtime.getRuntime().exec(format);

        exec1.waitFor();
    }


    static void rename(File s, File d) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        in = new FileInputStream(s);
        out = new FileOutputStream(d);
        int len = -1;
        byte[] buff = new byte[1024];
        while ((len = in.read(buff)) != -1) {
            out.write(buff, 0, len);
        }
        out.flush();
        in.close();
        out.close();
    }
}
