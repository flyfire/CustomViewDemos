package org.solarex.customviewdemos.ui

import android.graphics.Color
import android.os.Bundle
import com.solarexsoft.solarexcustomview.customview.hencoder.PieChart
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:25/2019/4/2
 *    Desc:
 * </pre>
 */
 
class PieChartActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_piechart
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pieChart = findViewById(R.id.piechart) as PieChart
        val angles = ArrayList<Float>()
        angles.add(60f)
        angles.add(100f)
        angles.add(120f)
        angles.add(80f)
        pieChart.setAnglesAndColors(angles, arrayOf(Color.parseColor("#2979FF"), Color.parseColor("#C2185B"),
                Color.parseColor("#009688"), Color.parseColor("#FF8F00")))
    }
}