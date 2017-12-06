package com.oa.web.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.oa.bean.sys.SysDepartment;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;
import com.oa.core.utils.date.DateUtil;
import com.oa.core.utils.date.DateValida;
import com.oa.web.mapper.SysDepartmentMapper;
import com.oa.web.service.sys.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by [张渊]
 * 2017/11/25 17:02
 */
@Service("sysDepartmentService")
@Transactional
public class SysDepartmentServiceImpl implements SysDepartmentService {

    @Autowired
    private SysDepartmentMapper mapper;

    @Override
    public int save(SysDepartment entity) {
        return this.mapper.saveDepartment(entity);
    }

    @Override
    public int update(SysDepartment entity) {
        return this.mapper.updateDepartment(entity);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysDepartment getByPrimaryKey(Long id) {
        return this.mapper.getByPrimaryKey(id);
    }

    @Override
    public List<SysDepartment> getAll() {
        return this.mapper.getAll();
    }

    @Override
    public int saveDepartment(SysDepartment dept) throws ValidateException {

        if (StringUtil.isEmpty(dept.getName())) {
            throw new ValidateException("部门名称不能为空");
        }

        if (dept.getParentId() == null) {
            throw new ValidateException("必须指定上级部门");
        }

        if (this.getDepartmentByName(dept.getName()) != null) {
            throw new ValidateException("该部门已经存在");
        }

        return this.save(dept);
    }

    @Override
    public SysDepartment getDepartmentByName(String deptName) {
        return this.mapper.getDepartmentByName(deptName);
    }

    @Override
    public PageBean<SysDepartment> getDepartmentList(PageBean<SysDepartment> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<SysDepartment> list = this.mapper.getDepartmentList(page);
        page.convertPage(list);
        return page;
    }

    @Override
    public PageBean<SysDepartment> getDepartmentList(PageBean<SysDepartment> page, HttpServletRequest request) throws ValidateException {

        String deptName = StringUtil.removeTrim(request.getParameter("deptName"));
        String begin = StringUtil.removeTrim(request.getParameter("begin"));
        String end = StringUtil.removeTrim(request.getParameter("end"));

        if (StringUtil.isNotNull(deptName)) {
            page.put("nameLike", deptName);
        }

        if (StringUtil.isNotNull(begin) && StringUtil.isEmpty(end)) {
            String currentDate = DateUtil.getCurrentDateFormat("yyyy-MM-dd");
            DateValida.dateValidate(begin, currentDate);

            page.put("begin", begin);
            page.put("end", currentDate);
        }

        if (StringUtil.isEmpty(begin) && StringUtil.isNotNull(end)) {
            String currentDate = DateUtil.getCurrentDateFormat("yyyy-MM-dd");
            DateValida.dateValidate(currentDate, end);

            page.put("begin", currentDate);
            page.put("end", end);
        }

        if (StringUtil.isNotNull(begin) && StringUtil.isNotNull(end)) {
            DateValida.dateValidate(begin, end);

            page.put("begin", begin);
            page.put("end", end);
        }

        return this.getDepartmentList(page);
    }
}
