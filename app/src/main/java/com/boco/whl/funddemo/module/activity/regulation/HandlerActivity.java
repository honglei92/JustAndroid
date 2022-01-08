package com.boco.whl.funddemo.module.activity.regulation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.config.Constant;

import java.lang.ref.WeakReference;

/**
 * @author:honglei92
 * @time:2018/8/3
 */
public class HandlerActivity extends BaseActivity {
    MyHandler handler = new MyHandler(this);
    WhlHandler whlHandler = new WhlHandler();
    private final MyRunnable myRunnable = new MyRunnable(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //倒计时
        /*CountDownTimer timer = new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                handler.sendEmptyMessage(123);
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();*/
        ImageView iv = new ImageView(this);
        for (int i = 0; i < 100; i++) {
            iv.setImageResource(R.drawable.bigsize);
        }
        Byte[] bytes = new Byte[16 * 1024 * 1024];
//        handler.sendEmptyMessageDelayed(123, 1000L * 20);
        whlHandler.postDelayed(myRunnable, 1000L * 20);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_easy_commom;
    }

    static class MyHandler extends Handler {
        private final WeakReference<HandlerActivity> mActivity;

        public MyHandler(HandlerActivity handlerActivity) {
            mActivity = new WeakReference<HandlerActivity>(handlerActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 123) {
                HandlerActivity activity = mActivity.get();
                Log.d(Constant.TAG, "扯一张洁柔" + "activity==null" + (activity == null));
            }
        }
    }

    static class WhlHandler extends Handler {

    }

    static class MyRunnable implements Runnable {
        public final WeakReference<HandlerActivity> mActivity;

        public MyRunnable(HandlerActivity handlerActivity) {
            mActivity = new WeakReference<>(handlerActivity);
        }

        @Override
        public void run() {
            HandlerActivity activity = mActivity.get();
            Log.d(Constant.TAG, "买一块FILA手表" + "activity==null:" + (activity == null));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        whlHandler.removeCallbacksAndMessages(null);
    }
}
