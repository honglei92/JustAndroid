package com.boco.whl.funddemo.module.activity.selfview.didi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 网易云音乐
 *
 * @author honglei92
 * @createTime 2018/5/17 0017
 */
public class MusicView extends View {


    private float startY = 0;
    private float startY1 = 0;
    private float startY2 = 0;
    private float startY3 = 0;

    public MusicView(Context context) {
        super(context);
    }

    public MusicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MusicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xffC62F2F);
        paint.setStrokeWidth(3);
        canvas.drawLine(3, startY, 3, 60, paint);
        canvas.drawLine(23, startY1, 23, 60, paint);
        canvas.drawLine(43, startY2, 43, 60, paint);
        canvas.drawLine(63, startY3, 63, 60, paint);
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
        invalidate();
    }

    public float getStartY1() {
        return startY1;
    }

    public void setStartY1(float startY1) {
        this.startY1 = startY1;
        invalidate();
    }

    public float getStartY2() {
        return startY2;
    }

    public void setStartY2(float startY2) {
        this.startY2 = startY2;
        invalidate();
    }

    public float getStartY3() {
        return startY3;
    }

    public void setStartY3(float startY3) {
        this.startY3 = startY3;
        invalidate();
    }
}
