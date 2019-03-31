package com.solarexsoft.solarexcustomview.customview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by houruhou on 06/02/2018.
 */

public class SearchView extends View {
    public static final String TAG = SearchView.class.getSimpleName();
    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    private State mCurrentState = State.NONE;

    // 放大镜与外部圆环
    private Path path_search;
    private Path path_circle;

    private PathMeasure mMeasure;

    private int mDuration = 2000;

    // 控制各个过程的动画
    private ValueAnimator mStartingAnimator;
    private ValueAnimator mSearchingAnimator;
    private ValueAnimator mEndingAnimator;

    // 动画数值，用于控制动画状态，同一时间只允许有一种状态出现，具体数值处理取决于当前状态
    private float mAnimatorValue = 0;

    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Animator.AnimatorListener mAnimatorListener;

    private Handler mAnimatorHandler;

    // 判断搜索是否已经结束
    private boolean isOver = false;

    private int count = 0;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
        initPath();
        initListener();
        initHandler();
        initAnimator();

        //进入开始动画
        mCurrentState = State.STARTING;
        mStartingAnimator.start();
    }

    public static enum State {
        NONE,
        STARTING,
        SEARCHING,
        ENDING
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
    }

    private void initPath() {
        path_search = new Path();
        path_circle = new Path();

        mMeasure = new PathMeasure();

        // 注意,不要到360度,否则内部会自动优化,测量不能取到需要的数值
        RectF oval1 = new RectF(-50, -50, 50, 50);//放大镜圆环
        path_search.addArc(oval1, 45, 359.9f);// 顺时针角度依次是 -90,0,90

        RectF oval2 = new RectF(-100, -100, 100, 100); //外部圆环
        path_circle.addArc(oval2, 45, -359.9f);

        float[] pos = new float[2];
        mMeasure.setPath(path_circle, false);
        mMeasure.getPosTan(0, pos, null); // 外部圆45度那个点的位置

        path_search.lineTo(pos[0], pos[1]); // 放大镜把手

        Log.d(TAG, "pos = " + pos[0] + "," + pos[1]);
    }

    private void initListener() {
        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };
        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimatorHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }

    private void initHandler() {
        mAnimatorHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (mCurrentState) {
                    case STARTING:
                        isOver = false;
                        mCurrentState = State.SEARCHING;
                        mStartingAnimator.removeAllListeners();
                        mSearchingAnimator.start();
                        break;
                    case SEARCHING:
                        if (!isOver) {
                            mSearchingAnimator.start();
                            count++;
                            if (count > 2) {
                                isOver = true;
                            }
                        } else {
                            mCurrentState = State.ENDING;
                            mEndingAnimator.start();
                        }
                        break;
                    case ENDING:
                        mCurrentState = State.NONE;
                        break;
                }
            }
        };
    }

    private void initAnimator() {
        mStartingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(mDuration);
        mSearchingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(mDuration);
        mEndingAnimator = ValueAnimator.ofFloat(1, 0).setDuration(mDuration);

        mStartingAnimator.addUpdateListener(mUpdateListener);
        mSearchingAnimator.addUpdateListener(mUpdateListener);
        mEndingAnimator.addUpdateListener(mUpdateListener);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchingAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSearch(canvas);
    }

    private void drawSearch(Canvas canvas) {
        mPaint.setColor(Color.WHITE);

        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawColor(Color.parseColor("#0082d7"));

        switch (mCurrentState) {
            case NONE:
                canvas.drawPath(path_search, mPaint);
                break;
            case STARTING:
                mMeasure.setPath(path_search, false);
                Path dst1 = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(),
                        dst1, true);
                canvas.drawPath(dst1, mPaint);
                break;
            case SEARCHING:
                mMeasure.setPath(path_circle, false);
                Path dst2 = new Path();
                float stop = mMeasure.getLength() * mAnimatorValue;
                float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
                mMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;
            case ENDING:
                mMeasure.setPath(path_search, false);
                Path dst3 = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(),
                    dst3, true);
                canvas.drawPath(dst3, mPaint);
                break;
        }
    }


}
