package org.solarex.customviewdemos.impl;

import android.view.View;

import org.solarex.customviewdemos.customview.BezierDemo2;

/**
 * Created by houruhou on 09/04/2017.
 */

public class BezierDemo2Activity extends BaseCustomViewActivity {
    @Override
    protected View createContentView() {
        return new BezierDemo2(this);
    }
}
