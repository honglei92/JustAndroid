package com.boco.whl.rxjavademo.ui.activity.secondtab.threadcommunication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.boco.whl.rxjavademo.R;
import com.boco.whl.rxjavademo.ui.activity.secondtab.threadcommunication.thread.MyConsumer;
import com.boco.whl.rxjavademo.ui.activity.secondtab.threadcommunication.thread.MyProducer;
import com.boco.whl.rxjavademo.utils.SharedPreferencesUtil;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/19 0019 17:20
 *  updatetime : 2017/6/19 0019 17:20
 * </pre>
 */
public class ThreadCommunicationOne extends Activity {
    private Context context = ThreadCommunicationOne.this;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadcommunicationone);
        ButterKnife.bind(this);
        wayOne();
        wayTwo();
        wayThree();
        wayFour();
        wayFive();
        waySex();
    }

    /**
     * way1  SharedPreference
     */
    private void wayOne() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtils.d("Thread", Thread.currentThread().getName());
                SharedPreferencesUtil.setLoginUserPhone(context, "13982268713");
            }
        }).start();
    }

    private void wayTwo() {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();
        try {
            pis.connect(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new MyProducer(pos).start();
        new MyConsumer(pis).start();

    }

    private void wayThree() {

    }

    private void wayFour() {

    }

    private void wayFive() {

    }

    private void waySex() {

    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                LogUtils.d("Thread", Thread.currentThread().getName());
                ToastUtils.showShort(SharedPreferencesUtil.getPreLoginUserPhone(context));
                break;
            case R.id.btn2:

                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }
}
