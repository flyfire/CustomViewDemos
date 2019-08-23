package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.graphics.Canvas;
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
    private static final String TAG = HeartRateRulerView.class.getSimpleName();

    Paint mPaint;

    int DEFAULT_COLOR = Color.parseColor("#DBE0E4");
    int DEFAULT_COUNT = 50;
    float DEFAULT_ITEM_WIDTH = Utils.dp2px(1);
    float DEFAULT_HIGHLIGHT_HIGHT = Utils.dp2px(9);
    float DEFAULT_HIGHTLIGHT_MARGINTOP = Utils.dp2px(1);
    int DEFAULT_START_COLOR = Color.parseColor("#B7F27C");
    int DEFAULT_END_COLOR = Color.parseColor("#FFA5AB");

    int DEFAULT_HEIGHT = (int) Utils.dp2px(36);
    int DEFAULT_WIDTH = (int) Utils.dp2px(200);

    int mColor = DEFAULT_COLOR;
    int mCount = DEFAULT_COUNT;
    float mItemWidth = DEFAULT_ITEM_WIDTH;
    float mItemHeight = 2 * DEFAULT_HIGHLIGHT_HIGHT;
    float mHighlightHeadHeight = DEFAULT_HIGHLIGHT_HIGHT;
    float mHighlightMarginTop = DEFAULT_HIGHTLIGHT_MARGINTOP;
    int mStartColor = DEFAULT_START_COLOR;
    int mEndColor = DEFAULT_END_COLOR;

    int mWidth = DEFAULT_WIDTH;
    int mHeight = DEFAULT_HEIGHT;

    int mHighlightIndex = -1;
    ArrayList<Integer> colors = new ArrayList<>(DEFAULT_COUNT);
    float mGapWidth = 0;
    float left,top,right,bottom;

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
        // parse style maybe needed
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1f);
        mPaint.setColor(mColor);
        colors.clear();
        for (int i = 0; i < mCount; i++) {
            int color = Utils.getGradientColor(mStartColor, mEndColor, i, mCount);
            colors.add(color);
        }
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public void setProgress(float percent) {
        mHighlightIndex = Math.round(percent * mCount);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = Utils.measure(widthMeasureSpec, DEFAULT_WIDTH);
        int height = Utils.measure(heightMeasureSpec, DEFAULT_HEIGHT);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mGapWidth = (mWidth - mCount * mItemWidth) * 1.0f / (mCount - 1);
        mItemHeight = mHeight - 2 * (mHighlightMarginTop + mHighlightHeadHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 1; i <= mCount; i++) {
            left = (i-1)*(mItemWidth+mGapWidth);
            right = left + mItemWidth;
            if (i == mHighlightIndex) {
                top = mHighlightMarginTop;
                bottom = top + mItemHeight + 2 * mHighlightHeadHeight;
            } else {
                top = mHighlightMarginTop + mHighlightHeadHeight;
                bottom = top + mItemHeight;
            }
            if (mHighlightIndex != -1) {
                mPaint.setColor(colors.get(i-1));
            } else {
                mPaint.setColor(mColor);
            }
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }
}
