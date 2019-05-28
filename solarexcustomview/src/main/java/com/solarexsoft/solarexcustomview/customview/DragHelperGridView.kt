package com.solarexsoft.solarexcustomview.customview

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:35/2019/5/18
 *    Desc:
 * </pre>
 */

class DragHelperGridView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) : ViewGroup(context, attributeSet, defStyle) {
    val ROWS = 3
    val COLUMNS = 2

    lateinit var dragHelper: ViewDragHelper

    init {
        dragHelper = ViewDragHelper.create(this, DragCallback())
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val specWidth = MeasureSpec.getSize(widthMeasureSpec);
        val specHeight = MeasureSpec.getSize(heightMeasureSpec);
        val childWidth = specWidth / COLUMNS;
        val childHeight = specHeight / ROWS;
        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY))
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childTop = 0
        val childWidth = width / COLUMNS
        val childHeight = height / ROWS
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            childLeft = i % 2 * childWidth
            childTop = i / 2 * childHeight
            view.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    inner class DragCallback : ViewDragHelper.Callback() {
        var captureLeft = 0
        var captureTop = 0

        override fun tryCaptureView(child: View?, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionHorizontal(child: View?, left: Int, dx: Int): Int {
            return left
        }

        override fun clampViewPositionVertical(child: View?, top: Int, dy: Int): Int {
            return top
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onViewCaptured(capturedChild: View?, activePointerId: Int) {
            capturedChild?.elevation = elevation + 1
            captureLeft = capturedChild!!.left
            captureTop = capturedChild!!.top
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onViewDragStateChanged(state: Int) {
            if (state == ViewDragHelper.STATE_IDLE) {
                val view = dragHelper.capturedView
                view.elevation = view.elevation - 1
            }
        }

        override fun onViewReleased(releasedChild: View?, xvel: Float, yvel: Float) {
            dragHelper.settleCapturedViewAt(captureLeft, captureTop)
            postInvalidateOnAnimation()
        }


    }
}

