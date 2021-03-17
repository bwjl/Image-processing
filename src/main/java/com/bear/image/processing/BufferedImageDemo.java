package com.bear.image.processing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/17 3:07 下午
 * @Description: BufferedImage getHeight getWidth
 */

public class BufferedImageDemo {

    private static final String IMAGE = "/Users/bear/Desktop/1.3.jpeg";

    public static void main(String[] args) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new File(IMAGE));
        //获取高度
        System.out.println(bufferedImage.getHeight());
        //获取宽度
        System.out.println(bufferedImage.getWidth());
        //System.out.println(bufferedImage.getType());


    }
}
