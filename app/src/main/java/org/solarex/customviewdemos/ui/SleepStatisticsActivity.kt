package org.solarex.customviewdemos.ui

import android.os.Bundle
import android.util.Log
import com.solarexsoft.sleepstatisticsview.SleepDurationModel
import com.solarexsoft.sleepstatisticsview.SleepStatisticsDrawModel
import com.solarexsoft.sleepstatisticsview.SleepStatisticsView
import org.solarex.customviewdemos.R



/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:29/2019/4/28
 *    Desc:
 * </pre>
 */
 
class SleepStatisticsActivity : BaseCustomViewActivity() {
    val TAG = SleepStatisticsActivity::class.java.simpleName
    override fun getLayoutId(): Int {
        return R.layout.activity_sleepstatistic
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ssv1 = findViewById<SleepStatisticsView>(R.id.ssv1)
        val ssv2 = findViewById<SleepStatisticsView>(R.id.ssv2)

        ssv1.setOnClickListener(object : SleepStatisticsView.OnClickListener{
            override fun onClick(model: SleepDurationModel?) {
                Log.d(TAG, "ssv1 touch start = ${model?.starttime}, end = ${model?.endtime}, duration = ${model?.duration}")
            }
        })
        ssv2.setOnClickListener(object : SleepStatisticsView.OnClickListener{
            override fun onClick(model: SleepDurationModel?) {
                Log.d(TAG, "ssv2 touch start = ${model?.starttime}, end = ${model?.endtime}, duration = ${model?.duration}")
            }
        })

        val y23pm: Long = 1553007600
        val t10am: Long = 1553047200
        val t12am: Long = 1553054400
        val t13pm: Long = 1553058000
        val total = t13pm - y23pm

        val tmp2 = SleepStatisticsDrawModel()
        tmp2.start = "23:00"
        tmp2.end = "13:00"
        val durations2 = ArrayList<SleepDurationModel>()
        tmp2.models = durations2
        val duration4 = SleepDurationModel()
        duration4.starttime = y23pm
        duration4.endtime = t13pm
        duration4.duration = total
        duration4.isSleep = true
        duration4.percent = 1.0f
        durations2.add(duration4)
        ssv2.setModel(tmp2)

        val tmp1 = SleepStatisticsDrawModel()
        tmp1.start = "23:00"
        tmp1.end = "13:00"
        val durations1 = ArrayList<SleepDurationModel>()
        tmp1.models = durations1
        val duration1 = SleepDurationModel()
        duration1.starttime = y23pm
        duration1.endtime = t10am
        duration1.duration = t10am - y23pm
        duration1.percent = duration1.duration * 1.0f / total
        duration1.isSleep = true
        durations1.add(duration1)
        val duration2 = SleepDurationModel()
        duration2.starttime = t10am
        duration2.endtime = t12am
        duration2.duration = t12am - t10am
        duration2.percent = duration2.duration * 1.0f / total
        duration2.isSleep = false
        durations1.add(duration2)
        val duration3 = SleepDurationModel()
        duration3.starttime = t12am
        duration3.endtime = t13pm
        duration3.duration = t13pm - t12am
        duration3.percent = duration3.duration * 1.0f / total
        duration3.isSleep = true
        durations1.add(duration3)

        ssv1.setModel(tmp1)
    }
}