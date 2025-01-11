package com.example.couponcore.repository

import com.example.couponcore.util.CouponRedisUtils
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@RequiredArgsConstructor
@Repository
class RedisRepository(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    private val issueRequestQueueKey: String = CouponRedisUtils.getIssueRequestQueueKey()

    fun zAdd(key: String, value: String, score: Double): Boolean? {
        return redisTemplate.opsForZSet().addIfAbsent(key, value, score)
    }

    fun sAdd(key: String, value: String): Long? {
        return redisTemplate.opsForSet().add(key, value)
    }

    fun sCard(key: String): Long? {
        return redisTemplate.opsForSet().size(key)
    }

    fun sIsMember(key: String, value: String?): Boolean? {
        return redisTemplate.opsForSet().isMember(key, value)
    }

    fun rPush(key: String, value: String): Long? {
        return redisTemplate.opsForList().rightPush(key, value)
    }

    fun lIndex(key: String, index: Long): String? {
        return redisTemplate.opsForList().index(key, index)
    }

    fun lPop(key: String): String? {
        return redisTemplate.opsForList().leftPop(key)
    }

    fun lSize(key: String): Long {
        return redisTemplate.opsForList().size(key)?: 0
    }
}