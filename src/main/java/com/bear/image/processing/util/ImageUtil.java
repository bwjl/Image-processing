package com.bear.image.processing.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bear
 * @Date: 2021/3/18 11:01 上午
 * @Description: 图片工具类
 */

public class ImageUtil {

    /**
     * 获取图片真实格式
     *
     * @return
     */
    public static String getRealFormat(InputStream inputStream) {
        //读取文件的前几个字节来判断图片格式
        byte[] b = new byte[4];
        try {
            inputStream.read(b, 0, b.length);
            //String type = HexUtil.byteToHexStr(b).toUpperCase();
            String type = bytesToHexString(b).toUpperCase();
            if (type.contains("FFD8FF")) {
                return "jpg";
            } else if (type.contains("89504E47")) {
                return "png";
            } else if (type.contains("47494638")) {
                return "gif";
            } else if (type.contains("424D")) {
                return "bmp";
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //不关闭inputStream
//            if(inputStream != null){
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        return null;
    }

    /**
     * Convert byte[] to hex string
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


}
