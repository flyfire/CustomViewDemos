package com.solarexsoft.solarexcustomview.customview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.solarexsoft.solarexcustomview.utils.Utils

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 20:59/2019/4/2
 *    Desc:
 * </pre>
 */

class PieChart @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defaultStyle: Int = 0) : View(context, attributeSet, defaultStyle) {
    var angles = ArrayList<Float>()
    lateinit var colors: Array<Int>
    var touchedIndex = -1
    val rectF = RectF()
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var translate = 0f
    val regions = ArrayList<Region>()
    val animator = ValueAnimator.ofFloat(0f, TRANSLATION)
    var isAnimating = false
    var downX = 0f
    var downY = 0f

    companion object {
        val TAG = "PieChart";
        val RADIUS = Utils.dp2px(100f)
        val TRANSLATION = Utils.dp2px(20f)
    }

    init {
        animator.duration = 500
        animator.addUpdateListener {
            translate = it.animatedValue as Float
            invalidate()
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                isAnimating = true
            }

            override fun onAnimationCancel(animation: Animator?) {
                super.onAnimationCancel(animation)
                isAnimating = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                isAnimating = false
            }
        })
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rectF.set(w / 2 - RADIUS, h / 2 - RADIUS, w / 2 + RADIUS, h / 2 + RADIUS)
    }

    override fun onDraw(canvas: Canvas) {
        val size = angles.size
        var startAngle = -90f
        regions.clear()
        for (i in 0 until size) {
            val path = Path()
            if (i == touchedIndex) {
                val dx = Math.cos(Math.toRadians((startAngle + angles[i] / 2).toDouble())) * translate
                val dy = Math.sin(Math.toRadians((startAngle + angles[i] / 2).toDouble())) * translate
                path.moveTo((width / 2).toFloat() + dx.toFloat(), (height / 2).toFloat() + dy.toFloat())
                rectF.offset(dx.toFloat(), dy.toFloat())
                path.arcTo(rectF, startAngle, angles[i])
                rectF.offset(-dx.toFloat(), -dy.toFloat())
            } else {
                path.moveTo((width / 2).toFloat(), (height / 2).toFloat())
                path.arcTo(rectF, startAngle, angles[i])
            }
            path.close()
            paint.color = colors[i]
            canvas.drawPath(path, paint)
            startAngle += angles[i]
            val region = Region()
            val computeRectF = RectF()
            path.computeBounds(computeRectF, true)
            region.setPath(path, Region(computeRectF.left.toInt(), computeRectF.top.toInt(), computeRectF.right.toInt(), computeRectF.bottom.toInt()))
            regions.add(region)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "isAnimating = $isAnimating")
        if (isAnimating) {
            return true
        }
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "x = ${event.x}, y = ${event.y}")
                downX = event?.x; downY = event?.y
                val size = regions.size
                for (i in 0 until size) {
                    if (regions[i].contains(downX.toInt(), downY.toInt())) {
                        touchedIndex = i
                        Toast.makeText(context, "$i touched", Toast.LENGTH_SHORT).show()
                        animator.start()
                    }
                }
                Log.d(TAG, "regions size = $size")
                Log.d(TAG, "touchedIndex = $touchedIndex")
            }
        }
        return true
    }

    public fun setAnglesAndColors(angle: ArrayList<Float>, color: Array<Int>) {
        assert(angle.size == color.size)
        assert(angle.reduce { acc, i -> acc + i } == 360f)
        angles = angle
        colors = color
        invalidate()
    }


}