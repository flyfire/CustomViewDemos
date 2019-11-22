package org.solarex.customviewdemos.ui

import android.util.Log
import android.view.MotionEvent
import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.widgets.detecttouchevent.DetectTouchEventView
import org.solarex.customviewdemos.widgets.detecttouchevent.TouchWithoutContentViewActivity

class DetectTouchEventActivity : BaseCustomViewActivity() {
    val TAG = DetectTouchEventActivity::class.java.simpleName

    override fun getLayoutId(): Int {
        return org.solarex.customviewdemos.R.layout.activity_detect_touch_event
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        Log.d(TAG, "dispatchTouchEvent action = " + ev?.action, RuntimeException("dispatchTouchEvent").fillInStackTrace());
        Log.d(TAG, "dispatchTouchEvent action = " + ev?.action);
        val superConsume = super.dispatchTouchEvent(ev)
        Log.d(TAG, "dispatchTouchEvent superConsume = $superConsume")
        return superConsume
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        Log.d(TAG, "onTouchEvent action = ${event?.action}", RuntimeException("onTouchEvent").fillInStackTrace())
        Log.d(TAG, "onTouchEvent action = ${event?.action}")
        val superConsume = super.onTouchEvent(event)
        Log.d(TAG, "onTouchEvent superConsume = $superConsume")
        return superConsume
    }

    override fun initView() {
        super.initView()
        val dtev_1 = findViewById<DetectTouchEventView>(R.id.dtev_1)
        val dtev_2 = findViewById<DetectTouchEventView>(R.id.dtev_2)
        val dtev_3 = findViewById<DetectTouchEventView>(R.id.dtev_3)
        val dtev_4 = findViewById<DetectTouchEventView>(R.id.dtev_4)
        val dtev_5 = findViewById<DetectTouchEventView>(R.id.dtev_5)
        val dtev_6 = findViewById<DetectTouchEventView>(R.id.dtev_6)
        dtev_3.setOnClickListener {
            Log.d(TAG, "view clicked", RuntimeException("click").fillInStackTrace())
            TouchWithoutContentViewActivity.goTouchWithoutContentViewActivity(this)
        }
        Log.d(TAG, "1-->${dtev_1.id}")
        Log.d(TAG, "2-->${dtev_2.id}")
        Log.d(TAG, "3-->${dtev_3.id}")
        Log.d(TAG, "4-->${dtev_4.id}")
        Log.d(TAG, "5-->${dtev_5.id}")
        Log.d(TAG, "6-->${dtev_6.id}")
    }
}