package com.shl.java基础.反射;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReflectDemo {

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

            // 获取图片的宽度和高度
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width > height) {
                String a = imageFile.getAbsolutePath();
                String name = imageFile.getName();
                String b = trans + "/" + name;
                String format = String.format("D:/ImageMagick-7.1.1-Q16-HDRI/magick.exe convert -resize %s %s %s",  "500x500", a, b);
                Process exec = Runtime.getRuntime().exec(format);

                exec.waitFor();

                String c = resPath + "/" + name;
                format = String.format("D:/ImageMagick-7.1.1-Q16-HDRI/magick.exe convert -rotate %s %s %s", 90, b, c);
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


    static class ImageCompress {

        public static void compressImage(File src, File dest, String suffix, int kb) throws IOException {
            BufferedImage image = ImageIO.read(src);
            long imageSize = src.length();
            double quality = (1024.0 * kb) / imageSize;
            ImageIO.write(compress(image, quality), suffix, dest);
        }

        private static BufferedImage compress(BufferedImage image, double quality) {
            Image scaledImage = image.getScaledInstance((int) (quality * image.getWidth()), (int) (quality * image.getHeight()), Image.SCALE_SMOOTH);
            BufferedImage bufferedImage = new BufferedImage((int) (quality * image.getWidth()), (int) (quality * image.getHeight()), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bufferedImage.createGraphics();
            graphics.drawImage(scaledImage, 0, 0, null);
            graphics.dispose();
            return bufferedImage;
        }
    }

}
