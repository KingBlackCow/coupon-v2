package com.example.couponcore.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    private val host: String,

    @Value("\${spring.data.redis.port}")
    private val port: Int
) {

    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config()
        val address = "redis://$host:$port"
        config.useSingleServer().setAddress(address)
        return Redisson.create(config)
    }
}
