package org.solarex.customviewdemos.widgets.ketonuriatesting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class KetonuriaTestingView extends View {

    private int COLOR_TYPE1 = Color.parseColor("#FDF3DD");
    private int COLOR_TYPE2 = Color.parseColor("#F5DEDF");
    private int COLOR_TYPE3 = Color.parseColor("#D1B3CF");
    private int COLOR_TYPE4 = Color.parseColor("#BF8DB0");
    private int COLOR_TYPE5 = Color.parseColor("#A0578D");
    private int COLOR_TYPE6 = Color.parseColor("#432752");

    private int COLOR_TEXT_CHECKED = Color.parseColor("#374147");
    private int COLOR_TEXT_UNCHECKED = Color.parseColor("#FFFFFF");

    private String TEXT_TYPE1 = "-";
    private String TEXT_TYPE2 = "±";
    private String TEXT_TYPE3 = "＋";
    private String TEXT_TYPE4 = "++";
    private String TEXT_TYPE5 = "+++";
    private String TEXT_TYPE6 = "++++";

    private int[] typeColors = {COLOR_TYPE1, COLOR_TYPE2, COLOR_TYPE3, COLOR_TYPE4, COLOR_TYPE5, COLOR_TYPE6};
    private String[] typeText = {TEXT_TYPE1, TEXT_TYPE2, TEXT_TYPE3, TEXT_TYPE4, TEXT_TYPE5, TEXT_TYPE6};

    public KetonuriaTestingView(Context context) {
        this(context, null);
    }

    public KetonuriaTestingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KetonuriaTestingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }
}
