package com.oa.core;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LoggerUtil {

    // 缓存
    private static Map<String, org.slf4j.Logger> loggerMap = new HashMap<String, org.slf4j.Logger>();

    /**
     * debug级日志输出
     */
    public static void debug(Class<?> clazz, String msg) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isDebugEnabled()) {
            log.debug(msg);
        }
    }

    public static void debug(Class<?> clazz, String msg, Throwable e) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isDebugEnabled()) {
            log.debug(msg, e);
        }
    }

    public static void debug(Class<?> clazz, String msg, Object arg) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isDebugEnabled()) {
            log.debug(msg, arg);
        }
    }

    public static void debug(Class<?> clazz, String msg, Object arg, Throwable e) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isDebugEnabled()) {
            log.debug(msg, arg, e);
        }
    }

    public static void debug(Class<?> clazz, String msg, Object[] arg) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isDebugEnabled()) {
            log.debug(msg, arg);
        }
    }

    public static void debugTag(Class<?> clazz, String tag, Object msg) {
        debug(clazz, "【" + tag + msg + "】");
    }

    public static void debugTag(Class<?> clazz, String tag, String msg, Throwable e) {
        debug(clazz, "【" + tag + msg + "】", e);
    }

    public static void debugTag(Class<?> clazz, String tag, String msg, Object arg, Throwable e) {
        debug(clazz, "【" + tag + msg + "】", arg, e);
    }


    /**
     * info级日志输出
     */
    public static void info(Class<?> clazz, String msg) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isInfoEnabled()) {
            log.info(msg);
        }
    }

    public static void info(Class<?> clazz, String msg, Throwable e) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isInfoEnabled()) {
            log.info(msg, e);
        }
    }

    public static void info(Class<?> clazz, String msg, Object arg) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isInfoEnabled()) {
            log.info(msg, arg);
        }
    }

    public static void info(Class<?> clazz, String msg, Object arg, Throwable e) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isInfoEnabled()) {
            log.info(msg, arg, e);
        }
    }

    public static void info(Class<?> clazz, String msg, Object[] arg) {
        org.slf4j.Logger log = getLogger(clazz.getName());
        if (log.isInfoEnabled()) {
            log.info(msg, arg);
        }
    }

    public static void infoTag(Class<?> clazz, String tag, Object msg) {
        info(clazz, "【" + tag + msg + "】");
    }

    public static void infoTag(Class<?> clazz, String tag, String msg, Throwable e) {
        info(clazz, "【" + tag + msg + "】", e);
    }

    public static void infoTag(Class<?> clazz, String tag, String msg, Object arg, Throwable e) {
        info(clazz, "【" + tag + msg + "】", arg, e);
    }


    /**
     * warn级日志输出
     */
    public static void warn(Class<?> clazz, String msg) {
        getLogger(clazz.getName()).warn(msg);
    }

    public static void warn(Class<?> clazz, String msg, Throwable e) {
        getLogger(clazz.getName()).warn(msg, e);
    }

    public static void warn(Class<?> clazz, String msg, Object arg) {
        getLogger(clazz.getName()).warn(msg, arg);
    }

    public static void warn(Class<?> clazz, String msg, Object arg, Throwable e) {
        getLogger(clazz.getName()).warn(msg, arg, e);
    }

    public static void warn(Class<?> clazz, String msg, Object[] arg) {
        getLogger(clazz.getName()).warn(msg, arg);
    }

    public static void warnTag(Class<?> clazz, String tag, Object msg) {
        warn(clazz, "【" + tag + "】" + msg);
    }

    public static void warnTag(Class<?> clazz, String tag, String msg, Throwable e) {
        warn(clazz, "【" + tag + msg + "】", e);
    }

    public static void warnTag(Class<?> clazz, String tag, String msg, Object arg, Throwable e) {
        warn(clazz, "【" + tag + msg + "】", arg, e);
    }


    /**
     * warn级日志输出
     */
    public static void error(Class<?> clazz, String msg) {
        getLogger(clazz.getName()).error(msg);
    }

    public static void error(Class<?> clazz, String msg, Throwable e) {
        getLogger(clazz.getName()).error(msg, e);
    }

    public static void error(Class<?> clazz, String msg, Object arg) {
        getLogger(clazz.getName()).error(msg, arg);
    }

    public static void error(Class<?> clazz, String msg, Object arg, Throwable e) {
        getLogger(clazz.getName()).error(msg, arg, e);
    }

    public static void error(Class<?> clazz, String msg, Object[] arg) {
        getLogger(clazz.getName()).error(msg, arg);
    }

    public static void errorTag(Class<?> clazz, String tag, Object msg) {
        error(clazz, "【" + tag + "】" + msg);
    }

    public static void errorTag(Class<?> clazz, String tag, String msg, Throwable e) {
        error(clazz, "【" + tag + msg + "】", e);
    }

    public static void errorTag(Class<?> clazz, String tag, String msg, Object arg, Throwable e) {
        error(clazz, "【" + tag + msg + "】", arg, e);
    }


    /**
     * 获取最开始的调用者所在类
     *
     * @return 全类名
     */
    private static String getClassName() {
        Throwable th = new Throwable();
        StackTraceElement[] stes = th.getStackTrace();
        if (stes != null && stes.length > 0) {
            StackTraceElement ste = stes[stes.length - 1];
            return ste.getClassName();
        }
        return "";
    }

    /**
     * 根据类名获得logger对象
     *
     * @return logger对象
     */
    private static org.slf4j.Logger getLogger() {
        String className = getClassName();
        return getLogger(className);
    }

    /**
     * 根据类名获得logger对象
     *
     * @param className 全类名
     * @return logger对象
     */
    public static org.slf4j.Logger getLogger(String className) {
        org.slf4j.Logger log = null;
        if (loggerMap.containsKey(className)) {
            log = loggerMap.get(className);
        } else {
            try {
                log = LoggerFactory.getLogger(Class.forName(className));
                loggerMap.put(className, log);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return log;
    }
}
