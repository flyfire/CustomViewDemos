package org.solarex.customviewdemos.ui

import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.widgets.ExpandableTextView


/**
 * Author: Solarex
 * Date: 2019/9/25
 * Desc:
 */
// see https://blog.csdn.net/dodouaj/article/details/52212835
class ExpandTextViewActivity : BaseCustomViewActivity() {
    val expand = "\u25BC"
    val content = "测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本"
    override fun getLayoutId(): Int {
        return R.layout.activity_expand_textview
    }

    override fun initView() {
        val tv = findViewById<ExpandableTextView>(R.id.tv)
//        tv.makeExpandable(content)
        tv.post {
            tv.makeExpandable(content)
        }
    }
}