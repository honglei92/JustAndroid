package com.boco.whl.funddemo.module.activity.component.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author:honglei92
 * @time:2018/7/18
 */
public class ServiceActivity extends BaseActivity {
    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.bindBtn)
    Button bindBtn;
    @BindView(R.id.finishBtn)
    Button finishBtn;
    Intent intent;
    @BindView(R.id.unbindBtn)
    Button unbindBtn;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private ServiceConnection mServiceConnetion;
    private boolean isServiceDisconnected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        intent = new Intent(this, TestService.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service;
    }

    @OnClick({R.id.startBtn, R.id.bindBtn, R.id.finishBtn, R.id.unbindBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                startService(intent);
                break;
            case R.id.bindBtn:
                if (!isServiceDisconnected) {
                    mServiceConnetion = new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            Log.d(TestService.TAG, "TestService onServiceConnected");
                            isServiceDisconnected = true;
                            TestService.MyBinder myBinder = (TestService.MyBinder) service;
                            TestService testService = myBinder.getService();
                            testService.showInfo();
                            testService.setOnProgressListener(new TestService.OnProgressListener() {
                                @Override
                                public void setProgress(int progress) {
                                    progressBar.setProgress(progress);
                                }
                            });
                            testService.startDownLoad();
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                            Log.d(TestService.TAG, "TestService onServiceDisconnected");
                            isServiceDisconnected = false;
                        }
                    };
                    bindService(intent, mServiceConnetion, Service.BIND_AUTO_CREATE);
                }
                break;
            case R.id.finishBtn:
                stopService(intent);
                break;
            case R.id.unbindBtn:
                if (mServiceConnetion != null && isServiceDisconnected) {
                    unbindService(mServiceConnetion);
                }
                break;
        }
    }

}
