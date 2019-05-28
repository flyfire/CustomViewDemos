package com.solarexsoft.solarexcustomview.customview

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 11:51/2019/5/19
 *    Desc:
 * </pre>
 */

class DragListenerView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) : ViewGroup(context, attributeSet, defStyle) {
    private val COLUMNS = 2
    private val ROWS = 3
    val orderedChildren = arrayListOf<View>()
    lateinit var draggedView: View
    val onDragListener = SolarexDragListener()

    init {
        setChildrenDrawingCacheEnabled(true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            orderedChildren.add(view)
            view.setOnLongClickListener(object : OnLongClickListener {
                override fun onLongClick(v: View?): Boolean {
                    draggedView = v!!
                    v.startDrag(null, DragShadowBuilder(v), v, 0)
                    return true
                }

            })
            view.setOnDragListener(onDragListener)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val specWidth = MeasureSpec.getSize(widthMeasureSpec);
        val specHeight = MeasureSpec.getSize(heightMeasureSpec);
        val childWidth = specWidth / COLUMNS
        val childHeight = specHeight / ROWS

        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY))
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0f
        var childTop = 0f
        val childWidth = width / COLUMNS
        val childHeight = height / ROWS
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            childLeft = (i % 2 * childWidth).toFloat()
            childTop = (i / 2 * childHeight).toFloat()
            view.layout(0, 0, childWidth, childHeight);
            view.translationX = childLeft
            view.translationY = childTop
        }
    }

    inner class SolarexDragListener : OnDragListener {
        override fun onDrag(v: View?, event: DragEvent?): Boolean {
            when (event?.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    if (event?.localState == v) {
                        v?.visibility = View.INVISIBLE
                    }
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    if (event?.localState != v) {
                        sort(v)
                    }
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    v?.visibility = View.VISIBLE
                }
            }
            return true
        }

    }

    fun sort(targetView: View?) {
        var draggedIndex = -1
        var targetIndex = -1

        for (i in 0 until childCount) {
            val view = orderedChildren[i]
            if (targetView == view) {
                targetIndex = i
            } else if (draggedView == view) {
                draggedIndex = i
            }
        }

        if (targetIndex < draggedIndex) {
            orderedChildren.removeAt(draggedIndex)
            orderedChildren.add(targetIndex, draggedView)
        } else if (targetIndex > draggedIndex) {
            orderedChildren.removeAt(draggedIndex)
            orderedChildren.add(targetIndex, draggedView)
        }

        var childLeft = 0
        var childTop = 0
        val childWidth = width/COLUMNS
        val childHeight = height/ROWS
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            childLeft = i%2*childWidth
            childTop = i/2*childHeight
            view.animate()
                    .translationX(childLeft.toFloat())
                    .translationY(childTop.toFloat()).duration = 150
        }
    }
}