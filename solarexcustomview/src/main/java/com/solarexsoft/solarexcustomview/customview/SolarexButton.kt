package com.solarexsoft.solarexcustomview.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Button

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 15:28/2019/5/17
 *    Desc:
 * </pre>
 */
 
class SolarexButton @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) : Button(context, attributeSet, defStyle) {
    val tag = SolarexButton::class.java.simpleName
    var TAG = ""
    init {
        TAG = "$tag-$id"
    }
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "touch down (${ev?.x},${ev?.y})")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "touch move (${ev?.x},${ev?.y})")
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "touch up (${ev?.x},${ev?.y})")
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.d(TAG, "touch cancel (${ev?.x},${ev?.y})")
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                Log.d(TAG, "touch action pointer down action index = ${ev?.actionIndex}")
                Log.d(TAG, "touch action pointer down ${ev?.getX(ev?.actionIndex)}, ${ev?.getX(ev?.actionIndex)}")
                Log.d(TAG, "touch action pointer down pointer id = ${ev?.getPointerId(ev?.actionIndex)}")
            }
            MotionEvent.ACTION_POINTER_UP -> {
                Log.d(TAG, "touch action pointer up action index = ${ev?.actionIndex}")
                Log.d(TAG, "touch action pointer up ${ev?.getX(ev?.actionIndex)}, ${ev?.getX(ev?.actionIndex)}")
                Log.d(TAG, "touch action pointer up pointer id = ${ev?.getPointerId(ev?.actionIndex)}")
            }
        }
        val result = super.onTouchEvent(ev)
        Log.d(TAG, "touch result = $result, return true")
        return true
    }
}