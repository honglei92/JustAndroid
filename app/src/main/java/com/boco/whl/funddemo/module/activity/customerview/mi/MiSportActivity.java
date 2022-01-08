package com.boco.whl.funddemo.module.activity.customerview.mi;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import android.widget.Button;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.module.activity.customerview.mi.view.MiSportConnectView;
import com.boco.whl.funddemo.module.activity.customerview.mi.view.SportData;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-
 * createTime: 2017/11/6 0006
 * updateTime: 2017/11/6 0006
 */

public class MiSportActivity extends BaseActivity {
    @BindView(R.id.btn_disconnected)
    Button btnDisconnected;
    private Handler handler;
    boolean isConnected = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_misport;
    }

    private void initView() {
        SportData sportData = new SportData();
        sportData.step = 2714;
        sportData.distance = 1700;
        sportData.calories = 34;
        sportData.progress = 75;
        final MiSportConnectView misportview = (MiSportConnectView) findViewById(R.id.misportview);
        misportview.setSportData(sportData);
        handler = new Handler();
        btnDisconnected.setOnClickListener(v -> {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isConnected = !isConnected;
                    misportview.setConnected(isConnected);
                    btnDisconnected.setText(isConnected ? "已连接" : "未连接");
                }
            }, 500);
        });

    }
}
