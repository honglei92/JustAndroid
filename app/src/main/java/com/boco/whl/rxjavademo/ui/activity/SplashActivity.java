package com.boco.whl.rxjavademo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boco.whl.rxjavademo.R;
import com.boco.whl.rxjavademo.utils.IntentUT;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends Activity {

    @BindView(R.id.splash_iv)
    ImageView splashIv;
    @BindView(R.id.skip_tv)
    TextView skipTv;
    boolean isSkip = false;
    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!isSkip) {
                IntentUT.getInstance().openActivity(SplashActivity.this, IndexActivity.class, true);
            }
            // overridePendingTransition must be called AFTER finish() or startActivity, or it won't work.
//            overridePendingTransition(R.anim.activity_in, R.anim.splash_out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
        mMainHandler.sendEmptyMessageDelayed(0, 2000);

    }

    private void initView() {
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492587773250&di=44a84307140e4ae5eb1cd9989b60011c&imgtype=0&src=http%3A%2F%2Fs2.sinaimg.cn%2Fmw690%2F005CHbPbgy71VAJKlYRb1%26690").into(splashIv);
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
