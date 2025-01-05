package com.example.couponcore.component

import com.example.couponcore.common.Log
import lombok.RequiredArgsConstructor
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@RequiredArgsConstructor
@Component
class DistributeLockExecutor(
    private val redissonClient: RedissonClient,
) {
    companion object: Log

    fun execute(lockName: String, waitMilliSecond: Long, leaseMilliSecond: Long, logic: Runnable) {
        val lock = redissonClient.getLock(lockName)
        try {
            val isLocked = lock.tryLock(waitMilliSecond, leaseMilliSecond, TimeUnit.MILLISECONDS)
            check(isLocked) { "[$lockName] lock 획득 실패" }
            logic.run()
        } catch (e: InterruptedException) {
            log.error(e.message, e)
            throw RuntimeException(e)
        } finally {
            if (lock.isHeldByCurrentThread) {
                lock.unlock()
            }
        }
    }
}