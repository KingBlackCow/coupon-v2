package com.example.couponapi.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class CouponIssueResponseDto(val isSuccess: Boolean, val comment: String?)