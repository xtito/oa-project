package com.oa.core.utils;

import com.oa.core.constant.RegexConstant;
import com.oa.core.utils.date.DateUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 字符串操作相关
 * <p/>
 * Created by [张渊]
 * 2017/8/10 11:01
 */
public class StringUtil {

    /**
     * 比较字符串大小 2014年4月2日 19:07:56
     * 主要用于日期。
     */
    public static boolean compareString(String str1, String str2) {
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();

        int minLen = c1.length > c2.length ? c2.length : c1.length;

        for (int i = 0; i < minLen; i++) {
            if (c1[i] > c2[i])
                return true;
            else if (c1[i] < c2[i])
                return false;
        }
        // 长度短的大
        return c1.length < c2.length;
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.toString().trim().equals("");
    }

    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }

    public static String toStringNum(Object obj) {
        String value = toString(obj);
        return isEmpty(value) ? "0" : value;
    }

    /**
     * obj 对象转换为String
     */
    public static String toString(Object obj) {
        return valueOf(obj);
    }

    /**
     * 把对象转换为String，如果为日期，转换为yyyy-MM-dd HH:mm:ss的结构
     */
    public static String valueOf(Object obj) {
        if (obj == null)
            return "";

        if (obj instanceof Date)
            return DateUtil.format((Date) obj);

        return obj.toString();
    }

    /**
     * 比较两个字符串是否相等
     */
    public static boolean compare(String str1, String str2) {
        return !(isEmpty(str1) || isEmpty(str2)) && str1.equals(str2);
    }

    public static boolean compareIgnoreCase(String str1, String str2) {
        return !(isEmpty(str1) || isEmpty(str2)) && str1.equalsIgnoreCase(str2);
    }

    /**
     * 判断字符串是否不为空
     */
    public static boolean isNotNull(Object str) {
        return null != str && !"".equals(str.toString().trim());
    }

    public static boolean isNotNull(Object[] obj) {
        return obj != null && obj.length > 0;
    }

    /**
     * 去除所有空格
     */
    public static String trimAllSpace(String str) {
        if (!isEmpty(str)) {
            str = str.replaceAll(" +", "");
        }
        return str;
    }

    public static int count(String str, char c) {

        if (isEmpty(str)) return 0;

        int count = 0;

        char[] cs = str.toCharArray();

        for (char ch : cs)

            if (ch == c)
                count++;

        return count;
    }


    /**
     * String array covert to string
     * Example ["A", "B", "C"]  covert to A,B,C
     */
    public static String arrayToString(Object[] array) {
        if (array == null || array.length == 0) {
            return "";
        }

        StringBuilder str = new StringBuilder();
        for (Object s : array) {
            str.append(",").append(s);
        }
        return isEmpty(str.toString()) ? str.toString() : str.substring(1);
    }


    /**
     * 按英文分号分隔成in里面的格式
     *
     * @param str ：a,b,c
     * @return 'a','b','c'
     */
    public static String strToIn(String str) {
        if (isNotNull(str)) {
            String[] temp = str.split(",");
            if (temp.length > 0) {
                StringBuilder strBuilder = new StringBuilder();
                for (String aTemp : temp) {
                    strBuilder.append("'").append(aTemp).append("',");
                }
                str = strBuilder.toString();
                str = str.substring(0, str.lastIndexOf(","));
            }
            return str;
        }
        return "";
    }

    /**
     * sqlServer like特殊字符
     */
    public static String likeForSqlServer(String string) {
        string = string.replace("[", "[[]");
        string = string.replace("%", "[%]");
        string = string.replace("_", "[_]");
        string = string.replace("^", "[^]");
        return string;
    }


    /**
     * 去除字符串前后空格
     *
     * @param object 要去除空格的字符串
     * @return 去除空格后的字符串
     */
    public static String removeTrim(Object object) {
        String str = null;
        if (isNotNull(object)) {
            str = object.toString().trim();
        }
        return str;
    }


    /**
     * 判断多个字符串都不为空或者NULL
     * <p/>
     * 只要有一个为NULL或空都返回true，所有字符都不为空返回false
     *
     * @param str 要判断的多个字符串
     * @return 只要有一个为空或Null则返回true
     */
    public static boolean isNullBatch(Object... str) {

        boolean isNull = false;

        for (Object s : str) {
            if (isEmpty(s)) {
                isNull = true;
                break;
            }
        }

        return isNull;

    }


    /**
     * 通过反射给实体类的String字符串字段去除空格
     *
     * @param object 要去空格实体类对象
     * @return 去除空格后的实体类对象
     * @throws IllegalAccessException    安全权限异常
     * @throws InvocationTargetException 执行反射时发生的异常
     * @throws NoSuchMethodException     没有找到方法异常
     */
    public static Object removeBlank(Object object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        if (object != null) {
            // 获取字节码文件
            Class<?> clazz = object.getClass();

            // 获取所有公共方法
            Method[] methods = clazz.getMethods();

            if (methods != null && methods.length > 0) {
                // 遍历获取get方法，取得字段值
                for (Method method : methods) {
                    // 过滤，只处理返回值为String类型的方法
                    if (method.getName().startsWith("get") && String.class.equals(method.getReturnType())) {

                        // 获得字段的值
                        String tempFieldValue = (String) method.invoke(object);

                        // 如果字段的值不为Null或者空
                        if (tempFieldValue != null && !"".equals(tempFieldValue)) {
                            // 通过get方法名，取出字段名
                            String name = method.getName().substring(3);
                            Method m = clazz.getMethod("set" + name, String.class);
                            m.invoke(object, tempFieldValue.trim());
                        }
                    }
                }
            }
        }
        return object;
    }


    /**
     * 截取年份最后两位
     *
     * @param yearStr 要截取的年份
     * @return 截取后的年份
     */
    public static Integer substringYear(String yearStr) {

        Integer year = null;

        try {

            if (yearStr.length() >= 4 && yearStr.matches(RegexConstant.ID_NUM)) {
                year = Integer.parseInt(yearStr.substring(2, 4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return year;
    }


    /**
     * 计算百份比，返回字符串，默认不保留小数
     *
     * @param cs 被除数
     * @param zs 总数
     * @param xs 保留几位小数, 如果为 NULL 默认不保留小数
     * @return 百分比值
     */
    public static String parsBfb(Integer cs, Integer zs, Integer... xs) {

        StringBuilder bfb = new StringBuilder("0");
        Integer defaultXs = 0;// 默认保留0位小数

        try {

            if (xs.length > 0) {
                // 由于 xs 只是为了判断保留几位小数的，所以忽略可变参数的其他值，只取下标0的数值为小数位数
                defaultXs = xs[0];
            }

            if (cs != null && zs != null && cs > 0 && zs > 0) {

                Double bcs = Double.valueOf(cs.toString());// 将被除数转为double型
                Double sum = Double.valueOf(zs.toString());// 将总数转为double型

                // 计算百分比
                Double tempResult = (bcs / sum) * 100;

                BigDecimal format = new BigDecimal(tempResult);

                // 四舍五入转换小数
                BigDecimal result = format.setScale(defaultXs, BigDecimal.ROUND_HALF_UP);

                if (defaultXs > 0) {
                    bfb = new StringBuilder(result.toString() + "%");
                } else {
                    // 四舍五入转换整数
                    bfb = new StringBuilder(result.intValue() + "%");
                }
            } else {
                for (int i = 0; i < defaultXs; i++) {
                    if (i == 0) {
                        bfb.append(".0");
                    } else {
                        bfb.append("0");
                    }
                }
                bfb.append("%");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return bfb.toString();
    }


    /**
     * 判断是否包含指定字符串
     *
     * @param seq       原字符串
     * @param searchSeq 是否包含的字符串
     * @return 包含返回 true，不包含返回 false
     */
    public static boolean contains(Object seq, Object searchSeq) {
        return isNotNull(seq) && isNotNull(searchSeq) && seq.toString().contains(searchSeq.toString());
    }

    /**
     * 一次性判断多个或单个对象为空或null，只要有一个为空或null返回true。
     *
     * @param objects 要判断的对象
     * @return 只要有一个元素为Blank，则返回true
     */
    public static boolean isBlank(Object... objects) {
        Boolean result = false;
        if (isNotNull(objects)) {
            for (Object object : objects) {
                if (isEmpty(object) || "null".equals(object.toString().trim())) {
                    result = true;
                    break;
                }
            }
        } else {
            result = true;
        }
        return result;
    }

    /**
     * 判断是否为数字并强制转换，如果不是则返回0
     */
    public static int string2Int(Object value) {
        try {
            if (isNotNull(value)) {
                return Integer.parseInt(value.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将字符转换为数字
     * @param object 待转换对象
     * @return 转换后的数字
     */
    public static Integer parseInteger(Object object) {
        try {
            if (StringUtil.isNotNull(object)) {
                return Integer.parseInt(object.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符转换为长整型数字
     * @param object 待转换对象
     * @return 转换后的长整型数字
     */
    public static Long parseLong(Object object) {
        try {
            if (StringUtil.isNotNull(object)) {
                return Long.valueOf(object.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取四舍五入
     *
     * @param num 要转换的数
     * @param xs  保留几位小数，默认不保留
     * @return 转换后的数转为字符串
     */
    public static String parseDouble(Double num, int... xs) {

        String str = "0";
        int defaultXs = 0;// 默认保留0位小数
        if (num != null) {
            if (xs.length > 0) {
                defaultXs = xs[0];
            }

            BigDecimal format = new BigDecimal(num);

            // 四舍五入转换小数
            BigDecimal result = format.setScale(defaultXs, BigDecimal.ROUND_HALF_UP);

            str = result.toString();
        }

        return str;
    }

    /**
     * 四舍五入取整
     */
    public static int getInteger(Double score) {
        if (score != null) {
            BigDecimal itg = new BigDecimal(score).setScale(0, BigDecimal.ROUND_HALF_UP);
            return itg.intValue();
        }
        return 0;
    }
}
