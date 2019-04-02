package com.solarexsoft.solarexcustomview.customview

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.solarexsoft.solarexcustomview.R
import com.solarexsoft.solarexcustomview.utils.Utils

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:07/2019/4/1
 *    Desc:
 * </pre>
 */

class AvaterView(context: Context, attributeSet: AttributeSet?, defaultStyle: Int) : View(context, attributeSet, defaultStyle) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    companion object {
        val WIDTH = Utils.dp2px(220f)
        val PADDING = Utils.dp2px(20f)
        val EDGE_WIDTH = Utils.dp2px(10f)
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    lateinit var bitmap: Bitmap
    val savedArea = RectF()

    init {
        val typedArray =  context.obtainStyledAttributes(attributeSet, R.styleable.AvaterView);
        if (typedArray != null) {
            val resourceId = typedArray.getResourceId(R.styleable.AvaterView_drawable, R.drawable.abc_btn_radio_material);
            bitmap = Utils.getBitmap(context.resources, resourceId, WIDTH.toInt())
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        savedArea.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(savedArea, paint)
        val saveLayer = canvas.saveLayer(savedArea, paint)
        canvas.drawOval(PADDING + EDGE_WIDTH, PADDING + EDGE_WIDTH, PADDING + WIDTH - EDGE_WIDTH, PADDING + WIDTH - EDGE_WIDTH, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveLayer)
    }
}





























