package com.boco.whl.funddemo.module.activity.thirdlib.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.utils.download.DownloadApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-联合RxJava使用 下载apk
 * createTime: 2018/1/2 0002
 * updateTime: 2018/1/2 0002
 *
 * @author Administrator
 */

public class DownloadApkActivity extends BaseActivity {
    private MyHandler handler = new MyHandler();

    @BindView(R.id.mProgressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setMax(100);
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                new DownloadApi().download((Activity) context, "195D0D?qbsrc=51&asr=4286", handler);
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_easy_commom;
    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 123) {
                mProgressBar.setProgress(msg.what);
            }
        }
    }
}
