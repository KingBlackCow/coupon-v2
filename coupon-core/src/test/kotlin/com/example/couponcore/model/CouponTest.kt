package com.example.couponcore.model

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CouponTest {

    @Test
    @DisplayName("발급 수량이 남아있다면 true를 반환한다.")
    fun availableIssueQuantity_1() {
        val coupon = Coupon(totalQuantity = 100, issuedQuantity = 99, dateIssueStart = LocalDateTime.now().minusDays(10), dateIssueEnd = LocalDateTime.now().minusDays(100))
        val result = coupon.availableIssueQuantity()
        assertTrue(result)
    }
}