package com.oa.workflow.test.c_processInstanceTest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Created by [张渊]
 * 2018/5/14 20:56
 */
public class ProcessInstanceTest {

    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义-ZIP格式文件
     */
    @Test
    public void deploymentProcessDefinitionZip() {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/helloWord.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);

        Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
                .createDeployment()// 创建一个部署对象
                .name("流程定义")// 添加部署名称
                .addZipInputStream(zipInputStream)
                .deploy();

        System.out.println("ZIP部署ID：" + deployment.getId());
        System.out.println("ZIP部署名称：" + deployment.getName());
    }


    /**
     * 启动流程实例
     */
    @Test
    public void startProcessInstance() {

        // 流程定义的Key
        String processDefinitionId = "helloWord";

        ProcessInstance processInstance = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processDefinitionId);// 使用流程定义的key启动流程实例，key对应helloWord.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动

        System.out.println("流程实例ID：" + processInstance.getId());
        System.out.println("流程定义ID：" + processInstance.getProcessDefinitionId());
    }


    /**
     * 查询当前人的个人任务
     */
    @Test
    public void findMyPersonalTask() {

        String assignee = "王五";
        List<Task> list = processEngine.getTaskService()// 与正在执行的任务管理相关的Service
                .createTaskQuery()// 创建任务查询对象
                .taskAssignee(assignee)// 指定个人任务查询，指定办理人
//                .taskCandidateUser(candidateUser)// 组任务的办理人查询
//                .processDefinitionId(processDefinitionId)// 使用流程实例ID查询
//                .executionId(executionId)// 使用执行对象ID查询
                /* 排序 */
                .orderByTaskCreateTime().asc()// 使用创建时间的升序排列

                /* 返回结果集 */
//                .singleResult()// 返回唯一结果集
//                .count()// 返回结果集的数量
                .list();// 返回列表

        if (!CollectionUtils.isEmpty(list)) {
            for (Task task : list) {
                System.out.println("任务ID：" + task.getId());
                System.out.println("任务名称：" + task.getName());
                System.out.println("任务的办理人：" + task.getAssignee());
                System.out.println("任务创建时间：" + task.getCreateTime());
                System.out.println("流程实例ID：" + task.getProcessInstanceId());
                System.out.println("执行对象ID：" + task.getExecutionId());
                System.out.println("#################################");
            }
        }
    }


    /**
     * 完成我的任务
     */
    @Test
    public void completeMyPersonalTask() {

        // 任务ID
        String taskId = "27502";

        processEngine.getTaskService()
                .complete(taskId);

        System.out.println("完成任务，任务ID是：" + taskId);

    }


    /**
     * 查询流程状态（判断流程正在执行，还是结束
     */
    @Test
    public void isProcessEnd() {

        String processInstanceId = "35001";
        ProcessInstance pi = processEngine.getRuntimeService()// 表示正在执行的流程实例和执行对象
                .createProcessInstanceQuery()// 创建流程实例查询
                .processInstanceId(processInstanceId)// 使用流程实例ID查询
                .singleResult();

        if (pi != null) {
            System.out.println("流程没有结束");
        } else {
            System.out.println("流程结束了");
        }
    }


    /**
     * 查询历史任务
     */
    @Test
    public void findHistoryTask() {

        String taskAssignee = "张三";
        List<HistoricTaskInstance> list = processEngine.getHistoryService()// 与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()// 创建历史任务查询
                .taskAssignee(taskAssignee)// 指定历史任务的办理人
                .list();

        if (!CollectionUtils.isEmpty(list)) {
            for (HistoricTaskInstance hti : list) {
                String str = hti.getId() + "  " + hti.getName() + "  " + hti.getProcessInstanceId() + "  "
                        + hti.getStartTime() + "  " + hti.getEndTime() + "  " + hti.getDurationInMillis();

                System.out.println(str);
                System.out.println("#################################");
            }
        }
    }


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
                .singleResult();

        String str = pi.getId() + "  " + pi.getName() + "  " + pi.getProcessDefinitionId() + "  "
                + pi.getStartTime() + "  " + pi.getEndTime() + "  " + pi.getDurationInMillis();

        System.out.println(str);
    }
}
