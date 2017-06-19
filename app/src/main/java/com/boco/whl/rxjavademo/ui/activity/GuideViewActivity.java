package com.boco.whl.rxjavademo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boco.whl.rxjavademo.R;
import com.boco.whl.rxjavademo.ui.adapter.GuideViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class GuideViewActivity extends Activity {
    private ViewPager viewPage;
    // 图片
//    private int[] imageView = {R.drawable.yindaoye1, R.drawable.yindaoye2,
//            R.drawable.yindaoye3, R.drawable.yindaoye4};
    private String[] imageView = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492342698186&di=92d3ec68658a0c01755186b5ae4b7515&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fjwn_151216%2F001.jpg",
            "http://img.51ztzj.com/upload/image/20140710/sj201407101016_279x419.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492343221418&di=1f3b1fdd42e479ee43fb1868311e9bfb&imgtype=0&src=http%3A%2F%2Fwww.sinaimg.cn%2Fdy%2Fslidenews%2F21_img%2F2015_46%2F41065_4617487_966410.jpg",
            "http://imgsrc.baidu.com/forum/pic/item/470eeac8a786c917096a5f54c83d70cf3ac757b2.jpg"};
    private List<View> list = new ArrayList<>();
    //立即进入按钮
    private TextView textView;
    private boolean isLastPage = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_view);
        initview();
        initoper();
        addView();

    }

    private void initoper() {
        // 进入按钮
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    private void initview() {
        viewPage = (ViewPager) findViewById(R.id.viewpage);
        textView = (TextView) findViewById(R.id.guideTv);

    }

    /**
     * 添加图片到view
     */
    private void addView() {
        // 将imageview添加到view
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < imageView.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            iv.setImageResource(imageView[i]);
            Glide.with(GuideViewActivity.this).load(imageView[i]).into(iv);
            list.add(iv);
        }
        // 加入适配器
        viewPage.setAdapter(new GuideViewAdapter(list));
        viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (isLastPage) {   //当前页是最后一页，并且是拖动状态，并且像素偏移量为0
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                isLastPage = position == imageView.length - 1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }
}