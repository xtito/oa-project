package com.oa.web.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oa.bean.sys.UserOnline;
import com.oa.core.base.controller.BaseController;
import com.oa.core.bean.PageBean;
import com.oa.core.constant.HttpResponseStatusConstant;
import com.oa.core.exception.ValidateException;
import com.oa.web.support.shiro.session.CustomSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by [张渊]
 * 2017/12/30 20:57
 */
@Controller
@RequestMapping("/member/mgr")
public class MemberController extends BaseController {

    private final CustomSessionManager sessionManager;

    @Autowired
    public MemberController(CustomSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * 在线用户列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", produces = "application/json; charset=utf-8")
    public Object list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       HttpServletRequest request) throws JsonProcessingException {

        PageBean<UserOnline> page = new PageBean<UserOnline>(pageNum, pageSize);

        try {

            page = this.sessionManager.getSessionUserListByPage(page);

        } catch (Exception e) {
            e.printStackTrace();
            page.setMsg(e.getMessage());
            page.setCode(HttpResponseStatusConstant.INTERNAL_SERVER_ERROR);
        }

        return page;
    }

}
