package org.solarex.customviewdemos.widgets.detecttouchevent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;

import java.lang.reflect.Field;

public class TouchWithoutContentViewActivity extends Activity {
    private static final String TAG = "TouchWithoutContentView";

    public static void goTouchWithoutContentViewActivity(Context context) {
        Intent intent = new Intent(context, TouchWithoutContentViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "window = " + getWindow());
        try {
            @SuppressLint("PrivateApi")
            Class clz = Class.forName("com.android.internal.policy.PhoneWindow");
            Field mDecor = clz.getField("mDecor");
            mDecor.setAccessible(true);
            Object obj = mDecor.get(getWindow());
            Log.d(TAG, "onCreate decorView = " + obj);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Thread.dumpStack();
        Log.d(TAG, "dispatchTouchEvent action = " + ev.getAction());
        boolean superConsume = super.dispatchTouchEvent(ev);
        Log.d(TAG, "dispatchTouchEvent superConsume = " + superConsume);
        return superConsume;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Thread.dumpStack();
        Log.d(TAG, "onTouchEvent action = " + event.getAction());
        boolean superConsume = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent superConsume = " + superConsume);
        return superConsume;
    }
}
