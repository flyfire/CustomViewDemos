package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:49/2019-08-23
 *    Desc:
 * </pre>
 */

public class HeartRateRangeCircleView extends View {
    Paint mPaint;
    LinearGradient mShader;
    public HeartRateRangeCircleView(Context context) {
        super(context);
    }

    public HeartRateRangeCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeartRateRangeCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
