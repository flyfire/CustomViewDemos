package org.solarex.customviewdemos.widgets.checkedbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.solarexsoft.solarexcustomview.utils.Utils;

import org.solarex.customviewdemos.R;

/**
 * Author: Solarex
 * Date: 2019/10/11
 * Desc:
 */
@SuppressLint("AppCompatCustomView")
public class SolarexCheckedButton extends RadioButton {
    private Bitmap mTickBitmap;
    private int mTickWidth = (int)Utils.dp2px(14f);
    private int mTickHeight = mTickWidth;
    private int mTickMarginRight = (int)Utils.dp2px(18);
    private int mTickLeft,mTickTop;
    private Paint mTransparentPaint;
    private Paint mTextPaint;

    public SolarexCheckedButton(Context context) {
        super(context);
        init(context);
    }

    public SolarexCheckedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SolarexCheckedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_tick);
        convertDrawable2TickBitmap(drawable);
        mTransparentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTransparentPaint.setColor(context.getResources().getColor(android.R.color.transparent));
        mTransparentPaint.setStyle(Paint.Style.FILL);
        mTransparentPaint.setStrokeWidth(1f);
    }

    private void convertDrawable2TickBitmap(Drawable drawable) {
        mTickBitmap = Bitmap.createBitmap(mTickWidth, mTickHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mTickBitmap);
        drawable.setBounds(0, 0, mTickWidth, mTickHeight);
        drawable.draw(canvas);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTickLeft = w - mTickMarginRight - mTickWidth;
        mTickTop = (h - mTickHeight)>>1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mTextPaint = getPaint();
        if (isChecked()) {
            mTextPaint.setFakeBoldText(true);
        } else {
            mTextPaint.setFakeBoldText(false);
        }
        super.onDraw(canvas);
        if (isChecked()) {
            drawTickAtRight(canvas);
        } else {
            drawEmptyAtRight(canvas);
        }
    }

    private void drawTickAtRight(Canvas canvas) {
        canvas.drawBitmap(mTickBitmap, mTickLeft, mTickTop, null);
    }

    private void drawEmptyAtRight(Canvas canvas) {
        canvas.drawRect(mTickLeft, mTickTop, mTickLeft + mTickWidth, mTickTop + mTickHeight, mTransparentPaint);
    }
}
