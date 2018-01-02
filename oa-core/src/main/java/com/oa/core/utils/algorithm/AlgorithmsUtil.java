package com.oa.core.utils.algorithm;

import com.oa.core.LoggerUtil;
import com.oa.core.utils.StringUtil;
import org.slf4j.Logger;

import java.security.MessageDigest;

/**
 * 加密算法工具类
 * <p/>
 * Created by [张渊]
 * 2017/12/15 12:00
 */
public class AlgorithmsUtil {

    private static Logger logger = LoggerUtil.getLogger(AlgorithmsUtil.class);

    private AlgorithmsUtil() {
    }

    /**
     * 转换MD5
     */
    public static String encryptMd5(String string) {
        char HexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
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
                    str[k++] = HexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = HexDigits[byte0 & 0xf];
                }
                return new String(str);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 转换MD5
     */
    public static String encrypt(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            logger.error(e.getMessage(), "MD5转换异常！message：%s", e);
        }

        StringBuilder md5StrBuff = new StringBuilder();
        if (messageDigest != null) {
            byte[] byteArray = messageDigest.digest();
            if (byteArray != null && byteArray.length > 0) {
                for (byte aByteArray : byteArray) {
                    if (Integer.toHexString(0xFF & aByteArray).length() == 1) {
                        md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
                    } else {
                        md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
                    }
                }
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * 使用MD5加密算法进行加密，并在加密前指定盐值
     *
     * @param str  要加密的字符串
     * @param salt 盐值
     * @return 加密后的字符，如果 str 或 salt为null 则返回 null
     */
    public static String encrypt(Object str, Object salt) {
        if (str != null && salt != null) {
            return encrypt(String.format("%s#%s", String.valueOf(str), String.valueOf(salt)));
        }
        return null;
    }

}
