package com.blueanvil.asciitools.charts

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * @author Cosmin Marginean
 */
class BarChartTest {

    @Test
    fun basics() {
        BarChart(data = mapOf("John" to 75, "Mary" to 23, "Alexander" to 34))
                .assert("""
                0                                       75
                ▼                                       ▼
John            │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 75
Mary            │▇▇▇▇▇▇▇▇▇▇▇▇ 23
Alexander       │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 34
                """)


        BarChart(data = mapOf("John" to 75, "Mary" to 23, "Alexander" to 34),
                showRangeMarkers = false)
                .assert("""
John            │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 75
Mary            │▇▇▇▇▇▇▇▇▇▇▇▇ 23
Alexander       │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 34
                """)


        val data = mapOf("John" to 75, "Mary" to 23, "Alexander" to 34)
        BarChart(data = data, minValue = -100, maxValue = 200)
                .assert("""
             -100            0                          200
                ▼            ▼                          ▼
John                         │▇▇▇▇▇▇▇▇▇▇ 75
Mary                         │▇▇▇ 23
Alexander                    │▇▇▇▇ 34
                """)


        BarChart(data = mapOf("John" to -50, "Mary" to 23, "Alexander" to 34))
                .assert("""
               -50                      0                34
                 ▼                      ▼                ▼
John         -50 ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇│                 
Mary                                    │▇▇▇▇▇▇▇▇▇▇ 23
Alexander                               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 34
                """)

        BarChart(data = mapOf("John" to -50, "Mary" to 23, "Alexander" to 34), showValueLabels = false)
                .assert("""
               -50                      0                34
                 ▼                      ▼                ▼
John             ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇│                 
Mary                                    │▇▇▇▇▇▇▇▇▇▇    
Alexander                               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇    
                """)

        BarChart(data = mapOf("John" to -50, "Mary" to 23, "Alexander" to 34), showValueLabels = false, showRangeMarkers = false)
                .assert("""
John             ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇│                 
Mary                                    │▇▇▇▇▇▇▇▇▇▇    
Alexander                               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇    
                """)

        BarChart(data = mapOf("John" to -50, "Mary" to 23, "Alexander" to 34), showValueLabels = false, showRangeMarkers = false, barChar = '+')
                .assert("""
John             +++++++++++++++++++++++│                 
Mary                                    │++++++++++    
Alexander                               │++++++++++++++++    
                """)

    }


    @Test
    fun doubles() {
        BarChart(data = mapOf("John" to 0.005, "Mary" to 0.006, "Alexander" to 0.012))
                .assert("""
                   0                                       0.012
                   ▼                                       ▼
John               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 0.005
Mary               │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 0.006
Alexander          │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 0.012
                """)
    }

    @Test
    fun ranges() {
        BarChart(data = mapOf("John" to -20, "Mary" to 100, "Alexander" to 150), minValue = -200, maxValue = 200)
                .assert("""
              -200                   0                   200
                 ▼                   ▼                   ▼
John                           -20 ▇▇│                    
Mary                                 │▇▇▇▇▇▇▇▇▇▇ 100
Alexander                            │▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇ 150
                """)
    }
}

private fun BarChart.assert(expected: String) {
    val actualLines = this.toString().split("\n").toList().filter { it.isNotBlank() }
    val expectedLines = expected.split("\n").toList().filter { it.isNotBlank() }
    assertEquals(actualLines, expectedLines, "\n" + this.toString() + "\n")
}
