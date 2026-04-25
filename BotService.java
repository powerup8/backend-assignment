package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BotService {

    @Autowired
    private AtomicLockService atomicLockService;

    @Autowired
    private RedisService redisService;

    public String botReply(Long postId, Long botId, Long humanId) {

        if (!atomicLockService.checkCooldown(botId, humanId)) {
            throw new ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "Bot cooldown active (10 min)"
            );
        }

        if (!atomicLockService.allowBotReply(postId)) {
            throw new ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "Bot limit reached (100 per post)"
            );
        }

        redisService.updateVirality(postId, "BOT_REPLY");

        return "Bot reply successful";
    }
}