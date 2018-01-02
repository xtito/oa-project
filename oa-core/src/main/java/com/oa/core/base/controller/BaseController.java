package com.oa.core.base.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by [张渊]
 * 2017/11/27 23:11
 */
public class BaseController {

    // 返回状态
    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    protected static final String STATUS = "status";
    protected static final String MESSAGE = "message";

    /**
     * 获取 Session
     * @param request request请求域
     * @return session对象
     */
    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    /**
     * 将状态和提示文字封装到JSON对象
     *
     * @param success 成功或失败状态
     * @param info    提示文字
     * @return JSON字符串
     */
    protected String parseJsonStr(boolean success, String info) {
        JSONObject json = new JSONObject();
        json.put("success", success);
        json.put("info", info);
        return json.toString();
    }

    /**
     * 页面重定向
     * @param redirectUrl 重定向页面路径
     * @return 新的页面模型
     */
    public ModelAndView redirect(String redirectUrl) {
        return new ModelAndView(new RedirectView(redirectUrl));
    }

    public ModelAndView redirect(String redirectUrl, Map<String, Object> parameter) {
        return new ModelAndView(new RedirectView(redirectUrl), parameter);
    }
}
