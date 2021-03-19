package com.bear.image.processing.util;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/17 11:11 下午
 * @Description: 水印工具类
 */

public class WaterMarkUtil {

    /**
     * 最小添加图片水印大小倍数 multiple
     */
    private static final int MIN_MULTIPLE = 4;
    /**
     * 原水印地址
     */
    private final String iconImagePath;
    private final InputStream iconImageInputStream;
    private final String targetPath;
    /**
     * 水印透明度
     */
    private float alpha = 1f;
    /**
     * 水印icon File对象
     */
    private File iconFile;
    /**
     * 原图片File对象
     */
    private File srcFile;
    /**
     * 度数
     */
    private Integer degree = null;
    /**
     * 原图片InputStream
     */
    private InputStream srcImageInputStream;


    /**
     * @param srcImageInputStream
     * @param iconImagePath
     * @param targetPath
     * @throws IOException
     */
    public WaterMarkUtil(InputStream srcImageInputStream, String iconImagePath, String targetPath) throws IOException {

        //this.srcImagePath = srcImagePath;
        this.srcImageInputStream = srcImageInputStream;
        this.iconImagePath = iconImagePath;
        this.targetPath = targetPath;
        this.iconFile = new File(iconImagePath);
        //this.srcImageInputStream = FileUtils.openInputStream(srcFile);
        this.iconImageInputStream = FileUtils.openInputStream(iconFile);

    }

    /**
     * 添加图片水印条件
     * 添加图片水印条件 至少水印图片的四倍大小
     *
     * @return
     */
    public boolean condition() throws IOException {

        //return srcImageInputStream.available() >= MIN_MULTIPLE * iconFile.length();
        return true;
    }

    /**
     * @return
     */
    public String markImageByIcon() {

        OutputStream os = null;
        try {

            //不满足图片加水印条件
            if (!condition()) {
                return null;
            }

            Image srcImg = ImageIO.read(srcImageInputStream);

            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 1、得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            // 2、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
            // 3、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }

            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconImagePath);

            // 5、得到Image对象。
            Image img = imgIcon.getImage();

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));

            // 6、水印图片的位置

            //右下角位置设置

            g.drawImage(img, buffImg.getWidth() - imgIcon.getIconWidth(), buffImg.getHeight() - imgIcon.getIconHeight(), null);
            //g.drawImage(img, positionWidth, positionHeight, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            // 7、释放资源
            g.dispose();

            // 8、生成图片
            os = new FileOutputStream(targetPath);
            //获取生成图片的真实格式
            //String formatName = srcImgPath.substring(srcImgPath.lastIndexOf(".") + 1);
            //String formatName = ImageUtil.getRealFormat(srcImageInputStream);
            ImageIO.write(buffImg, "jpg", os);

            System.out.println("图片完成添加水印图片");
            return targetPath;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
