package org.solarex.customviewdemos.ui

import android.os.Bundle
import com.solarexsoft.displayconncetview.DisplayConnectView
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 14:57/2019/5/16
 *    Desc:
 * </pre>
 */
 
class DisplayConnectActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_displayconnect
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dcv_1 = findViewById<DisplayConnectView>(R.id.dcv_1);
        val dcv_6 = findViewById<DisplayConnectView>(R.id.dcv_6);

        dcv_1.setConnectType(DisplayConnectView.ConnectType.START);
        dcv_6.setConnectType(DisplayConnectView.ConnectType.END);
    }
}