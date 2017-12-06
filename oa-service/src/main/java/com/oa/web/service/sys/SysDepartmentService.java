package com.oa.web.service.sys;

import com.oa.bean.sys.SysDepartment;
import com.oa.core.base.BaseService;
import com.oa.core.bean.PageBean;

/**
 * Created by [张渊]
 * 2017/11/25 16:48
 */
public interface SysDepartmentService extends BaseService<SysDepartment> {

    PageBean<SysDepartment> getDepartmentList(PageBean<SysDepartment> page);

}
