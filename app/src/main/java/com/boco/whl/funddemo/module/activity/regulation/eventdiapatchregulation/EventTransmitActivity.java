package com.boco.whl.funddemo.module.activity.regulation.eventdiapatchregulation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 事件分发机制
 *
 * @author Administrator
 */
public class EventTransmitActivity extends BaseActivity {
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
            default:
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("eventTest", "Activity | dispatchTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("eventTest", "Activity | onTouchEvent -->" + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }
}
