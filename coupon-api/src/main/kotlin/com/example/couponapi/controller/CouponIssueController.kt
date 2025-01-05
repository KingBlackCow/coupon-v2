package com.example.couponapi.controller

import com.example.couponapi.dto.CouponIssueRequestDto
import com.example.couponapi.dto.CouponIssueResponseDto
import com.example.couponapi.service.CouponIssueRequestService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RestController
class CouponIssueController(
    private val couponIssueRequestService: CouponIssueRequestService

) {

    @PostMapping("/v1/issue")
    fun issueV1(@RequestBody body: CouponIssueRequestDto): CouponIssueResponseDto {
        couponIssueRequestService.issueRequestV1(body)
        return CouponIssueResponseDto(true, null)
    }
}