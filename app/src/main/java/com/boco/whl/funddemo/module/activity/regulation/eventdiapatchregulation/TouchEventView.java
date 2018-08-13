package com.boco.whl.funddemo.module.activity.regulation.eventdiapatchregulation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/21 0021 9:28
 *  updatetime : 2017/6/21 0021 9:28
 * </pre>
 * @author Administrator
 */
public class TouchEventView extends android.support.v7.widget.AppCompatTextView {
    public TouchEventView(Context context) {
        super(context);
    }

    public TouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventView(Context context, AttributeSet attrs, int defStyles) {
        super(context, attrs, defStyles);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("eventTest", "\n\nView | dispatchTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
//        return true;
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("eventTest", "\n\nView | onTouchEvent -->" + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
//        return true;
//        return false;
    }
}
