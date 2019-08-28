package org.solarex.customviewdemos.ui

import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 16:15/2019-08-28
 *    Desc:
 * </pre>
 */
 
class DemoTestKeyboard : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        // 布局中使用IndexableLayout,键盘弹起，布局无法上浮，原因待查
        return R.layout.activity_demotest_keyboard_indexablelayout
    }
}