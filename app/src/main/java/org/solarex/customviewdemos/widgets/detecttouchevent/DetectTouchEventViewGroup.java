package org.solarex.customviewdemos.widgets.detecttouchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:34/2019-10-28
 *    Desc:
 * </pre>
 */

public class DetectTouchEventViewGroup extends ViewGroup {
    private static final String TAG = "DetectTouchEventViewGro";
    public DetectTouchEventViewGroup(Context context) {
        super(context);
    }

    public DetectTouchEventViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DetectTouchEventViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
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
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
