package org.solarex.customviewdemos.ui;

import android.graphics.Color;

import org.solarex.customviewdemos.R;
import org.solarex.customviewdemos.widgets.HeartRateRangeCircleView;

import java.util.ArrayList;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 11:14/2019-08-24
 *    Desc:
 * </pre>
 */

public class HeartRateRangeCircleActivity extends BaseCustomViewActivity {

    int aColor1 = Color.parseColor("#FFD8BE");
    int aColor2 = Color.parseColor("#FD6090");
    int bColor1 = Color.parseColor("#FFF58C");
    int bColor2 = Color.parseColor("#FFC400");
    int cColor1 = Color.parseColor("#DEFF94");
    int cColor2 = Color.parseColor("#6CD94C");
    int dColor1 = Color.parseColor("#93FDEB");
    int dColor2 = Color.parseColor("#43B4FF");
    int eColor1 = Color.parseColor("#76D5FF");
    int eColor2 = Color.parseColor("#459EFF");
    int fColor1 = Color.parseColor("#C2B2FF");
    int fColor2 = Color.parseColor("#8465FF");

    @Override
    protected int getLayoutId() {
        return R.layout.activity_heartrate_rangecircle;
    }

    @Override
    protected void initView() {
        HeartRateRangeCircleView hrrcv_1 = findViewById(R.id.hrrcv_1);
        HeartRateRangeCircleView hrrcv_2 = findViewById(R.id.hrrcv_2);
        HeartRateRangeCircleView hrrcv_3 = findViewById(R.id.hrrcv_3);

        ArrayList<Integer> h2Values = new ArrayList<>();
        ArrayList<Integer> h2Colors = new ArrayList<>();

        ArrayList<Integer> h3Values = new ArrayList<>();
        ArrayList<Integer> h3Colors = new ArrayList<>();

        h2Values.add(5);
        h2Values.add(5);
        h2Colors.add(aColor1);
        h2Colors.add(aColor2);
        h2Colors.add(bColor1);
        h2Colors.add(bColor2);

        h3Values.add(5);
        h3Values.add(6);
        h3Values.add(3);
        h3Values.add(4);
        h3Values.add(8);
        h3Values.add(6);
        h3Colors.add(aColor1);
        h3Colors.add(aColor2);
        h3Colors.add(bColor1);
        h3Colors.add(bColor2);
        h3Colors.add(cColor1);
        h3Colors.add(cColor2);
        h3Colors.add(dColor1);
        h3Colors.add(dColor2);
        h3Colors.add(eColor1);
        h3Colors.add(eColor2);
        h3Colors.add(fColor1);
        h3Colors.add(fColor2);

        hrrcv_2.setValuesAndColors(h2Values, h2Colors);
        hrrcv_3.setValuesAndColors(h3Values, h3Colors);
    }
}
