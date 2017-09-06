package com.boco.whl.funddemo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * author: wanghonglei@boco.com.cn
 * desc:  基类activity
 * createTime: 2017/8/26 0026
 * updateTime: 2017/8/26 0026
 */

public class BaseActivity extends AppCompatActivity {
    private Context context = this;
    private Activity activity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}