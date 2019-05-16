package org.solarex.customviewdemos.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.solarexsoft.displayconncetview.DisplayConnectWithTextView
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 15:04/2019/5/16
 *    Desc:
 * </pre>
 */
 
class DisplayConnectWithTextActivity : BaseCustomViewActivity() {
    var i = 0;
    var isFinished = false;
    val fixed = "solarex";

    override fun getLayoutId(): Int {
        return R.layout.activity_displayconnectwithtext;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dcv_1 = findViewById<DisplayConnectWithTextView>(R.id.dcv_1);
        val ll_main = findViewById<LinearLayout>(R.id.ll_main);
        ll_main.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                dcv_1.setText("$fixed $i")
                i+=10
                isFinished = !isFinished
                dcv_1.setFinished(isFinished)
            }

        });
    }
}