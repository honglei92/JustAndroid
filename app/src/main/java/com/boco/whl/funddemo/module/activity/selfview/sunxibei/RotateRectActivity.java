package com.boco.whl.funddemo.module.activity.selfview.sunxibei;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.module.activity.selfview.sunxibei.view.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-图片翻转示例
 * createTime: 2017/10/24 0024
 * updateTime: 2017/10/24 0024
 */

public class RotateRectActivity extends BaseActivity {
    @BindView(R.id.my_animation_view)
    MapView myAnimationView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_rect);
        ButterKnife.bind(this);
        initAnimation();
    }

    private void initAnimation() {
        //属性动画配置
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myAnimationView, "degreeY", 0, -45);
        objectAnimator.setDuration(1000);
        objectAnimator.setStartDelay(500);

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(myAnimationView, "degreeZ", 0, 270);
        animator1.setDuration(800);
        animator1.setStartDelay(500);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(myAnimationView, "fixDegreeY", 0, 30);
        animator2.setDuration(500);
        animator2.setStartDelay(500);

        final AnimatorSet set = new AnimatorSet();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myAnimationView.reset();
                                set.start();
                            }
                        });
                    }
                }, 500);
            }
        });
        set.playSequentially(objectAnimator, animator1, animator2);
        set.start();
    }


}
