package com.oa.core;

import org.slf4j.LoggerFactory;

public class Logger {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public static org.slf4j.Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static void debug(String msg) {
        logger.debug(msg);
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void warn(String msg) {
        logger.warn(msg);
    }

    public static void error(String msg) {
        logger.error(msg);
    }

    public static void debug(String msg, Object param) {
        logger.debug(msg, param);
    }

    public static void info(String msg, Object param) {
        logger.info(msg, param);
    }

    public static void warn(String msg, Object param) {
        logger.warn(msg, param);
    }

    public static void error(String msg, Object param) {
        logger.error(msg, param);
    }


    public static void debug(Class<?> clazz, String msg, Throwable ex) {
        getLogger(clazz).debug(msg, ex);
    }

    public static void info(Class<?> clazz, String msg, Throwable ex) {
        getLogger(clazz).info(msg, ex);
    }

    public static void warn(Class<?> clazz, String msg, Throwable ex) {
        getLogger(clazz).warn(msg, ex);
    }

    public static void error(Class<?> clazz, String msg, Throwable ex) {
        getLogger(clazz).error(msg, ex);
    }


    public static void debug(Class<?> clazz, String msg) {
        getLogger(clazz).debug(msg);
    }

    public static void info(Class<?> clazz, String msg) {
        getLogger(clazz).info(msg);
    }

    public static void warn(Class<?> clazz, String msg) {
        getLogger(clazz).warn(msg);
    }

    public static void error(Class<?> clazz, String msg) {
        getLogger(clazz).error(msg);
    }


    public static void debug(Class<?> clazz, String msg, Object param) {
        getLogger(clazz).debug(msg, param);
    }

    public static void info(Class<?> clazz, String msg, Object param) {
        getLogger(clazz).info(msg, param);
    }

    public static void warn(Class<?> clazz, String msg, Object param) {
        getLogger(clazz).warn(msg, param);
    }

    public static void error(Class<?> clazz, String msg, Object param) {
        getLogger(clazz).error(msg, param);
    }


    public static void debug(Class<?> clazz, String msg, Object[] param) {
        getLogger(clazz).debug(msg, param);
    }

    public static void info(Class<?> clazz, String msg, Object[] param) {
        getLogger(clazz).info(msg, param);
    }

    public static void warn(Class<?> clazz, String msg, Object[] param) {
        getLogger(clazz).warn(msg, param);
    }

    public static void error(Class<?> clazz, String msg, Object[] param) {
        getLogger(clazz).error(msg, param);
    }


    public static void debug(Class<?> clazz, String msg, Object param, Throwable ex) {
        getLogger(clazz).debug(msg, param, ex);
    }

    public static void info(Class<?> clazz, String msg, Object param, Throwable ex) {
        getLogger(clazz).info(msg, param, ex);
    }

    public static void warn(Class<?> clazz, String msg, Object param, Throwable ex) {
        getLogger(clazz).warn(msg, param, ex);
    }

    public static void error(Class<?> clazz, String msg, Object param, Throwable ex) {
        getLogger(clazz).error(msg, param, ex);
    }


    public static void debug(Class<?> clazz, String msg, Object[] param, Throwable ex) {
        getLogger(clazz).debug(msg, param, ex);
    }

    public static void info(Class<?> clazz, String msg, Object[] param, Throwable ex) {
        getLogger(clazz).info(msg, param, ex);
    }

    public static void warn(Class<?> clazz, String msg, Object[] param, Throwable ex) {
        getLogger(clazz).warn(msg, param, ex);
    }

    public static void error(Class<?> clazz, String msg, Object[] param, Throwable ex) {
        getLogger(clazz).error(msg, param, ex);
    }


} 
