package org.solarex.customviewdemos.ui

import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.widgets.ketonuriatesting.CommonTestingView

class CommonTestingActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_common_testing
    }

    override fun initView() {
        val ctv_1 = findViewById<CommonTestingView>(R.id.ctv_1)
        ctv_1.setTimestampAndData("10/21 09:00", 3.7f);
        val ctv_2 = findViewById<CommonTestingView>(R.id.ctv_2)
        ctv_2.setTimestampAndData("10/21 09:00", 0.1f);
        val ctv_3 = findViewById<CommonTestingView>(R.id.ctv_3)
        ctv_3.setTimestampAndData("10/21 09:00", 0.9f);
        val ctv_4 = findViewById<CommonTestingView>(R.id.ctv_4)
        ctv_4.setTimestampAndData("10/21 09:00", 3.0f);
        val ctv_5 = findViewById<CommonTestingView>(R.id.ctv_5)
        ctv_5.setTimestampAndData("10/21 09:00", 1.0f);
    }
}