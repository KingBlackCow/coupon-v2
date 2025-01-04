package com.example.couponcore.model

import com.example.couponcore.exception.CouponIssueException
import com.example.couponcore.exception.ErrorCode
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "coupons")
class Coupon : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false)
    private var title: String? = null

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private var couponType: CouponType? = null

    private val totalQuantity: Int? = null

    @Column(nullable = false)
    private var issuedQuantity = 0

    @Column(nullable = false)
    private var discountAmount = 0

    @Column(nullable = false)
    private var minAvailableAmount = 0

    @Column(nullable = false)
    private var dateIssueStart: LocalDateTime? = null

    @Column(nullable = false)
    private var dateIssueEnd: LocalDateTime? = null

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
        return dateIssueStart!!.isBefore(now) && dateIssueEnd!!.isAfter(now)
    }

    val isIssueComplete: Boolean
        get() {
            val now = LocalDateTime.now()
            return dateIssueEnd!!.isBefore(now) || !availableIssueQuantity()
        }
}