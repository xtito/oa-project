package com.oa.web.service.sys.impl;

import com.oa.bean.sys.SysDepartment;
import com.oa.web.mapper.SysDepartmentMapper;
import com.oa.web.service.sys.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
