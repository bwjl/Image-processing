package com.bear.image.processing.util;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/15 10:07 下午
 * @Description: 多个斜体水印
 */

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 图像加水印工具类
 *
 * @author HanQi [Jpanda@aliyun.com]
 * @version 1.0
 * @since 2020/3/17 9:44
 */
@Accessors(chain = true, fluent = true)
public class MultipleItalicWatermarksUtil {

    private BufferedImage bi;
    private Graphics2D g2;
    private OutputStream out;

    /**
     * 文字颜色
     */
    @Setter
    private Color color = Color.WHITE;
    /**
     * 文字字体
     */
    @Setter
    private Font font = new Font("simhei", Font.BOLD, 30);
    /**
     * 水印间距
     */
    @Setter
    private Integer interval = 160;

    /**
     * 旋转度数
     */
    @Setter
    private Integer rotate = 45;

    /**
     * 图片类型
     */
    @Setter
    private String imageType = "JPEG";

    /**
     * 允许定制Graphics2D
     */
    @Setter
    private Composite composite = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3F);

    @SneakyThrows
    private MultipleItalicWatermarksUtil(InputStream image) {
        this.bi = ImageIO.read(image);
        this.g2 = bi.createGraphics();
    }

    public static MultipleItalicWatermarksUtil of(InputStream image) {
        return new MultipleItalicWatermarksUtil(image);
    }

    public MultipleItalicWatermarksUtil to(OutputStream outputStream) {
        this.out = outputStream;
        return this;
    }


    @SneakyThrows
    public void doMask(String mask) {
        // 文字旋转
        g2.setColor(color);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(rotate), 0, 0);
        g2.setFont(font.deriveFont(affineTransform));

        g2.setComposite(composite);

        // 文字宽度
        int fw = g2.getFontMetrics().stringWidth(mask);
        // 文本高度
        int fh = g2.getFontMetrics().getHeight();

        // 计算增量
        int xInc = fw + interval;
        int yInc = fh + interval;

        // 添加水印
        int y = 0;
        do {
            int x = 0;
            do {
                g2.drawString(mask, x, y);
                x += xInc;
            } while (x < bi.getWidth());
            y += yInc;
        } while (y < bi.getHeight());

        g2.dispose();

        if (this.out != null) {
            ImageIO.write(bi, imageType, out);
        }
    }

}
