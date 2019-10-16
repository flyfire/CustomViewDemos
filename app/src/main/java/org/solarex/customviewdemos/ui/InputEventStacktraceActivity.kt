package org.solarex.customviewdemos.ui

import android.os.SystemClock
import android.util.Log
import android.view.View
import org.solarex.customviewdemos.R

/**
 * Author: Solarex
 * Date: 2019/10/10
 * Desc:
 */
class InputEventStacktraceActivity : BaseCustomViewActivity() {
    val TAG = "InputEventStacktrace"
    var delay = 10000L;
    override fun getLayoutId(): Int {
        return R.layout.activity_input_event_stacktrace
    }

    override fun initView() {
        val view = findViewById<View>(R.id.view_placeholder)
        view.setOnClickListener {
            Log.d(TAG, "current thread -> ${Thread.currentThread().name}")
            SystemClock.sleep(delay)
        }
    }
}