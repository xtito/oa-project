package com.oa.core.db;

import com.oa.core.context.AppContext;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

/**
 * Mysql判断表或视图是否存在
 *
 * Created by [张渊]
 * 2017/10/29 16:47
 */
public class DbMysql implements Db {

    JdbcTemplate jdbcTemplate = (JdbcTemplate) AppContext.getBean("jdbcTemplate");


    @Override
    public boolean isViewExist(String viewName) {
        String sql = "SELECT COUNT(*) FROM information_schema.views WHERE table_name = ?";
        return jdbcTemplate.queryForInt(sql, new Object[] {viewName}) > 0;
    }

    @Override
    public boolean isTableExist(String tableName) {
        String sql = "show tables like '"+tableName+"'";
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList(sql);
        return !CollectionUtils.isEmpty(mapList);
    }


}