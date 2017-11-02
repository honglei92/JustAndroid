package com.boco.whl.funddemo.module.activity.blog.sunxibei;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-
 * createTime: 2017/10/24 0024
 * updateTime: 2017/10/24 0024
 */

public class RotateRectActivity extends BaseActivity {
    @BindView(R.id.my_animation_view)
    MapView myAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_rect);
        ButterKnife.bind(this);
        initAnimation();
    }

    private void initAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myAnimationView, "degreeY", 0, -45);
        objectAnimator.setDuration(1000);
        objectAnimator.setStartDelay(500);
        myAnimationView.reset();

        objectAnimator.start();
    }


}
