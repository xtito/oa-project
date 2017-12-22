package com.oa.web.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oa.bean.sys.SysRole;
import com.oa.core.base.controller.BaseController;
import com.oa.core.bean.PageBean;
import com.oa.core.constant.HttpResponseStatusConstant;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.CollectionUtil;
import com.oa.core.utils.StringUtil;
import com.oa.web.service.sys.SysRoleService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by [张渊]
 * 2017/12/15 14:54
 */
@Controller
@RequestMapping("/sysRole/mgr")
public class SysRoleController extends BaseController {

    private final SysRoleService service;

    @Autowired
    public SysRoleController(SysRoleService service) {
        this.service = service;
    }


    @ResponseBody
    @RequestMapping(value = "/list", produces = "application/json; charset=utf-8")
    public Object list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       HttpServletRequest request) throws JsonProcessingException {

        PageBean<SysRole> page = new PageBean<SysRole>(pageNum, pageSize);

        try {

            page = this.service.getRoleList(page, request);

        } catch (Exception e) {
            e.printStackTrace();
            page.setMsg(e.getMessage());
            page.setCode(HttpResponseStatusConstant.INTERNAL_SERVER_ERROR);
        }

        return page;
    }


    @ResponseBody
    @RequestMapping(value = "/role/win/list", produces = "application/json; charset=utf-8")
    public Object loadRoleList(@RequestParam("userId") String userId) throws JsonProcessingException {

        // 分页插件必须要返回指定格式
        PageBean<SysRole> page = new PageBean<SysRole>();

        List<String> userRoleIds = this.service.getUserRoleIdByUserId(userId);

        List<SysRole> roles = this.service.getRoleList();

        if (CollectionUtil.isNotEmpty(roles) && CollectionUtil.isNotEmpty(userRoleIds)) {
            // 设置是否选中
            for (SysRole role : roles) {
                role.setRoleChecked(userRoleIds.contains(role.getId().toString()));
            }
        }

        page.setList(roles);

        return page;
    }


    /**
     * 保存角色
     */
    @ResponseBody
    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8")
    public String saveSysUser(SysRole role, BindingResult bindingResult) {

        boolean success = true;
        String info = "角色添加成功";

        try {
            Date currentDate = new Date();
            role.setCreateTime(currentDate);
            role.setUpdateTime(currentDate);
            role.setDefIdentify(0);
            this.service.saveRole(role);

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
        SysRole role = this.service.getByPrimaryKey(Long.valueOf(id));
        modelMap.put("role", role);
        return "sys/role/update_role";
    }


    /**
     * 更新角色
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = "application/json; charset=utf-8")
    public String updateSysUser(SysRole role, BindingResult bindingResult) {

        String info = "角色更新成功";
        boolean success = true;

        try {

            if (role != null) {
                role.setUpdateTime(new Date());
                this.service.updateRole(role);
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = e.getMessage();
        }

        return parseJsonStr(success, info);
    }


    /**
     * 删除角色
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
    public String deleteSysUser(@RequestParam("id") String userId) {

        String info = "角色删除成功";
        boolean success = true;

        try {

            if (StringUtil.isNotNull(userId)) {
                this.service.deleteByPrimaryKey(Long.valueOf(userId));
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "删除角色异常，请联系管理员";
        }

        return parseJsonStr(success, info);
    }

    /**
     * 分配页面
     */
    @RequestMapping("/assign/roles/page")
    public String assignRolesPage(@RequestParam("userId") String userId, ModelMap modelMap, HttpServletRequest request) {
        modelMap.put("userId", userId);
        return "/sys/role/role_list_win";
    }


    /**
     * 分配角色
     */
    @ResponseBody
    @RequestMapping(value = "/assign/roles", produces = "application/json; charset=utf-8")
    public String assignRoles(@RequestParam("userId") String userId, @RequestParam("roleId[]") List<String> roleId) {

        boolean success = true;
        String info = "角色分配成功";

        try {

            // 插入前先删除已存在角色
            this.service.deleteUserRolesAll(userId);

            this.service.saveUserRole(userId, roleId.toArray());

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "分配角色异常，请联系管理员！";
            if (e instanceof ValidateException) {
                info = e.getMessage();
            }
        }

        return parseJsonStr(success, info);
    }



    /**
     * 清空用户角色
     */
    @ResponseBody
    @RequestMapping(value = "/clear/user/roles", produces = "application/json; charset=utf-8")
    public String clearUserRole(@RequestParam("userId") String userId) {

        boolean success = true;
        String info = "用户角色清除成功";

        try {

            if (StringUtil.isNotNull(userId)) {
                this.service.deleteUserRolesAll(userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "用户角色清除异常，请联系管理员！";
        }

        return parseJsonStr(success, info);
    }

}
