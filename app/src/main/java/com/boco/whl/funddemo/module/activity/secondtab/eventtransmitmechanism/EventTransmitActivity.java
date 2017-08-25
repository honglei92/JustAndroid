package com.boco.whl.funddemo.module.activity.secondtab.eventtransmitmechanism;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.boco.whl.funddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/20 0020 17:47
 *  updatetime : 2017/6/20 0020 17:47
 * </pre>
 */
public class EventTransmitActivity extends Activity {
    @BindView(R.id.tv_view)
    TouchEventView tvView;
    @BindView(R.id.event_viewgroup)
    TouchEventViewGroup eventViewgroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventtransmitmechanism);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_view, R.id.event_viewgroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_view:
                break;
            case R.id.event_viewgroup:
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d("eventTest", "Activity | dispatchTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d("eventTest", "Activity | onTouchEvent -->" + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }
}
