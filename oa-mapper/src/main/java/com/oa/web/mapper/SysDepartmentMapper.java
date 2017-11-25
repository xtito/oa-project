package com.oa.web.mapper;

import com.oa.bean.sys.SysDepartment;
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
     * 获取所有数据
     * @return 所有数据的集合
     */
    List<SysDepartment> getAll();

}
