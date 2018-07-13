package com.oa.web.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oa.bean.sys.SysPermission;
import com.oa.core.base.controller.BaseController;
import com.oa.core.bean.PageBean;
import com.oa.core.constant.HttpResponseStatusConstant;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.CollectionUtil;
import com.oa.core.utils.StringUtil;
import com.oa.web.service.sys.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by [张渊]
 * 2018/07/05 14:54
 */
@Controller
@RequestMapping("/sysPermission/mgr")
public class SysPermissionController extends BaseController {

    private final SysPermissionService service;

    @Autowired
    public SysPermissionController(SysPermissionService service) {
        this.service = service;
    }


    @ResponseBody
    @RequestMapping(value = "/list", produces = "application/json; charset=utf-8")
    public Object list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       HttpServletRequest request) throws JsonProcessingException {

        PageBean<SysPermission> page = new PageBean<SysPermission>(pageNum, pageSize);

        try {

            page = this.service.getPermissionList(page, request);

        } catch (Exception e) {
            e.printStackTrace();
            page.setMsg(e.getMessage());
            page.setCode(HttpResponseStatusConstant.INTERNAL_SERVER_ERROR);
        }

        return page;
    }


    /**
     * 保存权限
     */
    @ResponseBody
    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8")
    public String saveSysPermission(SysPermission pms, BindingResult bindingResult) {

        boolean success = true;
        String info = "权限添加成功";

        try {
            pms.setDefIdentify(0);
            this.service.savePermission(pms);

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
        SysPermission pms = this.service.getByPrimaryKey(Long.valueOf(id));
        modelMap.put("pms", pms);
        return "sys/pms/update_permission";
    }


    /**
     * 更新权限
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = "application/json; charset=utf-8")
    public String updateSysPermission(SysPermission pms, BindingResult bindingResult) {

        String info = "权限更新成功";
        boolean success = true;

        try {

            if (pms != null) {
                this.service.update(pms);
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "更新权限异常，请联系管理员！";
        }

        return parseJsonStr(success, info);
    }


    /**
     * 删除权限
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
    public String deleteSysPermission(@RequestParam("id") String userId) {

        String info = "权限删除成功";
        boolean success = true;

        try {

            if (StringUtil.isNotNull(userId)) {
                this.service.deleteByPrimaryKey(Long.valueOf(userId));
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "删除权限异常，请联系管理员";
        }

        return parseJsonStr(success, info);
    }


    @ResponseBody
    @RequestMapping(value = "/pms/win/list", produces = "application/json; charset=utf-8")
    public Object loadRoleList(@RequestParam("roleId") String roleId) {

        // 分页插件必须要返回指定格式
        PageBean<SysPermission> page = new PageBean<SysPermission>();

        List<String> rolePms = this.service.getRolePmsIdByRoleId(roleId);

        List<SysPermission> pmsList = this.service.getPermissionList();

        if (CollectionUtil.isNotEmpty(pmsList) && CollectionUtil.isNotEmpty(rolePms)) {
            // 设置是否选中
            for (SysPermission pms : pmsList) {
                pms.setPmsChecked(rolePms.contains(pms.getId().toString()));
            }
        }

        page.setList(pmsList);

        return page;
    }


    /**
     * 分配权限
     */
    @ResponseBody
    @RequestMapping(value = "/assign/pms", produces = "application/json; charset=utf-8")
    public String assignRoles(@RequestParam("roleId") String roleId, @RequestParam("pmsId[]") List<String> pmsId) {

        boolean success = true;
        String info = "角色权限分配成功";

        try {

            // 插入前先删除已存在角色
            this.service.deleteRolePmsAll(roleId);

            this.service.saveRolePms(roleId, pmsId.toArray());

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "角色权限分配异常，请联系管理员！";
            if (e instanceof ValidateException) {
                info = e.getMessage();
            }
        }

        return parseJsonStr(success, info);
    }

    /**
     * 分配权限页面
     */
    @RequestMapping("/assign/pms/page")
    public String assignPmsPage(@RequestParam("roleId") String roleId, ModelMap modelMap, HttpServletRequest request) {
        modelMap.put("roleId", roleId);
        return "sys/pms/pms_list_win";
    }



    /**
     * 清空角色所有权限
     */
    @ResponseBody
    @RequestMapping(value = "/clear/role/pms", produces = "application/json; charset=utf-8")
    public String clearRolePms(@RequestParam("roleId") String roleId) {

        boolean success = true;
        String info = "角色权限清除成功";

        try {

            if (StringUtil.isNotNull(roleId)) {
                this.service.deleteRolePmsAll(roleId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "角色权限清除异常，请联系管理员！";
        }

        return parseJsonStr(success, info);
    }

}
