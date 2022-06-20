package com.naver.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {
    private static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    private static final Map<Object, Object> CACHE_DATA = new HashMap<>();

    public void processCacheData(Object key, Object val) {
        Object existVal = getCache(key);
        if (existVal != null) {
            someProcessMethod(existVal);
            return;
        }
        setCache(key, val);
        someProcessMethod(val);
    }

    private void someProcessMethod(Object val) {

    }

    private Object getCache(Object key) {
        READ_WRITE_LOCK.readLock().lock();
        Object val = CACHE_DATA.get(key);
        READ_WRITE_LOCK.readLock().unlock();
        return val;
    }

    private void setCache(Object key, Object val) {
        READ_WRITE_LOCK.writeLock().lock();
        try {
            CACHE_DATA.put(key, val);
        } finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }
}
