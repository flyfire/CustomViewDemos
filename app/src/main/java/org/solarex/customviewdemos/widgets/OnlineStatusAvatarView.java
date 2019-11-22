package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.solarexsoft.solarexcustomview.utils.Utils;

import org.solarex.customviewdemos.R;

public class OnlineStatusAvatarView extends AppCompatImageView {

    private static final int DEFAULT_OUTER_RADIUS = (int) Utils.dp2px(6f);
    private static final int DEFAULT_OUTER_COLOR = Color.parseColor("#ffffff");
    private static final int DEFAULT_INNER_RADIUS = (int) Utils.dp2px(4f);
    private static final int DEFAULT_INNER_COLOR = Color.parseColor("#d1d7da");

    private static final int ONLINE_COLOR = Color.parseColor("#00D5A8");
    private static final int OFFLINE_COLOR = Color.parseColor("#D1D7DA");
    public static final int STATUS_ONLINE = 0, STATUS_OFFLINE = 1;

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 2;

    private int mOuterRadius,mInnerRadius;
    private int mOuterColor,mInnerColor;
    private boolean mShouldDrawDot;

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private Paint mBitmapPaint;
    private Paint mInnerDotPaint,mOuterDotPaint;
    private int mWidth;
    private int mRadius;
    private int innerDotCenter,outerDotCenter;

    public OnlineStatusAvatarView(Context context) {
        this(context, null);
    }

    public OnlineStatusAvatarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OnlineStatusAvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OnlineStatusAvatarView);
            mOuterColor = typedArray.getColor(R.styleable.OnlineStatusAvatarView_outerDotColor, DEFAULT_OUTER_COLOR);
            mOuterRadius = typedArray.getDimensionPixelSize(R.styleable.OnlineStatusAvatarView_outerDotRadius, DEFAULT_OUTER_RADIUS);
            mInnerColor = typedArray.getColor(R.styleable.OnlineStatusAvatarView_innerDotColor, DEFAULT_INNER_COLOR);
            mInnerRadius = typedArray.getDimensionPixelSize(R.styleable.OnlineStatusAvatarView_innerDotRadius, DEFAULT_INNER_RADIUS);
            mShouldDrawDot = typedArray.getBoolean(R.styleable.OnlineStatusAvatarView_shouldDrawDot, true);
            typedArray.recycle();
        }
        super.setScaleType(ScaleType.CENTER_CROP);
        setup();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mRadius = mWidth/2;
        outerDotCenter = mWidth - mOuterRadius - (int)Utils.dp2px(2f);
        initializeBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) {
            return;
        }
        canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
        if (mShouldDrawDot) {
            canvas.drawCircle(outerDotCenter, outerDotCenter, mOuterRadius, mOuterDotPaint);
            canvas.drawCircle(outerDotCenter, outerDotCenter, mInnerRadius, mInnerDotPaint);
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        initializeBitmap();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        initializeBitmap();
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        initializeBitmap();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        initializeBitmap();
    }

    private void initializeBitmap() {
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private void setup() {
        if (mBitmapPaint == null) {
            mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        if (mInnerDotPaint == null) {
            mInnerDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mInnerDotPaint.setStyle(Paint.Style.FILL);
        }
        mInnerDotPaint.setColor(mInnerColor);
        if (mOuterDotPaint == null) {
            mOuterDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mOuterDotPaint.setStyle(Paint.Style.FILL);
        }
        mOuterDotPaint.setColor(mOuterColor);
        if (getWidth() == 0 && getHeight() == 0) {
            return;
        }

        if (mBitmap == null) {
            return;
        }
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapPaint.setShader(mBitmapShader);
        invalidate();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setOnlineStatus(int status) {
        if (status == STATUS_ONLINE) {
            mInnerColor = ONLINE_COLOR;
        } else if (status == STATUS_OFFLINE) {
            mInnerColor = OFFLINE_COLOR;
        }
    }
}
