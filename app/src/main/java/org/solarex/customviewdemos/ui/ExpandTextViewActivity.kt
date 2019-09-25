package org.solarex.customviewdemos.ui

import android.widget.TextView
import org.solarex.customviewdemos.R


/**
 * Author: Solarex
 * Date: 2019/9/25
 * Desc:
 */
// see https://blog.csdn.net/dodouaj/article/details/52212835
class ExpandTextViewActivity : BaseCustomViewActivity() {
    val expand = "\u25BC"
    val content = "测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本"
    override fun getLayoutId(): Int {
        return R.layout.activity_expand_textview
    }

    override fun initView() {
        val tv = findViewById<TextView>(R.id.tv)
        tv.text = content
    }
}