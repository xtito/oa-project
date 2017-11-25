package com.oa.web.controller.sys;

import com.oa.bean.sys.SysDepartment;
import com.oa.web.service.sys.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 部门管理
 *
 * Created by [张渊]
 * 2017/11/25 16:44
 */
@Controller
@RequestMapping("/sysDepartment/mgr")
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService deptService;

    /**
     * 部门列表
     */
    @RequestMapping("/list")
    public String list() {

        return "sys/dept/sys_department";
    }


    @RequestMapping("/add/ui")
    public String addUI() {
        return "sys/dept/add_department";
    }


    /**
     * 跳转到更新页面
     */
    @RequestMapping("/update/ui")
    public String updateUI() {
        return "sys/dept/update_department";
    }

    /**
     * 保存部门
     */
    @ResponseBody
    @RequestMapping("/save")
    public String saveDepartment(SysDepartment dept, BindException bindResult) {


        return null;
    }


    @ResponseBody
    @RequestMapping("/update")
    public String updateDepartment(SysDepartment dept, BindException bindResult) {

        return null;
    }


    @ResponseBody
    @RequestMapping("/delete")
    public String deleteDepartment(String deptId, BindException bindResult) {

        return null;
    }

}
