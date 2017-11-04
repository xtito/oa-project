package com.oa.web.support.shiro.service.impl;

import com.oa.core.LoggerUtil;
import com.oa.core.config.INI4j;
import com.oa.core.context.AppContext;
import com.oa.web.support.shiro.service.ShiroManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * 动态加载权限 Service
 * <p/>
 * Created by [张渊]
 * 2017/11/4 14:00
 */
public class ShiroManagerImpl implements ShiroManager {

    // 注意/r/n前不能有空格
    private static final String CRLF = "\r\n";
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Override
    public String loadFilterChainDefinitions() {
        // 固定权限，采用读取配置文件
        return getFixedAuthRule();
    }

    @Override
    public synchronized void reCreateFilterChains() {

        AbstractShiroFilter shiroFilter = null;
        try {
            shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
        } catch (Exception e) {
            LoggerUtil.error(getClass(), "getShiroFilter from shiroFilterFactoryBean error!", e);
            throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
        }

        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                .getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                .getFilterChainManager();

        // 清空老的权限控制
        manager.getFilterChains().clear();

        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        shiroFilterFactoryBean.setFilterChainDefinitions(loadFilterChainDefinitions());
        // 重新构建生成
        Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
        for (Map.Entry<String, String> entry : chains.entrySet()) {
            String url = entry.getKey();
            String chainDefinition = entry.getValue().trim().replace(" ", "");
            manager.createChain(url, chainDefinition);
        }
    }

    /**
     * 从配额文件获取固定权限验证规则串
     */
    private String getFixedAuthRule() {
        String fileName = "shiro_base_auth.ini";
        ClassPathResource cp = new ClassPathResource(fileName);
        INI4j ini = null;
        try {
            ini = new INI4j(cp.getFile());
        } catch (IOException e) {
            LoggerUtil.fmtError(getClass(), "加载文件出错。file:[%s]", e, fileName);
        }
        String section = "base_auth";
        StringBuilder sb = new StringBuilder();

        if (ini != null) {
            Set<String> keys = ini.get(section).keySet();
            for (String key : keys) {
                String value = ini.get(section, key);
                sb.append(key).append(" = ").append(value).append(CRLF);
            }
        }

        return sb.toString();
    }

}
