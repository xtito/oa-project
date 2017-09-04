package com.oa.core;

import com.oa.core.utils.StringUtil;
import java.security.MessageDigest;

/**
 * MD5方式加密
 */
public final class MD5 {

    private static final char HEXDIGITS[] = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 转换MD5
     */
    public static String encrypt(String string) {

        if (StringUtil.isNotNull(string)) {
            try {
                byte[] strTemp = string.getBytes();
                MessageDigest mdTemp = MessageDigest.getInstance("MD5");
                mdTemp.update(strTemp);
                byte[] md = mdTemp.digest();
                int j = md.length;
                char str[] = new char[j * 2];
                int k = 0;
                for (byte byte0 : md) {
                    str[k++] = HEXDIGITS[byte0 >>> 4 & 0xf];
                    str[k++] = HEXDIGITS[byte0 & 0xf];
                }
                return new String(str);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
