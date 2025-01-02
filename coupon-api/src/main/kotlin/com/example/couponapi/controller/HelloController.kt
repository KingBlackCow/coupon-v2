package com.example.couponapi.controller

import com.example.couponapi.common.Log
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    companion object: Log

    @GetMapping("/hello")
    fun hello(): String {
        log.info("hello!!")
        return "hello!"
    }
}