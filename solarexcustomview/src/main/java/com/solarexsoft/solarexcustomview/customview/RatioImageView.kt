package com.solarexsoft.solarexcustomview.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.solarexsoft.solarexcustomview.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:19/2019/5/28
 *    Desc:
 * </pre>
 */
 
class RatioImageView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) : ImageView(context, attributeSet, defStyle) {
    val DEFAULT_WIDTH = 670;
    val DEFAULT_HEIGHT = 300;

    var mWidth = DEFAULT_WIDTH;
    var mHeight = DEFAULT_HEIGHT;

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RatioImageView);
        mWidth = typedArray.getInt(R.styleable.RatioImageView_designWidth, DEFAULT_WIDTH);
        mHeight = typedArray.getInt(R.styleable.RatioImageView_designHeight, DEFAULT_HEIGHT);
        typedArray.recycle();
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        val height = measuredWidth * mHeight / mWidth;
        val heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, heightSpec)
    }
}