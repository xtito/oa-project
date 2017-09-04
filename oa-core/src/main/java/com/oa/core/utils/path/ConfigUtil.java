package com.oa.core.utils.path;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 获取配置文件
 */
public class ConfigUtil {

    private static final Logger logger = Logger.getLogger(ConfigUtil.class);

    private static final Map<String, String> hasMap = new HashMap<String, String>();

    private ConfigUtil() {
        super();
    }

    public static final String JDBC_FILE = "jdbc.properties";
    /**
     * 构建系统配置
     */
    public static void build() {

        File jdbcFile = PathUtil.loadingFile(JDBC_FILE);
        logger.info("正在加载配置文件...");
        build(jdbcFile);
        logger.info("配置文件加载完毕.");
    }

    private static void build(File file) {

        if (!file.exists() || file.isDirectory())
            return;

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream(file));
            logger.info("已加载配置文件[" + file.getPath() + "]");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("配置文件加载失败...[file=" + file.getPath() + "]", e);
        }

        String configPath = "";
        String configFiles = "";
        if(properties.size() > 0){
            for (Object obj : properties.keySet()) {

                String key = String.valueOf(obj);
                String value = properties.getProperty(key);

                if (hasMap.containsKey(key))
                    logger.warn("配置中存在重复的key[" + key + "] , 原值[" + hasMap.get(key) + "], 替换值[" + value + "]");

                hasMap.put(key, value);

            }
        }

    }

    public static String get(String key) {
        synchronized (ConfigUtil.class) {
            return hasMap.get(key);
        }
    }

    public static Integer getInteger(String key) {
        try {
            return Integer.parseInt(get(key));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long getLong(String key) {
        try {
            return Long.parseLong(get(key));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Boolean getBoolean(String key) {
        try {
            return Boolean.parseBoolean(get(key));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 重新构建系统配置
     */
    public static void rebuild() {
        logger.info("配置修改，重新加载。");
        destroy();
        build();
    }

    /**
     * 销毁
     */
    public static void destroy() {
        hasMap.clear();
    }

    public static int getLength() {
        return hasMap.size();
    }
}
