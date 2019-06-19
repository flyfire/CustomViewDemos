package org.solarex.customviewdemos.ui

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.TextView
import org.solarex.customviewdemos.R

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 11:26/2019/6/19
 *    Desc:
 * </pre>
 */
 
class DemoTestActivity : BaseCustomViewActivity() {
    val TAG = DemoTestActivity::class.java.simpleName

    override fun getLayoutId(): Int {
        return R.layout.activity_demo_test;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv_content = findViewById<TextView>(R.id.tv_content)
        val str = String.format(resources.getString(R.string.content_format), resources.getString(R.string.content))
        Log.d(TAG, "str = $str")
        // textview 不支持 css 放弃 或尝试使用 https://github.com/SufficientlySecure/html-textview
        tv_content.text = Html.fromHtml(str)
    }
}