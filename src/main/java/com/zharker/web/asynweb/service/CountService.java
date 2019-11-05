package com.zharker.web.asynweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CountService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public synchronized String count(String key){
        String countStr = redisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(countStr)){
            countStr = "0";
        }
        int count = Integer.parseInt(countStr);
        count = countIncrease(count);
        countStr = String.valueOf(count);
        redisTemplate.opsForValue().set(key,countStr);
        return countStr;
    }
    private int countIncrease(int count){
        return ++count;
    }
}
