package org.solarex.customviewdemos.ui

import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.widgets.ketonuriatesting.KetonuriaTestingView

class KetonuriaTestingActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_ketonuria_testing
    }

    override fun initView() {
        val ktv_1 = findViewById<KetonuriaTestingView>(R.id.ktv_1)
        val ktv_2 = findViewById<KetonuriaTestingView>(R.id.ktv_2)
        val ktv_3 = findViewById<KetonuriaTestingView>(R.id.ktv_3)
        val ktv_4 = findViewById<KetonuriaTestingView>(R.id.ktv_4)
        val ktv_5 = findViewById<KetonuriaTestingView>(R.id.ktv_5)
        val ktv_6 = findViewById<KetonuriaTestingView>(R.id.ktv_6)
        ktv_2.setCheckIndexTimestampAndData(2, "10/21 09:00", "50(5.0)")
        ktv_3.setCheckIndexTimestampAndData(3, "10/21 09:00", "50(5.0)")
        ktv_4.setCheckIndexTimestampAndData(4, "10/21 09:00", "50(5.0)")
        ktv_5.setCheckIndexTimestampAndData(5, "10/21 09:00", "50(5.0)")
        ktv_6.setCheckIndexTimestampAndData(6, "10/21 09:00", "50(5.0)")
    }
}