package com.boco.whl.funddemo.module.activity.component.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

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

    @OnClick({R.id.startBtn, R.id.bindBtn, R.id.finishBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                startService(intent);
                break;
            case R.id.bindBtn:
                break;
            case R.id.finishBtn:
                stopService(intent);
                break;
        }
    }

    @OnClick(R.id.finishBtn)
    public void onViewClicked() {
    }
}
