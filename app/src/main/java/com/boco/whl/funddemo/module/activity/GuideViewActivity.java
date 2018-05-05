package com.boco.whl.funddemo.module.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.adapter.GuideViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * 帮助介绍页
 *
 * @author Administrator
 */
public class GuideViewActivity extends Activity {
    private ViewPager viewPage;

    /**
     * 图片
     */
    private int[] imageView = {R.drawable.yindaoye1, R.drawable.yindaoye2,
            R.drawable.yindaoye3, R.drawable.yindaoye4};
    /*  private String[] imageView = {
              "https://wx2.sinaimg.cn/mw690/727190c6ly1fnkxdneuhzj21hc1z4qv9.jpg",
              "https://wx1.sinaimg.cn/mw690/727190c6ly1fnkxdqa7m0j21z41hchdx.jpg",
              "https://wx2.sinaimg.cn/mw690/727190c6ly1fnkxdu5sagj21hc1z44qv.jpg",
              "https://wx1.sinaimg.cn/mw690/b527203dgy1fnkfka64bnj23402c0npd.jpg"};*/
    private List<View> list = new ArrayList<>();

    private boolean isLastPage = false;
    /**
     * 立即进入按钮
     */
    private TextView textView;

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
            Glide.with(GuideViewActivity.this).load(imageView[i]).into(iv);
            list.add(iv);
        }
        // 加入适配器
        viewPage.setAdapter(new GuideViewAdapter(list));
        viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (isLastPage) {
                    //当前页是最后一页，并且是拖动状态，并且像素偏移量为0
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