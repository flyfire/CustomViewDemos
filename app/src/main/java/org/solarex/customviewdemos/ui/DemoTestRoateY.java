package org.solarex.customviewdemos.ui;

import android.animation.ValueAnimator;
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
        final TextView tv4 = findViewById(R.id.tv4);
        TextView tv5 = findViewById(R.id.tv5);
        tv1.animate().rotationY(45f).setDuration(3000);
        tv2.animate().rotationY(90f).setDuration(3000).start();
        tv3.animate().rotationY(135f).setDuration(3000).start();
        tv4.animate().rotationY(180f).setDuration(3000).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (fraction > 0.5f) {
                    tv4.setScaleX(-1);
                }
            }
        }).start();
        tv5.animate().rotationY(270f).setDuration(3000).start();
        TextView tv6 = findViewById(R.id.tv6);
        TextView tv7 = findViewById(R.id.tv7);
        TextView tv8 = findViewById(R.id.tv8);
        final TextView tv9 = findViewById(R.id.tv9);
        TextView tv10 = findViewById(R.id.tv10);
        tv6.animate().rotationX(45f).setDuration(3000).start();
        tv7.animate().rotationX(90f).setDuration(3000).start();
        tv8.animate().rotationX(135f).setDuration(3000).start();
        tv9.animate().rotationX(180f).setDuration(3000).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (fraction > 0.5f) {
                    tv9.setScaleY(-1);
                }
            }
        }).start();
        tv10.animate().rotationX(270f).setDuration(3000).start();

    }
}
