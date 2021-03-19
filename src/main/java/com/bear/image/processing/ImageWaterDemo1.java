package com.bear.image.processing;

import com.bear.image.processing.util.ImageUtil;
import com.bear.image.processing.util.ImageWaterMarkUtil;
import com.bear.image.processing.util.WaterMarkUtil;
import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/17 4:09 下午
 * @Description: 给图片添加图片水印
 */

public class ImageWaterDemo1 {

    private static final String IMAGE = "/Users/bear/Desktop/5.jpeg";

    private static final String ICON = "/Users/bear/Desktop/wxcs.png";

    private static final String MASK_IMAGE = "/Users/bear/Desktop/w2." + RandomStringUtils.randomNumeric(10) + ".jpg";


    public static void main(String[] args) throws IOException {

        InputStream srcImageInputStream = new FileInputStream(IMAGE);
        //System.out.println(ImageUtil.getRealFormat(srcImageInputStream));
        WaterMarkUtil waterMarkUtil = new WaterMarkUtil(srcImageInputStream, ICON, MASK_IMAGE);
        waterMarkUtil.markImageByIcon();

    }
}
