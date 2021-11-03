package com.tempo.task

import com.tempo.task.utils.Extensions.dateToAgo
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant



class ExtensionsTest {

    @Test
    fun dateToAgeTest() {
        val startData = "2021-11-02T07:00:00Z"
        val endDate = "2021-11-03T07:00:00Z"
        val agoString = startData.dateToAgo(Instant.parse(endDate))
        val expectedString = "01 day ago"

        assertEquals(expectedString, agoString)
    }

}