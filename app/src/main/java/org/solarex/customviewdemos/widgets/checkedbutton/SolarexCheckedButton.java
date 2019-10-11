package org.solarex.customviewdemos.widgets.checkedbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Author: Solarex
 * Date: 2019/10/11
 * Desc:
 */
@SuppressLint("AppCompatCustomView")
public class SolarexCheckedButton extends RadioButton {
    private int mWidth,mHeight;

    public SolarexCheckedButton(Context context) {
        super(context);
    }

    public SolarexCheckedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SolarexCheckedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        if (isChecked()) {
            drawTickAtRight(canvas);
        } else {
            drawEmptyAtRight(canvas);
        }
    }

    private void drawTickAtRight(Canvas canvas) {

    }

    private void drawEmptyAtRight(Canvas canvas) {

    }
}
