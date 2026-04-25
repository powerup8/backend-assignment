package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 🔥 Single unified virality update method
    public void updateVirality(Long postId, String action) {

        String key = "post:" + postId + ":virality_score";

        int score = switch (action) {
            case "BOT_REPLY" -> 1;
            case "LIKE" -> 20;
            case "COMMENT" -> 50;
            default -> 0;
        };

        redisTemplate.opsForValue().increment(key, score);
    }

    // 🔥 Always return numeric value (IMPORTANT)
    public Long getVirality(Long postId) {

        String key = "post:" + postId + ":virality_score";
        String value = redisTemplate.opsForValue().get(key);

        return value == null ? 0L : Long.parseLong(value);
    }
}