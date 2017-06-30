package com.boco.whl.rxjavademo.ui.activity.firsttab.rxjava;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boco.whl.rxjavademo.R;
import com.boco.whl.rxjavademo.sdk.eventbus.MessageEvent;
import com.boco.whl.rxjavademo.utils.NetworkUT;
import com.boco.whl.rxjavademo.utils.PrintfUT;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

public class RxImageActivity extends Activity {

    @BindView(R.id.show_image)
    ImageView showImage;
    @BindView(R.id.show_image1)
    ImageView showImage1;
    @BindView(R.id.show_image2)
    ImageView showImage2;
    @BindView(R.id.ll_no_network)
    LinearLayout llNoNetwork;
    @BindView(R.id.refresh_iv)
    ImageView refreshIv;
    @BindView(R.id.refresh_tv)
    TextView refreshTv;
    @BindView(R.id.setnet_btn)
    Button setnetBtn;
    private byte[] picByte;
    ProgressDialog dialog;
    private Context context = RxImageActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);
        showImage();
        EventBus.getDefault().post(new MessageEvent("afs win"));
    }

    private void showImage() {
        boolean flag = NetworkUT.getInstance().isConnected(RxImageActivity.this) &&
                NetworkUT.getInstance().isAvailable(RxImageActivity.this);
        dialog = new ProgressDialog(RxImageActivity.this);
        dialog.setMessage("加载中");
        dialog.show();
        if (flag) {
            llNoNetwork.setVisibility(View.GONE);
            Observable.create(new ObservableOnSubscribe<byte[]>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter<byte[]> emitter) throws Exception {
                    String uri = "https://timgsa.baidu.com/advertising?image&quality=80&size=b9999_10000&sec=1490616135886&di=a6e90cd40cd274e9adde216c40a97321&imgtype=0&src=http%3A%2F%2Fimg1.gamedog.cn%2F2017%2F02%2F08%2F1418043-1F20P923180-50.jpg";
                    try {
                        URL url = new URL(uri);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setReadTimeout(10000);
                        if (conn.getResponseCode() == 200) {
                            InputStream fis = conn.getInputStream();
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            byte[] bytes = new byte[1024];
                            int length = -1;
                            while ((length = fis.read(bytes)) != -1) {
                                bos.write(bytes, 0, length);
                            }
                            picByte = bos.toByteArray();
                            bos.close();
                            fis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    emitter.onNext(picByte);
                    emitter.onComplete();
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<byte[]>() {

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(RxImageActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(byte[] picByte) {
                            if (picByte != null) {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(picByte, 0, picByte.length);
                                showImage.setImageBitmap(bitmap);
                                showImage1.setImageBitmap(bitmap);
                                showImage2.setImageBitmap(bitmap);
                            }
                        }
                    });
        } else {
            llNoNetwork.setVisibility(View.VISIBLE);
            PrintfUT.getInstance().showShortToast(context, "网络无连接");
            dialog.dismiss();
        }
    }

    @OnClick({R.id.refresh_iv, R.id.refresh_tv, R.id.setnet_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.refresh_iv:
                showImage();
                break;
            case R.id.refresh_tv:
                showImage();
                break;
            case R.id.setnet_btn:
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                break;
        }
    }
}
