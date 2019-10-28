package org.solarex.customviewdemos.widgets.detecttouchevent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:33/2019-10-28
 *    Desc:
 * </pre>
 */

public class DetectTouchEventView extends View {
    public DetectTouchEventView(Context context) {
        super(context);
    }

    public DetectTouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DetectTouchEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
