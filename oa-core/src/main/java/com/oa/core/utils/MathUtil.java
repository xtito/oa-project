package com.oa.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by [张渊]
 * 2018/7/23 16:30
 */
public class MathUtil {

    private static final int DEF_DIV_SCALE = 10;// 默认除法运算精度
    private static final int DEF_ROUND_SCALE = 2;// 默认四舍五入保留2位小数
    private static final Logger logger = LoggerFactory.getLogger(MathUtil.class);

    private MathUtil() {
    }


    /**
     * 加法算数运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两数之和
     */
    public static BigDecimal add(Object v1, Object v2) {
        if (v1 != null && v2 != null) {
            BigDecimal b1 = new BigDecimal(v1.toString());
            BigDecimal b2 = new BigDecimal(v2.toString());
            return b1.add(b2);
        }
        return new BigDecimal(0);
    }


    /**
     * 减法算数运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两数之差
     */
    public static BigDecimal sub(Object v1, Object v2) {
        if (v1 != null && v2 != null) {
            BigDecimal b1 = new BigDecimal(v1.toString());
            BigDecimal b2 = new BigDecimal(v2.toString());
            return b1.subtract(b2);
        }
        return new BigDecimal(0);
    }


    /**
     * 乘法算数运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两数的积
     */
    public static BigDecimal mul(Object v1, Object v2) {
        if (v1 != null && v2 != null) {
            BigDecimal b1 = new BigDecimal(v1.toString());
            BigDecimal b2 = new BigDecimal(v2.toString());
            return b1.multiply(b2);
        }
        return new BigDecimal(0);
    }


    /**
     * 乘法算数运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两数的积
     */
    public static BigDecimal mul(Object v1, Object v2, int scale) {
        return mul(v1, v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 除法算数运算
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点后10位，以后的数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两数的商
     */
    public static BigDecimal div(Object v1, Object v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }


    /**
     * 除法算数运算
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两数的商
     */
    public static BigDecimal div(Object v1, Object v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("除法运算精度不能小于0");
        }

        if (v1 != null && v2 != null) {
            BigDecimal b1 = new BigDecimal(v1.toString());
            BigDecimal b2 = new BigDecimal(v2.toString());
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
        }
        return new BigDecimal(0);
    }


    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(Object v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("除法运算精度不能小于0");
        }

        if (v != null) {
            BigDecimal b1 = new BigDecimal(v.toString());
            return b1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        }
        return new BigDecimal(0);
    }


    /**
     * 提供精确的小数位四舍五入处理，默认进行四五入并保留两位小数
     *
     * @param v     需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(Object v) {
        return round(v, DEF_ROUND_SCALE);
    }


    /**
     * 格式化数字，如果数字为空或为NULL 返回0
     *
     * @param v 要格式化的数字
     * @return 返回格式化后数字，如果要格式化的对象为空，返回0
     */
    public static BigDecimal formatNumber(Object v) {
        try {
            return new BigDecimal(v.toString());
        } catch (NumberFormatException e) {
            String str = v != null ? v.toString() : "空对象";
            logger.error(str + "转换数字失败！", e);
        }
        return new BigDecimal(0);
    }
}
