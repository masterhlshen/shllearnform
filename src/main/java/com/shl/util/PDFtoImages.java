package com.shl.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFtoImages {


    /**
     * 转换单页pdf
     *
     * @param filename PDF文件名
     */
    public static String pdftoPng(String filename) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(filename);
        String fileName = file.getName();
        int dot = fileName.lastIndexOf('.');
        if ((dot > -1) && (dot < (fileName.length() - 1))) {
            filename = fileName.substring(0, dot);
        }
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            BufferedImage image = renderer.renderImageWithDPI(0, 124);
            //为了方便，暂时写死
            File imageFile = new File("E:\\pdftoimges\\" + filename + ".png");
            ImageIO.write(image, "png", imageFile);

            return imageFile.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * pdf转图片
     */
    public static void pdftoimage(String filepath) {
        File file = new File(filepath);
        File dir = new File("E:\\pdftoimges\\" + file.getName());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                int size = i + 1;
                //          BufferedImage image = renderer.renderImage(i, 2.5f);
                ImageIO.write(image, "PNG", new File(dir.getAbsolutePath() + "\\" + "第" + size + "页.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

