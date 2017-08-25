package com.boco.whl.funddemo.module.activity.annndroid.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.boco.whl.funddemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class RxjavaTestActivity extends Activity {

    @BindView(R.id.showtext)
    TextView showtext;
    @BindView(R.id.textView)
    TextView textView;
    private String result = "楚汉传奇将军:\n\n";
    private String onNextStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_test);
        ButterKnife.bind(this);
        rxjavaTest();
        printStringArray();
    }

    /**
     * fromIterable()
     */
    private void printStringArray() {
        String[] generals = {"韩信", "项羽", "刘邦"};
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
    }

    /**
     * onNext()
     * just()
     */
    private void rxjavaTest() {
        Observable<String> myObservable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("初级工程师");
                e.onNext("中级工程师");
                e.onNext("高级工程师");
                e.onComplete();
            }

        });
        Observable<String> myObservable = Observable.just("one", "two", "three");
        Observer<String> myObserver = new Observer<String>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                showtext.setText("监听onCompleted操作:  " + onNextStr);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(String s) {
                onNextStr += s + "    ";
            }
        };
        myObservable.subscribe(myObserver);
        myObservable1.subscribe(myObserver);
    }
}
