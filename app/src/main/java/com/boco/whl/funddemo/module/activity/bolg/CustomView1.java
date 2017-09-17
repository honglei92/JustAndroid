package com.boco.whl.funddemo.module.activity.bolg;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.boco.whl.funddemo.R;

/**
 * author: wanghonglei@boco.com.cn
 * desc:自定义view1
 * createTime: 2017/9/8 0008
 * updateTime: 2017/9/8 0008
 */

public class CustomView1 extends View {
    private int mRadius;
    private TypedArray typearray;

    public CustomView1(Context context) {
        super(context);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        typearray = context.obtainStyledAttributes(attrs, R.styleable.CustomView1);
        mRadius = (int) typearray.getDimension(R.styleable.CustomView1_Radius, getResources().getDimension(R.dimen.circle_radius)); //半径长度
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置背景色
        canvas.drawColor(0x88880000);
        onCircle(canvas);
        onRect(canvas);
        onPoint(canvas);
        onPoints(canvas);
    }

    /**
     * @param canvas 画多个点
     */
    private void onPoints(Canvas canvas) {
        float[] points = {0, 100, 0, 200};
    }

    /**
     * @param canvas 画点
     */
    private void onPoint(Canvas canvas) {
        Paint paintPoint = new Paint();
        paintPoint.setStrokeWidth(20);
        paintPoint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(290, 80, paintPoint);
    }

    /**
     * @param canvas 画矩形
     */
    private void onRect(Canvas canvas) {
        Paint paintRect = new Paint();
        paintRect.setColor(0xffff0000);
        paintRect.setStyle(Paint.Style.STROKE);
        paintRect.setStrokeWidth(2);
        paintRect.setAntiAlias(true);
        canvas.drawRect(180, 0, 280, 100, paintRect);
    }

    /**
     * @param canvas 画圆
     */
    private void onCircle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(0xffff0000);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        canvas.drawCircle(80, 80, mRadius, paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
