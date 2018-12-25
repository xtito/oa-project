package com.oa.web.controller.login;

import com.oa.bean.sys.SysUser;
import com.oa.core.LoggerUtil;
import com.oa.core.base.controller.BaseController;
import com.oa.core.constant.HttpResponseStatusConstant;
import com.oa.core.utils.StringUtil;
import com.oa.web.support.shiro.token.manager.TokenManager;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by [张渊]
 * 2017/8/10 9:11
 */
@Controller
@RequestMapping("/login/mgr")
public class LoginController extends BaseController {


    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
    public Object login(@RequestParam(value = "rememberMe", required = false) boolean rememberMe,
                          SysUser user, HttpServletRequest request) throws Exception {

        try {

            user = TokenManager.login(user, rememberMe);
            resultMap.put(STATUS, HttpResponseStatusConstant.OK);
            resultMap.put(MESSAGE, "登录成功");

            // shiro 获取登录之前的地址
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = null;
            if (null != savedRequest) {
                url = savedRequest.getRequestUrl();
            }

            /*
             * 我们平常用的获取上一个请求的方式，在Session不一致的情况下是获取不到的
             * String url = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
             */
            LoggerUtil.debug(getClass(), "获取登录之前的URL:{}", url);
            // 如果登录之前没有地址，那么就跳转到首页。
            if (StringUtil.isBlank(url)) {
                url = request.getContextPath() + "/pages/index.jsp";
            }

            // 跳转地址
            resultMap.put("toUrl", url);

            LoggerUtil.info(getClass(), user.getLoginName() + "成功登录系统");

        } catch (Exception e) {
            resultMap.put(STATUS, HttpResponseStatusConstant.INTERNAL_SERVER_ERROR);
            if (e instanceof UnknownAccountException) {
                resultMap.put(MESSAGE, "用户不存在！");
            } else if (e instanceof IncorrectCredentialsException) {
                resultMap.put(MESSAGE, "帐号密码不正确！");
            } else if (e instanceof LockedAccountException) {
                resultMap.put(MESSAGE, "用户已被锁定！");
            } else if (e instanceof DisabledAccountException) {
                resultMap.put(MESSAGE, "帐号已经禁止登录！");
            } else {
                resultMap.put(MESSAGE, "登录异常，请联系管理员！");
            }
        }

        return resultMap;
    }


    @ResponseBody
    @RequestMapping("/loginOut")
    public Object loginOut() {
        try {
            TokenManager.logout();
            resultMap.put("status", 200);
        } catch (Exception e) {
            resultMap.put("status", 500);
            LoggerUtil.error(getClass(), "退出出现错误，{}。", e.getMessage(), e);
        }
        return resultMap;
    }

}
