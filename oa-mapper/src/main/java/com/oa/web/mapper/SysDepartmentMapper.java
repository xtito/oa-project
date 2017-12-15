package com.oa.web.mapper;

import com.oa.bean.sys.SysDepartment;
import com.oa.core.bean.PageBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by [张渊]
 * 2017/11/25 17:10
 */
@Component
public interface SysDepartmentMapper {

    /**
     * 将数据插入数据库
     * @param department 数据实体
     * @return 返回插入的该数据主键ID或受影响条数
     */
    int saveDepartment(SysDepartment department);

    /**
     * 根据数据实体，更新该实体数据对应的数据库表数据
     * @param department 要更新的数据实体
     * @return 返回受影响条数
     */
    int updateDepartment(SysDepartment department);

    /**
     * 根据数据实体主键，删除该条数据实体对应的数据库数据
     * @param id 数据实体主键ID
     * @return 受影响的条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据数据实体主键ID获取该数据实体
     * @param id 数据主键ID
     * @return 数据实体
     */
    SysDepartment getByPrimaryKey(Long id);


    /**
     * 查询部门列表，可以指定查询参数
     * @param page 参数实体
     * @return 符合查询条件的结果集
     */
    List<SysDepartment> getDepartmentList(PageBean<SysDepartment> page);

    /**
     * 根据部门名称查询部门
     * @param deptName 部门名称
     * @return 部门实体
     */
    SysDepartment getDepartmentByName(String deptName);

    /**
     * 根据部门ID查询子级部门
     * @param deptId 部门ID
     * @return 子级部门列表
     */
    List<SysDepartment> getDepartmentByPid(String deptId);
}
