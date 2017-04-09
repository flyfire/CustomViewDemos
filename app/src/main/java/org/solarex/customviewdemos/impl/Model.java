package org.solarex.customviewdemos.impl;

import android.content.Intent;

/**
 * Created by houruhou on 09/04/2017.
 */

public class Model {
    private String mTitle;
    private Intent mIntent;
    public Model(String title, Intent intent){
        mTitle = title;
        mIntent = intent;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent intent) {
        mIntent = intent;
    }

    @Override
    public String toString() {
        return "Model[title = " + getTitle() + ",intent = " + getIntent() + "]";
    }
}
