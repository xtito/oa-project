package com.oa.workflow.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * Created by [张渊]
 * 2018/5/13 20:24
 */
public class TestActiviti {

    @Test
    public void createTable() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/xtito?useUnicode=true&characterEncoding=utf-8");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("root");

        /*
        public static final String DB_SCHEMA_UPDATE_FALSE = "false";// 不能自动创建表，需要表存在
        public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";// 先删除表再创建新表
        public static final String DB_SCHEMA_UPDATE_TRUE = "true";// 如果表不存在，自动创建表
         */
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 删除表再建表
//        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP);

        // 工作流的核心对象，ProcessEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println("processEngine:" + processEngine);
    }


    /**
     * 使用配置文件创建工作流需要的23张表
     */
    @Test
    public void createTableTwo() {
//        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
//                .createProcessEngineConfigurationFromResource("activiti.cfg.xml");

//        // 工作流的核心对象，ProcessEngine对象
//        ProcessEngine processEngine = configuration.buildProcessEngine();


        // 链式编程
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();

        /*
        RepositoryService   管理流程定义
        RuntimeService      执行管理，包括启动、推进、删除流程实例等操作
        TaskService         任务管理
        HistoryService      历史管理（执行完的数据的管理）
        IdentityService     组织机构管理
        FormService         一个可选服务，任务表单管理
        ManagementService
         */

        System.out.println("processEngine:" + processEngine);
    }

}
