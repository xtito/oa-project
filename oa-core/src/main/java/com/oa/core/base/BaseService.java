package com.oa.core.base;

import java.util.List;

/**
 * 基础Mapper接口
 *
 * Created by [张渊]
 * 2017/11/7 21:49
 */
public interface BaseService<T> {

    /**
     * 将数据插入数据库
     * @param entity 数据实体
     * @return 返回插入的该数据主键ID或受影响条数
     */
    int save(T entity);

    /**
     * 根据数据实体，更新该实体数据对应的数据库表数据
     * @param entity 要更新的数据实体
     * @return 返回受影响条数
     */
    int update(T entity);

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
    T getByPrimaryKey(Long id);

}
