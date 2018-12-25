package com.oa.web.support.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.oa.core.LoggerUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Shiro Filter 工具类
 * <p/>
 * Created by [张渊]
 * 2017/11/4 13:46
 */
public class ShiroFilterUtil {

    //登录页面
    static final String LOGIN_URL = "/u/login.shtml";
    //踢出登录提示
    final static String KICKED_OUT = "/open/kickedOut.shtml";
    //没有权限提醒
    final static String UNAUTHORIZED = "/open/unauthorized.shtml";

    /**
     * 是否是Ajax请求
     */
    public static boolean isAjax(ServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

    /**
     * response 输出JSON
     */
    public static void out(ServletResponse response, Map<String, String> resultMap) {

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(JSONObject.toJSON(resultMap).toString());
        } catch (Exception e) {
            LoggerUtil.error(ShiroFilterUtil.class, "输出JSON报错。", e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}
