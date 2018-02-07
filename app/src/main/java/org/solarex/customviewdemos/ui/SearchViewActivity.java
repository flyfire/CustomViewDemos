package org.solarex.customviewdemos.ui;

import android.view.View;

import org.solarex.customviewdemos.customview.SearchView;

/**
 * Created by houruhou on 07/02/2018.
 */

public class SearchViewActivity extends BaseCustomViewActivity {
    @Override
    protected View createContentView() {
        return new SearchView(this);
    }
}
