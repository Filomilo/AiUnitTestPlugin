package Tools

import ai.grazie.utils.mpp.time.Time
import ai.grazie.utils.mpp.time.Timestamp
import java.lang.Thread.sleep
import java.time.Duration
import java.util.concurrent.TimeoutException

object Awaiters {

    fun awaitTrue(function: () -> Boolean, timeout: Duration = Duration.ofSeconds(10), message: String = "") {
        var res: Boolean = function()
        val time: Timestamp = Time.now()
        while (!res) {
            res = function()

            if (Time.now().millis - time.millis > timeout.toMillis()) {

                throw TimeoutException("Function didn't return true in ${timeout.toSeconds()} s :: ${message}")
            }
            sleep(100)
        }
    }


    fun awaitNotThrows(function: () -> Unit, timeout: Duration = Duration.ofSeconds(10), message: String = "") {

        val time: Timestamp = Time.now()
        while (true) {
            try {
                function()
            } catch (e: Exception) {
                if (Time.now().millis - time.millis > timeout.toMillis()) {
                    throw TimeoutException("Function didn't return true in ${timeout.toSeconds()} s :: ${message} :: ${e.message} :: ${e.stackTraceToString()}")

                }
                sleep(100)
                continue
            }
            return


        }
    }
}