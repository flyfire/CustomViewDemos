package com.solarexsoft.solarexcustomview.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
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

    public static Bitmap getBitmap(Resources resources, @DrawableRes int resourceId, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resourceId, options);
        options.inJustDecodeBounds = false;
//        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(resources, resourceId, options);
    }

}
