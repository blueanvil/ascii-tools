package com.blueanvil.asciitools.charts

import java.text.DecimalFormat
import kotlin.math.absoluteValue

/**
 * @author Cosmin Marginean
 */
class AsciiBarChart(val data: Map<String, Number>,
                    val width: Int = 40,
                    val minValue: Number? = null,
                    val maxValue: Number? = null,
                    val barChar: Char = '▇',
                    val showValueLabels: Boolean = true,
                    val showRangeMarkers: Boolean = false) {


    override fun toString(): String {
        val output = StringBuilder()

        addRangeGuide(output)

        data.forEach { (label, number) ->
            val value = number.toDouble()
            val valueStr = valueStr(value)

            var line = label.padEnd(rangeStart) + " ".repeat(width + 1)
            val segments = (value.absoluteValue / segmentSize).toInt()
//            println("line before: ${line.length}: $line")
            when {
                value < 0 -> {
                    line = line.replaceRange(rangeBaseline - valueStr.length - segments - 1, rangeBaseline - segments - 1, valueStr)
                            .replaceRange(rangeBaseline - segments, rangeBaseline, segments(segments))
                }
                value > 0 -> {
                    val endSegments = rangeBaseline + segments + 1
                    line = line.replaceRange(rangeBaseline + 1, endSegments, segments(segments))
                            .substring(0, endSegments) +
                            ' ' +
                            valueStr
                }
                else -> {
                    line = line.substring(0, rangeBaseline + 1) + valueStr
                }
            }
            line = line.replaceRange(rangeBaseline, rangeBaseline + 1, "│")
//            println("line after : ${line.length}: $line")

            output.appendln(line)
        }

        return output.toString()
    }

    private fun addRangeGuide(output: StringBuilder) {
        if (showRangeMarkers) {
            var labels = " ".repeat(rangeEnd)
                    .replaceRange(rangeBaseline, rangeBaseline + 1, "0")
                    .replaceRange(rangeEnd, rangeEnd, fmt.format(max))
            if (min < 0) {
                val minStr = fmt.format(min)
                labels = labels.replaceRange(rangeStart - minStr.length + 1, rangeStart + 1, minStr)
            }

            output.appendln(labels)

            var markers = " ".repeat(rangeEnd)
                    .replaceRange(rangeBaseline, rangeBaseline + 1, RANGE_CHAR)
                    .replaceRange(rangeEnd, rangeEnd, RANGE_CHAR)
            if (rangeStart != rangeBaseline) {
                markers = markers.replaceRange(rangeStart, rangeStart + 1, RANGE_CHAR)
            }
            output.appendln(markers)
        }
    }

    private val min = (minValue?.toDouble() ?: data.values.minBy { it.toDouble() }!!.toDouble())
            .coerceAtMost(0.0)
    private val max = (maxValue?.toDouble() ?: data.values.maxBy { it.toDouble() }!!.toDouble())
            .coerceAtLeast(0.0)
    private val maxLabelLength = data.keys.map { it.length }.max()!!
    private val maxValueLength = data.values.map { fmt.format(it).length }.max()!!
    private val segmentSize = ((max - min) / width).absoluteValue

    private val rangeStart = maxLabelLength + maxValueLength + 5
    private val rangeBaseline = rangeStart + (if (min < 0) (min.absoluteValue / segmentSize).toInt() else 0)
    private val rangeEnd = rangeStart + width

    private fun valueStr(value: Number): String {
        if (!showValueLabels) {
            return " ".repeat(maxValueLength)
        }

        return if (value.toDouble() < 0.0)
            fmt.format(value).padStart(maxValueLength, ' ')
        else
            fmt.format(value)
    }

    private fun segments(count: Int) = barChar.toString().repeat(count)

    companion object {
        private val fmt = DecimalFormat("#.##########")
        private const val RANGE_CHAR = "▼"
    }
}