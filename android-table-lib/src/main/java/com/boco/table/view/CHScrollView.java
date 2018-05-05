package com.boco.table.view;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Administrator
 */
public class CHScrollView extends HorizontalScrollView {

    public HorizontalScrollView mTouchView;
    protected List<CHScrollView> mHScrollViews = new ArrayList<CHScrollView>();
    private CHScrollViewListener scroller;
    private View view;
    private ImageView leftImage;
    private ImageView rightImage;
    private int windowWitdh = 0;
    private Activity mContext;
    private View headView;

    public CHScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CHScrollView(Context context) {
        super(context);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        for (CHScrollView scrollView : mHScrollViews) {
            // 防止重复滑动
            if (mTouchView != scrollView) {
                scrollView.smoothScrollTo(l, t);
            }
        }

        //显示左右箭头
        if (mContext != null && !mContext.isFinishing() && view != null && rightImage != null
                && leftImage != null && headView != null) {
            if (view.getWidth() <= windowWitdh) {
                leftImage.setVisibility(View.GONE);
                rightImage.setVisibility(View.GONE);
            } else {
                if (l == 0) {
                    leftImage.setVisibility(View.GONE);
                    rightImage.setVisibility(View.VISIBLE);
                } else if (view.getWidth() - l <= windowWitdh - headView.getWidth()) {
                    leftImage.setVisibility(View.VISIBLE);
                    rightImage.setVisibility(View.GONE);
                } else {
                    leftImage.setVisibility(View.VISIBLE);
                    rightImage.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void setSomeParam(View view, View headView, ImageView leftImage,
                             ImageView rightImage, Activity context) {
        this.mContext = context;
        this.view = view;
        this.leftImage = leftImage;
        this.rightImage = rightImage;
        //获取屏幕大小
        DisplayMetrics dm = new DisplayMetrics();
        this.mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        windowWitdh = dm.widthPixels;
        this.headView = headView;
    }

    public void setIScroller(CHScrollViewListener scroller) {
        this.scroller = scroller;
    }

    public void addView(final CHScrollView sView, ListView listView, List<CHScrollView> mHScrollViews) {
        this.mHScrollViews = mHScrollViews;
        if (!mHScrollViews.isEmpty()) {
            int size = mHScrollViews.size();
            CHScrollView scrollView = mHScrollViews.get(size - 1);
            // 获取滚动位置
            final int scrollX = scrollView.getScrollX();
            // 第一次满屏后，向下滑动，有一条数据在开始时未加入
            if (scrollX != 0) {
                listView.post(new Runnable() {
                    @Override
                    public void run() {
                        // 当listView刷新完成之后，把该条移动到最终位置
                        sView.scrollTo(scrollX, 0);
                    }
                });
            }
        }
        mHScrollViews.add(sView);
    }

}
