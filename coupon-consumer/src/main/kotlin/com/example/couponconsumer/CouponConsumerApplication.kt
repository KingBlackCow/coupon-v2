package com.example.couponconsumer

import com.example.couponcore.CouponCoreConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@Import(CouponCoreConfiguration::class)
@SpringBootApplication
class CouponConsumerApplication

fun main(args: Array<String>) {
    // yml 파일의 내용을 반영하려는거 현재 yml파일명이 application-core.yml, application-consumer.yml
    System.setProperty("spring.config.name", "application-core,application-consumer")
    SpringApplication.run(CouponConsumerApplication::class.java, *args)
}