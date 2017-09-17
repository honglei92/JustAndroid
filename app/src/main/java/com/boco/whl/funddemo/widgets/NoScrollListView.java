package com.boco.whl.funddemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * scrollview嵌套listview高度自适应
 * Created by LiuJie on 2015/11/12.
 */
public class NoScrollListView extends ListView {

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
