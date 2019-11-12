package org.solarex.customviewdemos.ui;

import android.widget.TextView;

import org.solarex.customviewdemos.R;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:46/2019-11-12
 *    Desc:
 * </pre>
 */

public class DemoTestRoateY extends BaseCustomViewActivity {
    boolean flag = false;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demotest_rotatey;
    }

    @Override
    protected void initView() {
        super.initView();
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv5 = findViewById(R.id.tv5);
        tv1.animate().rotationY(45f).setDuration(3000).start();
        tv2.animate().rotationY(90f).setDuration(3000).start();
        tv3.animate().rotationY(135f).setDuration(3000).start();
        tv4.animate().rotationY(180f).setDuration(3000).start();
        tv5.animate().rotationY(270f).setDuration(3000).start();
    }
}
