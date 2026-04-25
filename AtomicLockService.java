package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class AtomicLockService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 🔒 1. HORIZONTAL CAP (Max 100 bot replies per post)
    public boolean allowBotReply(Long postId) {

        String key = "post:" + postId + ":bot_count";

        Long count = redisTemplate.opsForValue().increment(key);

        if (count != null && count > 100) {
            return false; // reject bot
        }

        return true;
    }

    // 🔒 2. VERTICAL CAP (Max comment depth = 20)
    public boolean validateDepth(int depthLevel) {
        return depthLevel <= 20;
    }

    // 🔒 3. COOLDOWN (Bot ↔ Human 10 min restriction)
    public boolean checkCooldown(Long botId, Long humanId) {

        String key = "cooldown:bot_" + botId + ":human_" + humanId;

        Boolean exists = redisTemplate.hasKey(key);

        if (Boolean.TRUE.equals(exists)) {
            return false; // blocked
        }

        redisTemplate.opsForValue()
                .set(key, "1", Duration.ofMinutes(10));

        return true;
    }

    // 🔥 OPTIONAL: reset bot counter (useful for testing)
    public void resetBotCount(Long postId) {
        String key = "post:" + postId + ":bot_count";
        redisTemplate.delete(key);
    }
}
