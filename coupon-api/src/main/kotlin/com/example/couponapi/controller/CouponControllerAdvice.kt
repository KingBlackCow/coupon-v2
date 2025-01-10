package com.example.couponapi.controller

import com.example.couponapi.dto.CouponIssueResponseDto
import com.example.couponcore.exception.CouponIssueException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CouponControllerAdvice {
    @ExceptionHandler(CouponIssueException::class)
    fun couponIssueExceptionHandler(exception: CouponIssueException): CouponIssueResponseDto {
        return CouponIssueResponseDto(false, exception.errorCode.message)
    }
}