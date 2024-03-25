package com.example.harmonic.components.insights

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

import com.example.harmonic.models.instances.TimerInstanceModel

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.ValueFormatter

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CreationTimeLineChart(timerInstances: List<TimerInstanceModel>, modifier: Modifier = Modifier) {
    AndroidView(modifier = modifier, factory = { context ->
        val chart = LineChart(context).apply {
            description.text = "Creation Time of Timer Instances"
            description.isEnabled = true
            setNoDataText("No data available")
            setTouchEnabled(true)
            setDrawGridBackground(false)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)

            val yAxisRight: YAxis = this.axisRight
            yAxisRight.setDrawAxisLine(false)
            yAxisRight.setDrawLabels(false)

            val yAxisLeft: YAxis = this.axisLeft
            yAxisLeft.granularity = 1f
            yAxisLeft.valueFormatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return value.toInt().toString()
                }
            }

            val xAxis: XAxis = this.xAxis
            xAxis.valueFormatter = object : ValueFormatter() {
                private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    val date = Date(value.toLong())
                    return dateFormat.format(date)
                }
            }
            xAxis.setLabelCount(10, true) // Limit the number of labels on the x-axis
            xAxis.labelRotationAngle = -45f // Rotate the labels by -45 degrees
        }

        val sortedInstances = timerInstances.sortedBy { it.creationDateTime }
        val entries = sortedInstances.map { instance ->
            val timestamp = instance.creationDateTime.toEpochMilli()
            Entry(timestamp.toFloat(), instance.getNumSegments().toFloat())
        }

        val dataSet = LineDataSet(entries, "Creation Time")
        chart.data = LineData(dataSet)
        chart.invalidate()
        chart
    }, update = { view ->
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
    })
}