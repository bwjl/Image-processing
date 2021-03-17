package com.bear.image.processing;

import com.bear.image.processing.util.ImageWaterMarkUtil;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/17 4:09 下午
 * @Description: 给图片添加图片水印
 */

public class ImageWaterDemo {

    private static final String ICON = "/Users/bear/Desktop/wxcs.png";

    private static final String IMAGE = "/Users/bear/Desktop/w2.jpg";

    private static final String MASK_IMAGE = "/Users/bear/Desktop/w2." + RandomStringUtils.randomNumeric(10) + ".jpg";


    public static void main(String[] args) {

        ImageWaterMarkUtil.markImageByIcon(ICON, IMAGE, MASK_IMAGE);

    }
}
