package org.solarex.customviewdemos.widgets.detecttouchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:34/2019-10-28
 *    Desc:
 * </pre>
 */

public class DetectTouchEventViewGroupEx extends ViewGroup {
    private static final String TAG = "DetectTouchEventViewGro";
    private Random random;
    boolean randomConsume;
    public DetectTouchEventViewGroupEx(Context context) {
        super(context);
    }

    public DetectTouchEventViewGroupEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DetectTouchEventViewGroupEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidth = 0,measureHeight = 0;
        if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
            measureWidth = widthSpecSize;
            measureHeight = heightSpecSize;
        } else {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                int childWidth = layoutParams.leftMargin + child.getMeasuredWidth() + layoutParams.rightMargin;
                if (measureWidth < childWidth) {
                    measureWidth = childWidth;
                }
                int childHeight = layoutParams.topMargin + child.getMeasuredHeight() + layoutParams.bottomMargin;
                if (measureHeight < childHeight) {
                    measureHeight = childHeight;
                }
            }
        }
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left,top,right,bottom;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            left = layoutParams.leftMargin;
            top = layoutParams.topMargin;
            right = left + child.getMeasuredWidth();
            bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent action = " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent action = " + ev.getAction());
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent action = " + event.getAction());
        boolean superConsume = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent superConsume = " + superConsume);
        return superConsume;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
