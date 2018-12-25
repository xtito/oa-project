package com.oa.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.oa.core.LoggerUtil;

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

    static final Class<?> CLAZZ = SerializeUtil.class;

    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
            LoggerUtil.error(CLAZZ, "serialize error %s", JSONObject.parse(value.toString()), e);
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }


    public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }

    @SafeVarargs
    public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
            }
        } catch (Exception e) {
            LoggerUtil.error(CLAZZ, "serialize error %s", Arrays.toString(in), e);
        } finally {
            close(is);
            close(bis);
        }
        return (T) rv;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                LoggerUtil.error(CLAZZ, "close stream error");
            }
        }
    }
}
