package com.oa.web.mapper;

import com.oa.bean.sys.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户 Mapper
 *
 * Created by [张渊]
 * 2017/10/29 15:32
 */
@Component
public interface SysUserMapper {

    /**
     * 将数据插入数据库
     * @param sysUser 数据实体
     * @return 返回插入的该数据主键ID或受影响条数
     */
    int saveSysUser(SysUser sysUser);

    /**
     * 根据数据实体，更新该实体数据对应的数据库表数据
     * @param sysUser 要更新的数据实体
     * @return 返回受影响条数
     */
    int updateSysUser(SysUser sysUser);

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
    SysUser getByPrimaryKey(Long id);

    /**
     * 获取所有数据
     * @return 所有数据的集合
     */
    List<SysUser> getAll();

}
