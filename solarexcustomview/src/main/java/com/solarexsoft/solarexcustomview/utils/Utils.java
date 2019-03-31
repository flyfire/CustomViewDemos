package com.solarexsoft.solarexcustomview.utils;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by houruhou on 10/04/2017.
 */

public class Utils {

    public static int measure(int measureSpec, int defaultSize){
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        if (specMode == View.MeasureSpec.EXACTLY){
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST){
            result = Math.min(specSize, defaultSize);
        }
        return result;
    }

    public static float dp2px(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

}
