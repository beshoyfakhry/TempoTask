package com.tempo.task.utils

import java.time.Duration
import java.util.concurrent.TimeUnit

class Utils {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        CLEAR
    }

    companion object {
        fun formatStringToAgo(
            format: String,
            wholeDuration: Duration,
            specificDuration: Long
        ): String {
            return java.lang.String.format(
                format,
                specificDuration,
                TimeUnit.MILLISECONDS.convert(
                    wholeDuration.toNanos() % 1000000000,
                    TimeUnit.NANOSECONDS
                )
            )
        }
    }

}