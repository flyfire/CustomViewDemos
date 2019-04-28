package org.solarex.customviewdemos.ui

import android.os.Bundle
import com.solarexsoft.solarexzoomimageview.SolarexZoomImageViewNew
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 20:07/2019/4/28
 *    Desc:
 * </pre>
 */
 
class ZoomImageViewActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_zoomimageview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val szi = findViewById<SolarexZoomImageViewNew>(R.id.szi).also {
            it.setImageResource(R.mipmap.ic_launcher)
        }
    }
}