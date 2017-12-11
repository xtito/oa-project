package com.oa.web.support.db;

/**
 * 数据库表、视图是否存在接口
 *
 * Created by [张渊]
 * 2017/10/29 16:45
 */
public interface Db {

    /**
     * 是否存在视图
     * @param viewName 视图名称
     * @return true 存在， false 不存在
     */
    boolean isViewExist(String viewName);


    /**
     * 表是否存在
     * @param tableName 表名
     * @return true 存在， false 不存在
     */
    boolean isTableExist(String tableName);

}
