package com.oa.web.service.sys;

import com.oa.bean.TreeNode;
import com.oa.bean.sys.SysDepartment;
import com.oa.core.base.BaseService;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by [张渊]
 * 2018/07/09 16:48
 */
public interface SysDepartmentService extends BaseService<SysDepartment> {

    void saveDepartment(SysDepartment dept) throws ValidateException;

    SysDepartment getDepartmentByName(String deptName);

    PageBean<SysDepartment> getDepartmentList(PageBean<SysDepartment> page);

    PageBean<SysDepartment> getDepartmentList(PageBean<SysDepartment> page, HttpServletRequest request) throws ValidateException;

    // 根据部门ID查询下级部门
    List<SysDepartment> getDepartmentByPid(String deptId);

    // 查询一级部门树
    List<TreeNode> listRootTree();

    // 查询部门下的部门
    List<TreeNode> listChildTree(String deptId);

    // 查询所有部门
    List<TreeNode> listTreeAll();

}
