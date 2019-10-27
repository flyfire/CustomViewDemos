package org.solarex.customviewdemos.ui


/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:59/2019-10-27
 *    Desc:
 * </pre>
 */
 
class FullScreenMoveViewActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return org.solarex.customviewdemos.R.layout.activity_full_screen_move_view
    }

    override fun initView() {
        window.statusBarColor = resources.getColor(android.R.color.transparent)
    }
}