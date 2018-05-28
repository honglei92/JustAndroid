package com.boco.whl.funddemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author honglei92
 * @createTime 2018/5/28 0028
 */
public class ObserableScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener = null;

    public ObserableScrollView(Context context) {
        super(context);
    }

    public ObserableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObserableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface ScrollViewListener {
        void onScrollChanged(ObserableScrollView obserableScrollView, int x, int y, int oldX, int oldY);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}
