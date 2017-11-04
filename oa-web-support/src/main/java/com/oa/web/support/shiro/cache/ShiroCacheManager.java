package com.oa.web.support.shiro.cache;

import org.apache.shiro.cache.Cache;

/**
 * Shiro缓存管理
 *
 * Created by [张渊]
 * 2017/10/29 22:02
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}
