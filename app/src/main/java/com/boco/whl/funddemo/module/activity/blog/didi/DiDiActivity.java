package com.boco.whl.funddemo.module.activity.blog.didi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.utils.IntentUT;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义view 定位动画
 *
 * @author Administrator
 */
public class DiDiActivity extends AppCompatActivity {
    @BindView(R.id.didiView)
    DiDiView didiView;
    private Handler handler = new Handler();
    private int num = 1;
    private ObjectAnimator animator;

    public static void doIntent(Activity activity, boolean isFinish) {
        IntentUT.getInstance().openActivity(activity, DiDiActivity.class, isFinish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_di_di);
        ButterKnife.bind(this);
        initAnimation();
    }

    private void initAnimation() {
        //属性动画配置
        if (animator == null) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(didiView, "radius", 5, 19);
            animator.setDuration(600);
            animator.setStartDelay(200);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    didiView.setInnerColor(0xff3BBCA3);
                    num++;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    didiView.setInnerColor(0xffffffff);
                    didiView.setRadius(5);
                    if (num <= 10) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animator.start();
                                    }
                                });
                            }
                        }, 0);
                    }
                }
            });
            animator.start();
        } else {
            animator.start();
        }
    }
}
