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
 * @author honglei92
 * @createTime 2018/4/25 0025
 */
public class MayiView extends View {
    private float circleX = 60;
    private float circleY = 160;
    private float gotTextY = 0;
    private int radius = 50;
    private int gotAlpha = 255;
    private int energy = 100;

    public MayiView(Context context) {
        super(context);
    }

    public MayiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MayiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MayiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw stroke
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(0x8800ff00);
        canvas.drawCircle(circleX, circleY, radius + 5, paint);
        //draw circle
        paint.setColor(0xff00ff00);
        canvas.drawCircle(circleX, circleY, radius, paint);
        // above text
        paint.setColor(0xaa199C1D);
        paint.setTextSize(22);
        paint.setStrokeWidth(1);
        float tipWidth = paint.measureText("可收取");
        canvas.drawText("可收取", circleX - tipWidth / 2, circleY + 80, paint);
        //inner text
        String energyValue = energy + "g";
        paint.setColor(0xff199C1D);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baseLine = -(fontMetrics.ascent + fontMetrics.descent) / 2;
        float textWidth = paint.measureText(energyValue);
        float startX = circleX - textWidth / 2;
        float endY = circleY + baseLine;
        canvas.drawText(energyValue, startX, endY, paint);
        //got text
        String gotEnergy = "+25g";
        paint.setTextSize(30);
        paint.setStrokeWidth(1.5f);
        float gotWidth = paint.measureText(energyValue);
        float gotStartX = circleX - gotWidth / 2;
        paint.setAlpha(gotAlpha);
        canvas.drawText(gotEnergy, gotStartX, circleY - 70 + gotTextY, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setCircleY(float circleY) {
        this.circleY = circleY;
        invalidate();
    }

    public void setGotTextY(float gotTextY) {
        this.gotTextY = gotTextY;
        invalidate();
    }

    public void setGotAlpha(int gotAlpha) {
        this.gotAlpha = gotAlpha;
        invalidate();
    }
}
