package com.oa.web.support.tag;

import com.oa.bean.sys.SysUser;
import com.oa.core.constant.SessionConst;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 自定义标签
 * <p/>
 * Created by [张渊]
 * 2017/10/29 17:55
 */
public class FunctionTag {

    private FunctionTag() {
    }

    /**
     * 获取当前session
     */
    public static HttpSession getCurrentSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }

    /**
     * 获取当前登录用户
     */
    public static SysUser getCurrentUser() {

        try {
            if (getCurrentSession() != null) {
                return (SysUser) getCurrentSession().getAttribute(SessionConst.CURR_LOGIN_USER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取当前登录用户用户名
     */
    public static String getCurrentUserName() {
        SysUser user = getCurrentUser();
        if (user != null) {
            return user.getNickname();
        }
        return "";
    }

    /**
     * 获取当前登录用户登录帐号
     */
    public static String getCurrentUserLoginName() {
        SysUser user = getCurrentUser();
        if (user != null) {
            return user.getLoginName();
        }
        return "";
    }
}
