package com.oa.core.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 处理图片 base64 编码的相互转换
 * <p>
 * Created by [张渊]
 * 2019/2/15 14:05
 */
public class Base64Util {

    /**
     * base64 图片前缀正则
     */
    public static final String BASE64_REGEX = "data:image/(png|jpeg|gif);base64,";

    private static final String BASE64_PREFIX = "data:image/png;base64,";


    /**
     * 将指定路径图片转换为带前缀，页面可识别的 base64 字符串
     * @param imageFilePath 图片地址
     * @return 页面识别的 base64 字符串
     */
    public static String getImageBase64(String imageFilePath) {
        return BASE64_PREFIX + ImageToBase64ByLocal(imageFilePath);
    }

    /**
     * 将本地图片转换成 base64 字符串
     *
     * @param imgFile 图片本地路径
     * @return 转换后的图片 base64 字符串
     */
    public static String ImageToBase64ByLocal(String imgFile) {

        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;

        try {
            is = new FileInputStream(imgFile);
            byte[] buf = new byte[1024];
            int len = 0;

            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(baos.toByteArray());// 返回Base64编码过的字节数组字符串
    }


    /**
     * 在线图片转换成base64字符串
     *
     * @param imageURL 图片线上路径
     * @return 转换后的 base64 字符串
     */
    public static String ImageToBase64ByOnline(String imageURL) {

        ByteArrayOutputStream data = new ByteArrayOutputStream();
        InputStream is = null;

        try {
            // 创建URL
            URL url = new URL(imageURL);
            byte[] buf = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            is = conn.getInputStream();

            // 将内容读取内存中
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                data.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();// 关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                data.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }


    /**
     * 对字节数组字符串进行 Base64 解码并生成图片
     *
     * @param imageBase64 图片 base64 字符串
     * @param saveDirPath 图片存储文件夹路径
     * @param fileName 图片存储名称
     * @return 转换后图片存储结果
     */
    public static boolean Base64ToImage(String imageBase64, String saveDirPath, String fileName) {

        File dirFile = new File(saveDirPath);
        if(!dirFile.exists()){
            boolean state = dirFile.mkdirs();
        }

        String imageSavePath = dirFile.getPath() + "/" + fileName;
        return Base64ToImage(imageBase64, imageSavePath);
    }


    /**
     * 对字节数组字符串进行 Base64 解码并生成图片
     *
     * @param imageBase64 图片 base64 字符串
     * @param imageSavePath 图片存放路径
     * @return 转换后图片存储结果
     */
    public static boolean Base64ToImage(String imageBase64, String imageSavePath) {

        // 图像数据为空
        if (StringUtil.isEmpty(imageSavePath)) {
            return false;
        }

        BASE64Decoder decoder = new BASE64Decoder();

        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imageBase64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imageSavePath);

            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
