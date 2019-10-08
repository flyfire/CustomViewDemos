package org.solarex.customviewdemos.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.solarexsoft.solarexcustomview.utils.Utils;

import org.solarex.customviewdemos.R;

import java.util.List;

/**
 * Author: Solarex
 * Date: 2019/10/8
 * Desc:
 */
public class DynamicSingleChoiceLayout extends LinearLayout {
    ImageView iv_head;
    TextView tv_title;
    RadioGroup rg_content;
    ColorStateList mTextColorStateList;
    public DynamicSingleChoiceLayout(Context context) {
        super(context);
        init(context);
    }

    public DynamicSingleChoiceLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DynamicSingleChoiceLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dynamic_single_choice, this, true);
        iv_head = view.findViewById(R.id.iv_head);
        tv_title = view.findViewById(R.id.tv_title);
        rg_content = view.findViewById(R.id.rg_content);
        mTextColorStateList = context.getResources().getColorStateList(R.color.bg_dynamic_single_choice_textcolor);
    }

    public void setupView(DynamicSingleChoiceEntity entity) {
        String imgUrl = entity.getIvHeadUrl();
        iv_head.setImageResource(R.mipmap.ic_launcher);
        String title = entity.getTitle();
        tv_title.setText(title);
        List<String> contents = entity.getContents();
        if (contents != null && contents.size() > 0) {
            int size = contents.size();
            for (int i = 0; i < size; i++) {
                String content = contents.get(i);
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setButtonDrawable(null);
                radioButton.setBackgroundResource(R.drawable.bg_dynamic_single_choice);
                radioButton.setTextColor(mTextColorStateList);
                RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(LayoutParams.MATCH_PARENT, (int)Utils.dp2px(50));
                lp.setMargins(0, 0, 0, (int)Utils.dp2px(10));
                radioButton.setLayoutParams(lp);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                radioButton.setText(content);
                rg_content.addView(radioButton);
            }
        }
    }
}
