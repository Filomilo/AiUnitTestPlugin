package org.filomilo.AiTestGenerator.Tools

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Thread.sleep
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.util.concurrent.TimeoutException

object Awaiters {
    private val log: Logger = LoggerFactory.getLogger(Awaiters::class.java)

    fun awaitTrue(function: () -> Boolean, timeout: Duration = Duration.ofSeconds(10), message: String = "") {
        var res: Boolean = function()
        val time: Instant = Instant.now()
        while (!res) {
            res = function()

            if (Instant.now().toEpochMilli() - time.toEpochMilli() > timeout.toMillis()) {

                throw TimeoutException("Function didn't return true in ${timeout.toSeconds()} s :: ${message}")
            }
            sleep(100)
        }
    }


    fun awaitNotThrows(function: () -> Unit, timeout: Duration = Duration.ofSeconds(10), message: String = "") {

        val time: Instant = Instant.now()
        while (true) {
            try {
                function()
            } catch (e: Exception) {
                if (Instant.now().toEpochMilli() - time.toEpochMilli() > timeout.toMillis()) {
                    throw TimeoutException("Function didn't return true in ${timeout.toSeconds()} s :: ${message} :: ${e.message} :: ${e.stackTraceToString()}")

                }
                sleep(100)
                log.info("Await no exception failed: ${e.message} ----\n\n ${e.stackTraceToString()}")
                continue
            }
            return


        }
    }
}