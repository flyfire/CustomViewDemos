package com.solarexsoft.solarexcustomview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.solarexsoft.solarexcustomview.R;

/**
 * Created by houruhou on 07/02/2018.
 */

public class PaperPlaneView extends View {
    private float currentValue1 = 0;
    private float currentValue2 = 0;
    private Matrix mMatrix1;
    private Matrix mMatrix2;
    private Path mPath1;
    private PathMeasure mMeasure1;
    private Path mPath2;
    private PathMeasure mMeasure2;
    private Bitmap mBitmap;
    private float[] mPos;
    private float[] mTan;
    private Paint mPaint;

    private int mWidth;
    private int mHeight;


    public PaperPlaneView(Context context) {
        this(context, null);
    }

    public PaperPlaneView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaperPlaneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 10;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.paper_plane,
                options);
        mMatrix1 = new Matrix();
        mPath1 = new Path();
        mMeasure1 = new PathMeasure();
        mMatrix2 = new Matrix();
        mPath2 = new Path();
        mMeasure2 = new PathMeasure();
        mPos = new float[2];
        mTan = new float[2];
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w/2;
        mHeight = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUsePosTan(canvas);
        drawUseMatrix(canvas);
    }

    private void drawUsePosTan(Canvas canvas) {
        mPath1.addCircle(mWidth, mHeight+200, 200, Path.Direction.CW);
        mMeasure1.setPath(mPath1, false);
        currentValue1 += 0.005;
        if (currentValue1 >= 1) {
            currentValue1 = 0;
        }
        mMeasure1.getPosTan(mMeasure1.getLength() * currentValue1, mPos, mTan);
        mMatrix1.reset();
        float degree = (float) (Math.atan2(mTan[1], mTan[0]) * 180.0f / Math.PI);
        mMatrix1.postRotate(degree, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        mMatrix1.postTranslate(mPos[0] - mBitmap.getWidth() / 2, mPos[1] - mBitmap.getHeight() / 2);
        canvas.drawPath(mPath1, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix1, mPaint);
        invalidate();
    }

    private void drawUseMatrix(Canvas canvas) {
        canvas.save();
        canvas.translate(mWidth, mHeight);
        mPath2.addCircle(0, -200, 200, Path.Direction.CCW);
        mMeasure2.setPath(mPath2, false);
        currentValue2 += 0.005;
        if (currentValue2 >= 1) {
            currentValue2 = 0;
        }
        mMeasure2.getMatrix(mMeasure2.getLength() * currentValue2, mMatrix2, PathMeasure
                .POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        mMatrix2.preTranslate(-mBitmap.getWidth()/2, -mBitmap.getHeight()/2);
        canvas.drawPath(mPath2, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix2, mPaint);
        canvas.restore();
        invalidate();
    }


}
