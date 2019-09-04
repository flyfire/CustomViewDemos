package org.solarex.customviewdemos.widgets;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
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
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import org.solarex.customviewdemos.R;


public class HGradientViewExt extends View {
    private int startColor;
    private int endColor;
    private int gridColor;
    private int labelColor;
    private boolean isRound;
    private float stokeWidth, gridWidth, labelSize;
    private int gridCount = 3;
    private float maxValue = 60, minValue = 50;
    private float[] datas;
    private Rect rectMin = new Rect();
    private Rect rectMax = new Rect();
    private Rect rectTemp = new Rect();

    private Paint gridPaint;
    private Paint textPaint;
    private Paint chartPaint;
    private final float density;
    private int[] colors;
    private int progressBgColor = Color.parseColor("#F5F6F8");
    private long animDuration = 0;
    ValueAnimator valueAnimator;
    float maxFraction = 1.0f;

    public HGradientViewExt(Context context) {
        this(context, null);
    }

    public HGradientViewExt(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public HGradientViewExt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gridPaint = new Paint();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.TwoColorGradientView);
        isRound = mTypedArray.getBoolean(R.styleable.TwoColorGradientView_gv_isRound, false);
        startColor = mTypedArray.getColor(R.styleable.TwoColorGradientView_gv_startColor, Color.GREEN);
        endColor = mTypedArray.getColor(R.styleable.TwoColorGradientView_gv_endColor, Color.MAGENTA);
        gridColor = mTypedArray.getColor(R.styleable.TwoColorGradientView_gv_gridColor, Color.GRAY);
        stokeWidth = mTypedArray.getDimension(R.styleable.TwoColorGradientView_gv_stokeWidth, 10f);
        gridCount = mTypedArray.getInt(R.styleable.TwoColorGradientView_gv_gridCount, 3);
        gridWidth = mTypedArray.getDimension(R.styleable.TwoColorGradientView_gv_gridWidth, 15f);
        labelColor = mTypedArray.getColor(R.styleable.TwoColorGradientView_gv_labelColor, Color.GRAY);
        labelSize = mTypedArray.getDimension(R.styleable.TwoColorGradientView_gv_labelSize, 11f);

