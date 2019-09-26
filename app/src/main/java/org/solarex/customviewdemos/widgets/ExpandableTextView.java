package org.solarex.customviewdemos.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

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
    private final String IMAGE_PLACEHOLDER = "  ";
    private String mFullText;

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
        mTopicDownSpan = new ImageSpan(context, R.drawable.expand_textview_topic_down);
        mTopicUpSpan = new ImageSpan(context, R.drawable.expand_textview_topic_up);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
            mMaxShowLines = typedArray.getInteger(R.styleable.ExpandableTextView_maxshowlines, DEFAULT_MAX_SHOW_LINES);
            typedArray.recycle();
        }
    }
    public void makeExpandable() {
        mFullText = getText().toString();
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
        String newText = mFullText + IMAGE_PLACEHOLDER;
        SpannableStringBuilder ssb = new SpannableStringBuilder(newText);
        ssb.setSpan(mTopicUpSpan, newText.length() - IMAGE_PLACEHOLDER.length(), newText.length(), 0);
        ssb.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showCollapse();
            }
        }, newText.length() - IMAGE_PLACEHOLDER.length(), newText.length(), 0);
        setText(ssb, BufferType.SPANNABLE);
    }


}
