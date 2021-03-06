package org.solarex.customviewdemos.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by houruhou on 09/04/2017.
 */

public abstract class BaseCustomViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        } else {
            setContentView(createContentView());
        }
        initView();
    }

    protected void initView() {}

    protected View createContentView() {
        return null;
    }

    protected int getLayoutId() {
        return 0;
    }
}
