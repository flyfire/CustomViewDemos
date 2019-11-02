package org.solarex.customviewdemos.widgets.detecttouchevent

import android.util.Log
import android.view.MotionEvent
import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.ui.BaseCustomViewActivity

class DetectTouchEventActivityEx : BaseCustomViewActivity() {
    val TAG = DetectTouchEventActivityEx::class.java.simpleName

    override fun getLayoutId(): Int {
        return org.solarex.customviewdemos.R.layout.layout_detect_touch_ex
    }

    override fun initView() {
        super.initView()
        val dteve_1 = findViewById<DetectTouchEventViewEx>(R.id.dteve_1)
        val dteve_2 = findViewById<DetectTouchEventViewEx>(R.id.dteve_2)
        val dteve_3 = findViewById<DetectTouchEventViewEx>(R.id.dteve_3)
        Log.d(TAG, "ex-1-> ${dteve_1.id}")
        Log.d(TAG, "ex-2-> ${dteve_2.id}")
        Log.d(TAG, "ex-3-> ${dteve_3.id}")
        Log.d(TAG, "DOWN = ${MotionEvent.ACTION_DOWN}")
        Log.d(TAG, "MOVE = ${MotionEvent.ACTION_MOVE}")
        Log.d(TAG, "UP = ${MotionEvent.ACTION_UP}")
    }
}