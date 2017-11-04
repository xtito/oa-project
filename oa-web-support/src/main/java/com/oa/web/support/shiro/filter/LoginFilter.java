package com.oa.web.support.shiro.filter;

import com.oa.bean.sys.SysUser;
import com.oa.core.LoggerUtil;
import com.oa.web.support.shiro.token.manager.TokenManager;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 判断登录
 * <p/>
 * Created by [张渊]
 * 2017/11/4 13:48
 */
public class LoginFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {

        SysUser token = TokenManager.getCurrentUser();

        if (null != token || isLoginRequest(request, response)) {// && isEnabled()
            return Boolean.TRUE;
        }
        if (ShiroFilterUtil.isAjax(request)) {// ajax请求
            Map<String, String> resultMap = new HashMap<String, String>();
            LoggerUtil.debug(getClass(), "当前用户没有登录，并且是Ajax请求！");
            resultMap.put("login_status", "300");
            resultMap.put("message", "\u5F53\u524D\u7528\u6237\u6CA1\u6709\u767B\u5F55\uFF01");// 当前用户没有登录！
            ShiroFilterUtil.out(response, resultMap);
        }
        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 保存Request和Response 到登录后的链接
        saveRequestAndRedirectToLogin(request, response);
        return Boolean.FALSE;
    }
}
