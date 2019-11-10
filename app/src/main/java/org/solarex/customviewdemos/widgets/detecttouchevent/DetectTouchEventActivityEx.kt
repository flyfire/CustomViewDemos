package org.solarex.customviewdemos.widgets.detecttouchevent

import android.os.*
import android.util.Log
import android.util.LogPrinter
import android.view.MotionEvent
import android.widget.Toast
import org.solarex.customviewdemos.R
import org.solarex.customviewdemos.ui.BaseCustomViewActivity

class DetectTouchEventActivityEx : BaseCustomViewActivity() {
    val TAG = DetectTouchEventActivityEx::class.java.simpleName

    override fun getLayoutId(): Int {
        return org.solarex.customviewdemos.R.layout.layout_detect_touch_ex
    }

    override fun initView() {
        super.initView()
        val dteve_1 = findViewById<DetectTouchEventViewEx>(R.id.dteve_1)
        val dteve_2 = findViewById<DetectTouchEventViewEx>(R.id.dteve_2)
        val dteve_3 = findViewById<DetectTouchEventViewEx>(R.id.dteve_3)
        Log.d(TAG, "ex-1-> ${dteve_1.id}")
        Log.d(TAG, "ex-2-> ${dteve_2.id}")
        Log.d(TAG, "ex-3-> ${dteve_3.id}")
        Log.d(TAG, "DOWN = ${MotionEvent.ACTION_DOWN}")
        Log.d(TAG, "MOVE = ${MotionEvent.ACTION_MOVE}")
        Log.d(TAG, "UP = ${MotionEvent.ACTION_UP}")
        Log.d(TAG, "CANCEL = ${MotionEvent.ACTION_CANCEL}")
        Thread{
            Looper.prepare();
            val handler = Handler(Looper.myLooper()){
                when(it.what) {
                    100 -> {
                        Log.d("solarex-dump", "100 invoked")
                        SystemClock.sleep(3000)
                    }
                    200 -> {
                        Log.d("solarex-dump", "200 invoked")
                    }
                }
                true
            }
            handler.sendEmptyMessage(100)
//            dumpMessages()
            dumpMessagesMethod()
            Log.d("solarex-dump", "------------------")
            // WindowManager.BadTokenException
            // TN handler handleShow WindowManager.addView ViewRootImpl.setView ViewRootImpl.requestLayout ViewRootImpl.checkThread
            Toast.makeText(this, "solarex", Toast.LENGTH_SHORT).show()
            handler.sendEmptyMessage(200)
//            dumpMessages()
            dumpMessagesMethod()
            Looper.loop();
        }.start()
    }

    val nextField = Message::class.java.declaredFields.first { it.name == "next" }.also { it.isAccessible = true }
    fun dumpMessages() {
        val mQueueField = Looper::class.java.declaredFields.first { it.name == "mQueue" }.also { it.isAccessible = true }
        val mMessagesField = MessageQueue::class.java.declaredFields.first { it.name == "mMessages" }.also { it.isAccessible = true }
        val messageQueue = mQueueField.get(Looper.myLooper())
        val message = mMessagesField.get(messageQueue)
        if (message != null) {
            doDumpMessages(message as Message)
        }
    }
    private fun doDumpMessages(message: Message) {
        if (message != null) {
            Log.d("solarex-dump", message.toString())
            val next = nextField.get(message)
            if (next != null) {
                doDumpMessages(next as Message)
            }
        }
    }

    fun dumpMessagesMethod() {
        val mQueueField = Looper::class.java.declaredFields.first { it.name == "mQueue" }.also { it.isAccessible = true }
        val messageQueue = mQueueField.get(Looper.myLooper())
        val dumpMethod = MessageQueue::class.java.declaredMethods.first { it.name == "dump" }.also { it.isAccessible = true }
        dumpMethod.invoke(messageQueue, LogPrinter(Log.DEBUG, "solarex-dump"), "Test", null)
    }
}