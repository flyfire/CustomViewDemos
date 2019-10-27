package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:55/2019-10-27
 *    Desc:
 * </pre>
 */

public class FullScreenMoveView extends View {
    int mLastX,mLastY;
    int x,y,deltaX,deltaY;

    public FullScreenMoveView(Context context) {
        super(context);
    }

    public FullScreenMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
        x = (int) event.getRawX();
        y = (int) event.getRawY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            deltaX = x - mLastX;
            deltaY = y - mLastY;
            setTranslationX(getTranslationX() + deltaX);
            setTranslationY(getTranslationY() + deltaY);
        }
        mLastX = x;
        mLastY = y;
        return true;
    }
}
