package com.bear.image.processing.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/6/10 5:04 下午
 * @Description: 文件工具类
 */

public class FileUtil {

    public static String generateFilePath() {
        return new SimpleDateFormat("yyyyMM/dd/").format(new Date());
    }

    /**
     * 文件名称生成
     *
     * @param ext        文件后缀
     * @param originName
     * @return
     */
    public static String generateFileName(String ext, String originName) {
        return UUID.randomUUID().toString().replace("-", "")
                + (originName.isEmpty() ? "" : "_" + originName) + "." + ext;
    }

    public static String generateFileName(String ext) {
        return generateFileName(ext, "");
    }

    /**
     * 创建文件夹目录
     *
     * @param fileName
     */
    public static void generateFileDir(String fileName) {
        File file = new File(fileName);
        File fileDir = file.getParentFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
    }
}
