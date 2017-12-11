package com.oa.web.support.db;

/**
 * SqlServer判断表或视图是否存在
 *
 * Created by [张渊]
 * 2017/10/29 18:06
 */
public class DbSqlServer implements Db {

    @Override
    public boolean isViewExist(String viewName) {
        return false;
    }

    @Override
    public boolean isTableExist(String tableName) {
        return false;
    }
}
