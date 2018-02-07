package org.solarex.customviewdemos.ui;

import android.view.View;

import com.solarexsoft.solarexcustomview.customview.NumberProgress;

/**
 * Created by houruhou on 09/04/2017.
 */

public class NumberProgressActivity extends BaseCustomViewActivity {
    @Override
    protected View createContentView() {
        return new NumberProgress(this);
    }
}
