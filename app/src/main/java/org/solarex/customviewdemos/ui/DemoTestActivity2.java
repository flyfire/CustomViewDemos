package org.solarex.customviewdemos.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ReplacementSpan;
import android.widget.TextView;

import org.solarex.customviewdemos.R;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 20:22/2019/6/19
 *    Desc:
 * </pre>
 */

public class DemoTestActivity2 extends BaseCustomViewActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_test;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv_content = findViewById(R.id.tv_content);
        String[] tags = {"精华", "活动","推荐"};
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        for (String tag : tags) {
            String thisTag = " " + tag + " ";
            stringBuilder.append(thisTag);
            RoundedBackgroundSpan span;
            if("活动".equals(tag)){
                span= new RoundedBackgroundSpan(ContextCompat.getColor(this,R.color.test3), ContextCompat.getColor(this,R.color.white));
            }else if ("推荐".equals(tag)){
                span= new RoundedBackgroundSpan(ContextCompat.getColor(this,R.color.test2), ContextCompat.getColor(this,R.color.white));
            }else{
                span= new RoundedBackgroundSpan(ContextCompat.getColor(this,R.color.test1), ContextCompat.getColor(this,R.color.white));
            }
            stringBuilder.setSpan(span, stringBuilder.length() - thisTag.length(), stringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            stringBuilder.append(" ");
        }
        stringBuilder.append("王宝强凌晨宣布离婚，妻子劈腿经纪人。王宝强凌晨宣布离婚，妻子劈腿经纪人。王宝强凌晨宣布离婚，妻子劈腿经纪人。");
        tv_content.setText(stringBuilder);
    }

    class RoundedBackgroundSpan  extends ReplacementSpan {

        private int CORNER_RADIUS = 8;
        private int backgroundColor = 0;
        private int textColor = 0;

        public RoundedBackgroundSpan(int backgroundColor,int textColor) {
            super();
            this.backgroundColor = backgroundColor;
            this.textColor = textColor;
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            Paint.FontMetrics fm = paint.getFontMetrics();
//        RectF rect = new RectF(x, top, x + measureText(paint, text, start, end), bottom);
            // fm.bottom - fm.top 解决设置行距（android:lineSpacingMultiplier="1.2"）时背景色高度问题
            RectF rect = new RectF(x, top, x + measureText(paint, text, start, end), fm.bottom - fm.top );
            paint.setColor(backgroundColor);
            canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);
            paint.setColor(textColor);
            canvas.drawText(text, start, end, x, y, paint);
        }

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm){
            return Math.round(paint.measureText(text, start, end));
        }

        private float measureText(Paint paint, CharSequence text, int start, int end) {
            return paint.measureText(text, start, end);
        }
    }
}
