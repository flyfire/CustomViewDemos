package org.solarex.customviewdemos.ui

import android.view.View
import com.solarexsoft.solarexbezier.Bezier1

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:08/2019/4/26
 *    Desc:
 * </pre>
 */

class BezierActivity : BaseCustomViewActivity() {
    override fun createContentView(): View {
        return Bezier1(this)
    }
}