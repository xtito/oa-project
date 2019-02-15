package com.oa.core.utils.path;

import com.oa.core.LoggerUtil;
import com.oa.core.utils.StringUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class PathUtil {

    public static String getClassPath() {
        String classPath = "";
        URL url = PathUtil.class.getClassLoader().getResource("");
        if (url == null) {
            classPath = System.getProperty("user.dir") + "/";
            if (classPath.contains("bin/")) {
                classPath = classPath.substring(0, classPath.indexOf("bin/"));
            }
        } else {
            classPath = url.toString();
        }

        try {
            classPath = URLDecoder.decode(classPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            classPath = "";
        }

        if (classPath.startsWith("file:/")) {
            classPath = classPath.substring("file:/".length());
        }

        return classPath;
    }

    public static String getTomcatPath() {

        String rootPath = getRootPath();
        if (rootPath.contains("/webapps")) {
            rootPath = rootPath.substring(0, rootPath.indexOf("/webapps"));
        } else {
            rootPath = rootPath.substring(0, rootPath.lastIndexOf("/") + 1);
        }

        return rootPath;
    }


    /**
     * 获取命令行程序的根目录
     */
    public static String getBinRootPath() {

        //在开发模式下可能会用到classPath
        String path = getClassPath();

        if (!StringUtil.isEmpty(path)) {
            return path;
        }

        //下面的这种情况适合在程序被打为jar包
        //并且不通够获取classPath的情况下可用
        path = System.getProperty("user.dir");
        if (path.startsWith("file:/")) {
            path = path.substring("file:/".length());
        }
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LoggerUtil.error(PathUtil.class,"exception", e);
        }

        if (path.endsWith("bin")) {
            path = path.substring(0, path.length() - 3);
        }

        return path;
    }

    /**
     * 获取系统运行时的根目录（WEB应用有效）
     */
    public static String getRootPath() {
        String classPath = getClassPath();
        if (classPath.contains("/WEB-INF")) {
            classPath = classPath.substring(0, classPath.indexOf("/WEB-INF"));
        }
        return classPath;
    }


    /**
     * 获取临时目录路径
     */
    public static String getTempPath() {

        String tmpPath = getRootPath() + "/tmp/";

        File file = new File(tmpPath);
        if (!file.exists()) {
            boolean state = file.mkdirs();
        }

        return tmpPath;
    }


    public static File loadingFile(String path) {
        return loadingFile("", path);
    }


    public static File loadingFile(String namespace, String path) {

        String filePath;

        path = path.replaceAll("^\\\\|^/", "");

        if (namespace.endsWith("/") || namespace.endsWith("\\"))
            filePath = namespace + path;
        else
            filePath = namespace + "/" + path;

        return new File(PathUtil.getClassPath() + filePath);
    }

}