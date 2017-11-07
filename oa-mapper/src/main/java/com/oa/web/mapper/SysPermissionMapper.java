package com.oa.web.mapper;

import com.oa.bean.sys.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 权限 Mapper
 *
 * Created by [张渊]
 * 2017/10/29 15:42
 */
@Component
public interface SysPermissionMapper {


    Set<String> getPermissionByUserId(Long userId);

    /**
     * 将数据插入数据库
     * @param permission 数据实体
     * @return 返回插入的该数据主键ID或受影响条数
     */
    int saveSysPermission(SysPermission permission);

    /**
     * 根据数据实体，更新该实体数据对应的数据库表数据
     * @param permission 要更新的数据实体
     * @return 返回受影响条数
     */
    int updateSysPermission(SysPermission permission);

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
    SysPermission getByPrimaryKey(Long id);

    /**
     * 获取所有数据
     * @return 所有数据的集合
     */
    List<SysPermission> getAll();

}
