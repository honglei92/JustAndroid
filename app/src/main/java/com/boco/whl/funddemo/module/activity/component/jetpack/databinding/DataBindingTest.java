package com.boco.whl.funddemo.module.activity.component.jetpack.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.config.Constant;
import com.boco.whl.funddemo.databinding.ActivityEasyCommomDatabindingBinding;
import com.boco.whl.funddemo.entity.Plants;

/**
 * data binding 12345实践
 *
 * @author:honglei92
 * @time:2018/8/21
 */
public class DataBindingTest extends BaseActivity {
    private Handler handler = new Handler();
    String url = "https://wx4.sinaimg.cn/mw1024/6b6562f4gy1fo4mo6z7h2j20qo0zkwi1.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEasyCommomDatabindingBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_easy_commom_databinding);

        Plants plants = new Plants();
        plants.setName("绿萝");
        plants.setImageUrl(url
//                "https://wx4.sinaimg.cn/mw1024/6b6562f4gy1fo4mo6z7h2j20qo0zkwi1.jpg"
//                "http://lvluo.yangzhiriji.com/wp-content/uploads/sites/6/2014/05/1.jpg"
        );
        binding.setTest(plants);
        String[] array = {"纸巾", "手机", "中性笔"};
        binding.setArrayIndex(1);
        binding.setArray(array);
        binding.setHasFlower(true);
        binding.setMyHandler(new OnTestClick() {

            @Override
            public void onClick(View view) {
                Log.e(Constant.TAG, "我被单击了");

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                plants.setName("一盆绿油油的绿萝");
                url = "http://lvluo.yangzhiriji.com/wp-content/uploads/sites/6/2014/05/1.jpg";
                plants.setImageUrl(url);
                binding.setTest(plants);
            }
        }, 3000);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    public interface OnTestClick {
        void onClick(View view);
    }
}
