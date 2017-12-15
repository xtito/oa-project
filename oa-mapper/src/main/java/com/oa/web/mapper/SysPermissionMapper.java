package com.oa.web.mapper;

import com.oa.bean.sys.SysPermission;
import com.oa.core.bean.PageBean;
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
     * 根据权限权限地址查询权限
     * @param urlPath 权限地址
     * @return 权限实体
     */
    SysPermission getPermissionByUrl(String urlPath);


    /**
     * 根据权限名查询权限
     * @param pmsName 权限名称
     * @return 权限实体
     */
    SysPermission getPermissionByName(String pmsName);


    /**
     * 查询权限列表
     * @param page 查询参数实体
     * @return 权限列表
     */
    List<SysPermission> getPermissionList(PageBean<SysPermission> page);
}
