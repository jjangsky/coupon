package com.example.api.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppliedUserRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public AppliedUserRepository(RedisTemplate<String, String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    // Redis에서 중복을 제거하기 위한 SADD 사용
    public Long add(Long userId){
        return redisTemplate
                .opsForSet()
                .add("applied_user", userId.toString());
    }
}
