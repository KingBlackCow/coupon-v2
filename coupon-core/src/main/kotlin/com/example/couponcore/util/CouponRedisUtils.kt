package com.example.couponcore.util

object CouponRedisUtils {
    fun getIssueRequestKey(couponId: Long): String {
        return "issue.request.couponId=%s".formatted(couponId)
    }

    val issueRequestQueueKey: String
        get() = "issue.request"
}