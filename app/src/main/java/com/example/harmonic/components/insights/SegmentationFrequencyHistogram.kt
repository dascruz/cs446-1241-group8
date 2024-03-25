package com.example.harmonic.components.insights

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@Composable
fun SegmentationFrequencyHistogram(
    countOccurrences: Map<Int, Int>,
    entries: List<BarEntry>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val chart = BarChart(context).apply {
                description.text = "Frequency of Timer Job Segmentation Values"
                description.isEnabled = true
                setNoDataText("No data available")
                setTouchEnabled(true)
                setDrawGridBackground(false)
                isDragEnabled = true
                setScaleEnabled(true)
                setPinchZoom(true)

                xAxis.valueFormatter = IndexAxisValueFormatter(
                    countOccurrences.keys.toList().sorted().map { it.toString() })
                xAxis.setGranularity(1f)
                axisLeft.setGranularity(1f)
                axisRight.setGranularity(1f)
            }

            // Update the chart data with the transformed data
            val barDataSet = BarDataSet(entries, "Frequency").apply {
                setDrawValues(true)
            }

            val data = BarData(barDataSet)
            chart.data = data

            // Refresh the chart
            chart.invalidate()

            chart
        },
        update = { view ->
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    )
}