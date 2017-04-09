package org.solarex.customviewdemos.impl;

import android.view.View;

import org.solarex.customviewdemos.customview.BezierDemo1;

/**
 * Created by houruhou on 09/04/2017.
 */

public class BezierDemo1Activity extends BaseCustomViewActivity {
    @Override
    protected View createContentView() {
        return new BezierDemo1(this);
    }
}
