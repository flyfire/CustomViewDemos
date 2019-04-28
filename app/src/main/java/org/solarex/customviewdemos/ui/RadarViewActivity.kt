package org.solarex.customviewdemos.ui

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.solarexsoft.radarview.RadarData
import com.solarexsoft.radarview.RadarView
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 19:40/2019/4/28
 *    Desc:
 * </pre>
 */

class RadarViewActivity : BaseCustomViewActivity(), OnClickListener {

    lateinit var radarView: RadarView

    override fun getLayoutId(): Int {
        return R.layout.activity_radarview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        radarView = findViewById(R.id.radarView)
        val dataList = ArrayList<RadarData>()
        for (i in 1 until 10) {
            val data = RadarData("标题${i}", (i * 11).toDouble())
            dataList.add(data)
        }
        radarView.setData(dataList)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
    }

    fun test(count: Int) {
        val dataList = ArrayList<RadarData>()
        for (i in 0 until count) {
            val data = RadarData("标题${i + 1}", ((i + 1) * 11).toDouble())
            dataList.add(data)
        }
        radarView.setData(dataList)
    }

    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.btn3 -> test(3)
            R.id.btn4 -> test(4)
            R.id.btn5 -> test(5)
            R.id.btn6 -> test(6)
            R.id.btn7 -> test(7)
            R.id.btn8 -> test(8)
            R.id.btn9 -> test(9)
        }
    }

}