package com.example.couponconsumer.listener

import com.example.couponcore.common.Log
import com.example.couponcore.repository.RedisRepository
import com.example.couponcore.repository.redis.CouponIssueRequest
import com.example.couponcore.service.CouponIssueService
import com.example.couponcore.util.CouponRedisUtils
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
@EnableScheduling
class CouponIssueListener(
    private val couponIssueService: CouponIssueService,
    private val redisRepository: RedisRepository,
    private val objectMapper: ObjectMapper,

) {
    private val issueRequestQueueKey: String = CouponRedisUtils.getIssueRequestQueueKey()
    companion object: Log
    @Scheduled(fixedDelay = 1000)
    @Throws(JsonProcessingException::class)
    fun issue() {
        CouponIssueListener.log.info("listen...")
        while (existCouponIssueTarget()) {
            val target: CouponIssueRequest = issueTarget
            CouponIssueListener.log.info("발급 시작 target: $target")
            couponIssueService.issue(target.couponId, target.userId)
            CouponIssueListener.log.info("발급 완료 target: $target")
            removeIssuedTarget()
        }
    }

    private fun existCouponIssueTarget(): Boolean {
        return redisRepository.lSize(issueRequestQueueKey) > 0
    }

    @get:Throws(JsonProcessingException::class)
    private val issueTarget: CouponIssueRequest
        get() = objectMapper.readValue(redisRepository.lIndex(issueRequestQueueKey, 0), CouponIssueRequest::class.java)

    private fun removeIssuedTarget() {
        redisRepository.lPop(issueRequestQueueKey)
    }
}