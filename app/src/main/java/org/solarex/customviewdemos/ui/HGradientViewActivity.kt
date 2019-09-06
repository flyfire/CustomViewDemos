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
        val view1 = findViewById<HGradientViewExt>(R.id.gradient_view1)
        view1.maxValue = 120f
        view1.minValue = 80f
        view1.setDatas(100f, 90f)
        val view2 = findViewById<HGradientViewExt>(R.id.gradient_view2)
        view2.maxValue = 120f
        view2.minValue = 80f
        view2.setDatas(81f, 90f)
    }
}