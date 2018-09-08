package com.garyfimo.barchartexample

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarChart()
        configurarData()
    }

    private fun configurarData() {
        val datos: MutableList<Dato> = mutableListOf()
        for (i in 1..6) {
            val random = i * (i - 1)
            datos.add(Dato("C$i", random))
        }
        setData(datos)
    }

    private fun configurarChart() {
        chart.setDrawBorders(false)
        chart.setDrawGridBackground(false)
        chart.axisLeft.setDrawGridLines(false)
        chart.axisLeft.isEnabled = false
        chart.axisLeft.axisMinimum = 0f
        chart.axisRight.isEnabled = false
        chart.axisRight.axisMinimum = 0f
        chart.axisRight.setDrawGridLines(false)
        chart.xAxis.setDrawGridLines(false)
//        chart.xAxis.isEnabled = false
        chart.setTouchEnabled(false)
        chart.description = null
        chart.legend.isEnabled = false
        chart.setDrawBarShadow(true)
    }

    private fun setData(datos: List<Dato>) {
        val barEntries: MutableList<BarEntry> = mutableListOf()
        datos.forEachIndexed { index, dato ->
            barEntries.add(BarEntry(index.toFloat(), dato.puntaje.toFloat()))
        }
        val dataSet = BarDataSet(barEntries, "")
        dataSet.setDrawValues(false)
        dataSet.color = R.color.primary_material_dark
        val barData = BarData(dataSet)
        barData.barWidth = 0.5f
        chart.data = barData
        chart.xAxis.valueFormatter = CampaniaAxisValueFormatter(datos)
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.setFitBars(true)
        chart.invalidate()
    }

    inner class CampaniaAxisValueFormatter(val datos: List<Dato>) : IAxisValueFormatter {

        override fun getFormattedValue(value: Float, axis: AxisBase?): String {
            return datos[value.toInt()].campania
        }
    }

}
