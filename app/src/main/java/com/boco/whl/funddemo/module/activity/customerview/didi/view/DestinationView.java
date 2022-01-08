package com.boco.whl.funddemo.module.activity.customerview.didi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author honglei92
 * @createTime 2018/4/20 0020
 */
public class DestinationView extends View {

    private float innerRadius = 5;
    private float outRadius = 5;

    public DestinationView(Context context) {
        super(context);
    }

    public DestinationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DestinationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DestinationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(0xffFED4BC);
        canvas.drawCircle(50, 50, outRadius, paint);
        paint.setColor(0xffFC9153);
        canvas.drawCircle(50, 50, innerRadius, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public float getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(float innerRadius) {
        this.innerRadius = innerRadius;
        invalidate();
    }

    public float getOutRadius() {
        return outRadius;
    }

    public void setOutRadius(float outRadius) {
        this.outRadius = outRadius;
        invalidate();
    }
}
