package org.solarex.customviewdemos.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.solarexsoft.solarexcustomview.utils.Utils;

import org.solarex.customviewdemos.R;

/**
 * Author: Solarex
 * Date: 2019/9/26
 * Desc:
 */
@SuppressLint("AppCompatCustomView")
public class ExpandableTextView extends TextView {
    private static final String TAG = "ExpandableTextView";
    private ImageSpan mTopicDownSpan,mTopicUpSpan;
    private final int DEFAULT_MAX_SHOW_LINES = 3;
    private int mMaxShowLines = DEFAULT_MAX_SHOW_LINES;
    private final String ELLIPSE = "...";
    private final String IMAGE_PLACEHOLDER = "中";
    private String mFullText;
    private int mInitWidth;
    private SpannableStringBuilder mCollapseString,mExpandString;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
//        mTopicDownSpan = new ImageSpan(context, R.drawable.expand_textview_topic_down);
        mTopicUpSpan = new ImageSpan(context, R.drawable.expand_textview_topic_up);
        Drawable drawable = context.getResources().getDrawable(R.drawable.expand_textview_topic_down);
        drawable.setBounds(0,0, (int)Utils.dp2px(22),(int)Utils.dp2px(22));
        mTopicDownSpan = new ImageSpan(drawable, DynamicDrawableSpan.ALIGN_BOTTOM);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
            mMaxShowLines = typedArray.getInteger(R.styleable.ExpandableTextView_maxshowlines, DEFAULT_MAX_SHOW_LINES);
            typedArray.recycle();
        }
    }

    public void test(String content) {
        Layout workingLayout = createWorkingLayout(content);
        for (int i = 0; i < workingLayout.getLineCount(); i++) {
            int startIndex = workingLayout.getLineStart(i);
            int endIndex = workingLayout.getLineEnd(i);
            String str = content.substring(startIndex, endIndex);
            Log.d(TAG, "line " + i + " --> " + str);
        }
    }
    // 方法不管用 getLayout返回null
    public void makeExpandable() {
        mFullText = getText().toString();
        /*
        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                ViewTreeObserver obs = getViewTreeObserver();
                obs.removeOnPreDrawListener(this);
                if (getLineCount() <= mMaxShowLines) {
                    setText(mFullText);
                } else {
                    setMovementMethod(LinkMovementMethod.getInstance());
                    showCollapse();
                }
                return true;
            }
        });
         */
        /*
        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
                if (getLineCount() <= mMaxShowLines) {
                    setText(mFullText);
                } else {
                    setMovementMethod(LinkMovementMethod.getInstance());
                    showCollapse();
                }
            }
        });
         */
    }

    public void makeExpandable(String content) {
        mFullText = content;
        Layout workingLayout = createWorkingLayout(content);
        if (workingLayout.getLineCount() > mMaxShowLines) {
            setMovementMethod(LinkMovementMethod.getInstance());
            int lineEndIndex = workingLayout.getLineEnd(mMaxShowLines - 1);
            Log.d(TAG, "lineEndIndex = " + lineEndIndex);
            getCollapseString(content, lineEndIndex);
            setText(mCollapseString, BufferType.SPANNABLE);
        } else {
            setText(content);
        }
    }

    private void getCollapseString(String content, int lineEndIndex) {
        int endIndex = lineEndIndex - 1;
        while (endIndex > 0) {
            String tmpStr = content.substring(0, endIndex) + ELLIPSE + IMAGE_PLACEHOLDER;
            Layout tmpLayout = createWorkingLayout(tmpStr);
            if (tmpLayout.getLineCount() > mMaxShowLines) {
                endIndex--;
            } else {
                break;
            }
        }
        Log.d(TAG, "endIndex = " + endIndex);
        String newText = mFullText.substring(0, endIndex) + ELLIPSE + IMAGE_PLACEHOLDER;
        mCollapseString = new SpannableStringBuilder(newText);
        mCollapseString.setSpan(mTopicDownSpan, newText.length() - IMAGE_PLACEHOLDER.length(), newText.length(), 0);
        mCollapseString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Log.d(TAG, "showExpand");
                showExpand();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, newText.length() - IMAGE_PLACEHOLDER.length(), newText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private Layout createWorkingLayout(String content) {
        Log.d(TAG, "width = " + getWidth());
        return new StaticLayout(content, getPaint(), getWidth() - getPaddingLeft() - getPaddingRight(), Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(),getLineSpacingExtra(), getIncludeFontPadding()  );
    }

    private void showCollapse() {
       int lineEndIndex = getLayout().getLineEnd(mMaxShowLines - 1);
       String newText = mFullText.substring(0, lineEndIndex - (ELLIPSE.length() + IMAGE_PLACEHOLDER.length() + 1)) + ELLIPSE + IMAGE_PLACEHOLDER;
       SpannableStringBuilder ssb = new SpannableStringBuilder(newText);
       ssb.setSpan(mTopicUpSpan, newText.length() - IMAGE_PLACEHOLDER.length(), newText.length(), 0);
       ssb.setSpan(new ClickableSpan() {
           @Override
           public void onClick(@NonNull View widget) {
               showExpand();
           }
       }, newText.length() - IMAGE_PLACEHOLDER.length(), newText.length(), 0);
       setText(ssb, BufferType.SPANNABLE);
    }

    private void showExpand() {
        if (mExpandString == null) {
            String newText = mFullText + IMAGE_PLACEHOLDER;
            mExpandString = new SpannableStringBuilder(newText);
            mExpandString.setSpan(mTopicUpSpan, newText.length() - IMAGE_PLACEHOLDER.length(), newText.length(), 0);
            mExpandString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    Log.d(TAG, "showCollapseNew");
                    showCollapseNew();
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    ds.setUnderlineText(false);
                }
            }, newText.length() - IMAGE_PLACEHOLDER.length(), newText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        setText(mExpandString, BufferType.SPANNABLE);
    }

    private void showCollapseNew() {
        setText(mCollapseString, BufferType.SPANNABLE);
    }


}
