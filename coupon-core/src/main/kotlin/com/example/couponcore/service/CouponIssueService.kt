package com.example.couponcore.service

import com.example.couponcore.exception.CouponIssueException
import com.example.couponcore.exception.ErrorCode
import com.example.couponcore.model.Coupon
import com.example.couponcore.model.CouponIssue
import com.example.couponcore.model.event.CouponIssueCompleteEvent
import com.example.couponcore.repository.CouponIssueJpaRepository
import com.example.couponcore.repository.CouponIssueRepository
import com.example.couponcore.repository.CouponJpaRepository
import lombok.RequiredArgsConstructor
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@RequiredArgsConstructor
@Service
class CouponIssueService(
    private val couponJpaRepository: CouponJpaRepository,
    private val couponIssueJpaRepository: CouponIssueJpaRepository,
    private val couponIssueRepository: CouponIssueRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    @Transactional
    fun issue(couponId: Long, userId: Long) {
        val coupon = findCoupon(couponId)
        coupon.issue()
        saveCouponIssue(couponId, userId)
        publishCouponEvent(coupon)
    }

    @Transactional(readOnly = true)
    fun findCoupon(couponId: Long): Coupon {
        return couponJpaRepository.findById(couponId).orElseThrow {
            throw CouponIssueException(
                ErrorCode.COUPON_NOT_EXIST,
                "쿠폰 정책이 존재하지 않습니다. %s".formatted(couponId)
            )
        }
    }

    @Transactional
    fun findCouponWithLock(couponId: Long): Coupon {
        return couponJpaRepository.findCouponWithLock(couponId).orElseThrow {
            throw CouponIssueException(ErrorCode.COUPON_NOT_EXIST, "쿠폰 정책이 존재하지 않습니다. %s".formatted(couponId))
        }
    }

    @Transactional
    fun saveCouponIssue(couponId: Long, userId: Long): CouponIssue {
        checkAlreadyIssuance(couponId, userId)
        val couponIssue = CouponIssue(couponId = couponId, userId = userId)
        return couponIssueJpaRepository.save(couponIssue)
    }

    private fun checkAlreadyIssuance(couponId: Long, userId: Long) {
        val issue: CouponIssue? = couponIssueRepository.findFirstCouponIssue(couponId, userId)
        if (issue != null) {
            throw CouponIssueException(
                ErrorCode.DUPLICATED_COUPON_ISSUE,
                "이미 발급된 쿠폰입니다. user_id: %d, coupon_id: %d".formatted(userId, couponId)
            )
        }
    }

    private fun publishCouponEvent(coupon: Coupon) {
        if (coupon.isIssueComplete) {
            applicationEventPublisher.publishEvent(CouponIssueCompleteEvent(coupon.id))
        }
    }
}