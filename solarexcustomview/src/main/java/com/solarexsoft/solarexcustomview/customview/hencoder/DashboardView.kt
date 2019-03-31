package com.solarexsoft.solarexcustomview.customview.hencoder

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.solarexsoft.solarexcustomview.utils.Utils

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 21:36/2019/3/31
 *    Desc:
 * </pre>
 */

class DashboardView(context: Context, attrs: AttributeSet?, defaultStyle: Int) : View(context, attrs, defaultStyle) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    companion object {
        val ANGEL = 200
        val RADIUS = Utils.dp2px(150f)
        val LENGTH = Utils.dp2px(100f)
    }

    var paint = Paint()
    var dashPath = Path()
    var arcPath = Path()
    lateinit var pathMeasure: PathMeasure;
    lateinit var pathEffect: PathDashPathEffect;
    lateinit var rectF: RectF

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = Utils.dp2px(2f)
        dashPath.addRect(0f, 0f, Utils.dp2px(2f), Utils.dp2px(10f), Path.Direction.CW)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        arcPath.addArc((w / 2 - RADIUS).toFloat(), (h / 2 - RADIUS).toFloat(), (w / 2 + RADIUS).toFloat(), (h / 2 + RADIUS).toFloat(), (90 + ANGEL / 2).toFloat(), (360 - ANGEL).toFloat())
        pathMeasure = PathMeasure(arcPath, false)
        pathEffect = PathDashPathEffect(dashPath, (pathMeasure.length - Utils.dp2px(2f) / 20), 0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(arcPath, paint)
        paint.setPathEffect(pathEffect)
        canvas.drawPath(arcPath, paint)
        paint.setPathEffect(null)

        canvas.drawLine((width/2).toFloat(), (height/2).toFloat(), (Math.cos(Math.toRadians(getAngelFromMark(5).toDouble()))*LENGTH+width/2).toFloat(), (Math.sin(Math.toRadians(getAngelFromMark(5).toDouble()))* LENGTH+height/2).toFloat(), paint)
    }

    private fun getAngelFromMark(mark: Int): Float {
        return (90 + ANGEL/2 + (360- ANGEL)*mark/20).toFloat()
    }
}


































































