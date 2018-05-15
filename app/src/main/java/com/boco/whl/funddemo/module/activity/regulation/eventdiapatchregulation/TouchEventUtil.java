package com.boco.whl.funddemo.module.activity.regulation.eventdiapatchregulation;

import android.view.MotionEvent;

/**
 * @author Administrator
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
            default:
                break;
        }
        return actionName;
    }
}
