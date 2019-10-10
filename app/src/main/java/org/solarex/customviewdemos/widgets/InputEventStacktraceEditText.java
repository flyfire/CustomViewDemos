package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;

/**
 * Author: Solarex
 * Date: 2019/10/10
 * Desc:
 */
public class InputEventStacktraceEditText extends AppCompatEditText {
    private static final String TAG = "InputEventStacktraceEdi";
    public InputEventStacktraceEditText(Context context) {
        super(context);
    }

    public InputEventStacktraceEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputEventStacktraceEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(TAG, "", new RuntimeException().fillInStackTrace());
        return super.dispatchKeyEvent(event);
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

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        Log.d(TAG, "", new RuntimeException().fillInStackTrace());
        return super.onKeyPreIme(keyCode, event);
    }
}
