package com.oa.web.support.db;


import com.oa.web.support.content.AppContext;

/**
 *
 * Created by [张渊]
 * 2017/10/29 18:05
 */
public class DbFactory {

    private static Db db;

    public static Db getDb() {
        DbType dbType = AppContext.getDbType();
        if (db == null) {
            switch (dbType) {
                case DB2:
                    db = new DbDb2();
                    break;
                case MsSql:
                    db = new DbSqlServer();
                    break;
                case MySql:
                    db = new DbMysql();
                    break;
                case Oracle:
                    db = new DbOracle();
                    break;
                default:
                    db = new DbMysql();
                    break;
            }
        }
        return db;
    }


}
