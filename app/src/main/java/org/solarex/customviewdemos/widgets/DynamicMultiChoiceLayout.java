package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.solarexsoft.solarexcustomview.utils.Utils;

import org.solarex.customviewdemos.R;

import java.util.List;

/**
 * Author: Solarex
 * Date: 2019/10/9
 * Desc:
 */
public class DynamicMultiChoiceLayout extends LinearLayout {
    TextView tv_title;
    LinearLayout ll_content;
    ColorStateList mTextColorStateList;
    public DynamicMultiChoiceLayout(Context context) {
        super(context);
        init(context);
    }

    public DynamicMultiChoiceLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DynamicMultiChoiceLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dynamic_multi_choice, this, true);
        tv_title = view.findViewById(R.id.tv_title);
        ll_content = view.findViewById(R.id.ll_content);
        mTextColorStateList = context.getResources().getColorStateList(R.color.bg_dynamic_single_choice_textcolor);
    }

    public void setupView(DynamicMultiChoiceEntity entity) {
        String title = entity.getTitle();
        tv_title.setText(title);
        List<String> contents = entity.getContents();
        if (contents != null && contents.size() > 0) {
            int size = contents.size();
            for (int i = 0; i < size; i++) {
                String s = contents.get(i);
                CheckBox checkBox = new CheckBox(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) Utils.dp2px(50));
                lp.setMargins(0, 0, 0, (int)Utils.dp2px(10));
                checkBox.setLayoutParams(lp);
                checkBox.setButtonDrawable(null);
                checkBox.setBackgroundResource(R.drawable.bg_dynamic_single_choice);
                checkBox.setTextColor(mTextColorStateList);
                checkBox.setGravity(Gravity.CENTER);
                checkBox.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                checkBox.setText(s);
                ll_content.addView(checkBox);
            }
        }
    }
}
