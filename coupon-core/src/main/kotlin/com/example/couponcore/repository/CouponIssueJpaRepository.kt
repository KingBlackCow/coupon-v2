package com.example.couponcore.repository

import com.example.couponcore.model.CouponIssue
import org.springframework.data.jpa.repository.JpaRepository

interface CouponIssueJpaRepository : JpaRepository<CouponIssue, Long>
