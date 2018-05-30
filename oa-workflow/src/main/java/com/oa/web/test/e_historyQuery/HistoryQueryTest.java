package com.oa.web.test.e_historyQuery;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by [张渊]
 * 2018/5/15 22:33
 */
public class HistoryQueryTest {

    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 查询历史流程实例
     */
    @Test
    public void findHistoryProcessInstance() {

        // 流程实例ID查询
        String processInstanceId = "22501";

        HistoricProcessInstance pi = processEngine.getHistoryService()// 与历史数据（历史表）相关的Service
                .createHistoricProcessInstanceQuery()// 创建历史流程实例查询
                .processInstanceId(processInstanceId)// 使用流程实例ID查询
                .orderByProcessInstanceStartTime().asc()
                .singleResult();

        String str = pi.getId() + "  " + pi.getName() + "  " + pi.getProcessDefinitionId() + "  "
                + pi.getStartTime() + "  " + pi.getEndTime() + "  " + pi.getDurationInMillis();

        System.out.println(str);
    }


    /**
     * 查询历史活动
     */
    @Test
    public void findHistoryActivity() {

        String processInstanceId = "47501";

        List<HistoricActivityInstance> list = processEngine.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime().asc()
                .list();


        if (!CollectionUtils.isEmpty(list)) {
            for (HistoricActivityInstance hai : list) {
                String str = hai.getId() + " : " + hai.getProcessInstanceId() + " : " + hai.getActivityType()
                        + " : " + hai.getStartTime() + " : " + hai.getEndTime();
                System.out.println(str);
                System.out.println("#########################");
            }
        }
    }


    /**
     * 查询历史任务
     */
    @Test
    public void findHistoryTask() {

        String processInstanceId = "47501";
        List<HistoricTaskInstance> list = processEngine.getHistoryService()// 与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()// 创建历史任务查询
                .processInstanceId(processInstanceId)// 使用流程实例ID
                .orderByHistoricTaskInstanceStartTime().asc()
                .list();

        if (!CollectionUtils.isEmpty(list)) {
            for (HistoricTaskInstance hti : list) {
                String str = hti.getId() + " : " + hti.getName() + " : " + hti.getProcessInstanceId() + " : "
                        + hti.getStartTime() + " : " + hti.getEndTime() + " : " + hti.getDurationInMillis();

                System.out.println(str);
                System.out.println("#################################");
            }
        }
    }


    /**
     * 查询流程变量的历史表
     */
    @Test
    public void findHistoryProcessVariables() {

        String processInstanceId = "47501";
        List<HistoricVariableInstance> list = processEngine.getHistoryService()
                .createHistoricVariableInstanceQuery()// 创建一个历史的流程变量查询对象
                .processInstanceId(processInstanceId)
                .list();

        if (!CollectionUtils.isEmpty(list)) {
            for (HistoricVariableInstance hvi : list) {
                System.out.println(hvi.getId() + " : " + hvi.getProcessInstanceId() + " : " + hvi.getVariableName() + " : " + hvi.getVariableTypeName());
                System.out.println("###################################");
            }
        }

    }

}
