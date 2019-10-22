package org.solarex.customviewdemos.widgets.ketonuriatesting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private int COLOR_TEXT_DATAUNIT = COLOR_TEXT_CHECKED;
    private int COLOR_TEXT_TIMESTAMP = Color.parseColor("#AAB2B7");

    private String TEXT_TYPE1 = "-";
    private String TEXT_TYPE2 = "±";
    private String TEXT_TYPE3 = "＋";
    private String TEXT_TYPE4 = "++";
    private String TEXT_TYPE5 = "+++";
    private String TEXT_TYPE6 = "++++";

    private int[] typeColors = {COLOR_TYPE1, COLOR_TYPE2, COLOR_TYPE3, COLOR_TYPE4, COLOR_TYPE5, COLOR_TYPE6};
    private String[] typeText = {TEXT_TYPE1, TEXT_TYPE2, TEXT_TYPE3, TEXT_TYPE4, TEXT_TYPE5, TEXT_TYPE6};

    Bitmap zhibiaoBitmap;
    private int mCheckedIndex = 1;
    private int mWidth,mHeight;
    private float mWidthPerType;
    private float mHeightPerType = Utils.dp2px(20f);
    private float mGapBetweenFlagAndText = Utils.dp2px(4f);
    private float mGapBetweenTypeAndBelowText = Utils.dp2px(10f);
    private float mBelowTypeDashLineHeight = Utils.dp2px(50f);
    private float mGapBetweenTimestampAndDataUnit = Utils.dp2px(6f);

    private String LOW_TEXT_BELOW_TYPE = "未检测出\n脂肪消耗";
    private String MEDIUM_TEXT_BELOW_TYPE = "酮体产生增高，\n脂肪消耗逐步增加";
    private String HIGH_TEXT_BELOW_TYPE = "酮体产生过多，\n容易引起酮中毒";
    private String NO_CHECK_TEXT = "未检出";
    private String DATA_UNIT = "mg/dL(mmol/L)";
    private String TEXT_TIMESTAMP = "10/14 12:12";
    private String TEXT_DATA_UNIT = "40(4.0)\n" + DATA_UNIT;
    private float mDataUnitWidth;
    private float mFlagWidth = Utils.dp2px(5);
    private float mFlagHeight = Utils.dp2px(52f);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


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

        mPaint.setTextSize(Utils.dp2px(10));
        mDataUnitWidth = mPaint.measureText(DATA_UNIT);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        mWidthPerType = w * 1.0f / 6;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawFlag(canvas);
        drawTimeStampAndData(canvas);
        drawTypes(canvas);
        drawTextBelowTypes(canvas);
        drawDashLineBelowTypes(canvas);
    }

    private void drawDashLineBelowTypes(Canvas canvas) {
    }

    private void drawTextBelowTypes(Canvas canvas) {
    }

    private void drawTypes(Canvas canvas) {
        float top = mFlagHeight;
        float left = 0f;
        float right = 0f;
        float bottom = top + mHeightPerType;
        mPaint.setTextSize(Utils.dp2px(12f));
        
        for (int i = 0; i < typeText.length; i++) {
            left = i * mWidthPerType;
            right = (i+1) * mWidthPerType;
            mPaint.setColor(typeColors[i]);
            if (i == 0) {

            }
        }
    }

    private void drawTimeStampAndData(Canvas canvas) {
        float left = (mCheckedIndex - 0.5f) * mWidthPerType;
        if (mCheckedIndex == 1) {
            TEXT_DATA_UNIT = NO_CHECK_TEXT;
        }
        float dataUnitRight = left + mFlagWidth + mGapBetweenFlagAndText + mDataUnitWidth;
        float timeStampAndDataLeft = left + mFlagWidth + mGapBetweenFlagAndText;
        if (dataUnitRight >= mWidth) {
            timeStampAndDataLeft = left - mGapBetweenFlagAndText - mDataUnitWidth;
        }
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float timestampBaseline = - fontMetrics.top;
        float dataAndUnitBaseline = fontMetrics.bottom - fontMetrics.top + mGapBetweenTimestampAndDataUnit - fontMetrics.top;
        mPaint.setColor(COLOR_TEXT_TIMESTAMP);
        mPaint.setTextSize(Utils.dp2px(10f));
        canvas.drawText(TEXT_TIMESTAMP, timeStampAndDataLeft, timestampBaseline, mPaint);
        mPaint.setColor(COLOR_TEXT_DATAUNIT);
        canvas.drawText(TEXT_DATA_UNIT, timeStampAndDataLeft, dataAndUnitBaseline, mPaint);
    }

    private void drawFlag(Canvas canvas) {
        float left = (mCheckedIndex - 0.5f) * mWidthPerType;
        canvas.drawBitmap(zhibiaoBitmap, left, 0f, null);
    }
}
