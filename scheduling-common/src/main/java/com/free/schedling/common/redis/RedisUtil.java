package com.free.schedling.common.redis;

import com.free.schedling.common.exception.CacheException;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

/**
 * @author funanbing
 * @date 2025-11-20 09:59:34
 * @description
 */
@Component
public class RedisUtil {

    private static RedissonClient redissonClient;

    @Autowired
    public RedisUtil(RedissonClient redissonClient) {
        RedisUtil.redissonClient = redissonClient;
    }

    /**
     * 设置缓存
     *
     * @param key      缓存的key
     * @param value    缓存的值
     * @param timeout  缓存的过期时间（秒）
     */
    public static void set(String key, String value, long timeout) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null");
        }
        if (timeout <= 0) {
            throw new IllegalArgumentException("Timeout must be positive");
        }
        try {
            redissonClient.getBucket(key).set(value, Duration.ofSeconds(timeout));
        } catch (Exception e) {
            throw new CacheException("Failed to set value in Redis");
        }
    }
    /**
     * 获取缓存
     *
     * @param key 缓存的key
     * @return 缓存的值
     */
    public static Object get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        try {
            return redissonClient.getBucket(key).get();
        } catch (Exception e) {
            throw new CacheException("Failed to get value from Redis");
        }
    }
    /**
     * 删除缓存
     *
     * @param key 缓存的key
     */
    public static void delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        try {
            redissonClient.getBucket(key).delete();
        } catch (Exception e) {
            throw new CacheException("Failed to delete key from Redis");
        }
    }

    /********
     * 获取锁
     *
     * @param key   锁的key
     * @return 是否获取成功
     */
    public static boolean lock(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        try {
            return redissonClient.getLock( key).tryLock();
        } catch (Exception e) {
            throw new CacheException("Failed to acquire lock in Redis");
        }
    }
    /**
     * 释放锁
     *
     * @param key 锁的key
     */
    public static void unlock(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        try {
            redissonClient.getLock(key).unlock();
        } catch (Exception e) {
            throw new CacheException("Failed to release lock in Redis");
        }
    }

    public static Map<String,Object> getMap(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        try {
            return redissonClient.getMap(key);
        } catch (Exception e) {
            throw new CacheException("Failed to get map from Redis");
        }
    }

    public static Object getMapValue(String key,String mapKey) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        try {
            return redissonClient.getMap(key).get(mapKey);
        } catch (Exception e) {
            throw new CacheException("Failed to get map from Redis");
        }
    }

    public static void setMap(String key, Map<String,Object> map) {
        if (key == null || map == null) {
            throw new IllegalArgumentException("Key and map cannot be null");
        }
        try {
            redissonClient.getMap(key).putAll(map);
        } catch (Exception e) {
            throw new CacheException("Failed to set map in Redis");
        }
    }

    public static void delMapValue(String key,String mapKey) {
        if (key == null || mapKey == null) {
            throw new IllegalArgumentException("Key and mapKey cannot be null");
        }
        try {
            redissonClient.getMap(key).remove(mapKey);
        } catch (Exception e) {
            throw new CacheException("Failed to del map in Redis");
        }
    }
}
