package com.bear.image.processing;

import com.bear.image.processing.util.MultipleItalicWatermarksUtil;
import lombok.Cleanup;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/15 10:07 下午
 * @Description: 多个斜体水印
 */

public class MultipleItalicWatermarksDemo {

    private static final String IMAGE = "/Users/bear/Desktop/1.3.jpeg";
    private static final String MASK_IMAGE = "/Users/bear/Desktop/1.3." + RandomStringUtils.randomNumeric(4) + ".png";

    @SneakyThrows
    public static void main(String[] args) {

        @Cleanup
        //FileInputStream fileInputStream = FileUtils.openInputStream(Paths.get(IMAGE).toFile());
        FileInputStream fileInputStream = FileUtils.openInputStream(new File(IMAGE));
        @Cleanup
        //FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(MASK_IMAGE).toFile());
        FileOutputStream fileOutputStream = new FileOutputStream(new File(MASK_IMAGE));

        MultipleItalicWatermarksUtil.of(fileInputStream)
                .to(fileOutputStream)
                .color(Color.BLACK)
                .interval(180)
                .rotate(330)
                .imageType("png")
                .doMask("仅供房贷所用");

        fileOutputStream.flush();

    }
}
