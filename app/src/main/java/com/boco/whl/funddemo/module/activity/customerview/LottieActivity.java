package com.boco.whl.funddemo.module.activity.customerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.module.activity.thirdlib.retrofit.RetrofitTest;

import java.io.IOException;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author:honglei92
 * @time:2018/8/2
 */
public class LottieActivity extends BaseActivity {
    @BindView(R.id.mLottieAnimationView)
    LottieAnimationView mLottieAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLottie();

    }

    private void initLottie() {
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
                        if (context != null) {
                            Toast.makeText(context, "LOAD ERROR!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String result) {
                        if (result != null && mLottieAnimationView != null) {
                            mLottieAnimationView.setAnimationFromJson(result);
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lottie;
    }
}
