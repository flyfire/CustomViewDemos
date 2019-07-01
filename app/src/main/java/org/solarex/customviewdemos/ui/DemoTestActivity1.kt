package org.solarex.customviewdemos.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ReplacementSpan
import android.util.Log
import android.widget.TextView
import com.solarexsoft.solarexcustomview.utils.Utils
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 11:26/2019/6/19
 *    Desc:
 * </pre>
 */
 
class DemoTestActivity1 : BaseCustomViewActivity() {
    val TAG = DemoTestActivity1::class.java.simpleName

    override fun getLayoutId(): Int {
        return R.layout.activity_demo_test;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv_content = findViewById<TextView>(R.id.tv_content)
        val str = String.format(resources.getString(R.string.share_content_format), resources.getString(R.string.content))
        Log.d(TAG, "str = $str")
        val format = SpannableString(str)
        format.setSpan(AbsoluteSizeSpan(Utils.dp2px(10f).toInt()), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        format.setSpan(RoundBackgroundColorSpan(Color.parseColor("#1AD9CA"), Color.parseColor("#ffffff")),0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        format.setSpan(AbsoluteSizeSpan(Utils.dp2px(12f).toInt()), 2, format.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_content.text = format
    }
}

class RoundBackgroundColorSpan(private val bgColor: Int, private val textColor: Int) : ReplacementSpan() {
    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        //设置宽度为文字宽度加16dp
        return (paint.measureText(text, start, end) + Utils.dp2px(16f)).toInt()
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val originalColor = paint.getColor()
        paint.color = this.bgColor
        //画圆角矩形背景
        canvas.drawRoundRect(RectF(x,
                top + Utils.dp2px(3f),
                x + (paint.measureText(text, start, end) + Utils.dp2px(16f)),
                bottom - Utils.dp2px(1f)),

                Utils.dp2px(4f),
                Utils.dp2px(4f),
                paint)
        paint.setColor(this.textColor)
        //画文字,两边各增加8dp
        canvas.drawText(text, start, end, (x + Utils.dp2px(8f)).toFloat(), y.toFloat(), paint)
        //将paint复原
        paint.color = originalColor
    }
}