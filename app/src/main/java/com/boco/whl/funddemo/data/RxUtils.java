package com.boco.whl.funddemo.data;

import android.view.View;

import com.boco.whl.funddemo.config.Constants;
import com.boco.whl.funddemo.data.api.ApiException;
import com.boco.whl.funddemo.entity.ApiBean;
import com.boco.whl.funddemo.utils.LogUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RxUtils {

    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable<T> getObservable(T t) {
        return Observable.just(t).throttleFirst(Constants.DEFAULT_CLICK_INTERVAL, TimeUnit.MILLISECONDS);
    }

    public static Observable<Object> getObservable(View view) {
        return RxView.clicks(view).throttleFirst(Constants.DEFAULT_CLICK_INTERVAL, TimeUnit.MILLISECONDS);
    }

    public static <T> ObservableTransformer<ApiBean<T>, T> handleResult() {
        return new ObservableTransformer<ApiBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ApiBean<T>> baseModelObservable) {
                return baseModelObservable.flatMap(new Function<ApiBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(ApiBean<T> tBaseModel) throws Exception {
                        LogUtil.e("RxHelper---status：" + tBaseModel.getStatus() + "，msg：" + tBaseModel.getMsg());
                        if (tBaseModel.getStatus() == Constants.STATUS_SUCCESS) {
                            return createData(tBaseModel.getData());
                        } else {
                            return Observable.error(new ApiException(tBaseModel));
                        }
                    }
                });
            }
        };
    }

    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(data);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    public static boolean isNotDispose(Disposable observer) {
        return observer != null && !observer.isDisposed();
    }

    public static void disposable(Disposable observer) {
        if (observer != null && !observer.isDisposed()) {
            observer.dispose();
        }
    }
}
