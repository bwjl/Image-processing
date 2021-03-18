package com.bear.image.processing;

import com.bear.image.processing.util.ImageUtil;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/18 11:17 上午
 * @Description: 图片格式判断
 */

public class ImageFormatDemo {

    private static final String srcImage = "/Users/bear/Desktop/3.png";

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(srcImage);
        System.out.println(ImageUtil.getRealFormat(inputStream));
        inputStream.close();
    }
}
