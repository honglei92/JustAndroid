package com.boco.whl.funddemo.module.activity.thirdlib.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.thirdlib.rxjava.iciba.GetRequestInterface;
import com.boco.whl.funddemo.module.activity.thirdlib.rxjava.iciba.Translation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * RxJava2 和RxJava1的区别在于取消传null值，取消observer被压，增加flowable被压，2增加抛出异常功能
 *
 * @author honglei92
 */
public class RxjavaTestActivity extends Activity {

    private static final String TAG = "RxJavaTestActivity";
    @BindView(R.id.showtext)
    TextView showtext;
    @BindView(R.id.textView)
    TextView textView;
    private String result = "大人物:";
    private String onNextStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_test);
        ButterKnife.bind(this);
        //操作符
        operator();
        // RxJava应用实践 无条件网络请求轮询
        looper();

    }

    /**
     *
     */
    private void looper() {
        Observable.interval(2, 1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "第" + aLong + "次轮询");
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://fy.iciba.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();
                        GetRequestInterface request = retrofit.create(GetRequestInterface.class);
                        Observable<Translation> observable = request.getCall();
                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<Translation>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Translation translation) {
                                        translation.show();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d(TAG, "error");

                                    }

                                    @Override
                                    public void onComplete() {
//                                        Log.d(TAG, "complete");
                                    }
                                });

                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "response for error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "response for complete");
            }
        });
    }

    /**
     * 操作符
     */
    private void operator() {
        //创建操作符
//        create();
//        just();
//        fromArray();
//        fromIterator();
//        never();
//        empty();
//        error();
//        defer();
//        timer();
//        interval();
//        intervalRange();
//        range();
//        rangeLong();

        //变换操作符
//        map();
//        flatMap();
//        contactMap();
//        buffer();

        //组合/合并操作符
//        concat();
//        concatArray();
//        merge();
//        mergeArray();
//        concatDelayError();
//        concatMergeError();
//        zip();
//        combineLatest();
//        combineLatestDelayError();
//        reduce();
//        collect();
//        startWith();
//        startWithArray();
//        count();

        //功能性操作符
//        subscribe();
//        subscribeOn();
//        observeOn();
//        delay();
//        do ();
//        onErrorReturn();
//        onErrorResumeNext();
//        onExceptionResumeNext();
//        retry();
//        retryUntil();
//        retryWhen();
//        repeat();
//        repeatWhen();
//
        //过滤操作符
//        debounce();
//        distinct();
//        elementAt();
//        filter();
//        first();
//        last();
//        ignoreElements();
//        sampleThrottleFirst();
//        skipSkipLast();
//        takeTakeLast();

        //条件/布尔操作符
//        all();
//        contains();
//        isEmpty();
//        amb();
//        takeWhile();
//        takeUntil();
//        skipWhile();
//        skipUntil();
//        defaultIfEmpty();
//        sequenceEqual();

    }

    private void fromArray() {
        String[] names = {"韩信", "项羽", "刘邦"};
        Observable.fromArray(names).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                result += (s + ";\n");
            }
        });
        Log.d(TAG, result);
    }

    private void just() {
        Observable.just("one", "two", "three").subscribe(new Observer<String>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "监听onCompleted操作:  " + onNextStr);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(String s) {
                onNextStr += s + "    ";
            }
        });
    }

    /**
     * 创建
     */
    private void create() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();

            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, integer + "事件被接收了");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "处理完成");
            }
        });
    }

    /**
     * fromIterable()
     */
    private void fromIterator() {

        List<String> list = new ArrayList<String>();
        list.add("韩信");
        list.add("项羽");
        list.add("刘邦");
        Observable.fromIterable(list).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                result += (s + ";\n\n");
            }
        });
        textView.setText(result);
        Log.d(TAG, result);
    }
}
