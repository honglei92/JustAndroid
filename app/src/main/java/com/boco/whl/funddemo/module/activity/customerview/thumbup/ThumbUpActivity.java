package com.boco.whl.funddemo.module.activity.customerview.thumbup;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.module.activity.customerview.thumbup.view.ThumbUpView;
import com.boco.whl.funddemo.module.activity.customerview.thumbup.view.ThumbView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-点赞效果
 * createTime: 2017/11/13 0013
 * updateTime: 2017/11/13 0013
 */

public class ThumbUpActivity extends BaseActivity {
    @BindView(R.id.thumbUpView)
    ThumbUpView thumbUpView;
    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.btn_setting)
    Button btnSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thumb;
    }

    private void initView() {
        thumbUpView.setThumbUpClickListener(new ThumbView.ThumbUpClickListener() {
            @Override
            public void thumbUpFinish() {

            }

            @Override
            public void thumbDownFinish() {

            }
        });
    }

    @OnClick({R.id.btn_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_setting:
                int num = Integer.valueOf(edNum.getText().toString().trim());
                thumbUpView.setCount(num);
                break;
        }
    }
}
