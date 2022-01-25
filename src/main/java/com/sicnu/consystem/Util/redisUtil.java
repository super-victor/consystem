package com.sicnu.consystem.Util;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName redisUtil
 * @Description
 * 为了防止乱码必须序列化
 **/

/**
 * @Configuration
 * public class RedisConfig {
 *     @Bean(name="redisTemplate")
 *     public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
 *         RedisTemplate<String, String> template = new RedisTemplate<>();
 *         RedisSerializer<String> redisSerializer = new StringRedisSerializer();
 *         template.setConnectionFactory(factory);
 *         //key序列化方式
 *         template.setKeySerializer(redisSerializer);
 *         //value序列化
 *         template.setValueSerializer(redisSerializer);
 *         //value hashmap序列化
 *         template.setHashValueSerializer(redisSerializer);
 *         //key haspmap序列化
 *         template.setHashKeySerializer(redisSerializer);
 *         //
 *         return template;
 *     }
 * }
 * @Author pan
 * @LastChangeDate 2021/7/18 0:17
 * @Version v1.0
 */
@Component
public class redisUtil {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    SerializeUtil serializeUtil;
    // 放入缓存
    public boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean setObject(String key, Object value){
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
        return true;
    }

    public Object getObject(String key, Class clazz){
             return JSON.parseObject(redisTemplate.opsForValue().get(key).toString(),clazz);
    }
    public boolean set(String key, Object value, long time){
        try {
            if (time>0){
                redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // 缓存获取
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key)!=null;
        }catch (Exception e){
            return false;
        }
    }

    public void del(String...keys){
        if (keys!=null && keys.length>0){
            if (keys.length == 1){
                redisTemplate.delete(keys[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }
}

