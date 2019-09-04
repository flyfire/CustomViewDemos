package org.solarex.customviewdemos.ui

import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.widgets.HGradientViewExt

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 16:40/2019-09-04
 *    Desc:
 * </pre>
 */
 
class HGradientViewActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_horizontal_gradient
    }

    override fun initView() {
        val view = findViewById<HGradientViewExt>(R.id.gradient_view)
        view.maxValue = 120f
        view.minValue = 80f

    }
}