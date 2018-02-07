package org.solarex.customviewdemos.ui;

import android.view.View;

import com.solarexsoft.solarexcustomview.customview.SearchView;

/**
 * Created by houruhou on 07/02/2018.
 */

public class SearchViewActivity extends BaseCustomViewActivity {
    @Override
    protected View createContentView() {
        return new SearchView(this);
    }
}
