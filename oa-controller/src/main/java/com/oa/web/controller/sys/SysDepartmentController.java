package com.oa.web.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.oa.bean.TreeNode;
import com.oa.bean.sys.SysDepartment;
import com.oa.core.base.controller.BaseController;
import com.oa.core.bean.PageBean;
import com.oa.core.constant.HttpResponseStatusConstant;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;
import com.oa.web.service.sys.SysDepartmentService;
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
 * 部门管理
 * <p/>
 * Created by [张渊]
 * 2018/07/09 16:44
 */
@Controller
@RequestMapping("/sysDepartment/mgr")
public class SysDepartmentController extends BaseController {

    private final SysDepartmentService service;

    @Autowired
    public SysDepartmentController(SysDepartmentService service) {
        this.service = service;
    }

    /**
     * 部门列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", produces = "application/json; charset=utf-8")
    public Object list(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
                       HttpServletRequest request) throws JsonProcessingException {

        PageBean<SysDepartment> page = new PageBean<SysDepartment>(pageNum, pageSize);

        try {

            page = this.service.getDepartmentList(page, request);

        } catch (ValidateException e) {
            e.printStackTrace();
            page.setMsg(e.getMsg());
            page.setCode(HttpResponseStatusConstant.INTERNAL_SERVER_ERROR);
        }

        return page;
    }

    /**
     * 跳转到更新页面
     */
    @RequestMapping("/update/ui")
    public String updateUI(@RequestParam("id") String id, ModelMap modelMap) {
        SysDepartment department = this.service.getByPrimaryKey(Long.valueOf(id));
        modelMap.put("dept", department);
        return "sys/dept/update_department";
    }

    /**
     * 保存部门
     */
    @ResponseBody
    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8")
    public String saveDepartment(SysDepartment dept, BindingResult bindResult) {

        boolean success = true;
        String info = "部门添加成功";

        try {

            dept.setCreateTime(new Date());
            dept.setDefIdentify(0);
            this.service.saveDepartment(dept);

        } catch (ValidateException e) {
            e.printStackTrace();
            success = false;
            info = e.getMsg();
        }

        return parseJsonStr(success, info);
    }


    @ResponseBody
    @RequestMapping(value = "/update", produces = "application/json; charset=utf-8")
    public String updateDepartment(SysDepartment dept, BindingResult bindingResult) {

        String info = "部门更新成功";
        boolean success = true;

        try {

            if (dept != null) {
                this.service.update(dept);
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "更新部门异常，请联系管理员！";
        }

        return parseJsonStr(success, info);
    }


    @ResponseBody
    @RequestMapping(value= "/delete", produces = "application/json; charset=utf-8")
    public Object deleteDepartment(@RequestParam("id") String deptId) throws JsonProcessingException {

        String info = "部门删除成功";
        boolean success = true;

        try {

            if (StringUtil.isNotNull(deptId)) {
                this.service.deleteByPrimaryKey(Long.valueOf(deptId));
            }

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            info = "删除部门异常，请联系管理员";
        }

        return parseJsonStr(success, info);
    }


    @ResponseBody
    @RequestMapping(value = "/list/tree", produces = "application/json; charset=utf-8")
    public Object listDeptTree(HttpServletRequest request) {

        String deptId = request.getParameter("id");
        List<TreeNode> treeNodes = null;

        if (StringUtil.isEmpty(deptId)) {
            treeNodes = this.service.listRootTree();
        } else {
            treeNodes = this.service.listChildTree(deptId);
        }

        return JSONArray.toJSONString(treeNodes);
    }

}
