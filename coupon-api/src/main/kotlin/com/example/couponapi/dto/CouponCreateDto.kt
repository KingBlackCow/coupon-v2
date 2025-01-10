package com.example.couponapi.dto

import com.example.couponcore.model.Coupon
import com.example.couponcore.model.CouponType
import java.time.LocalDateTime

data class CouponCreateDto(
    val title: String,
    val couponType: CouponType,
    val totalQuantity: Int? = null,
    val issuedQuantity: Int = 0,
    val discountAmount: Int,
    val minAvailableAmount: Int,
    val dateIssueStart: LocalDateTime,
    val dateIssueEnd: LocalDateTime
) {
    fun toEntity(): Coupon {
        return Coupon(
            title = this.title,
            couponType = this.couponType,
            totalQuantity = this.totalQuantity,
            issuedQuantity = this.issuedQuantity,
            discountAmount = this.discountAmount,
            minAvailableAmount = this.minAvailableAmount,
            dateIssueStart = this.dateIssueStart,
            dateIssueEnd = this.dateIssueEnd
        )
    }
}