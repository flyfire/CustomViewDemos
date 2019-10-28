package org.solarex.customviewdemos.widgets.detecttouchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:34/2019-10-28
 *    Desc:
 * </pre>
 */

public class DetectTouchEventViewGroup extends ViewGroup {
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
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

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
}
