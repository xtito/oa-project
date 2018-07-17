package com.oa.web.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oa.bean.sys.SysUser;
import com.oa.bean.sys.view.UserRoleView;
import com.oa.core.base.controller.BaseController;
import com.oa.core.bean.PageBean;
import com.oa.core.constant.Constant;
import com.oa.core.constant.HttpResponseStatusConstant;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;
import com.oa.web.service.sys.SysRoleService;
import com.oa.web.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 *
 * Created by [张渊]
 * 2017/11/4 21:02
 */
@Controller
@RequestMapping("/sysUser/mgr")
public class SysUserController extends BaseController {

    private final SysUserService service;
    private final SysRoleService roleService;

    @Autowired
    public SysUserController(SysUserService service, SysRoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @ResponseBody
    @RequestMapping(value = "/list", produces = "application/json; charset=utf-8")
    public Object list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       HttpServletRequest request) throws JsonProcessingException {

        PageBean<SysUser> page = new PageBean<SysUser>(pageNum, pageSize);

        try {

            page = this.service.getUserList(page, request);

        } catch (ValidateException e) {
            e.printStackTrace();
            page.setMsg(e.getMsg());
            page.setCode(HttpResponseStatusConstant.INTERNAL_SERVER_ERROR);
        }

        return page;
    }


    /**
     * 保存用户
     */
    @ResponseBody
    @RequestMapping(value = "/save/user", produces = "application/json; charset=utf-8")
    public String saveSysUser(SysUser user, BindingResult bindingResult, HttpServletRequest request) {

        boolean success = true;
        String info = "用户添加成功";

        try {
            Date currentDate = new Date();
            String[] roleIds = request.getParameterValues("roleId");

            // 用户状态
            user.setStatus(Constant.USER_NORMAL);
            user.setCreateTime(currentDate);
            user.setUpdateTime(currentDate);
            user.setDefIdentify(0);
            this.service.saveUser(user);

            // 保存用户和角色关系
            if (user.getId() != null) {
                this.roleService.saveUserRole(user.getId().toString(), roleIds);
            }

        } catch (ValidateException e) {
            e.printStackTrace();
            success = false;
            info = e.getMsg();
        }

        return parseJsonStr(success, info);
    }

    /**
     * 跳转到更新页面
     */
    @RequestMapping("/update/ui")
    public String updateUI(@RequestParam("id") String id, ModelMap modelMap) {
        SysUser user = this.service.getByPrimaryKey(Long.valueOf(id));
        List<String> roleIdList = this.roleService.getUserRoleIdByUserId(id);
        modelMap.put("user", user);
        modelMap.put("roleIds", StringUtil.arrayToString(roleIdList.toArray()));
        return "sys/user/update_user";
    }

    /**
     * 跳转到更新页面
     */
    @RequestMapping("/view/detail")
    public String viewDetail(@RequestParam("id") String id, ModelMap modelMap) {
        SysUser user = this.service.getByPrimaryKey(Long.valueOf(id));
        modelMap.put("user", user);
        return "sys/user/view_user";
    }


    /**
     * 更新用户
     */
    @ResponseBody
    @RequestMapping(value = "/update/user", produces = "application/json; charset=utf-8")
    public String updateSysUser(SysUser user, BindingResult bindingResult, HttpServletRequest request) {

        String info = "用户更新成功";
        boolean success = true;

        try {

            if (user != null) {
                user.setUpdateTime(new Date());
                this.service.update(user);

                String[] roleIds = request.getParameterValues("roleId");

                // 保存用户和角色关系
                if (user.getId() != null) {
                    // 先删除用户角色关联
                    this.roleService.deleteUserRolesAll(user.getId().toString());
                    this.roleService.saveUserRole(user.getId().toString(), roleIds);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = e.getMessage();
        }

        return parseJsonStr(success, info);
    }


    /**
     * 删除用户
     */
    @ResponseBody
    @RequestMapping(value = "/delete/user", produces = "application/json; charset=utf-8")
    public String deleteSysUser(@RequestParam("id") String userId) {

        String info = "用户删除成功";
        boolean success = true;

        try {

            if (StringUtil.isNotNull(userId)) {
                this.service.deleteByPrimaryKey(Long.valueOf(userId));
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "删除用户异常，请联系管理员";
        }

        return parseJsonStr(success, info);
    }


    /**
     * 获取用户角色列表数据
     */
    @ResponseBody
    @RequestMapping(value = "/user/role/list", produces = "application/json; charset=utf-8")
    public Object userAndRoleList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       HttpServletRequest request) throws JsonProcessingException {

        PageBean<UserRoleView> page = new PageBean<UserRoleView>(pageNum, pageSize);

        try {

            page = this.service.getUserAndRoleList(page, request);

        } catch (Exception e) {
            e.printStackTrace();
            page.setMsg(e.getMessage());
            page.setCode(HttpResponseStatusConstant.INTERNAL_SERVER_ERROR);
        }

        return page;
    }
}
