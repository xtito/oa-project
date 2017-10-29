package com.oa.core.utils.db;

import com.oa.core.utils.path.ConfigUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC数据库连接工具类
 *
 * Created by [张渊]
 * 2017/8/10 11:35
 */
public class DatabaseUtil {

    private DatabaseUtil() {}

    public static Connection getConnection() {
        Connection conn = null;
        try {

            Class.forName(ConfigUtil.get("jdbc.driver"));
            String tempUrl = ConfigUtil.get("jdbc.url");
            tempUrl = tempUrl.substring(0, tempUrl.indexOf("="));
            String url = tempUrl + "=master";
            String userName = ConfigUtil.get("jdbc.username");
            String password = ConfigUtil.get("jdbc.password");
            conn = DriverManager.getConnection(url, userName, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
