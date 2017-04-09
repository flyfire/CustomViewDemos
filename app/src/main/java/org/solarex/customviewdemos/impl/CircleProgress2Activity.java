package org.solarex.customviewdemos.impl;

import android.view.View;

import org.solarex.customviewdemos.customview.CircleProgress2;

/**
 * Created by houruhou on 09/04/2017.
 */

public class CircleProgress2Activity extends BaseCustomViewActivity {
    @Override
    protected View createContentView() {
        return new CircleProgress2(this);
    }
}
