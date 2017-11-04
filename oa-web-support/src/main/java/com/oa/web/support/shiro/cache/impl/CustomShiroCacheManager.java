package com.oa.web.support.shiro.cache.impl;

import com.oa.web.support.shiro.cache.ShiroCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * Shiro自定义缓存
 *
 * Created by [张渊]
 * 2017/11/4 12:52
 */
public class CustomShiroCacheManager implements CacheManager, Destroyable {

    private ShiroCacheManager shiroCacheManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return this.getShiroCacheManager().getCache(name);
    }

    @Override
    public void destroy() throws Exception {
        this.getShiroCacheManager().destroy();
    }


    public ShiroCacheManager getShiroCacheManager() {
        return shiroCacheManager;
    }

    public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
        this.shiroCacheManager = shiroCacheManager;
    }
}
