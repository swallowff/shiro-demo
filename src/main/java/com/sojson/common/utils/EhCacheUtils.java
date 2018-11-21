package com.sojson.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

/**
 * Created by yu on 2017/7/29.
 */
public class EhCacheUtils {
    //ehcache.xml 保存在src/main/resources/
    private static final String path = "/ehcache.xml";

    private URL url;

    private CacheManager manager;

    private static EhCacheUtils ehCache;

    private EhCacheUtils(String path) {
        url = getClass().getResource(path);
        manager = CacheManager.create(url);
    }

    public static EhCacheUtils getInstance() {
        if (ehCache == null) {
            ehCache = new EhCacheUtils(path);
        }
        return ehCache;
    }

    public void put(String cacheName, String key, Object value) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    public Object get(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public Cache get(String cacheName) {
        return manager.getCache(cacheName);
    }

    public void remove(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        cache.remove(key);
    }
}
