package com.boco.whl.rxjavademo.ui.activity.component;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boco.whl.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : honglei92
 * desc :srcoll to/by demo
 * createtime : 2017/8/16 0016 11:36
 * updatetime : 2017/8/16 0016 11:36
 */
public class ScrollActivity extends Activity {

    @BindView(R.id.tv_hi)
    TextView tvHi;
    @BindView(R.id.tv_scroll_to)
    TextView tvScrollTo;
    @BindView(R.id.tv_scroll_by)
    TextView tvScrollBy;
    @BindView(R.id.rl_scroll)
    RelativeLayout rlScroll;
    @BindView(R.id.ll_shelf)
    LinearLayout llShelf;
    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);
        List<String> dataList = new ArrayList<>();
        dataList.add("乡砚山村 2011-08-19 11:12 LOS红灯");
        dataList.add("万年场 2012-08-19 11:12 LOS红灯");
        dataList.add("牛市口 2013-08-19 11:12 LOS红灯\n" +
                "牛市口的事情非常严重");
        dataList.add("双桥子 2014-08-19 11:12 LOS红灯");
        dataList.add("龙泉驿 2015-08-19 11:12 LOS红灯");
        dataList.add("大面铺 2016-08-19 11:12 LOS红灯");
        dataList.add("省医院 2016-08-19 11:12 LOS红灯");
        dataList.add("人民公园 2017-08-19 11:12 LOS红灯");
        String textValue = "";
        for (int i = 0; i < dataList.size(); i++) {
            textValue += dataList.get(i);
        }
        tvHi.setText(textValue);
        height = tvHi.getHeight();
//        llShelf.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
    }

    /**
     * scrollTo
     */
    private void scrollTo() {
        tvHi.scrollTo(0, 66);
    }

    /**
     * acrollBy
     */
    private void scrollBy() {
        tvHi.scrollBy(0, 66);
    }

    @OnClick({R.id.tv_scroll_to, R.id.tv_scroll_by})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_scroll_to:
                scrollTo();
                break;
            case R.id.tv_scroll_by:
                final Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        scrollBy();
                        handler.postDelayed(this, 3000);
                    }
                };
                handler.postDelayed(runnable, 3000);
                tvHi.scrollTo(0, 0);
                break;
        }
    }
}
