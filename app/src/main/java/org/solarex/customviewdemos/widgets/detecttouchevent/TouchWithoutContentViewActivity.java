package org.solarex.customviewdemos.widgets.detecttouchevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;

public class TouchWithoutContentViewActivity extends Activity {
    private static final String TAG = "TouchWithoutContentView";

    public static void goTouchWithoutContentViewActivity(Context context) {
        Intent intent = new Intent(context, TouchWithoutContentViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent action = " + ev.getAction()/*, new RuntimeException("dispatchTouchEvent").fillInStackTrace()*/);
        boolean superConsume = super.dispatchTouchEvent(ev);
        Log.d(TAG, "dispatchTouchEvent superConsume = " + superConsume);
        return superConsume;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent action = " + event.getAction()/*, new RuntimeException("onTouchEvent").fillInStackTrace()*/);
        boolean superConsume = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent superConsume = " + superConsume);
        return superConsume;
    }
}
