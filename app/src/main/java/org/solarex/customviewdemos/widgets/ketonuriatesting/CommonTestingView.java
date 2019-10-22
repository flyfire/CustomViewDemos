package org.solarex.customviewdemos.widgets.ketonuriatesting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.solarexsoft.solarexcustomview.utils.Utils;

import org.solarex.customviewdemos.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommonTestingView extends View {

    private int DEFAULT_LOW_START_COLOR = Color.parseColor("#6CD94C");
    private int DEFAULT_LOW_END_COLOR = Color.parseColor("#D0FF68");
    private int DEFAULT_HIGH_START_COLOR = Color.parseColor("#EA5455");
    private int DEFAULT_HIGH_END_COLOR = Color.parseColor("#FEB692");
    private int COLOR_TIMESTAMP = Color.parseColor("#AAB2B7");
    private int COLOR_DATA = Color.parseColor("#374147");
    private int COLOR_DASHLINE = Color.parseColor("#BAC4CB");
    private float mFlagHeight = Utils.dp2px(40f);
    private float mFlagWidth = Utils.dp2px(5f);
    private Bitmap mFlagBitmap;
    private float mGapBetweenFlagAndTimestamp = Utils.dp2px(4f);
    private float mGapBetweenTimestampAndData = Utils.dp2px(4f);
    private float mDashLineHeight = Utils.dp2px(10f);
    private float mGapBetweenDashAndMidValue = Utils.dp2px(7f);
    private float mDashLineEndRectWidth = Utils.dp2px(2f);
    private float mDashLineEndRectHeight = Utils.dp2px(4f);
    private float mGapBetweenTypeAndDashLineEndRect = Utils.dp2px(6f);
    private float mGapBetweenMidValueAndUnit = Utils.dp2px(1f);
    private float mFlagMaxRight;
    private float mTypeWidth,mTypeHeight;

    private String DEFAULT_UNIT = "mmol/L";
    private float DEFAULT_MID_VALUE = 1.70f;
    private float mMaxValue;
    private int mLowStartColor,mLowEndColor,mHighStartColor,mHighEndColor;


    private String mTimestamp = "10/14 12:12";
    private String mData = "5.5 mmol/L";
    private String mMidValue = "1.70";
    private String mUnit;
    private String mLowText,mHighText;

    private int mWidth;
    private boolean mUseFlagMaxRight = false;
    private float mProgress;

    public CommonTestingView(Context context) {
        this(context, null);
    }

    public CommonTestingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTestingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonTestingView);
            mLowStartColor = typedArray.getColor(R.styleable.CommonTestingView_lowStartColor, DEFAULT_LOW_START_COLOR);
            mLowEndColor = typedArray.getColor(R.styleable.CommonTestingView_lowEndColor, DEFAULT_LOW_END_COLOR);
            mHighStartColor = typedArray.getColor(R.styleable.CommonTestingView_highStartColor, DEFAULT_HIGH_START_COLOR);
            mHighEndColor = typedArray.getColor(R.styleable.CommonTestingView_highEndColor, DEFAULT_HIGH_END_COLOR);
            float midValue = typedArray.getFloat(R.styleable.CommonTestingView_midValue, DEFAULT_MID_VALUE);
            BigDecimal bigDecimal = new BigDecimal(midValue).setScale(2, RoundingMode.HALF_UP);
            mMaxValue = bigDecimal.floatValue() * 2;
            mMidValue = String.valueOf(bigDecimal.floatValue());
            mUnit = typedArray.getString(R.styleable.CommonTestingView_unit);
            mLowText = typedArray.getString(R.styleable.CommonTestingView_lowText);
            mHighText = typedArray.getString(R.styleable.CommonTestingView_highText);
            typedArray.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mTypeWidth = mWidth / 2.0f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawFlag(canvas);
        drawTimestampAndData(canvas);
        drawTypes(canvas);
        drawDashLineAndRect(canvas);
        drawLowHighTextAndMidValue(canvas);
    }

    private void drawLowHighTextAndMidValue(Canvas canvas) {
    }

    private void drawDashLineAndRect(Canvas canvas) {
    }

    private void drawTypes(Canvas canvas) {
    }

    private void drawTimestampAndData(Canvas canvas) {
    }

    private void drawFlag(Canvas canvas) {
        float flagLeft = 0f;
        if (mUseFlagMaxRight) {
            flagLeft = mWidth - mFlagWidth;
        } else {
            if (mProgress < 0f) {
                mProgress = 0f;
            }
            flagLeft = mProgress * mWidth;
        }
    }

    public void setTimestampAndData(String timestamp, float data) {
        mTimestamp = timestamp;
        if (data >= mMaxValue) {
            mUseFlagMaxRight = true;
        } else {
            mProgress = data * 1.0f / mMaxValue;
        }
        if (!TextUtils.isEmpty(mUnit)) {
            mData = formatData(data) + " " + mUnit;
        } else {
            mData = formatData(data);
        }
    }

    private String formatData(float data) {
        BigDecimal bigDecimal = new BigDecimal(data).setScale(2, RoundingMode.HALF_UP);
        return String.valueOf(bigDecimal.floatValue());
    }
}
