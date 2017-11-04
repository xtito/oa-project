package com.oa.core.db;

import com.oa.core.context.AppContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Oracle判断表或视图是否存在
 *
 * Created by [张渊]
 * 2017/10/29 18:05
 */
public class DbOracle implements Db {

    @Override
     public boolean isViewExist(String viewName) {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) AppContext.getBean("jdbcTemplate");
        String sql = "select count(*) from user_views where table_name = ?";

        return jdbcTemplate.queryForInt(sql, new Object[] {viewName.toUpperCase()}) > 0;
    }

    @Override
    public boolean isTableExist(String tableName) {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) AppContext.getBean("jdbcTemplate");
        String sql = "select count(*) from user_tables where table_name = ?";

        return jdbcTemplate.queryForInt(sql, new Object[] {tableName.toUpperCase()}) > 0;
    }

}
