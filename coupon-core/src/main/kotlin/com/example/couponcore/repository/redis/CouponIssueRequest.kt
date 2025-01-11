package com.example.couponcore.repository.redis

data class CouponIssueRequest(
    val userId: Long,
    val couponId: Long
)