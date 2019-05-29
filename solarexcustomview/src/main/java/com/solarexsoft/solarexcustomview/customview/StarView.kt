package com.solarexsoft.solarexcustomview.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.solarexsoft.solarexcustomview.utils.Utils

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:27/2019/5/28
 *    Desc:
 * </pre>
 */
 
class StarView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) : View(context, attributeSet, defStyle) {
    // 画round rect 背景，画 五角星 画 得分 text
    val DEFAULT_WIDTH = Utils.dp2px(68f);
    val DEFAULT_HEIGHT = Utils.dp2px(26f);
    val DEFAULT_COLOR = Color.parseColor("#7C4E16");
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG);
    val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val STAR_WIDTH = Utils.dp2px(15f)
    val STAR_HEIGHT = Utils.dp2px(15f)
    val GAP_BETWEEN_TEXT_AND_STAR = Utils.dp2px(4f)
    val STROKE_WIDTH = Utils.dp2px(1f)
    val mRect = RectF()
    val RECT_RADIUS = Utils.dp2px(13f)
    var mText = "4.9"
    var mTextWidth = 0f;
    var mColor = DEFAULT_COLOR
    lateinit var path: Path

    init {
        mPaint.strokeWidth = STROKE_WIDTH
        mPaint.style = Paint.Style.STROKE
        mPaint.color = DEFAULT_COLOR
        mTextPaint.color = DEFAULT_COLOR
        mTextPaint.textSize = Utils.dp2px(13f)
        mTextPaint.style = Paint.Style.FILL
        mTextPaint.strokeWidth = Utils.dp2px(1f)

        val t = STAR_WIDTH / 2.0f;
        val r = 360/5;
        val angle = Math.PI / 180;
        path = Path()
        val a = arrayOf(Math.cos(0 * r * angle) * t, Math.sin(0 * r * angle) * t)
        val b = arrayOf(Math.cos(1 * r * angle) * t, Math.sin(1 * r * angle) * t)
        val c = arrayOf(Math.cos(2 * r * angle) * t, -Math.sin(2 * r * angle) * t)
        val d = arrayOf(Math.cos(3 * r * angle) * t, -Math.sin(3 * r * angle) * t)
        val e = arrayOf(Math.cos(4 * r * angle) * t, Math.sin(4 * r * angle) * t)
        path.moveTo(a[0].toFloat(), a[1].toFloat())
        path.lineTo(d[0].toFloat(), d[1].toFloat())
        path.lineTo(e[0].toFloat(), e[1].toFloat())
        path.lineTo(b[0].toFloat(), b[1].toFloat())
        path.lineTo(c[0].toFloat(), c[1].toFloat())
        path.close()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = Utils.measure(widthMeasureSpec, DEFAULT_WIDTH.toInt());
        val height = Utils.measure(heightMeasureSpec, DEFAULT_HEIGHT.toInt());
        setMeasuredDimension(width, height);
    }

    fun setTextAndColor(text: String, color: Int) {
        mText = text
        mColor = color
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        mPaint.color = mColor
        mTextPaint.color = mColor
        mRect.set(STROKE_WIDTH, STROKE_WIDTH, width.toFloat() - STROKE_WIDTH, height.toFloat() - STROKE_WIDTH)
        // 画背景圆角矩形
        mPaint.style = Paint.Style.STROKE
        canvas.drawRoundRect(mRect, RECT_RADIUS, RECT_RADIUS, mPaint)
        mTextWidth = mTextPaint.measureText(mText)
        val left = (width - STAR_WIDTH - GAP_BETWEEN_TEXT_AND_STAR - mTextWidth)*1.0f / 2.0f
        // 画五角星
        drawStar(left, canvas)
        // 画文本
        drawText(left+GAP_BETWEEN_TEXT_AND_STAR+STAR_WIDTH, canvas)
    }

    private fun drawStar(left: Float, canvas: Canvas) {
        canvas.save();
        canvas.translate(left + STAR_WIDTH/2, height/2.0f)
        canvas.rotate(-90f)
        mPaint.style = Paint.Style.FILL
        canvas.drawPath(path, mPaint)
        canvas.restore();
    }

    private fun drawText(left: Float, canvas: Canvas) {
        val baseline = (height - mTextPaint.fontMetrics.ascent - mTextPaint.fontMetrics.descent)/2.0f;
        canvas.drawText(mText, left, baseline, mTextPaint)
    }
}