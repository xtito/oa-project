package com.oa.web.test.b_processDefinition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * Created by [张渊]
 * 2018/5/14 20:50
 */
public class ProcessDefinitionTest {

    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义（从classpath）
     */
    @Test
    public void deploymentProcessDefinition() {

        Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
                .createDeployment()// 创建一个部署对象
                .name("流程定义")// 添加部署名称
                .addClasspathResource("diagrams/helloWord.bpmn")// 从classpath的资源中加载，一次只能加载一个文件
                .addClasspathResource("diagrams/helloWord.png")
                .deploy();

        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }


    /**
     * 部署流程定义-ZIP格式文件
     */
    @Test
    public void deploymentProcessDefinitionZip() {

        String zipFileName = "diagrams/helloWord.zip";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(zipFileName);
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
     * 查询流程定义
     */
    @Test
    public void findProcessDefinition() {

        List<ProcessDefinition> list = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
                .createProcessDefinitionQuery()// 创建一个流程定义的查询
                /* 查询指定条件，where条件 */
//                .deploymentId(deploymentId)// 使用部署对象ID查询
//                .processDefinitionId(processDefinitionId)// 使用流程定义ID查询
//                .processDefinitionKey(processDefinitionKey)// 使用流程定义的Key查询
//                .processDefinitionNameLike(processDefinitionNameLike)// 使用流程定义的名称模糊查询

                .orderByProcessDefinitionVersion().asc()// 按照版本的升序排列
//                .orderByProcessDefinitionName().desc()// 按照流程定义的名称降序排列

//                .singleResult()// 返回唯一结果集
//                .count()// 返回结果集数值
//                .listPage(firstResult, maxResults);// 分页查询
                // 返回一个集合列表，封闭流程定义
                .list();

        if (!CollectionUtils.isEmpty(list)) {
            for (ProcessDefinition pd : list) {
                System.out.println("流程定义ID：" + pd.getId());
                System.out.println("流程定义的名称：" + pd.getName());
                System.out.println("流程定义的Key：" + pd.getKey());
                System.out.println("流程定义的版本：" + pd.getVersion());
                System.out.println("资源名称bpmn文件：" + pd.getResourceName());
                System.out.println("资源名称png文件：" + pd.getDiagramResourceName());
                System.out.println("部署对象ID：" + pd.getDeploymentId());
                System.out.println("#######################################");
            }
        }
    }


    /**
     * 附加功能：查询最新版本的流程定义
     */
    @Test
    public void findLastVersionProcessDefinition() {

        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()// 使用流程定义的版本升序排列
                .list();

        /*
         * Map<String, ProcessDefinition>
         * map集合的key：流程定义的key
         * map集合的value：流程定义的对象
         * map集合的特点：当map集合key值相同的情况下，后一次的值将替换前一次的值
         */

        Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();

        if (!CollectionUtils.isEmpty(list)) {
            for (ProcessDefinition pd : list) {
                map.put(pd.getKey(),  pd);
            }
        }


        List<ProcessDefinition> pdList = new ArrayList<ProcessDefinition>(map.values());

        if (!CollectionUtils.isEmpty(pdList)) {
            for (ProcessDefinition pd : pdList) {
                System.out.println("流程定义ID：" + pd.getId());
                System.out.println("流程定义的名称：" + pd.getName());
                System.out.println("流程定义的Key：" + pd.getKey());
                System.out.println("流程定义的版本：" + pd.getVersion());
                System.out.println("资源名称png文件：" + pd.getDiagramResourceName());
                System.out.println("资源名称bpmn文件：" + pd.getResourceName());
                System.out.println("部署对象ID：" + pd.getDeploymentId());
                System.out.println("#######################################");
            }
        }
    }


    /**
     * 删除流程定义
     */
    @Test
    public void deleteProcessDefinition() {

        String deploymentId = "42501";

        // 不带级联的删除，只能删除没有启动的流程，如果流程启动，就会抛出异常
//        processEngine.getRepositoryService().deleteDeployment(deploymentId);

        // 级联删除，不管流程是否启动，都删除
        processEngine.getRepositoryService().deleteDeployment(deploymentId, true);

        System.out.println("流程删除成功，删除流程ID：" + deploymentId);
    }


    /**
     * 附加功能：删除流程定义（删除Key相同的所有不同版本的流程定义）
     */
    @Test
    public void deleteProcessDefinitionByKey() {

        // 流程定义的key
        String processDefinitionKey = "helloWord";
        // 先使用流程定义的key查询流程定义，查询所有的版本
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)// 使用流程定义的key查询
                .list();

        // 遍历，获取和线个流程定义的部署ID
        if (!CollectionUtils.isEmpty(list)) {
            for (ProcessDefinition pd : list) {
                // 获取部署ID
                String deploymentId = pd.getDeploymentId();
                processEngine.getRepositoryService().deleteDeployment(deploymentId, true);
                System.out.println("删除成功，删除流程定义ID：" + pd.getId());
            }
        }
    }


    /**
     * 查看流程图
     */
    @Test
    public void viewPic() throws IOException {

        // 将生成的图片放到文件夹下
        String deploymentId = "15001";

        List<String> list = processEngine.getRepositoryService().getDeploymentResourceNames(deploymentId);

        String resourceName = "";
        if (!CollectionUtils.isEmpty(list)) {
            for (String name : list) {
                if (name.endsWith(".png")) {
                    resourceName = name;
                }
            }
        }

        InputStream in = processEngine.getRepositoryService()
                .getResourceAsStream(deploymentId, resourceName);

        // 将图片生成到F盘的目录下
        File file = new File("F:/test/" + resourceName);
        // 将输入流的图片写到F盘下
        FileUtils.copyInputStreamToFile(in, file);
    }
}
