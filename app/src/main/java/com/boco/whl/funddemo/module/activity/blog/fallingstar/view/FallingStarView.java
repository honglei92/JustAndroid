package com.boco.whl.funddemo.module.activity.blog.fallingstar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-流星雨自定义View
 * createTime: 2018/1/27 0027
 * updateTime: 2018/1/27 0027
 */

public class FallingStarView extends View {

    private Paint paint;
    private float positionX;
    private float positionY;
    private double finalX = 5;
    private double finalY = 5;
    private final int starNum = 5;
    private int mAlpha = 255;
    private double mAddDistance = 2;

    public void setPositionX(float positionX) {
        this.positionX = positionX;
        invalidate();
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
        invalidate();
    }

    public FallingStarView(Context context) {
        this(context, null);
    }

    public FallingStarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FallingStarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化轨迹画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xff000000);
        for (int i = 0; i < starNum; i++) {
            float offsetX = i * 80;
            canvas.drawLine(positionX + offsetX, positionY, (float) finalX + offsetX, (float) finalY, paint);
            canvas.drawCircle((float) finalX + offsetX, (float) finalY, 4, paint);
        }
        initAnimation();
    }

    /**
     * 动画
     */
    public void initAnimation() {

        positionX += 1;
        positionY += 1;
        mAddDistance += 0.05;
        finalX += 1.5;
        finalY += 1.5;
        if (finalY > 250) {
            mAlpha = (int) (mAlpha * 0.95);
            paint.setAlpha(mAlpha);
        }
        if (finalY >= 300) {
            reset();
        }
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 重置x，y坐标
     */
    public void reset() {
        positionX = 0;
        positionY = 0;
        finalX = 5;
        finalY = 5;
        mAlpha = 255;
        paint.setAlpha(mAlpha);
        mAddDistance = 2;
    }
}
