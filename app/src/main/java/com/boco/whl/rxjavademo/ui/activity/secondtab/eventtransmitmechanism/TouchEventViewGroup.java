package com.boco.whl.rxjavademo.ui.activity.secondtab.eventtransmitmechanism;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/21 0021 9:28
 *  updatetime : 2017/6/21 0021 9:28
 * </pre>
 */
public class TouchEventViewGroup extends LinearLayout {
    public TouchEventViewGroup(Context context) {
        super(context);
    }

    public TouchEventViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d("eventTest", "ViewGroup | dispatchTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.d("eventTest", "ViewGroup | onInterceptTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d("eventTest", "ViewGroup | onTouchEvent -->" + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }
}
