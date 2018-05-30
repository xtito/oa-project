package com.oa.workflow.test.f_sequenceFlow;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by [张渊]
 * 2018/5/30 21:18
 */
public class SequenceFlowTest {

    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义-InputStream文件
     */
    @Test
    public void deploymentProcessDefinitionInputStream() {

        String resourceName = "sequenceFlow.bpmn";
        String resourcePng = "sequenceFlow.png";
        // 因为是在source中做@Test，在IDEA中会导致区分不了包，暂时将文件存放在resource中
        String path = "/diagrams/f_sequenceFlow/";

        InputStream resourceInput = this.getClass().getResourceAsStream(path+ resourceName);
        InputStream resourcePngInput = this.getClass().getResourceAsStream(path+ resourcePng);

        Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
                .createDeployment()// 创建一个部署对象
                .name("连线")// 添加部署名称
                .addInputStream(resourceName, resourceInput)
                .addInputStream(resourcePng, resourcePngInput)
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
        String processDefinitionId = "sequenceFlow";

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

        String assignee = "田七";

        List<Task> list = processEngine.getTaskService()// 与正在执行的任务管理相关的Service
                .createTaskQuery()// 创建任务查询对象
                .taskAssignee(assignee)// 指定个人任务查询，指定办理人
                .orderByTaskCreateTime().asc()// 使用创建时间的升序排列
                .list();// 返回列表

        if (!CollectionUtils.isEmpty(list)) {
            for (Task task : list) {
                System.out.println("任务ID：" + task.getId());
                System.out.println("任务的办理人：" + task.getAssignee());
                System.out.println("任务名称：" + task.getName());
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
        String taskId = "80003";
        TaskService service = processEngine.getTaskService();

        // 完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线，对应sequenceFlow.bpmn文件中${message=='不重要'}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "重要");
        service.complete(taskId, map);

        System.out.println("完成任务，任务ID是：" + taskId);

    }

}
