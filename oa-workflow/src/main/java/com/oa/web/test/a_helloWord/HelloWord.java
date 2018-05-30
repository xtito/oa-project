package com.oa.web.test.a_helloWord;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by [张渊]
 * 2018/5/14 14:08
 */
public class HelloWord {

    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义
     */
    @Test
    public void deploymentProcessDefinition() {

        Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
                .createDeployment()// 创建一个部署对象
                .name("helloWord入门程序")// 添加部署名称
                .addClasspathResource("diagrams/helloWord.bpmn")// 从classpath的资源中加载，一次只能加载一个文件
                .addClasspathResource("diagrams/helloWord.png")
                .deploy();

        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }


    /**
     * 启动流程实例
     */
    @Test
    public void startProcessInstance() {

        // 流程定义的Key
        String processInstanceByKey = "helloWord";

        ProcessInstance processInstance = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processInstanceByKey);// 使用流程定义的key启动流程实例，key对应helloWord.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动

        System.out.println("流程实例ID：" + processInstance.getId());
        System.out.println("流程定义ID：" + processInstance.getProcessDefinitionId());
    }


    /**
     * 查询当前人的个人任务
     */
    @Test
    public void findMyPersonalTask() {

        List<Task> list = processEngine.getTaskService()// 与正在执行的任务管理相关的Service
                .createTaskQuery()// 创建任务查询对象
                .taskAssignee("王五")// 指定个人任务查询，指定办理人
                .list();

        if (!CollectionUtils.isEmpty(list)) {
            for (Task task : list) {
                System.out.println("任务ID：" + task.getId());
                System.out.println("任务名称：" + task.getName());
                System.out.println("任务创建时间：" + task.getCreateTime());
                System.out.println("任务的办理人：" + task.getAssignee());
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
        String taskId = "10002";

        processEngine.getTaskService()
                .complete(taskId);

        System.out.println("完成任务，任务ID是：" + taskId);

    }
}
