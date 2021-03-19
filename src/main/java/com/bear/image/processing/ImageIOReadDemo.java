package com.bear.image.processing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/19 5:02 下午
 * @Description:
 */

public class ImageIOReadDemo {

    private static final String IMAGE = "/Users/bear/Desktop/w2.jpg";

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(IMAGE);
        BufferedImage bufferedImage = ImageIO.read(inputStream);
    }
}
