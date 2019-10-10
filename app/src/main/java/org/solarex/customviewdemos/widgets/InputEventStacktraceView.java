package org.solarex.customviewdemos.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author: Solarex
 * Date: 2019/10/10
 * Desc:
 */
public class InputEventStacktraceView extends View {
    private static final String TAG = "InputEventStacktraceVie";

    public InputEventStacktraceView(Context context) {
        super(context);
    }

    public InputEventStacktraceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InputEventStacktraceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "", new RuntimeException().fillInStackTrace());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "", new RuntimeException().fillInStackTrace());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, "", new RuntimeException().fillInStackTrace());
        return super.onKeyUp(keyCode, event);
    }
}
