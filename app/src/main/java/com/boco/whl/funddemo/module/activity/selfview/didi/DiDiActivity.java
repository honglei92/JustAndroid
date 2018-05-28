package com.boco.whl.funddemo.module.activity.selfview.didi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.base.BaseApplication;
import com.boco.whl.funddemo.entity.TestManager;
import com.boco.whl.funddemo.module.activity.selfview.didi.view.DestinationView;
import com.boco.whl.funddemo.module.activity.selfview.didi.view.DiDiView;
import com.boco.whl.funddemo.module.activity.selfview.didi.view.InfoWindowView;
import com.boco.whl.funddemo.module.activity.selfview.didi.view.MayiView;
import com.boco.whl.funddemo.module.activity.selfview.didi.view.MusicView;
import com.boco.whl.funddemo.module.activity.selfview.didi.view.ProgressView;
import com.boco.whl.funddemo.module.activity.selfview.didi.view.ValueView;
import com.boco.whl.funddemo.utils.IntentUT;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义view 定位动画
 *
 * @author Administrator
 */
public class DiDiActivity extends BaseActivity implements TestManager.OnDataArrivedListener {
    @BindView(R.id.didiView)
    DiDiView didiView;
    @BindView(R.id.destinationView)
    DestinationView destinationView;
    @BindView(R.id.mayiView)
    MayiView mayiView;
    @BindView(R.id.progressView)
    ProgressView progressView;
    @BindView(R.id.infoWindowView)
    InfoWindowView infoWindowView;
    @BindView(R.id.valueView)
    ValueView valueView;
    private static final String TAG = "DidiActivity";
    @BindView(R.id.musicView)
    MusicView musicView;
    private Handler handler = new Handler();
    private int num = 1;
    private ObjectAnimator animator;
    private ObjectAnimator animatorDistination;
    private ObjectAnimator animatorMayi;
    private ObjectAnimator animatorProgress;
    private ObjectAnimator animatorMusic;
    private ValueAnimator animatorValue;
    private static final int REPEAT_TIME = 200;
    private static Context mContext;
    private static View mView;
    float startY;
    float stopY;
    float startY2;
    float stopY2;
    float startY3;
    float stopY3;
    float startY1;
    float stopY1;
    AnimatorSet musicSet;

    public static void doIntent(Activity activity, boolean isFinish) {
        IntentUT.getInstance().openActivity(activity, DiDiActivity.class, isFinish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_di_di);
        ButterKnife.bind(this);
        //leakcanary
        mContext = this;
        mView = new View(this);
        TestManager.getInstance().registerListener(this);
        //anr: keyboard screen 5  broadcast 10  service20
//        SystemClock.sleep(30 * 1000);
        initAnimation();
        initDestAnimation();
        initMayiAnimation();
        initProgressAnimation();
        initInfoAnimation();
        initValueAnimation();
        musicAnimation();
    }

    /**
     * 音乐动画
     */
    private void musicAnimation() {
        startY = (float) (Math.random() * 60);
        stopY = (float) (Math.random() * 60);
        startY1 = (float) (Math.random() * 60);
        stopY1 = (float) (Math.random() * 60);
        startY2 = (float) (Math.random() * 60);
        stopY2 = (float) (Math.random() * 60);
        startY3 = (float) (Math.random() * 60);
        stopY3 = (float) (Math.random() * 60);
        ObjectAnimator animator = ObjectAnimator.ofFloat(musicView, "startY",
                startY, stopY);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(musicView, "startY1",
                startY1, stopY1);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(musicView, "startY2",
                startY2, stopY2);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(musicView, "startY3",
                startY3, stopY3);
        musicSet = new AnimatorSet();
        musicSet.play(animator).with(animator1).with(animator2).with(animator3);
        musicSet.setDuration(400).start();
        musicSet.setStartDelay(1);
        musicSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                musicAnimation();
            }
        });
    }

    /**
     * value 动画
     */
    private void initValueAnimation() {
        if (animatorValue == null) {
            animatorValue = ValueAnimator.ofInt(1, 100);
            animatorValue.setDuration(2000).setRepeatMode(ValueAnimator.RESTART);
            animatorValue.setRepeatCount(ValueAnimator.INFINITE);
            animatorValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int animationValue = (int) animation.getAnimatedValue();
                    valueView.setNum(animationValue);
                }
            });
        }
        animatorValue.setTarget(valueView);
        animatorValue.start();
        valueView.setOnClickListener(v -> {
            finish();
        });

    }

    /**
     * 气泡提示
     */
    private void initInfoAnimation() {
        //放大进入动画
        ObjectAnimator infoXAnimator = ObjectAnimator.ofFloat(infoWindowView, "scaleX", 0, 1);
        ObjectAnimator infoYAnimator = ObjectAnimator.ofFloat(infoWindowView, "scaleY", 0, 1);
        AnimatorSet set1 = new AnimatorSet();
        set1.play(infoXAnimator).with(infoYAnimator);
        set1.setDuration(1000).start();
        set1.setStartDelay(300);
        set1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                set1.start();
            }
        });
    }

    /**
     * 圆形加载
     */
    private void initProgressAnimation() {
        if (animatorProgress == null) {
            animatorProgress = ObjectAnimator.ofInt(progressView, "progress", -90, 0, 90, 180, 270);
            animatorProgress.setDuration(2000);
            animatorProgress.setRepeatMode(ValueAnimator.RESTART);
            animatorProgress.setRepeatCount(Integer.MAX_VALUE);
            animatorProgress.start();
        }
    }

    /**
     * 蚂蚁 animation
     */
    private void initMayiAnimation() {
        if (animatorMayi == null) {
            animatorMayi = ObjectAnimator.ofFloat(mayiView, "circleY", 160, 150, 140, 150, 159);
            animatorMayi.setDuration(2000);
            animatorMayi.setInterpolator(new LinearInterpolator());
            animatorMayi.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mayiView.setCircleY(160);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    animatorMayi.start();
                                }
                            });
                        }
                    }, 100);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }


            });
        }
        animatorMayi.start();
        //点击事件
        mayiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator gotAnimator = ObjectAnimator.ofFloat(mayiView, "gotTextY", 0, -50);
                gotAnimator.setDuration(1500);
                ObjectAnimator alphaAnimator = ObjectAnimator.ofInt(mayiView, "gotAlpha", 255, 0);
                alphaAnimator.setInterpolator(new LinearInterpolator());
                alphaAnimator.setDuration(1500);
                final AnimatorSet set = new AnimatorSet();
                set.playTogether(gotAnimator, alphaAnimator);
                set.start();
            }
        });
    }

    /**
     * 目的地动画
     */
    private void initDestAnimation() {
        if (animatorDistination == null) {
            animatorDistination = ObjectAnimator.ofFloat(destinationView, "outRadius", 5, 30);
            animatorDistination.setDuration(600);
            animatorDistination.setStartDelay(200);
            animatorDistination.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    num++;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    destinationView.setOutRadius(5);
                    if (num <= REPEAT_TIME) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animatorDistination.start();
                                    }
                                });
                            }
                        }, 0);
                    }
                }
            });
            animatorDistination.start();
        } else {
            animatorDistination.start();
        }
    }

    /**
     * 定位动画
     */
    private void initAnimation() {
        //属性动画配置
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(didiView, "radius", 5, 19);
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
                    if (num <= REPEAT_TIME) {
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

    @Override
    public void onDataArrived(Object data) {

        Log.d(TAG, "收到数据");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BaseApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
