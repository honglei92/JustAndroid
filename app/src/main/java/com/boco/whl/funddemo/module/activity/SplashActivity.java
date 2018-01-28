package com.boco.whl.funddemo.module.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.main.IndexActivity;
import com.boco.whl.funddemo.utils.IntentUT;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.boco.whl.funddemo.R.drawable.splash;


/**
 * 引导页
 */
public class SplashActivity extends Activity {

    @BindView(R.id.splash_iv)
    ImageView splashIv;
    @BindView(R.id.skip_tv)
    TextView skipTv;
    boolean isSkip = false;
    int count = 3;
    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!isSkip) {
                final Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(SplashActivity.this).load(R.drawable.advertising).into(splashIv);
                        skipTv.setText("跳过  " + count--);
                        skipTv.setVisibility(View.VISIBLE);
                        if (count > 0) {
                            handler.postDelayed(this, 1000);
                        } else {
                            IntentUT.getInstance().openActivity(SplashActivity.this, IndexActivity.class, true);

                        }
                    }
                };
                handler.postDelayed(runnable, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
        mMainHandler.sendEmptyMessageDelayed(0, 1000);

    }

    private void initView() {
        Glide.with(this).load(splash).into(splashIv);
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
        }
    }
}
