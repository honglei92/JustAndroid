package com.boco.table.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.boco.table.util.DisplayUtil;


public class InterceptView extends LinearLayout {


    private float downX, moveX;
    private boolean moved = false;
    private long downTime = 0;

    private boolean isNeedIntercept = true;

    private OnClickListener listener;

    public InterceptView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptView(Context context) {
        super(context);
    }

    public void setIsNeedIntercept(boolean isNeed) {
        this.isNeedIntercept = isNeed;
    }


    public void setOnClickListener(OnClickListener l) {
        this.listener = l;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isNeedIntercept) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = ev.getRawX();
                    downTime = System.currentTimeMillis();
                    moved = false;
                    setPressed(true);
                    break;
                case MotionEvent.ACTION_MOVE:
                    moveX = ev.getRawX();
                    if (Math.abs(moveX - downX) > DisplayUtil.dip2px(getContext(),
                            1)) {
                        setPressed(false);
                        moved = true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (!moved) {
                        long current = System.currentTimeMillis();
                        if (current - downTime < 800) {
                            if (listener != null) {
                                listener.onClick(this);
                            }
                        }
                    }
                    setPressed(false);
                case MotionEvent.ACTION_CANCEL:
                    setPressed(false);
                    break;
                default:
                    break;
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    public interface OnClickListener {
        void onClick(View view);
    }

}
