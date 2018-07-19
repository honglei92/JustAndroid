package com.boco.whl.funddemo.module.activity.thirdlib.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.boco.whl.funddemo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideTestActivity extends Activity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.imageView2)
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        String url = "http://p1.pstatp.com/large/166200019850062839d3";
        Glide.with(this).load(R.drawable.group9).placeholder(R.drawable.replace).into(imageView2);
        Glide.with(this).load(url).placeholder(R.drawable.replace).error(R.drawable.loaderror).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }
}

