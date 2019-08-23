package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 14:05/2019-08-20
 *    Desc:
 * </pre>
 */

public class HeartRateEnergyControlCircleView extends View {
    int mWidth,mHeight;
    Paint mBackgroundPaint, mGradientPaint,mCenterCirclePaint;
    int mHalfWidth;
    LinearGradient mLinearGradient;
    RectF mGradientRectF = new RectF();

    public HeartRateEnergyControlCircleView(Context context) {
        super(context);
    }

    public HeartRateEnergyControlCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeartRateEnergyControlCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(Color.parseColor("#ECF5F6"));
        mBackgroundPaint.setStrokeWidth(1f);
        mGradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGradientPaint.setStrokeWidth(1f);
        mCenterCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterCirclePaint.setColor(Color.parseColor("#ffffff"));
        mCenterCirclePaint.setStrokeWidth(1f);
        setPadding(0, 0, 0, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = w;
        mHalfWidth = w/2;
        mGradientRectF.set(40f, 40f, mWidth-40f, mHeight-40f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawRadialGradient(canvas);
        drawCenterCircle(canvas);
    }

    private void drawBackground(Canvas canvas) {
        float radius = mHalfWidth - 20f;
        canvas.drawCircle(mHalfWidth, mHalfWidth, radius, mBackgroundPaint);
    }

    private void drawRadialGradient(Canvas canvas) {
        mLinearGradient = new LinearGradient(mGradientRectF.left, mGradientRectF.top, mGradientRectF.right, mGradientRectF.bottom, Color.parseColor("#49C6F0"), Color.parseColor("#1ad9ca"), Shader.TileMode.MIRROR);
        mGradientPaint.setShader(mLinearGradient);
        canvas.drawArc(mGradientRectF, -90f, 360f, false, mGradientPaint);
    }

    private void drawCenterCircle(Canvas canvas) {
        float radius = mHalfWidth - 70f;
        canvas.drawCircle(mHalfWidth, mHalfWidth, radius, mCenterCirclePaint);
    }
}
