package org.solarex.customviewdemos.impl;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by houruhou on 07/02/2018.
 */

public class DividerItemdecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private RectF mRectF;
    private int mDividerHeight;

    public DividerItemdecoration() {
        super();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#ff00ddff"));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
        mRectF = new RectF();
        mDividerHeight = 15;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        float left = parent.getPaddingLeft();
        float right = parent.getWidth()-parent.getPaddingRight();
        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            float top = view.getBottom() + params.bottomMargin;
            float bottom = top + mDividerHeight;
            mRectF.set(left, top, right, bottom);
            c.drawRoundRect(mRectF, mDividerHeight, mDividerHeight, mPaint);
        }
    }
}
