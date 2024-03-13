package top.itshanhe.newcodevideo.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/13
 */
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * 存储String类型数据
     * @param key 存储的键
     * @param value 值
     */
    public void setStringValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
    
    /**
     * 获取String类型数据
     * @param key 键
     * @return 值
     */
    public String getStringValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
    
    /**
     * 删除String类型数据
     * @param key 键
     */
    public void deleteStringValue(String key) {
        stringRedisTemplate.delete(key);
    }
    
    /**
     * 存储Hash类型数据
     * @param hashKey Hash的键
     * @param key 字段
     * @param value 值
     */
    public void putHashValue(String hashKey, String key, String value) {
        HashOperations<String, String, String> hashOps = stringRedisTemplate.opsForHash();
        hashOps.put(hashKey, key, value);
    }
    
    /**
     * 获取Hash类型数据
     * @param hashKey Hash的键
     * @param key 字段
     * @return 值
     */
    public String getHashValue(String hashKey, String key) {
        HashOperations<String, String, String> hashOps = stringRedisTemplate.opsForHash();
        return hashOps.get(hashKey, key);
    }
    
    /**
     * 删除Hash类型数据
     * @param hashKey Hash的键
     * @param key 字段
     */
    public void deleteHashValue(String hashKey, String key) {
        HashOperations<String, String, String> hashOps = stringRedisTemplate.opsForHash();
        hashOps.delete(hashKey, key);
    }
    
    /**
     * 获取Hash类型数据的所有键值对
     * @param hashKey Hash的键
     * @return 所有键值对
     */
    public Map<String, String> getAllHashValues(String hashKey) {
        HashOperations<String, String, String> hashOps = stringRedisTemplate.opsForHash();
        return hashOps.entries(hashKey);
    }
    
    /**
     * 向List中添加数据
     * @param key 键
     * @param value 值
     */
    public void addToList(String key, String value) {
        ListOperations<String, String> listOps = stringRedisTemplate.opsForList();
        listOps.rightPush(key, value);
    }
    
    /**
     * 从List中获取数据
     * @param key 键
     * @return 数据列表
     */
    public List<String> getListValues(String key) {
        ListOperations<String, String> listOps = stringRedisTemplate.opsForList();
        return listOps.range(key, 0, -1);
    }
    
    /**
     * 从List中删除数据
     * @param key 键
     * @param value 值
     */
    public void removeFromList(String key, String value) {
        ListOperations<String, String> listOps = stringRedisTemplate.opsForList();
        listOps.remove(key, 0, value);
    }
    
}
