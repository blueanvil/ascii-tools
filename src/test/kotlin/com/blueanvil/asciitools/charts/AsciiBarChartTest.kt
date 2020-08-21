package com.blueanvil.asciitools.charts

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * @author Cosmin Marginean
 */
class BarChartTest {

    @Test
    fun basics() {
        AsciiBarChart(data = mapOf("John" to 75, "Mary" to 23, "Alexander" to 34), showRangeMarkers = true)
                .assert("""
                0                                       75
                ▼                                       ▼
John            │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 75
Mary            │▇▇▇▇▇▇▇▇▇▇▇▇ 23
Alexander       │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 34
                """)


        AsciiBarChart(data = mapOf("John" to 75, "Mary" to 23, "Alexander" to 34), showRangeMarkers = false)
                .assert("""
John            │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 75
Mary            │▇▇▇▇▇▇▇▇▇▇▇▇ 23
Alexander       │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 34
                """)

        AsciiBarChart(data = mapOf("John" to 75, "Mary" to 23, "Alexander" to 34))
                .assert("""
John            │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 75
Mary            │▇▇▇▇▇▇▇▇▇▇▇▇ 23
Alexander       │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 34
                """)


        val data = mapOf("John" to 75, "Mary" to 23, "Alexander" to 34)
        AsciiBarChart(data = data, minValue = -100, maxValue = 200, showRangeMarkers = true)
                .assert("""
             -100            0                          200
                ▼            ▼                          ▼
John                         │▇▇▇▇▇▇▇▇▇▇ 75
Mary                         │▇▇▇ 23
Alexander                    │▇▇▇▇ 34
                """)


        AsciiBarChart(data = mapOf("John" to -50, "Mary" to 23, "Alexander" to 34), showRangeMarkers = true)
                .assert("""
               -50                      0                34
                 ▼                      ▼                ▼
John         -50 ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇│                 
Mary                                    │▇▇▇▇▇▇▇▇▇▇ 23
Alexander                               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 34
                """)

        AsciiBarChart(data = mapOf("John" to -50, "Mary" to 23, "Alexander" to 34), showRangeMarkers = true)
                .assert("""
               -50                      0                34
                 ▼                      ▼                ▼
John         -50 ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇│                 
Mary                                    │▇▇▇▇▇▇▇▇▇▇ 23
Alexander                               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 34
                """)

        AsciiBarChart(data = mapOf("John" to -50, "Mary" to 23, "Alexander" to 34), showValueLabels = false)
                .assert("""
John             ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇│                 
Mary                                    │▇▇▇▇▇▇▇▇▇▇    
Alexander                               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇    
                """)

        AsciiBarChart(data = mapOf("John" to -50, "Mary" to 23, "Alexander" to 34), showValueLabels = false, barChar = '+')
                .assert("""
John             +++++++++++++++++++++++│                 
Mary                                    │++++++++++    
Alexander                               │++++++++++++++++    
                """)

    }


    @Test
    fun doubles() {
        AsciiBarChart(data = mapOf("John" to 0.005, "Mary" to 0.006, "Alexander" to 0.012))
                .assert("""
John               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 0.005
Mary               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 0.006
Alexander          │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 0.012
                """)
    }

    @Test
    fun ranges() {
        AsciiBarChart(data = mapOf("John" to -20, "Mary" to 100, "Alexander" to 150), minValue = -200, maxValue = 200, showRangeMarkers = true)
                .assert("""
              -200                   0                   200
                 ▼                   ▼                   ▼
John                           -20 ▇▇│                    
Mary                                 │▇▇▇▇▇▇▇▇▇▇ 100
Alexander                            │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 150
                """)
    }

    fun showCase() {
        val data = mapOf(
                "John" to 75,
                "Mary" to 23,
                "Alexander" to 34)
        println(AsciiBarChart(data = data, minValue = -100, maxValue = 200))
    }
}

private fun AsciiBarChart.assert(expected: String) {
    val actualLines = this.toString().split("\n").toList().filter { it.isNotBlank() }
    val expectedLines = expected.split("\n").toList().filter { it.isNotBlank() }
    assertEquals(actualLines, expectedLines, "\n" + this.toString() + "\n")
}
