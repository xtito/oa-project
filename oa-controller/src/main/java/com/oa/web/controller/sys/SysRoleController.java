package com.oa.web.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oa.bean.sys.SysRole;
import com.oa.core.base.controller.BaseController;
import com.oa.core.bean.PageBean;
import com.oa.core.constant.HttpResponseStatusConstant;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;
import com.oa.web.service.sys.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by [张渊]
 * 2017/12/15 14:54
 */
@Controller
@RequestMapping("/sysRole/mgr")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService service;


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


}
