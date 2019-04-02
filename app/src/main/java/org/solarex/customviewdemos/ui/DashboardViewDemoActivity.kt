package org.solarex.customviewdemos.ui

import android.view.View

import com.solarexsoft.solarexcustomview.customview.DashboardView

/**
 * <pre>
 * Author: houruhou
 * CreatAt: 22:14/2019/3/31
 * Desc:
</pre> *
 */

class DashboardViewDemoActivity : BaseCustomViewActivity() {
    override fun createContentView(): View {
        return DashboardView(this)
    }
}
