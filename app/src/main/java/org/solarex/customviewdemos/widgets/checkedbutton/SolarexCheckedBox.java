package org.solarex.customviewdemos.widgets.checkedbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Author: Solarex
 * Date: 2019/10/11
 * Desc:
 */
@SuppressLint("AppCompatCustomView")
public class SolarexCheckedBox extends CheckBox {
    public SolarexCheckedBox(Context context) {
        super(context);
    }

    public SolarexCheckedBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SolarexCheckedBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
