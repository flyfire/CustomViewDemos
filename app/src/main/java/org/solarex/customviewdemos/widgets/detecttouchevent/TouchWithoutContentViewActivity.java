package org.solarex.customviewdemos.widgets.detecttouchevent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

public class TouchWithoutContentViewActivity extends AppCompatActivity {
    private static final String TAG = "TouchWithoutContentView";
    public static void goTouchWithoutContentViewActivity(Context context) {
        Intent intent = new Intent(context, TouchWithoutContentViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent", new RuntimeException("dispatchTouchEvent").fillInStackTrace());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent", new RuntimeException("onTouchEvent").fillInStackTrace());
        return super.onTouchEvent(event);
    }
}
