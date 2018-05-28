package com.boco.whl.funddemo.module.activity.regulation.lru;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.module.adapter.ImageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 照片墙
 *
 * @author honglei92
 * @createTime 2018/5/17 0017
 */
public class PhotoWallActivity extends BaseActivity {
    @BindView(R.id.photo_wall_gridView)
    GridView photoWallGridView;
    private Boolean mIsGridViewIdle = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall);
        ButterKnife.bind(this);
        Log.d("honglei-process", Process.myPid() + "");
        Log.d("honglei-process-thread", Thread.currentThread().getName() +
                Thread.currentThread().getId());
        ImageAdapter adapter = new ImageAdapter(getApplicationContext(), mIsGridViewIdle);
        photoWallGridView.setAdapter(adapter);
        photoWallGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    mIsGridViewIdle = true;
                    adapter.notifyDataSetChanged();
                } else {
                    mIsGridViewIdle = false;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
}
