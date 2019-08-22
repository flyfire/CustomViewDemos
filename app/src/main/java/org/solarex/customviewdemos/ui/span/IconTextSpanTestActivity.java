package org.solarex.customviewdemos.ui.span;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ReplacementSpan;
import android.widget.TextView;

import org.solarex.customviewdemos.R;
import org.solarex.customviewdemos.ui.BaseCustomViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:17/2019-07-02
 *    Desc:
 * </pre>
 */

public class IconTextSpanTestActivity extends BaseCustomViewActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_span_demotest3;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv_content = findViewById(R.id.tv_content);
        tv_content.setTextSize(20);

        List<ReplacementSpan> spans = new ArrayList<>();
        String content = "Android是一种基于Linux的自由及开放源代码的操作系统，主要使用于移动设备，如智能手机和平板电脑，由Google公司和开放手机联盟领导及开发。尚未有统一中文名称，中国大陆地区较多人使用“安卓”或“安致”。";
        StringBuilder stringBuilder = new StringBuilder();
        //第一个Span
        stringBuilder.append(" ");
        IconTextSpan topSpan = new IconTextSpan(getApplicationContext(), R.color.colorPrimary, "置顶");
        spans.add(topSpan);
        //第二个Span
        stringBuilder.append(" ");
        IconTextSpan hotSpan = new IconTextSpan(getApplicationContext(), R.color.colorAccent, "热");
        hotSpan.setRightMarginDpValue(5);
        spans.add(hotSpan);

        stringBuilder.append(content);
        SpannableString spannableString = new SpannableString(stringBuilder.toString());
        //循环遍历设置Span
        for (int i = 0; i < spans.size(); i++) {
            spannableString.setSpan(spans.get(i), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv_content.setText(spannableString);
    }
}
