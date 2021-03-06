package com.oa.web.support.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 角色判断校验
 * <p/>
 * Created by [张渊]
 * 2017/11/4 13:53
 */
public class RoleFilter extends AccessControlFilter {

    private static final String LOGIN_URL = "http://localhost:8080/oa/login.jsp";
    private static final String UNAUTHORIZED_URL = "http://www.sojson.com/unauthorized.html";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String[] arra = (String[]) mappedValue;

        Subject subject = getSubject(request, response);
        for (String role : arra) {
            if (subject.hasRole("role:" + role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {//表示没有登录，重定向到登录页面
            saveRequest(request);
            WebUtils.issueRedirect(request, response, LOGIN_URL);
        } else {
            if (StringUtils.hasText(UNAUTHORIZED_URL)) {//如果有未授权页面跳转过去
                WebUtils.issueRedirect(request, response, UNAUTHORIZED_URL);
            } else {//否则返回401未授权状态码
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
    }
}
