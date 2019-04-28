package org.solarex.customviewdemos.ui

import android.os.Bundle
import com.solarexsoft.solarexdouyinloadingview.SolarexDouyinLoadingView
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:04/2019/4/28
 *    Desc:
 * </pre>
 */
 
class DouyinLoadingActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_douyinloadingview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loadingview = findViewById<SolarexDouyinLoadingView>(R.id.sdlv_loading)
        loadingview.start()
    }
}