package org.solarex.customviewdemos.widgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.solarexsoft.solarexcustomview.utils.Utils;

import java.util.ArrayList;
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
    List<Float> mAngles = new ArrayList<>();
    RectF mOval = new RectF();
    float mArcStrokeWidth = Utils.dp2px(25f);
    float mCenterCircleRadius = 0f;
    int mWidth;
    Paint mDashLinePaint,mCirclePaint;
    float mDashLineStrokeWidth = Utils.dp2px(1);
    float mProgress = 60f;

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
        mOval.set(mArcStrokeWidth/2, mArcStrokeWidth/2, mWidth-mArcStrokeWidth/2, mWidth-mArcStrokeWidth/2);
        mCenterCircleRadius = mWidth/2 - mArcStrokeWidth - Utils.dp2px(10);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mArcStrokeWidth);
        mDashLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDashLinePaint.setColor(Color.parseColor("#8599a7"));
        mDashLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
        mDashLinePaint.setStrokeWidth(mDashLineStrokeWidth);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setStyle(Paint.Style.FILL);
    }

    public void setValuesAndColors(List<Integer> values, List<Integer> colors) {
        if (values != null && colors != null) {
            if (values.size() * 2 != colors.size()) {
                return;
            }
            mAngles.clear();
            mValues = values;
            int sum = 0;
            for (Integer value : values) {
                sum += value;
            }
            for (Integer value : values) {
                float angle = (value * 360.0f) / sum;
                mAngles.add(angle);
            }
            mColors = colors;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        if (mAngles != null && mAngles.size() > 0) {
//            drawSweepGradientProgress(canvas);
            drawLinearGradientProgress(canvas);
            drawMiddleDashLine(canvas);
            drawCenterCircle(canvas);
        } else {
            drawMiddleDashLine(canvas);
            drawCenterCircle(canvas);
        }
    }

    private void drawLinearGradientProgress(Canvas canvas) {
        float startAngle = -90f;
        for (int i = 0; i < mAngles.size(); i++) {
            Float angle = mAngles.get(i);
            int startColor = mColors.get(i * 2);
            int endColor = mColors.get(i * 2 + 1);
            LinearGradient gradient = new LinearGradient(mOval.left, mOval.top, mOval.right, mOval.bottom, startColor, endColor, Shader.TileMode.MIRROR);
            mPaint.setShader(gradient);
            canvas.drawArc(mOval, startAngle, angle, false, mPaint);
            mPaint.setShader(null);
            startAngle+=angle;
        }
    }

    private void drawTestSweepGradient(Canvas canvas) {
        int startColor = mColors.get(0);
        int endcColor = mColors.get(1);
        SweepGradient gradient = new SweepGradient(mWidth/2.0f, mWidth/2.0f, new int[]{startColor, endcColor, startColor}, null);
        mPaint.setShader(gradient);
        canvas.drawArc(mOval, -90f, mProgress, false, mPaint);
    }

    public void start() {
        ValueAnimator animator = ValueAnimator.ofFloat(60f, 360f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(10000);
        animator.start();
    }

    private void drawSweepGradientProgress(Canvas canvas) {
        float startAngle = -90f;
        for (int i = 0; i < mAngles.size(); i++) {
            Float angle = mAngles.get(i);
            int startColor = mColors.get(i * 2);
            int endColor = mColors.get(i * 2 + 1);
            int[] colors = new int[]{startColor, endColor, startColor};
            SweepGradient gradient = new SweepGradient(mWidth/2.0f, mWidth/2.0f, colors, null);
            Matrix matrix = new Matrix();
            matrix.setRotate(startAngle, mOval.centerX(), mOval.centerY());
            gradient.setLocalMatrix(matrix);
            mPaint.setShader(gradient);
            canvas.drawArc(mOval, startAngle, angle, false, mPaint);
            mPaint.setShader(null);
            startAngle+=angle;
        }
    }

    private void drawCenterCircle(Canvas canvas) {
        canvas.drawCircle(mWidth/2.0f, mWidth/2.0f, mCenterCircleRadius, mCirclePaint);
    }

    private void drawMiddleDashLine(Canvas canvas) {
        /*
        canvas.drawLine((mWidth - mDashLineStrokeWidth)/2.0f,0, mWidth/2.0f + mDashLineStrokeWidth/2.0f, mWidth/2.0f, mDashLinePaint);
        */
        canvas.save();
        canvas.translate(mWidth/2.0f, mWidth/2.f);
        canvas.rotate(-90f);
        canvas.drawLine(0, 0, mWidth/2.0f, 0f, mDashLinePaint);
        canvas.restore();
    }
}
