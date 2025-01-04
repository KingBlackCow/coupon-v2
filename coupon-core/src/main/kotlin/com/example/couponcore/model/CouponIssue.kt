package com.example.couponcore.model

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime


@Getter
@Entity
@Table(name = "coupon_issues")
class CouponIssue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0L,

    @Column(nullable = false)
    private var couponId: Long,

    @Column(nullable = false)
    private var userId: Long,

    @Column(nullable = false)
    @CreatedDate
    private var dateIssued: LocalDateTime? = null,

    private val dateUsed: LocalDateTime? = null
) : BaseTimeEntity() {

}