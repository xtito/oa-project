package com.oa.web.support.shiro.service;

/**
 * Created by [张渊]
 * 2017/11/4 14:00
 */
public interface ShiroManager {

    /**
     * 加载过滤配置信息
     *
     */
    String loadFilterChainDefinitions();

    /**
     * 重新构建权限过滤器
     * 一般在修改了用户角色、用户等信息时，需要再次调用该方法
     */
    void reCreateFilterChains();
}
