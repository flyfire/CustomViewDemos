package org.solarex.customviewdemos.impl;

import android.view.View;

import org.solarex.customviewdemos.customview.NumberProgress;

/**
 * Created by houruhou on 09/04/2017.
 */

public class NumberProgressActivity extends BaseCustomViewActivity {
    @Override
    protected View createContentView() {
        return new NumberProgress(this);
    }
}
