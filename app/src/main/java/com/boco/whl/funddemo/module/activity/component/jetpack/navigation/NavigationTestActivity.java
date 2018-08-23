package com.boco.whl.funddemo.module.activity.component.jetpack.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;

import androidx.navigation.Navigation;

/**
 * @author:honglei92
 * @time:2018/8/23
 */
public class NavigationTestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onSupportNavigateUp() {
        //是为了禁止按下返回键直接返回Activity，而不是后退fragment。
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jetpack_navigation;
    }
}
