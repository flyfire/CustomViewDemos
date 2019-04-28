package org.solarex.customviewdemos.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.solarexsoft.solarexnumberprogressbar.SolarexNumberProgressbar
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 20:28/2019/4/28
 *    Desc:
 * </pre>
 */

class NumberProgressbarActivity : BaseCustomViewActivity() {
    val MSG_UPDATE = 100;
    var isIncrease = true;
    lateinit var mHandler: Handler

    override fun getLayoutId(): Int {
        return R.layout.activity_numberprogressbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<SolarexNumberProgressbar>(R.id.snp_5).also {
            it.progress = 60
        }
        val snp_1 = findViewById<SolarexNumberProgressbar>(R.id.snp_1)
        mHandler = Handler(Looper.getMainLooper(), object : Handler.Callback {
            override fun handleMessage(msg: Message?): Boolean {
                if (msg?.what == MSG_UPDATE) {
                    var progress = snp_1.progress
                    if (progress <= 0) {
                        progress = 0
                        isIncrease = true
                    } else if (progress >= 100) {
                        progress = 100
                        isIncrease = false
                    }
                    if (isIncrease) {
                        progress++
                    } else {
                        progress--
                    }
                    snp_1.progress = progress
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE, 100)
                }
                return true;
            }
        })
        mHandler.sendEmptyMessage(MSG_UPDATE)
    }

    override fun onDestroy() {
        mHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}