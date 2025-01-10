package com.example.couponapi.service

import com.example.couponapi.dto.CouponCreateDto
import com.example.couponcore.common.Log
import com.example.couponcore.repository.CouponJpaRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@RequiredArgsConstructor
@Service
class CouponService(
    private val couponJpaRepository: CouponJpaRepository
) {
    companion object : Log

    fun create(body: CouponCreateDto): Long {
        val coupon = couponJpaRepository.save(body.toEntity())
        log.info("쿠폰 생성 완료. couponId: ${coupon.id}")
        return coupon.id
    }

}