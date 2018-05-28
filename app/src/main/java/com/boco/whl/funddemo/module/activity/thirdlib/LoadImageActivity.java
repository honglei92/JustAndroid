package com.boco.whl.funddemo.module.activity.annndroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.utils.imaaage.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:wanghonglei@boco.com.cn
 * desc:
 * createTime:2017/8/26 0026
 * updateTime:2017/8/26 0026
 */

public class LoadImageActivity extends BaseActivity {
    @BindView(R.id.iv_load_image)
    ImageView ivLoadImage;
    private ImageLoader imageLoader = new ImageLoader();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String url = "http://wx1.sinaimg.cn/mw690/0060D6Ocly1fj9o19p1qej30zk0k0dh3.jpg";
        imageLoader.displayImage(url, ivLoadImage);
    }
}
