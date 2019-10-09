package org.solarex.customviewdemos.ui

import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.widgets.DynamicMultiChoiceEntity
import org.solarex.customviewdemos.widgets.DynamicMultiChoiceLayout

/**
 * Author: Solarex
 * Date: 2019/10/8
 * Desc:
 */
class DynamicMultiChoiceActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_dynamic_multi_choice
    }

    override fun initView() {
        val dmcl = findViewById<DynamicMultiChoiceLayout>(R.id.dmcl_main)
        val contents = mutableListOf<String>()
        contents.add("抽烟")
        contents.add("经常饮酒")
        contents.add("饮食不规律")
        contents.add("熬夜")
        contents.add("以上均无")
        val entity = DynamicMultiChoiceEntity("你有以下哪些不良习惯（多选）？", contents)
        dmcl.setupView(entity)
    }
}