        gridPaint = new Paint();
        gridPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);

        chartPaint = new Paint();
        chartPaint.setAntiAlias(true);

        density = getContext().getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrids(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        drawBars(canvas);
    }

    protected void drawBars(Canvas canvas) {
        if (getDatas() == null || getDatas().length < 1 || getMaxValue() == getMinValue()) {
            return;
        }
        int length = getDatas().length;
        float realHeight = (getHeight() - labelSize - density * 5f - stokeWidth)//
                , realWidth = (getWidth() - rectMin.width() / 2f - rectMax.width() / 2f);
        float vStepoff = realHeight / (length - 1 + 2);//线居中
        float halfStoke = stokeWidth / 2f;
        chartPaint.setShader(null);
        chartPaint.setColor(progressBgColor);
        for (int i = 0; i < length; i++) {
            canvas.drawLine(rectMin.width() / 2f + halfStoke
                    , halfStoke + vStepoff * i + vStepoff
                    , rectMin.width() / 2f + realWidth - halfStoke
                    , halfStoke + vStepoff * i + vStepoff
                    , chartPaint);
        }

        chartPaint.setColor(startColor);
        chartPaint.setStyle(Paint.Style.STROKE);
        chartPaint.setStrokeWidth(stokeWidth);
        chartPaint.setStrokeCap(isRound ? Paint.Cap.ROUND : Paint.Cap.SQUARE);
        for (int i = 0; i < length; i++) {
            float progressWidth = realWidth * (datas[i] - getMinValue()) / (getMaxValue() - getMinValue());
            if (progressWidth == 0f) continue;
            LinearGradient shader = new LinearGradient(0, 0, realWidth, 0
                    , getIndexGradientColors(i)
                    , null
                    , Shader.TileMode.CLAMP);
            chartPaint.setShader(shader);
            if (progressWidth >= stokeWidth) {
                canvas.drawLine(rectMin.width() / 2f + halfStoke
                        , halfStoke + vStepoff * i + vStepoff
                        , rectMin.width() / 2f + progressWidth - halfStoke
                        , halfStoke + vStepoff * i + vStepoff
                        , chartPaint);
            } else {
                drawLessStokeWidth(canvas, chartPaint.getShader(), rectMin.width() / 2f, vStepoff * i + vStepoff, progressWidth);
            }
        }
    }

    protected void drawLessStokeWidth(Canvas canvas, Shader shader, float sx, float sy, float pwidth) {
        float wtch = getStokeWidth();
        Paint mDstPaint, mSrcPaint;
        Bitmap mSrcBitmap, mDstBitmap;
        Canvas mSrcCanvas, mDstCanvas;

        mDstPaint = new Paint();
        mSrcPaint = new Paint();
        mDstPaint.setColor(progressBgColor);
        mDstPaint.setAntiAlias(true);
        mSrcPaint.setColor(Color.parseColor("#ff0000"));
        mSrcPaint.setAntiAlias(true);

        //准备画布
        mSrcBitmap = Bitmap.createBitmap((int) (wtch + 0.5f), (int) (wtch + 0.5f), Bitmap.Config.ARGB_8888);
        mSrcCanvas = new Canvas(mSrcBitmap);
        mDstBitmap = Bitmap.createBitmap((int) (wtch + 0.5f), (int) (wtch + 0.5f), Bitmap.Config.ARGB_8888);
        mDstCanvas = new Canvas(mDstBitmap);
        //dst
        mDstCanvas.drawRect(0, 0, pwidth, wtch * 2f, mDstPaint);
        mSrcPaint.setStrokeCap(isRound() ? Paint.Cap.ROUND : Paint.Cap.SQUARE);
        mSrcPaint.setStrokeWidth(wtch);
        mSrcPaint.setShader(shader);
        mSrcCanvas.drawCircle(wtch / 2, wtch / 2, wtch / 2f, mSrcPaint);

        //准备好两张位图后在设置画笔模式，然后将图片画上去
        mDstPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mSrcBitmap, sx, sy, mSrcPaint);
        canvas.drawBitmap(mDstBitmap, sx, sy, mDstPaint);
        mDstPaint.setXfermode(null);
        canvas.drawRect(sx + pwidth, sy, sx + stokeWidth, sy + stokeWidth, mDstPaint);
    }

    protected void drawGrids(Canvas canvas) {
        gridPaint.setShader(null);
        gridPaint.setStrokeWidth(getGridWidth());
        gridPaint.setColor(getGridColor());
        gridPaint.setPathEffect(new DashPathEffect(new float[]{10f, 5f}, 0));

        //文字显示
        textPaint.setColor(getLabelColor());
        textPaint.setTextSize(getLabelSize());
        String strValue = String.format("%1.0f", getMinValue());
        textPaint.getTextBounds(strValue, 0, strValue.length(), rectMin);
//        float mw = textPaint.measureText(strValue);
        canvas.drawText(strValue, 0 + 0.5f * density, getHeight() - density * 4f, textPaint);

        strValue = String.format("%1.0f", getMaxValue());
        textPaint.getTextBounds(strValue, 0, strValue.length(), rectMax);
        canvas.drawText(strValue, getWidth() - rectMax.width() - 0.5f * density, getHeight() - density * 4f, textPaint);

        //画垂直虚线
        float hGridoff = (getWidth() - rectMin.width() / 2f - rectMax.width() / 2f - getGridWidth() * 2f) / (getGridCount() - 1);
        for (int i = 0; i < getGridCount(); i++) {
            canvas.drawLine(hGridoff * i + getGridWidth() + rectMin.width() / 2f, 0
                    , hGridoff * i + getGridWidth() + rectMin.width() / 2f
                    , getHeight() - getLabelSize() - density * 5f
                    , gridPaint);
        }

        if (getGridCount() > 1) {
            float maxminOff = (getMaxValue() - getMinValue()) / (getGridCount() - 1);
            for (int i = 1; i < getGridCount() - 1; i++) {
                strValue = String.format("%1.0f", maxminOff * i + getMinValue());
                textPaint.getTextBounds(strValue, 0, strValue.length(), rectTemp);
                canvas.drawText(strValue, hGridoff * i + getGridWidth() + rectMin.width() / 2f
                        - rectTemp.width() / 2f, getHeight() - density * 4f, textPaint);
            }
        }
    }

    int[] getIndexGradientColors(int rangeIndex) {
        //没有范围和颜色
        if (colors == null || colors.length < 1
                || datas == null || datas.length < 1) {//根据set颜色渐变
            int[] tcs = {startColor, endColor};
            return new int[]{tcs[rangeIndex % 2], tcs[rangeIndex % 2]};
        }
        //计算渐变色个数
        int colorNeedCount = datas.length;
        if (colors.length % colorNeedCount != 0) {//渐变
            throw new IllegalStateException("Colors should be Multiplication of range size plus one");
        } else {
            int step = colors.length / colorNeedCount;
            if (step == 1) {
                return new int[]{colors[rangeIndex], colors[rangeIndex]};
            }
            int[] tcolors = new int[step];
            for (int i = 0; i < step; i++) {
                tcolors[i] = colors[rangeIndex * step + i];
            }
            return tcolors;
        }
    }

    public float getStokeWidth() {
        return stokeWidth;
    }

    public void setStokeWidth(float stokeWidth) {
        this.stokeWidth = stokeWidth;
    }

    public boolean isRound() {
        return isRound;
    }

    public void setRound(boolean round) {
        isRound = round;
    }

    public int getStartColor() {
        return startColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public void setGridColor(int gridColor) {
        this.gridColor = gridColor;
    }

    public int getGridColor() {
        return gridColor;
    }

    public int getGridCount() {
        return gridCount;
    }

    public void setGridCount(int gridCount) {
        this.gridCount = gridCount;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        if (this.maxValue > 0f) {
            maxFraction = maxValue / this.maxValue;
        } else {
            maxFraction = 1.0f;
        }
        this.maxValue = maxValue;
        invalidate();
//        this.setMinMaxValue(0, maxValue);
    }

    public void setMinMaxValue(float minValue, float maxValue) {
        if (this.maxValue - this.minValue > 0f) {
            maxFraction = (maxValue - minValue) / (this.maxValue - this.minValue);
        } else {
            maxFraction = 1.0f;
        }
        if (maxFraction == 0f) maxFraction = 1.0f;
        this.maxValue = maxValue;
        this.minValue = minValue;

        invalidate();
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        if (minValue < 0) minValue = 0;
        this.minValue = minValue;
        invalidate();
    }

    public void setDatas(float... datas) {
        if (getAnimDuration() < 1) {
            animUpdateDatas(datas);
            return;
        }
        int maxCount = Math.max(this.datas == null ? 0 : this.datas.length, datas == null ? 0 : datas.length);
        PropertyValuesHolder[] valuesHolder = new PropertyValuesHolder[maxCount];
        for (int i = 0; i < maxCount; i++) {
            valuesHolder[i] = PropertyValuesHolder
                    .ofFloat("Index" + i, this.datas == null
                                    ? 0f : this.datas.length > i ? this.datas[i] * maxFraction : 0f
                            , datas == null ? 0f : datas.length > i ? datas[i] : 0f);
        }
        maxFraction = 1.0f;
        if (maxCount > 0) {
            valueAnimator = ObjectAnimator.ofPropertyValuesHolder(valuesHolder);
            valueAnimator.setDuration(getAnimDuration());
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float[] floatValues = new float[animation.getValues().length];
                    PropertyValuesHolder holder = null;
                    for (int i = 0; i < floatValues.length; i++) {
                        holder = animation.getValues()[i];
                        floatValues[i] = (Float) animation.getAnimatedValue(holder.getPropertyName());
                    }
                    animUpdateDatas(floatValues);
                }
            });
            valueAnimator.start();
        }
