package org.solarex.customviewdemos.ui

import android.os.Bundle
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:07/2019/4/26
 *    Desc:
 * </pre>
 */
 
class WeeklyCalendarActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_weeklycalendar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}