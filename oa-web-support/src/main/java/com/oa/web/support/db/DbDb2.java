package com.oa.web.support.db;


/**
 * 数据库表、视图是否存
 *
 * Created by [张渊]
 * 2017/10/29 16:47
 */
public class DbDb2 implements Db {

    @Override
    public boolean isViewExist(String viewName) {
        return false;
    }

    @Override
    public boolean isTableExist(String tableName) {
        return false;
    }

}
