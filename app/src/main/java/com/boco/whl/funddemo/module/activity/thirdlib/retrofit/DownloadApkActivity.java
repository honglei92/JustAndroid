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
import com.boco.whl.funddemo.config.Constant;
import com.boco.whl.funddemo.utils.download.DownloadApi;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
    private static String TAOBAO_URL = "https://tcc.taobao.com/cc/json/";

    @BindView(R.id.mProgressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setMax(100);
//        downloadapk();
        interceptorTest();

    }

    private void downloadapk() {
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

    /**
     * 拦截器测试
     */
    private void interceptorTest() {
        //初始化拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println("Retrofit Log" + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //配置OKHttp
        File direction = new File(Constant.ROOT);
        if (!direction.exists()) {
            direction.mkdir();
        }
        Cache cache = new Cache(direction, 1024 * 1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new CacheInterceptor(context))
                .build();
        //配置retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TAOBAO_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .build();
        //声明接口
        RetrofitTest.TaobaoService taobaoService = retrofit.create(RetrofitTest.TaobaoService.class);
        taobaoService.getPhoneInfo("13982268713")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(String result) {
//                        System.out.println("onNext:\n" + result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
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
