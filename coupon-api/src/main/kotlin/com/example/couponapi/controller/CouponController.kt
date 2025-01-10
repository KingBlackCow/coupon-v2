package com.example.couponapi.controller

import com.example.couponapi.dto.CouponCreateDto
import com.example.couponapi.service.CouponService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RestController
class CouponController(
    private val couponService: CouponService
) {

    @PostMapping("/v1/create")
    fun create(@RequestBody body: CouponCreateDto): Long {
        return couponService.create(body);
    }
}