//        this.datas = datas;
//        invalidate();
    }

    protected void animUpdateDatas(float... datas) {
        this.datas = datas;
        this.invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (valueAnimator != null && valueAnimator.isRunning()) valueAnimator.cancel();
        super.onDetachedFromWindow();
    }

    public float[] getDatas() {
        return datas;
    }

    public float getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(float gridWidth) {
        this.gridWidth = gridWidth;
    }

    public int getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
    }

    public float getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(float labelSize) {
        this.labelSize = labelSize;
    }

    public int[] getColors() {
        return colors;
    }

    //2倍与数据长度渐变
    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public int getProgressBgColor() {
        return progressBgColor;
    }

    public void setProgressBgColor(int progressBgColor) {
        this.progressBgColor = progressBgColor;
    }

    public long getAnimDuration() {
        return animDuration;
    }

    public void setAnimDuration(long animDuration) {
        this.animDuration = animDuration;
    }

    //    protected void drawBars(Canvas canvas) {
//        if (getDatas() == null || getDatas().length < 1) {
//            return;
//        }
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        int length = getDatas().length;
//        float realHeight = (getHeight() - getLabelSize() - density * 5f - getStokeWidth())//
//                , realWidth = (getWidth() - rectMin.width() / 2f - rectMax.width() / 2f) - getStokeWidth();
//        float vStepoff = realHeight / (length - 1 + 2);//线居中
//
////        LinearGradient linearGradient = new LinearGradient(0, 0, 0, getHeight()
////                , getEndColor(), getStartColor(), Shader.TileMode.CLAMP);
////        chartPaint.setShader(linearGradient);
//        chartPaint.setColor(progressBgColor);
//        for (int i = 0; i < length; i++) {
//            canvas.drawLine(getStokeWidth() / 2f + rectMin.width() / 2f
//                    , getStokeWidth() / 2f + vStepoff * i + vStepoff
//                    , getStokeWidth() / 2f + rectMin.width() / 2f + realWidth
//                    , getStokeWidth() / 2f + vStepoff * i + vStepoff
//                    , chartPaint);
//        }
//
//        chartPaint.setColor(startColor);
//        chartPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        chartPaint.setStrokeWidth(getStokeWidth());
//        chartPaint.setStrokeCap(isRound ? Paint.Cap.ROUND : Paint.Cap.SQUARE);
//        if (colors == null || colors.length < 1) {
//            colors = new int[]{startColor, endColor};
//        }
//        for (int i = 0; i < length; i++) {
//            chartPaint.setColor(colors[i % colors.length]);
//            if (datas[i] <= getMinValue()) continue;
//            canvas.drawLine(getStokeWidth() / 2f + rectMin.width() / 2f
//                    , getStokeWidth() / 2f + vStepoff * i + vStepoff
//                    , getStokeWidth() / 2f + rectMin.width() / 2f
//                            + realWidth * (datas[i] - getMinValue()) / (getMaxValue() - getMinValue())
//                    , getStokeWidth() / 2f + vStepoff * i + vStepoff
//                    , chartPaint);
//        }
//    }
}
