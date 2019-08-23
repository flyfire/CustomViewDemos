package org.solarex.customviewdemos.ui;

import org.solarex.customviewdemos.R;
import org.solarex.customviewdemos.widgets.HeartRateRulerView;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 14:11/2019-08-23
 *    Desc:
 * </pre>
 */

public class HeartRateRulerActivity extends BaseCustomViewActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_heartrate_ruler;
    }

    @Override
    protected void initView() {
        HeartRateRulerView hrrv_1 = findViewById(R.id.hrrv_1);
        HeartRateRulerView hrrv_2 = findViewById(R.id.hrrv_2);
        HeartRateRulerView hrrv_3 = findViewById(R.id.hrrv_3);
        HeartRateRulerView hrrv_4 = findViewById(R.id.hrrv_4);

        hrrv_2.setProgress(0.5f);
        hrrv_3.setProgress(0.3f);
        hrrv_4.setProgress(0.7f);
    }
}
