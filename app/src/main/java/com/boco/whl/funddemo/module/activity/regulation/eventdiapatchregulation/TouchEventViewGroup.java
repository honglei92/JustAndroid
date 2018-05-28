package com.boco.whl.funddemo.module.activity.regulation.eventdiapatchregulation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/21 0021 9:28
 *  updatetime : 2017/6/21 0021 9:28
 * </pre>
 *
 * @author Administrator
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
        Log.d("eventTest", "ViewGroup | dispatchTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("eventTest", "ViewGroup | onInterceptTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));
        //默认不拦截
        //return super.onInterceptTouchEvent(ev);
        //拦截事件
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("eventTest", "ViewGroup | onTouchEvent -->" + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }
}
