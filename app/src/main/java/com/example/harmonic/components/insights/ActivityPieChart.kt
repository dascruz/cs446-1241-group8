package com.example.harmonic.components.insights

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

@Composable
fun ActivityPieChart(activeCount: Int, inactiveCount: Int, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val chart = PieChart(context).apply {
                description.text = "Active vs Inactive Instances"
                description.isEnabled = true
                setNoDataText("No data available")
                setTouchEnabled(true)
                setDrawEntryLabels(true)
                setUsePercentValues(true)
            }

            val entries = listOf(
                PieEntry(activeCount.toFloat(), "Active"),
                PieEntry(inactiveCount.toFloat(), "Inactive")
            )

            val dataSet = PieDataSet(entries, "Instances").apply {
                setColors(*ColorTemplate.COLORFUL_COLORS)
            }

            val data = PieData(dataSet)
            chart.data = data

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