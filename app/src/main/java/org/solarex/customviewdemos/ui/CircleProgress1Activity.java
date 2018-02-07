package org.solarex.customviewdemos.ui;

import android.view.View;

import org.solarex.customviewdemos.customview.CircleProgress1;

/**
 * Created by houruhou on 09/04/2017.
 */

public class CircleProgress1Activity extends BaseCustomViewActivity {
    @Override
    protected View createContentView() {
        return new CircleProgress1(this);
    }
}
