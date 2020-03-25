package org.solarex.customviewdemos.androidtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import org.solarex.customviewdemos.R;
import org.solarex.customviewdemos.ui.BaseCustomViewActivity;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 20:21/2020/3/25
 *    Desc:
 * </pre>
 */

public class LooperTest extends BaseCustomViewActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_avater;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Handler[] handler = new Handler[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler[0] = new Handler();
                countDownLatch.countDown();
                Looper.loop();
            }
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handler[0].post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("Solarex","1500");
            }
        });
        handler[0].postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("Solarex","500");
            }
        },500 );
        handler[0].postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("Solarex","1000");
            }
        },1000);
    }
}
