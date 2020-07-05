package org.solarex.customviewdemos.widgets

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:13/2020/7/5
 *    Desc:
 * </pre>
 */
val Float.dp
    get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
    )

class BubbleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val path: Path = Path()
    private val rectF: RectF = RectF()
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.style = Paint.Style.FILL
        paint.color = context.resources.getColor(android.R.color.holo_blue_light)
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.rewind()
        rectF.set(0.0f, 0.0f, (w/2).toFloat(), h.toFloat())
        path.moveTo(w.toFloat(), 0.0f)
        path.lineTo((w/2).toFloat(), 0.0f)
        path.cubicTo(0.0f, 0.0f, 0.0f, h.toFloat(), (w/2).toFloat(), h.toFloat())
        path.lineTo(w.toFloat(), h.toFloat())
        path.close()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}