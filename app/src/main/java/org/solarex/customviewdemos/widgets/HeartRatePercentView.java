package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.solarexsoft.solarexcustomview.utils.Utils;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 19:42/2019-08-22
 *    Desc:
 * </pre>
 */

public class HeartRatePercentView extends View {
    private static final String TAG = HeartRatePercentView.class.getSimpleName();

    private Paint mLinePaint;
    private Paint mPaint;
    private int mLineColor = Color.parseColor("#979797");

    private int mWidth;
    float x = 0;
    float step = 0;
    int count = 50;
    float translate = 0;
    public HeartRatePercentView(Context context) {
        super(context);
    }

    public HeartRatePercentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeartRatePercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(Utils.dp2px(1));
        mLinePaint.setStyle(Paint.Style.FILL);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        x = (float) Math.sqrt((mWidth/2)*(mWidth/2)/2.0);
        step = 2 * x / count;
        translate = (mWidth/2)*(mWidth/2)/(2.0f * (mWidth/2));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        /*
        canvas.translate(100, 100);
        canvas.rotate(-45);
        canvas.drawRect(new Rect(0, 0, 400, 400), mLinePaint);
        mLinePaint.setColor(Color.RED);
        canvas.translate(-200, 0);
        canvas.drawRect(new Rect(0, 0,300, 300), mLinePaint);
        */
        /*
        int i = canvas.saveLayer(0, 0, mWidth, mWidth, mPaint);
        Bitmap dst = makeDst();
        Bitmap src = makeSrc();
        canvas.drawBitmap(dst, 0, 0, mPaint);
        mLinePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        canvas.drawBitmap(src, 0, 0, mPaint);
        canvas.restoreToCount(i);
        */
        canvas.drawColor(Color.BLUE);
        canvas.translate(translate, translate);
        canvas.rotate(-45);
        canvas.translate(-x, 0);
        BitmapShader shader = new BitmapShader(makeDst(), BitmapShader.TileMode.REPEAT, BitmapShader.TileMode.REPEAT);
        mPaint.setShader(shader);
        canvas.drawCircle(x, x, x, mPaint);
    }

    private Bitmap makeDst() {
        Bitmap bitmap = Bitmap.createBitmap(Math.round(2*x), Math.round(2*x), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        for (int i = 0; i < count; i++) {
            canvas.drawLine(0, i*step, 2*x, i*step, mLinePaint);
        }
        return bitmap;
    }

    private Bitmap makeSrc() {
        /*
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mWidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.save();
//        canvas.drawColor(Color.RED);
        canvas.translate(translate, translate);
        canvas.rotate(-45);
        canvas.translate(-x, 0);
        mLinePaint.setColor(Color.GREEN);
        canvas.drawCircle(x, x, x, mLinePaint);
        return bitmap;
        */
        Bitmap bitmap = Bitmap.createBitmap(Math.round(2*x), Math.round(2*x), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mLinePaint.setColor(Color.GREEN);
        canvas.drawCircle(x, x, x, mLinePaint);
        return bitmap;
    }
}
