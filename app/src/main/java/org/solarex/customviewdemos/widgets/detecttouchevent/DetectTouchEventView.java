package org.solarex.customviewdemos.widgets.detecttouchevent;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.solarex.customviewdemos.R;

import java.util.Random;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:33/2019-10-28
 *    Desc:
 * </pre>
 */

public class DetectTouchEventView extends View {
    private int mWidth, mHeight;
    private int[] COLORS;
    private Random random;
    private int mColor;
    private String TAG;
    private boolean consumeDown;

    public DetectTouchEventView(Context context) {
        this(context, null);
    }

    public DetectTouchEventView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetectTouchEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DetectTouchEventView);
            consumeDown = typedArray.getBoolean(R.styleable.DetectTouchEventView_consumeDown, false);
            typedArray.recycle();
        }
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Resources resources = context.getResources();
        COLORS = new int[]{
                resources.getColor(android.R.color.holo_red_dark),
                resources.getColor(android.R.color.holo_green_dark),
                resources.getColor(android.R.color.holo_blue_dark),
                resources.getColor(android.R.color.holo_red_light),
                resources.getColor(android.R.color.holo_green_light),
                resources.getColor(android.R.color.holo_blue_light)
        };
        random = new Random();
        mColor = COLORS[random.nextInt(COLORS.length)];
        TAG = "Touch-" + getId();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mColor);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent action = " + event.getAction(), new RuntimeException("Touch-" + getId() + "->dispatchTouchEvent").fillInStackTrace());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "consume = " + consumeDown + ",onTouchEvent action = " + event.getAction(), new RuntimeException("Touch-" + getId() + "->onTouchEvent").fillInStackTrace());
        if (consumeDown && event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
