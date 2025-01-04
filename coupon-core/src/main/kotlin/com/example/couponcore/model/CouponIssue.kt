package com.example.couponcore.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "coupon_issues")
class CouponIssue : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false)
    private var couponId: Long? = null

    @Column(nullable = false)
    private var userId: Long? = null

    @Column(nullable = false)
    @CreatedDate
    private var dateIssued: LocalDateTime? = null

    private val dateUsed: LocalDateTime? = null
}