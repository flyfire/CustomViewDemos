package org.solarex.customviewdemos.ui

import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.widgets.DynamicSingleChoiceEntity
import org.solarex.customviewdemos.widgets.DynamicSingleChoiceLayout

/**
 * Author: Solarex
 * Date: 2019/10/8
 * Desc:
 */
class DynamicSingleChoiceActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_dynamic_single_choice;
    }

    override fun initView() {
        val dscl_main = findViewById<DynamicSingleChoiceLayout>(R.id.dscl_main)
        val contents = mutableListOf<String>()
        contents.add("测试1")
        contents.add("测试2")
        contents.add("测试3")
        contents.add("测试4")
        val entity = DynamicSingleChoiceEntity("", "测试头部", contents)
        dscl_main.setupView(entity)
    }
}