package com.example.couponapi

import com.example.couponcore.CouponCoreConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@Import(CouponCoreConfiguration::class)
@SpringBootApplication
class CouponApiApplication

fun main(args: Array<String>) {
    // yml 파일의 내용을 반영하려는거 현재 yml파일명이 application-core.yml, application-api.yml
    System.setProperty("spring.config.name", "application-core,application-api")
    SpringApplication.run(CouponApiApplication::class.java, *args)
}