package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.solarexsoft.solarexcustomview.utils.Utils;

import java.util.ArrayList;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 18:10/2019-08-22
 *    Desc:
 * </pre>
 */

public class HeartRateRulerView extends View {
    Paint mPaint;

    int DEFAULT_COLOR = Color.parseColor("#DBE0E4");
    int DEFAULT_COUNT = 200;
    float DEFAULT_ITEM_WIDTH = Utils.dp2px(1);
    float DEFAULT_HIGHLIGHT_HIGHT = Utils.dp2px(9);
    int DEFAULT_START_COLOR = Color.parseColor("#B7F27C");
    int DEFAULT_END_COLOR = Color.parseColor("#FFA5AB");

    int DEFAULT_HEIGHT = (int) Utils.dp2px(36);
    int DEFAULT_WIDTH = (int) Utils.dp2px(200);

    int mColor = DEFAULT_COLOR;
    int mCount = DEFAULT_COUNT;
    float mItemWidth = DEFAULT_ITEM_WIDTH;
    float mHighlightHeight = DEFAULT_HIGHLIGHT_HIGHT;
    int mStartColor = DEFAULT_START_COLOR;
    int mEndColor = DEFAULT_END_COLOR;

    int mWidth = DEFAULT_WIDTH;
    int mHeight = DEFAULT_HEIGHT;

    int mHightlightIndex = -1;
    ArrayList<Integer> colors = new ArrayList<>(DEFAULT_COUNT);


    public HeartRateRulerView(Context context) {
        super(context);
    }

    public HeartRateRulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeartRateRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1f);
        mPaint.setColor(mColor);
        colors.clear();
        for (int i = 0; i < mCount; i++) {
            int color = Utils.getGradientColor(mStartColor, mEndColor, i, mCount);
            colors.add(color);
        }
    }

    public void setProgress(float percent) {
        mHightlightIndex = Math.round(percent * mCount);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = Utils.measure(widthMeasureSpec, DEFAULT_WIDTH);
        int height = Utils.measure(heightMeasureSpec, DEFAULT_HEIGHT);
        setMeasuredDimension(width, height);
    }
}
