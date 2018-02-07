package org.solarex.customviewdemos.ui;

import android.view.View;

import com.solarexsoft.solarexcustomview.customview.PaperPlaneView;

/**
 * Created by houruhou on 07/02/2018.
 */

public class PaperPlaneActivity extends BaseCustomViewActivity {
    @Override
    protected View createContentView() {
        return new PaperPlaneView(this);
    }
}
