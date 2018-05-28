package com.boco.whl.funddemo.module.activity.annndroid.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.sdk.eventbus.MessageEvent;
import com.boco.whl.funddemo.sdk.eventbus.ShowImageEvent;
import com.boco.whl.funddemo.module.activity.annndroid.rxjava.RxImageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusTestActivity extends Activity {
    @BindView(R.id.mainimage)
    ImageView mainimage;
    @BindView(R.id.button4)
    Button button4;
    private TextView titleText;
    private byte[] picByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test);
        ButterKnife.bind(this);
        titleText = (TextView) findViewById(R.id.titletext);
        EventBus.getDefault().register(this);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        titleText.setText("message from second activity: " + event.message);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onShowMessageEvent(MessageEvent event) {
        String uri = "https://b-ssl.duitang.com/uploads/item/201411/21/20141121195800_P8xLs.thumb.700_0.jpeg";
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
                EventBus.getDefault().post(new ShowImageEvent(picByte));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showImageEvent(ShowImageEvent event) {
        picByte = event.getBytes();
        Bitmap bitmap = BitmapFactory.decodeByteArray(picByte, 0, picByte.length);
        mainimage.setImageBitmap(bitmap);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.button4)
    public void onViewClicked() {
        Intent intent = new Intent(EventBusTestActivity.this, RxImageActivity.class);
        startActivity(intent);

    }
}
