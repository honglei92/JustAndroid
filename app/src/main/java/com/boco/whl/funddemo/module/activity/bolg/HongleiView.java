package com.boco.whl.funddemo.module.activity.bolg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: wanghonglei@boco.com.cn
 * desc:
 * createTime: 2017/9/8 0008
 * updateTime: 2017/9/8 0008
 */

public class HongleiView extends View {
    Paint paint = new Paint();

    public HongleiView(Context context) {
        super(context);
    }

    public HongleiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HongleiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(0xffff0000);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawCircle(300, 300, 200, paint);
    }
}
