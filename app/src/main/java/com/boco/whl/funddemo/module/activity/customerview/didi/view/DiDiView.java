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
 * @createTime 2018/4/20 0020
 */
public class DiDiView extends View {

    private float radius = 5;


    private int innerColor = 0xffffffff;

    public DiDiView(Context context) {
        super(context);
    }

    public DiDiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DiDiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DiDiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        //外面套空心圆
        paint.setColor(0xff329E89);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        canvas.drawCircle(30, 30, 20, paint);
        //底色大圆
        paint.setColor(0xff3BBBA2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(30, 30, 19, paint);
        //变化小圆
        paint.setColor(0xffffffff);
        canvas.drawCircle(30, 30, radius, paint);
        //中间小圆
        paint.setColor(innerColor);
        canvas.drawCircle(30, 30, 5, paint);
        //支架
        Paint linePaint = new Paint();
        linePaint.setStrokeWidth(4);
        linePaint.setAntiAlias(true);
        linePaint.setColor(0xff329E89);
        canvas.drawLine(30, 50, 30, 68, linePaint);
        //底部阴影
        linePaint.setStrokeWidth(2);
        linePaint.setColor(0xff247465);
        linePaint.setShadowLayer(360, 1, 1, 0xff329E89);
        canvas.drawLine(28, 68, 32, 68, linePaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public int getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(int innerColor) {
        this.innerColor = innerColor;
        invalidate();
    }
}
