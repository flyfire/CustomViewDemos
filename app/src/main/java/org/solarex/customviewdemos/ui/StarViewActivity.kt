package org.solarex.customviewdemos.ui

import android.graphics.Color
import android.os.Bundle
import com.solarexsoft.solarexcustomview.customview.StarView
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 11:56/2019/5/29
 *    Desc:
 * </pre>
 */
 
class StarViewActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_starview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val starView = findViewById<StarView>(R.id.sv)
        starView.setTextAndColor("5.0", Color.parseColor("#7C4E16"))
    }
}