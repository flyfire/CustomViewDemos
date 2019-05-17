package com.solarexsoft.solarexcustomview.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:07/2019/5/17
 *    Desc:
 * </pre>
 */

class TwoPager @JvmOverloads constructor(context: Context, attributes: AttributeSet? = null, defStyle: Int = 0) : ViewGroup(context, attributes, defStyle) {
    lateinit var viewConfiguration: ViewConfiguration
    lateinit var velocityTracker: VelocityTracker
    lateinit var overScroller: OverScroller
    var maxVel = 0
    var minVel = 0
    var downX = 0f
    var downScrollX = 0
    var isScrolling = false
    var pageTouchSlop = 0
    val TAG = TwoPager::class.java.simpleName

    init {
        viewConfiguration = ViewConfiguration.get(context)
        velocityTracker = VelocityTracker.obtain()
        maxVel = viewConfiguration.scaledMaximumFlingVelocity
        minVel = viewConfiguration.scaledMinimumFlingVelocity
        overScroller = OverScroller(context)
        pageTouchSlop = viewConfiguration.scaledPagingTouchSlop
        Log.d(TAG, "minVel = $minVel,maxVel = $maxVel, pageTouchSlop = $pageTouchSlop")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childTop = 0
        var childRight = width
        var childBottom = height
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.layout(childLeft, childTop, childRight, childBottom)
            childLeft += width
            childRight += width
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var result = false

        when (ev?.actionMasked) {
            ACTION_DOWN -> {
                downX = ev.x
                isScrolling = false
                Log.d(TAG, "intercept down downX = $downX")
            }
            ACTION_MOVE -> {
                val dx = ev.x - downX
                Log.d(TAG, "intercept move dx = $dx, isScrolling = $isScrolling")
                if (!isScrolling) {
                    if (Math.abs(dx) > pageTouchSlop) {
                        result = true
                        isScrolling = true
                        parent.requestDisallowInterceptTouchEvent(true)
                    }
                }
            }
        }
        Log.d(TAG, "intercept result = $result")
        return result
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.actionMasked == ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)

        when (event?.actionMasked) {
            ACTION_DOWN -> {
                downX = event.x
                downScrollX = scrollX
            }
            ACTION_MOVE -> {
                Log.d(TAG, "touch move downScrollX = $downScrollX")
                var dx = downX - event.x + downScrollX
                Log.d(TAG, "touch move dx = $dx")
                if (dx > width) {
                    dx = width.toFloat()
                } else if (dx < 0) {
                    dx = 0f
                }
                Log.d(TAG, "touch move dx = $dx")
                scrollTo(dx.toInt(), 0)
            }
            ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000, maxVel.toFloat())
                var targetPage = 0
                val xVelocity = velocityTracker.xVelocity
                Log.d(TAG, "touch up xVel = $xVelocity")
                if (Math.abs(xVelocity) < minVel) {
                    // 轻轻滑动
                    targetPage = if (scrollX < width/2) 0 else 1
                } else {
                    // xvel 如何计算的 是downx - event.x 还是反过来?
                    targetPage = if (xVelocity > 0) 0 else 1
                }
                Log.d(TAG, "touch up targetpage = $targetPage")
                val dx = if (targetPage == 1) width - scrollX else -scrollX
                Log.d(TAG, "touch up dx = $dx")
                overScroller.startScroll(scrollX, 0, dx, 0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    override fun computeScroll() {
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }
}