package com.oa.web.controller.sys;

import com.oa.web.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户管理
 *
 * Created by [张渊]
 * 2017/11/4 21:02
 */
@Controller
@RequestMapping("/sysUser/mgr")
public class SysUserController {

    @Autowired
    private SysUserService service;

    @RequestMapping("/list")
    public String list() {
        return "sys/user/sys_user";
    }

    @RequestMapping(value = "/save/user", method = {RequestMethod.POST})
    public String saveSysUser() {

        return null;
    }

    @RequestMapping(value = "/update/user", method = {RequestMethod.POST})
    public String updateSysUser() {

        return null;
    }

    @RequestMapping(value = "/delete/user", method = {RequestMethod.POST})
    public String deleteSysUser() {
        return null;
    }

    @RequestMapping("/addUI")
    public String addUI() {
        return "sys/user/add_user";
    }

}
