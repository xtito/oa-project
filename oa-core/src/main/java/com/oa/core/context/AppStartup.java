package com.oa.core.context;

/**
 * 默认的服务器启动成功之后的动作
 * 如：加载配置、执行初始化操作
 *
 * 接口调用startup方法的时候spring、hibernate等一系列的开源框架已经加载成功
 *
 * Created by [张渊]
 * 2017/10/29 16:40
 */
public interface AppStartup {

    /**
     * 服务器启动成功后的事件
     */
    void startup();


    /**
     * 服务器关闭时的事件
     */
    void destroy();


}
