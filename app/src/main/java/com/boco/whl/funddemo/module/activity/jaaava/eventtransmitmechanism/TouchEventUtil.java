package com.boco.whl.funddemo.module.activity.jaaava.eventtransmitmechanism;

import android.view.MotionEvent;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/21 0021 9:13
 *  updatetime : 2017/6/21 0021 9:13
 * </pre>
 */
public class TouchEventUtil {
    public static String getTouchAction(int actionId) {
        String actionName = "UnKnown id:" + actionId;
        switch (actionId) {
            case MotionEvent.ACTION_DOWN:
                actionName = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                actionName = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                actionName = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                actionName = "ACTION_CANCEL";
                break;
            case MotionEvent.ACTION_OUTSIDE:
                actionName = "ACTION_OUTSIDE";
                break;
        }
        return actionName;
    }
}
