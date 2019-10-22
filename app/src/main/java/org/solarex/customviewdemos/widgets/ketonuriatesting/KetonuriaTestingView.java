package org.solarex.customviewdemos.widgets.ketonuriatesting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.solarexsoft.solarexcustomview.utils.Utils;

import org.solarex.customviewdemos.R;

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

    Bitmap zhibiaoBitmap;
    private int checkedIndex = 1;
    private int mWidth,mHeight;
    private float mWidthPerType;
    private float mHeightPerType = Utils.dp2px(20f);
    private float mGapBetweenFlagAndText = Utils.dp2px(4f);
    private float mGapBetweenTypeAndBelowText = Utils.dp2px(10f);
    private float mBelowTypeDashLineHeight = Utils.dp2px(50f);

    private String LOW_TEXT_BELOW_TYPE = "未检测出\n脂肪消耗";
    private String MEDIUM_TEXT_BELOW_TYPE = "酮体产生增高，\n脂肪消耗逐步增加";
    private String HIGH_TEXT_BELOW_TYPE = "酮体产生过多，\n容易引起酮中毒";
    private String NO_CHECK_TEXT = "未检出";

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
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_zhibiao);
        drawable.setBounds(0, 0, (int)Utils.dp2px(5), (int)Utils.dp2px(52));
        zhibiaoBitmap = Bitmap.createBitmap((int)Utils.dp2px(5), (int)Utils.dp2px(52), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(zhibiaoBitmap);
        drawable.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }
}
