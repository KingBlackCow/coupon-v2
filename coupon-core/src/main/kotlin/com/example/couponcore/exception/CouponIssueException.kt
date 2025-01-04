package com.example.couponcore.exception

import lombok.Getter

@Getter
class CouponIssueException(errorCode: ErrorCode, override val message: String) : RuntimeException() {
    private val errorCode: ErrorCode = errorCode

    fun getMessage(): String {
        return "[%s] %s".formatted(errorCode, message)
    }
}