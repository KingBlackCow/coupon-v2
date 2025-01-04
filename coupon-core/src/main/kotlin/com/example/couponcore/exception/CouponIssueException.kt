package com.example.couponcore.exception

class CouponIssueException(
    val errorCode: ErrorCode,
    private val errorMessage: String
) : RuntimeException() {

    override val message: String
        get() = "[${errorCode}] $errorMessage"
}