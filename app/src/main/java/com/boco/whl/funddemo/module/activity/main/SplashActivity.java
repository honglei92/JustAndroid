package com.boco.whl.funddemo.module.activity.main;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.config.Constant;
import com.boco.whl.funddemo.module.activity.my.AdvertizeActivity;
import com.boco.whl.funddemo.module.activity.thirdlib.retrofit.RetrofitTest;
import com.boco.whl.funddemo.utils.IntentUT;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 引导页
 */
public class SplashActivity extends Activity {

    boolean isSkip = false;
    int count = 3;
    LottieAnimationView mLottieAnimationView;
    TextView skipTv;

    private static class WhlHandler extends Handler {
        WeakReference<Activity> mActivityReference;

        WhlHandler(Activity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final SplashActivity splashActivity = (SplashActivity) mActivityReference.get();
            if (!splashActivity.isSkip) {
                final Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
//                        Glide.with(splashActivity).load(R.drawable.advertising).into(splashActivity.splashIv);
                        splashActivity.skipTv.setText("跳过  " + splashActivity.count--);
                        splashActivity.skipTv.setVisibility(View.VISIBLE);
                        if (splashActivity.count > 0) {
                            handler.postDelayed(this, 1000);
                        } else {
                            IntentUT.getInstance().openActivity(splashActivity, IndexActivity.class, true);

                        }
                    }
                };
                handler.postDelayed(runnable, 1000);
            }
        }
    }

    private Handler mMainHandler = new WhlHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
        mMainHandler.sendEmptyMessageDelayed(0, 0);

    }

    private void initView() {
        Log.d("fozu", Constant.master);
        initAnimation();
        //背景图
//        Glide.with(this).load(splash).into(splashIv);
        skipTv = findViewById(R.id.skip_tv);
        mLottieAnimationView = findViewById(R.id.mLottieAnimationView);
    }

    /**
     * 首页动画
     */
    private void initAnimation() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                String mJson = null;
                try {
                    mJson = RetrofitTest.getJson("https://raw.githubusercontent.com/18380438200/LottieAnim/master/app/src/main/assets/", "likeanim.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                emitter.onNext(mJson);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(SplashActivity.this, "LOAD ERROR!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String result) {
                        if (mLottieAnimationView != null && result != null) {
                            mLottieAnimationView.setAnimationFromJson(result);
                        }
                    }
                });

    }

    @OnClick({R.id.splash_iv, R.id.skip_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.splash_iv:
                isSkip = true;
                IntentUT.getInstance().openActivity(SplashActivity.this, AdvertizeActivity.class, true);
                break;
            case R.id.skip_tv:
                IntentUT.getInstance().openActivity(SplashActivity.this, IndexActivity.class, true);
                break;
            default:
                break;
        }
    }
}
