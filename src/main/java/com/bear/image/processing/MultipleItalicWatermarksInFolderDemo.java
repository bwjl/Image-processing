package com.bear.image.processing;

import com.bear.image.processing.util.FileUtil;
import com.bear.image.processing.util.MultipleItalicWatermarksUtil;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/15 10:07 下午
 * @Description: 遍历文件夹文件，生成多个斜体水印
 */

public class MultipleItalicWatermarksInFolderDemo {

    private static final String FOLDER_NAME = "/Users/bear/Desktop/gedian/20210623";
    private static final String[] ALLOWED_FILE_EXT = {"jpg", "jpeg", "png"};

    private static final Logger logger = LoggerFactory.getLogger(MultipleItalicWatermarksInFolderDemo.class);

    /**
     * 水印文字
     */
    private static final String WATERMARK_TEXT = "公众号：葛店在线";

    @SneakyThrows
    public static void main(String[] args) {

        File folder = new File(FOLDER_NAME);
        File[] files = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            if (files[i].isFile()) {
                //是文件
                String fileName = files[i].getName();
                System.out.println(fileName);

                String[] fileArr = fileName.split("\\.");

                //允许的后缀
                String newName = "";
                if (Arrays.asList(ALLOWED_FILE_EXT).contains(fileArr[fileArr.length - 1])) {
                    newName = fileName.substring(0, fileName.lastIndexOf(".")) + "-1"
                            + fileName.substring(fileName.lastIndexOf("."), fileName.length());
                    System.out.println(newName);
                } else {
                    logger.info("不合法文件名：{}", newName);
                    continue;
                }

                @Cleanup
                FileInputStream fileInputStream = FileUtils.openInputStream(new File(FOLDER_NAME + "/" + fileName));

                FileUtil.generateFileDir(FOLDER_NAME + "_watermark/" + newName);

                @Cleanup
                FileOutputStream fileOutputStream = new FileOutputStream(FOLDER_NAME + "_watermark/" + newName);


                MultipleItalicWatermarksUtil.of(fileInputStream)
                        .to(fileOutputStream)
                        .color(Color.BLACK)
                        //.interval(180)
                        .interval(360)
                        .rotate(330)
                        .imageType("png")
                        .doMask(WATERMARK_TEXT);

                fileOutputStream.flush();
            }
        }


    }
}
