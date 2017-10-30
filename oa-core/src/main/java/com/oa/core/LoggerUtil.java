package com.oa.core;

import com.oa.core.utils.StringUtil;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    /**
     * 是否开启Debug
     */
    public static boolean isDebug = Logger.getLogger(LoggerUtil.class).isDebugEnabled();

    public static org.slf4j.Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static void info(Class<?> clazz, String msg) {
        getLogger(clazz).info(msg);
    }

    public static void info(Class<?> clazz, String msg, Throwable ex) {
        getLogger(clazz).info(msg, ex);
    }

    public static void info(Class<?> clazz, String msg, Object param) {
        getLogger(clazz).info(msg, param);
    }

    public static void info(Class<?> clazz, String msg, Object param, Throwable ex) {
        getLogger(clazz).info(msg, param, ex);
    }

    public static void info(Class<?> clazz, String msg, Object[] param) {
        getLogger(clazz).info(msg, param);
    }

    public static void info(Class<?> clazz, String msg, Object[] param, Throwable ex) {
        getLogger(clazz).info(msg, param, ex);
    }


    /**
     * Debug 输出
     *
     * @param clazz   目标.Class
     * @param msg 输出信息
     */
    public static void debug(Class<?> clazz, String msg) {
        if (!isDebug) return;
        getLogger(clazz).debug(msg);
    }

    public static void debug(Class<?> clazz, String msg, Throwable ex) {
        if (!isDebug) return;
        getLogger(clazz).debug(msg, ex);
    }

    /**
     * Debug 输出
     *
     * @param clazz     目标.Class
     * @param fmtString 输出信息key
     * @param value     输出信息value
     */
    public static void fmtDebug(Class<?> clazz, String fmtString, Object... value) {
        if (!isDebug) return;
        if (StringUtil.isBlank(fmtString)) {
            return;
        }
        if (null != value && value.length != 0) {
            fmtString = String.format(fmtString, value);
        }
        debug(clazz, fmtString);
    }

    public static void debug(Class<?> clazz, String msg, Object param) {
        getLogger(clazz).debug(msg, param);
    }

    public static void debug(Class<?> clazz, String msg, Object param, Throwable ex) {
        getLogger(clazz).debug(msg, param, ex);
    }

    public static void debug(Class<?> clazz, String msg, Object[] param) {
        getLogger(clazz).debug(msg, param);
    }

    public static void debug(Class<?> clazz, String msg, Object[] param, Throwable ex) {
        getLogger(clazz).debug(msg, param, ex);
    }


    public static void warn(Class<?> clazz, String msg) {
        getLogger(clazz).warn(msg);
    }

    public static void warn(Class<?> clazz, String msg, Throwable ex) {
        getLogger(clazz).warn(msg, ex);
    }

    public static void warn(Class<?> clazz, String msg, Object param) {
        getLogger(clazz).warn(msg, param);
    }

    public static void warn(Class<?> clazz, String msg, Object param, Throwable ex) {
        getLogger(clazz).warn(msg, param, ex);
    }

    public static void warn(Class<?> clazz, String msg, Object[] param) {
        getLogger(clazz).warn(msg, param);
    }

    public static void warn(Class<?> clazz, String msg, Object[] param, Throwable ex) {
        getLogger(clazz).warn(msg, param, ex);
    }



    public static void error(Class<?> clazz, String msg) {
        getLogger(clazz).error(msg);
    }

    /**
     * Error 输出
     *
     * @param clazz   目标.Class
     * @param message 输出信息
     * @param ex      异常信息
     */
    public static void error(Class<?> clazz, String msg, Throwable ex) {
        getLogger(clazz).error(msg, ex);
    }

    public static void error(Class<?> clazz, String msg, Object param) {
        getLogger(clazz).error(msg, param);
    }

    public static void error(Class<?> clazz, String msg, Object param, Throwable ex) {
        getLogger(clazz).error(msg, param, ex);
    }

    public static void error(Class<?> clazz, String msg, Object[] param) {
        getLogger(clazz).error(msg, param);
    }

    public static void error(Class<?> clazz, String msg, Object[] param, Throwable ex) {
        getLogger(clazz).error(msg, param, ex);
    }

    /**
     * 异常填充值输出
     *
     * @param clazz     目标.Class
     * @param fmtString 输出信息key
     * @param value     输出信息value
     */
    public static void fmtError(Class<?> clazz, String fmtString, Object... value) {
        if (StringUtil.isBlank(fmtString)) {
            return;
        }
        if (null != value && value.length != 0) {
            fmtString = String.format(fmtString, value);
        }
        error(clazz, fmtString);
    }

    /**
     * 异常填充值输出
     *
     * @param clazz     目标.Class
     * @param fmtString 输出信息key
     * @param e         异常类
     * @param value     输出信息value
     */
    public static void fmtError(Class<?> clazz, String fmtString, Throwable e, Object... value) {
        if (StringUtil.isBlank(fmtString)) {
            return;
        }
        if (null != value && value.length != 0) {
            fmtString = String.format(fmtString, value);
        }
        error(clazz, fmtString, e);
    }


}
