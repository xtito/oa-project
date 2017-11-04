package com.oa.core.context;


import com.oa.core.db.DbType;
import com.oa.core.utils.StringUtil;
import com.oa.core.utils.path.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 静态获取Bean
 * <p/>
 * Created by [张渊]
 * 2017/10/29 16:34
 */
public class AppContext implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(AppContext.class);

    /**
     * 默认端口,过滤器会自动更新这个值
     */
    public static int PORT = 8080;

    private static WebApplicationContext webApplicationContext;

    public static String contextPath = "";

    public static String rootPath;

    private static DbType dbType;

    public AppStartup defaultStartup;

    private AppContext() {
    }


    @Override
    public void contextInitialized(ServletContextEvent event) {

        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

        logger.debug("get spring context success ...");
        logger.info(webApplicationContext.getServletContext().getServerInfo());

        rootPath = event.getServletContext().getRealPath("/");
        contextPath = event.getServletContext().getContextPath();
        dbType = getDbType();

        defaultStartup = new DefaultAppStartupHandler();
        defaultStartup.startup();
        ConfigUtil.build();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        defaultStartup.destroy();
    }

    /**
     * 根据Bean的ID获取实例
     *
     * @param beanName 实体Bean的ID
     * @return Bean的实例
     */
    public static Object getBean(String beanName) {
        try {
            return webApplicationContext.getBean(beanName);
        } catch (Exception e) {
            throw new RuntimeException("获取的Bean不存在！");
        }
    }

    /**
     * 根据Bean的ID获取实例
     *
     * @param beanName     实体Bean的ID
     * @param requiredType 实体类型
     * @param <T>          实体泛型
     * @return Bean的实例
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        try {
            return webApplicationContext.getBean(beanName, requiredType);
        } catch (Exception e) {
            throw new RuntimeException("获取的Bean不存在！");
        }
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param beanName 实体名称
     * @return 存在返回 true
     */
    public static boolean containsBean(String beanName) {
        return webApplicationContext.containsBean(beanName);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     *
     * @param beanName 注册的bean名称
     * @return boolean
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String beanName) throws NoSuchBeanDefinitionException {
        return webApplicationContext.isSingleton(beanName);
    }

    /**
     * 返回指定注册的bean的类型
     *
     * @param beanName 注册的bean的名称
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException
     */
    public static Class getType(String beanName) throws NoSuchBeanDefinitionException {
        return webApplicationContext.getType(beanName);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param beanName 注册的bean的名称
     * @return bean的别名
     * @throws NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String beanName) throws NoSuchBeanDefinitionException {
        return webApplicationContext.getAliases(beanName);
    }

    /**
     * 获取连接的数据库类型
     */
    public static DbType getDbType() {
        if (dbType == null) {
            JdbcTemplate jdbcTemplate = (JdbcTemplate) getBean("jdbcTemplate");
            Connection connection = null;
            try {
                connection = jdbcTemplate.getDataSource().getConnection();
                String jdbcUrl = connection.getMetaData().getURL();

                if (StringUtil.contains(jdbcUrl, ":mysql:")) {
                    dbType = DbType.MySql;
                } else if (StringUtil.contains(jdbcUrl, ":oracle:")) {
                    dbType = DbType.Oracle;
                } else if (StringUtil.contains(jdbcUrl, ":db2:")) {
                    dbType = DbType.DB2;
                } else if (StringUtil.contains(jdbcUrl, ":sqlserver")) {
                    dbType = DbType.MsSql;
                } else {
                    dbType = DbType.MySql;
                }

            } catch (SQLException e) {
                dbType = DbType.MySql;
                logger.error("获取数据类型出错", e);
            } finally {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return dbType;
    }


    /**
     * 当前系统是否为debug模式
     */
    public static boolean isDebugModel() {
        // TODO 需要处理是否为 DEBUG 模式的代码
//        return PropertyReader.getBoolean("debugModel");
        return true;
    }

}
