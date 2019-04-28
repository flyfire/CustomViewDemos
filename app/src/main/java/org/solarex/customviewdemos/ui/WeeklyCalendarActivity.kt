package org.solarex.customviewdemos.ui

import android.os.Bundle
import android.util.Log
import com.solarexsoft.weeklycalendarview.WeeklyCalendarView
import com.solarexsoft.weeklycalendarview.WeeklyItemModel
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:07/2019/4/26
 *    Desc:
 * </pre>
 */
 
class WeeklyCalendarActivity : BaseCustomViewActivity() {
    companion object {
        val TAG = WeeklyCalendarActivity.javaClass.name
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_weeklycalendar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val wlc = findViewById<WeeklyCalendarView>(R.id.wcl_main)
        wlc.setWeekAgo(30)
        wlc.setOnWeeklyItemClickListener(object : WeeklyCalendarView.OnWeeklyItemClickListener{
            override fun onItemClick(position: Int, model: WeeklyItemModel?) {
                Log.d(TAG, "clicked -> " + model)
            }
        })
    }
}