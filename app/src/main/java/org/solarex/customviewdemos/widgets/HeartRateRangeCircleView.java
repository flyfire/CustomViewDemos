package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:49/2019-08-23
 *    Desc:
 * </pre>
 */

public class HeartRateRangeCircleView extends View {
    Paint mPaint;
    SweepGradient mShader;
    List<Integer> mValues;
    List<Integer> mColors;
    List<Float> mAngles;
    float mCenterCircleRadius = 50f;
    int mWidth;

    public HeartRateRangeCircleView(Context context) {
        super(context);
    }

    public HeartRateRangeCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeartRateRangeCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(40f);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setValuesAndColors(List<Integer> values, List<Integer> colors) {
        if (values != null && colors != null) {
            if (values.size() * 2 != colors.size()) {
                return;
            }
            mValues = values;
            int sum = 0;
            for (Integer value : values) {
                sum += value;
            }
            for (Integer value : values) {
                float angle = (value * 360.0f) / sum;
                mAngles.add(angle);
            }
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        
    }
}
