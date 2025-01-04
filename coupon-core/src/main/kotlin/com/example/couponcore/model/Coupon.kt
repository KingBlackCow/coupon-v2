package com.example.couponcore.model

import com.example.couponcore.exception.CouponIssueException
import com.example.couponcore.exception.ErrorCode
import jakarta.persistence.*
import lombok.Getter
import java.time.LocalDateTime

@Getter
@Entity
@Table(name = "coupons")
class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var couponType: CouponType,

    @Column(nullable = true)
    val totalQuantity: Int? = null,

    @Column(nullable = false)
    var issuedQuantity: Int = 0,

    @Column(nullable = false)
    var discountAmount: Int = 0,

    @Column(nullable = false)
    var minAvailableAmount: Int = 0,

    @Column(nullable = false)
    var dateIssueStart: LocalDateTime,

    @Column(nullable = false)
    var dateIssueEnd: LocalDateTime

) : BaseTimeEntity() {

    fun issue() {
        if (!availableIssueQuantity()) {
            throw CouponIssueException(
                ErrorCode.INVALID_COUPON_ISSUE_QUANTITY,
                "total: %s, issued%s".formatted(totalQuantity, issuedQuantity)
            )
        }

        if (!availableIssueDate()) {
            throw CouponIssueException(
                ErrorCode.INVALID_COUPON_ISSUE_DATE, "request: %s, issueStart: %s".formatted(
                    LocalDateTime.now(), dateIssueStart
                )
            )
        }
        issuedQuantity++
    }

    fun availableIssueQuantity(): Boolean {
        if (totalQuantity == null) {
            return true
        }
        return totalQuantity > issuedQuantity
    }

    fun availableIssueDate(): Boolean {
        val now = LocalDateTime.now()
        return dateIssueStart.isBefore(now) && dateIssueEnd.isAfter(now)
    }

    val isIssueComplete: Boolean
        get() {
            val now = LocalDateTime.now()
            return dateIssueEnd.isBefore(now) || !availableIssueQuantity()
        }
}