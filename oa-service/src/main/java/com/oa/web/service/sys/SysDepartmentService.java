package com.oa.web.service.sys;

import com.oa.bean.sys.SysDepartment;
import com.oa.core.base.BaseService;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by [张渊]
 * 2017/11/25 16:48
 */
public interface SysDepartmentService extends BaseService<SysDepartment> {

    int saveDepartment(SysDepartment dept) throws ValidateException;

    SysDepartment getDepartmentByName(String deptName);

    PageBean<SysDepartment> getDepartmentList(PageBean<SysDepartment> page);

    PageBean<SysDepartment> getDepartmentList(PageBean<SysDepartment> page, HttpServletRequest request) throws ValidateException;

}
