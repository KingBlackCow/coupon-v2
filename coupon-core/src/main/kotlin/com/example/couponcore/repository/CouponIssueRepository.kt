package com.example.couponcore.repository

import com.example.couponcore.model.CouponIssue
import com.example.couponcore.model.QCouponIssue.couponIssue
import com.querydsl.jpa.JPQLQueryFactory
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository

@RequiredArgsConstructor
@Repository
class CouponIssueRepository(
    private val queryFactory: JPQLQueryFactory
) {
    fun findFirstCouponIssue(couponId: Long, userId: Long): CouponIssue? {
        return queryFactory.selectFrom(couponIssue)
            .where(couponIssue.couponId.eq(couponId))
            .where(couponIssue.userId.eq(userId))
            .fetchFirst()
    }
}
