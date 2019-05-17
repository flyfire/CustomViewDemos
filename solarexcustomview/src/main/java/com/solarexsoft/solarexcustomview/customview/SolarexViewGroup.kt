package com.solarexsoft.solarexcustomview.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.ViewGroup
import com.solarexsoft.solarexcustomview.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 14:59/2019/5/17
 *    Desc:
 * </pre>
 */

class SolarexViewGroup @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) : ViewGroup(context, attributeSet, defStyle) {
    var NUM_COLUMNS = -1
    val TAG = SolarexViewGroup::class.java.simpleName

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SolarexViewGroup);
        NUM_COLUMNS = typedArray.getInt(R.styleable.SolarexViewGroup_columns, 2)
        typedArray.recycle()
        Log.d(TAG, "childCount = $childCount")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d(TAG, "onMeasure childCount = $childCount")
        val width = MeasureSpec.getSize(widthMeasureSpec)
        measureChildren(MeasureSpec.makeMeasureSpec(width / NUM_COLUMNS, MeasureSpec.EXACTLY), heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val childWidth = width / NUM_COLUMNS;
        var left = 0
        var top = 0
        var right = childWidth
        var bottom = height
        for (i in 0 until NUM_COLUMNS) {
            val view = getChildAt(i)
            view.layout(left, top, right, bottom)
            left += childWidth
            right += childWidth
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.actionMasked) {
            ACTION_DOWN -> {
                Log.d(TAG, "intercept down (${ev?.x},${ev?.y})")
            }
            ACTION_MOVE -> {
                Log.d(TAG, "intercept move (${ev?.x},${ev?.y})")
            }
            ACTION_UP -> {
                Log.d(TAG, "intercept up (${ev?.x},${ev?.y})")
            }
            ACTION_CANCEL -> {
                Log.d(TAG, "intercept cancel (${ev?.x},${ev?.y})")
            }
            ACTION_POINTER_DOWN -> {
                Log.d(TAG, "intercept action pointer down action index = ${ev?.actionIndex}")
                Log.d(TAG, "intercept action pointer down ${ev?.getX(ev?.actionIndex)}, ${ev?.getX(ev?.actionIndex)}")
                Log.d(TAG, "intercept action pointer down pointer id = ${ev?.getPointerId(ev?.actionIndex)}")
            }
            ACTION_POINTER_UP -> {
                Log.d(TAG, "intercept action pointer up action index = ${ev?.actionIndex}")
                Log.d(TAG, "intercept action pointer up ${ev?.getX(ev?.actionIndex)}, ${ev?.getX(ev?.actionIndex)}")
                Log.d(TAG, "intercept action pointer up pointer id = ${ev?.getPointerId(ev?.actionIndex)}")
            }
        }
        val result = super.onInterceptTouchEvent(ev)
        Log.d(TAG, "intercept result = $result")
        return result
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.actionMasked) {
            ACTION_DOWN -> {
                Log.d(TAG, "touch down (${ev?.x},${ev?.y})")
            }
            ACTION_MOVE -> {
                Log.d(TAG, "touch move (${ev?.x},${ev?.y})")
            }
            ACTION_UP -> {
                Log.d(TAG, "touch up (${ev?.x},${ev?.y})")
            }
            ACTION_CANCEL -> {
                Log.d(TAG, "touch cancel (${ev?.x},${ev?.y})")
            }
            ACTION_POINTER_DOWN -> {
                Log.d(TAG, "touch action pointer down action index = ${ev?.actionIndex}")
                Log.d(TAG, "touch action pointer down ${ev?.getX(ev?.actionIndex)}, ${ev?.getX(ev?.actionIndex)}")
                Log.d(TAG, "touch action pointer down pointer id = ${ev?.getPointerId(ev?.actionIndex)}")
            }
            ACTION_POINTER_UP -> {
                Log.d(TAG, "touch action pointer up action index = ${ev?.actionIndex}")
                Log.d(TAG, "touch action pointer up ${ev?.getX(ev?.actionIndex)}, ${ev?.getX(ev?.actionIndex)}")
                Log.d(TAG, "touch action pointer up pointer id = ${ev?.getPointerId(ev?.actionIndex)}")
            }
        }
        val result = super.onTouchEvent(ev)
        Log.d(TAG, "touch result = $result")
        return result
    }
}