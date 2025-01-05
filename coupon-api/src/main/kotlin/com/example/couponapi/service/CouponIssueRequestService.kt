package com.example.couponapi.service

import com.example.couponcore.common.Log
import com.example.couponapi.dto.CouponIssueRequestDto
import com.example.couponcore.component.DistributeLockExecutor
import com.example.couponcore.service.CouponIssueService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@RequiredArgsConstructor
@Service
class CouponIssueRequestService(
    private val couponIssueService: CouponIssueService,
    private val distributeLockExecutor: DistributeLockExecutor,
) {
    companion object: Log

    fun issueRequestV1(requestDto: CouponIssueRequestDto) {
        distributeLockExecutor.execute(
            "lock_" + requestDto.couponId, 10000, 10000
        ) { couponIssueService.issue(requestDto.couponId, requestDto.userId) }
        CouponIssueRequestService.log.info("쿠폰 발급 완료. couponId: ${requestDto.couponId}, userId: ${requestDto.userId}")
    }
}