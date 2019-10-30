package org.solarex.customviewdemos.ui

import android.util.Log
import android.view.MotionEvent
import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.widgets.detecttouchevent.DetectTouchEventView
import org.solarex.customviewdemos.widgets.detecttouchevent.TouchWithoutContentViewActivity
import java.lang.RuntimeException

class DetectTouchEventActivity : BaseCustomViewActivity() {
    val TAG = DetectTouchEventActivity::class.java.simpleName

    override fun getLayoutId(): Int {
        return org.solarex.customviewdemos.R.layout.activity_detect_touch_event
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "dispatchTouchEvent", RuntimeException("dispatchTouchEvent").fillInStackTrace());
        return super.dispatchTouchEvent(ev)
    }

    override fun initView() {
        super.initView()
        val dtev_2 = findViewById<DetectTouchEventView>(R.id.dtev_2)
        dtev_2.setOnClickListener {
            Log.d(TAG, "view clicked", RuntimeException("click").fillInStackTrace())
            TouchWithoutContentViewActivity.goTouchWithoutContentViewActivity(this)
        }
    }
}