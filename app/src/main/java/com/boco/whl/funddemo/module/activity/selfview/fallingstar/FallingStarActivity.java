package com.boco.whl.funddemo.module.activity.selfview.fallingstar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.module.activity.selfview.fallingstar.view.FallingStarView;
import com.boco.whl.funddemo.utils.IntentUT;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wanghonglei@boco.com.cn
 * @desc 描述-流星雨示例
 * @createTime 2018/1/26 0026
 * @updateTime 2018/1/26 0026
 */

public class FallingStarActivity extends BaseActivity {
    @BindView(R.id.fallingView)
    FallingStarView fallingView;
    @BindView(R.id.btnStart)
    Button btnStart;
    private Handler handler = new Handler();

    public static void doIntent(Activity activity, boolean isFinish) {
        IntentUT.getInstance().openActivity(activity, FallingStarActivity.class, isFinish);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallingstar);
        ButterKnife.bind(this);
        initAnimation();
    }

    private void initAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(fallingView, "positionX", 200, 100);
        objectAnimator.setDuration(1500);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(fallingView, "positionY", 30, 300);
        objectAnimator1.setDuration(1500);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(fallingView, "radius", 3, 6, 9, 12, 1);
        objectAnimator2.setDuration(1500);
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
                                set.start();
                            }
                        });
                    }
                }, 500);
            }
        });
        set.playTogether(objectAnimator, objectAnimator1, objectAnimator2);
        set.start();
    }

    @OnClick(R.id.btnStart)
    public void onViewClicked() {

    }
}
