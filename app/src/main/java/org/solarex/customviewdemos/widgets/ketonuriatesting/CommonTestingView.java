package org.solarex.customviewdemos.widgets.ketonuriatesting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.solarexsoft.solarexcustomview.utils.Utils;

import org.solarex.customviewdemos.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
    private float mDashLineHeight = Utils.dp2px(14f);
    private float mGapBetweenDashAndMidValue = Utils.dp2px(7f);
    private float mGapBetweenTypeAndDashLineEndRect = Utils.dp2px(10f);
    private float mGapBetweenMidValueAndUnit = Utils.dp2px(1f);
    private float mTypeWidth,mTypeHeight = Utils.dp2px(14f);
    private float mTypeCircleRadius = Utils.dp2px(7f);

    private String DEFAULT_UNIT = "mmol/L";
    private float DEFAULT_MID_VALUE = 1.70f;
    private float mMaxValue;
    private int mLowStartColor,mLowEndColor,mHighStartColor,mHighEndColor;

    private int DEFAULT_LOW_COLOR = Color.parseColor("#52BAC4CB");
    private int DEFAULT_HIGH_COLOR = Color.parseColor("#29BAC4CB");


    private String mTimestamp = "10/14 12:12";
    private String mData = "";
    private String mMidValue = "1.70";
    private String mUnit;
    private String mLowText,mHighText;

    private int mWidth;
    private boolean mUseFlagMaxRight = false;
    private float mProgress;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

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
            mMaxValue = midValue * 2;
            mMidValue = formatData(midValue);
            mUnit = typedArray.getString(R.styleable.CommonTestingView_valueUnit);
            mLowText = typedArray.getString(R.styleable.CommonTestingView_lowText);
            mHighText = typedArray.getString(R.styleable.CommonTestingView_highText);
            typedArray.recycle();
        }
        mFlagBitmap = Bitmap.createBitmap((int)Utils.dp2px(5f), (int)Utils.dp2px(40f), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mFlagBitmap);
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_zhibiao);
        drawable.setBounds(0, 0, (int)Utils.dp2px(5f), (int)Utils.dp2px(40f));
        drawable.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mTypeWidth = mWidth / 2.0f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!TextUtils.isEmpty(mData)) {
            drawFlagAndTimestampData(canvas);
            drawTypes(canvas);
        } else {
            drawNoDataTypes(canvas);
        }
        drawDashLineAndRect(canvas);
        drawLowHighTextAndMidValue(canvas);
    }

    private void drawNoDataTypes(Canvas canvas) {
        float mTypeTop = mFlagHeight;
        int layer = canvas.saveLayer(0f, mTypeTop, mTypeWidth, mTypeTop + mTypeHeight, mPaint);
        mPaint.setColor(DEFAULT_LOW_COLOR);
        canvas.drawRoundRect(0f, mTypeTop, mTypeWidth, mTypeTop + mTypeHeight, mTypeCircleRadius, mTypeCircleRadius, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawRect(mTypeWidth - mTypeCircleRadius, mTypeTop, mTypeWidth, mTypeTop + mTypeHeight, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layer);
        layer = canvas.saveLayer(mTypeWidth, mTypeTop, mWidth, mTypeTop + mTypeHeight, mPaint);
        mPaint.setColor(DEFAULT_HIGH_COLOR);
        canvas.drawRoundRect(mTypeWidth, mTypeTop, mWidth, mTypeTop + mTypeHeight, mTypeCircleRadius, mTypeCircleRadius, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawRect(mTypeWidth, mTypeTop, mTypeWidth + mTypeCircleRadius, mTypeTop + mTypeHeight, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layer);
    }

    private void drawLowHighTextAndMidValue(Canvas canvas) {
        mPaint.setColor(COLOR_TIMESTAMP);
        mPaint.setTextSize(Utils.dp2px(11f));
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float textBaseline = mFlagHeight + mTypeHeight + mDashLineHeight + mGapBetweenDashAndMidValue - fontMetrics.top;
        float lowTextWidth = mPaint.measureText(mLowText);
        float midTextWidth = mPaint.measureText(mMidValue);
        float highTextWidth = mPaint.measureText(mHighText);
        float lowTextLeft = (mTypeWidth - lowTextWidth) / 2.0f;
        float midTextLeft = mTypeWidth - midTextWidth/2.0f;
        float highTextLeft = 1.5f * mTypeWidth - highTextWidth / 2.0f;
        canvas.drawText(mLowText, lowTextLeft, textBaseline, mPaint);
        canvas.drawText(mMidValue, midTextLeft, textBaseline, mPaint);
        canvas.drawText(mHighText, highTextLeft, textBaseline, mPaint);
        if (!TextUtils.isEmpty(mUnit)) {
            mPaint.setTextSize(Utils.dp2px(10f));
            float unitLeft = mTypeWidth + midTextWidth/2.0f + mGapBetweenMidValueAndUnit;
            canvas.drawText(mUnit, unitLeft, textBaseline, mPaint);
        }
    }

    private void drawDashLineAndRect(Canvas canvas) {
        float startX = mTypeWidth;
        float startY = mFlagHeight + mTypeHeight;
        float stopY = startY + mDashLineHeight;
        mPaint.setColor(COLOR_DASHLINE);
        mPaint.setStrokeWidth(Utils.dp2px(0.5f));
        mPaint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
        canvas.drawLine(startX, startY, startX, stopY, mPaint);
        float left = mTypeWidth - Utils.dp2px(1f);
        float top = mFlagHeight + mTypeHeight + mGapBetweenTypeAndDashLineEndRect;
        float right = mTypeWidth + Utils.dp2px(1f);
        float bottom = stopY;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(null);
        canvas.drawRect(left, top, right, bottom, mPaint);
    }

    private void drawTypes(Canvas canvas) {
        float mTypeTop = mFlagHeight;
        LinearGradient linearGradient = new LinearGradient(0f, mTypeTop, mTypeWidth, mTypeTop + mTypeHeight, mLowStartColor, mLowEndColor, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawRoundRect(0f, mTypeTop, mTypeWidth, mTypeTop + mTypeHeight, mTypeCircleRadius, mTypeCircleRadius, mPaint);
        linearGradient = new LinearGradient(mTypeWidth, mTypeTop, mWidth, mTypeTop + mTypeHeight, mHighStartColor, mHighEndColor, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawRoundRect(mTypeWidth, mTypeTop, mWidth, mTypeTop + mTypeHeight, mTypeCircleRadius, mTypeCircleRadius, mPaint);
        mPaint.setShader(null);
        mPaint.setColor(mLowEndColor);
        canvas.drawRect(mTypeWidth - mTypeCircleRadius, mTypeTop, mTypeWidth, mTypeTop + mTypeHeight, mPaint);
        mPaint.setColor(mHighStartColor);
        canvas.drawRect(mTypeWidth, mTypeTop, mTypeWidth + mTypeCircleRadius, mTypeTop + mTypeHeight, mPaint);
    }


    private void drawFlagAndTimestampData(Canvas canvas) {
        float flagLeft = 0f;
        if (mUseFlagMaxRight) {
            flagLeft = mWidth - mFlagWidth;
        } else {
            if (mProgress < 0f) {
                mProgress = 0f;
            }
            flagLeft = mProgress * mWidth;
        }
        canvas.drawBitmap(mFlagBitmap, flagLeft, 0f, null);
        mPaint.setTextSize(Utils.dp2px(10f));
        float timestampWidth = mPaint.measureText(mTimestamp);
        float dataWidth = mPaint.measureText(mData);
        float maxWidth = timestampWidth > dataWidth ? timestampWidth : dataWidth;
        float timestampAndDataRight = flagLeft + mFlagWidth + mGapBetweenFlagAndTimestamp + maxWidth;
        float timestampLeft = flagLeft + mFlagWidth + mGapBetweenFlagAndTimestamp;
        if (timestampAndDataRight >= mWidth) {
            timestampLeft = flagLeft - mGapBetweenFlagAndTimestamp - maxWidth;
        }
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float timestampBaseline = -fontMetrics.top;
        float dataBaseline = fontMetrics.bottom - fontMetrics.top + mGapBetweenTimestampAndData - fontMetrics.top;
        mPaint.setColor(COLOR_TIMESTAMP);
        canvas.drawText(mTimestamp, timestampLeft, timestampBaseline, mPaint);
        mPaint.setColor(COLOR_DATA);
        canvas.drawText(mData, timestampLeft, dataBaseline, mPaint);
    }

    public void setTimestampAndData(String timestamp, float data) {
        mTimestamp = timestamp;
        if (data >= mMaxValue) {
            mUseFlagMaxRight = true;
        } else {
            mUseFlagMaxRight = false;
            mProgress = data * 1.0f / mMaxValue;
        }
        if (!TextUtils.isEmpty(mUnit)) {
            mData = formatData(data) + " " + mUnit;
        } else {
            mData = formatData(data);
        }
        invalidate();
    }

    private String formatData(float data) {
        DecimalFormat decimalFormat=new DecimalFormat("0.00");;
        return decimalFormat.format(data);
    }
}
