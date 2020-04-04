package com.oa.core.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

/**
 * 序列化工具类
 * Java原生版的 Serialize
 * <p/>
 * Created by [张渊]
 * 2017/10/29 22:22
 */
public class SerializeUtil {

    private final static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    /**
     * 序列化
     */
    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }

        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            bytes = bos.toByteArray();
        } catch (Exception e) {
            logger.error("serialize error %s", JSONObject.parse(value.toString()), e);
        } finally {
            close(bos);
            close(oos);
        }
        return bytes;
    }


    /**
     * 反序列化
     */
    public static Object unSerialize(byte[] bytes) {

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;

        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            logger.error("serialize error %s", Arrays.toString(bytes), e);
        } finally {
            close(bais);
            close(ois);
        }
        return null;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error("close stream error");
            }
        }
    }
}
