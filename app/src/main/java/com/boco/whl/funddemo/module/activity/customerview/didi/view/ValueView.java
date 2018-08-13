package com.boco.whl.funddemo.module.activity.customerview.didi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author honglei92
 * @createTime 2018/4/28 0028
 */
public class ValueView extends View {

    private int num;

    public ValueView(Context context) {
        super(context);
    }

    public ValueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ValueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ValueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(25);
        paint.setColor(0xff329E89);
        canvas.drawColor(0x11666666);
        canvas.drawText(num + "", 15, 30, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setNum(int num) {
        this.num = num;
        invalidate();
    }
}
