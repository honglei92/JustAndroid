package com.boco.whl.funddemo.module.activity.selfview.thumbup.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.utils.DisplayUtil;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-
 * createTime: 2017/11/13 0013
 * updateTime: 2017/11/13 0013
 */

public class CountView extends View {
    public static final String DEFAULT_TEXT_COLOR = "#cccccc";
    public static final float DEFAULT_TEXT_SIZE = 15f;
    private static final int COUNT_DURING_TIME = 250;

    private Paint textPaint;
    private float textSize;
    private int textColor;
    private int endTextColor;

    private int count;
    private String[] texts;
    private TuvPoint[] textPoints;

    private float maxOffsetY;

    public void setMinOffsetY(float minOffsetY) {
        this.minOffsetY = minOffsetY;
        invalidate();
    }

    private float minOffsetY;

    public float getMinOffsetY() {
        return minOffsetY;
    }

    private float oldOffsetY;
    private float newOffsetY;
    private float fraction;
    private boolean countToBigger;


    public CountView(Context context) {
//        super(context);
        this(context, null);
    }

    public CountView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public CountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountView);
        count = typedArray.getInt(R.styleable.CountView_cv_count, 0);
        textColor = typedArray.getColor(R.styleable.CountView_cv_text_color, Color.parseColor(DEFAULT_TEXT_COLOR));
        textSize = typedArray.getDimension(R.styleable.CountView_cv_text_size, DisplayUtil.sp2px(context, 15f));
        typedArray.recycle();
        init();
    }

    private void init() {
        texts = new String[3];
        textPoints = new TuvPoint[3];
        textPoints[0] = new TuvPoint();
        textPoints[1] = new TuvPoint();
        textPoints[2] = new TuvPoint();
        calculateChangeNum(0);
        minOffsetY = 0;
        maxOffsetY = textSize;
        endTextColor = Color.argb(0, Color.red(textColor), Color.green(textColor), Color.blue(textColor));
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);


    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        calculateChangeNum(0);
        requestLayout();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        endTextColor = Color.argb(0, Color.red(textColor), Color.green(textColor), Color.blue(textColor));
        postInvalidate();
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        requestLayout();
    }

    public void setTextOffsetY(float offsetY) {
        this.oldOffsetY = offsetY;
        if (countToBigger) {
            this.newOffsetY = offsetY - maxOffsetY;
        } else {
            this.newOffsetY = offsetY + maxOffsetY;
        }
        fraction = (maxOffsetY - Math.abs(oldOffsetY)) / (maxOffsetY - minOffsetY);
        calculateLocation();
        postInvalidate();
    }

    private void calculateLocation() {
        String text = String.valueOf(count);
        float textWidth = textPaint.measureText(text) / text.length();
        float unChangedWidth = textWidth * texts[0].length();
        Paint.FontMetricsInt metricsInt = textPaint.getFontMetricsInt();
        float y = getPaddingTop() + (getContentHeight() - metricsInt.bottom - metricsInt.top) / 2;
        textPoints[0].x = getPaddingLeft();
        textPoints[1].x = getPaddingLeft() + unChangedWidth;
        textPoints[2].x = getPaddingLeft() + unChangedWidth;

        textPoints[0].y = y;
        textPoints[1].y = y - oldOffsetY;
        textPoints[2].y = y - newOffsetY;

    }

    private int getContentWidth() {
        return (int) Math.ceil(textPaint.measureText(String.valueOf(count)));
    }

    private int getContentHeight() {
        return (int) textSize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(TuvUtil.getDefaultSize(widthMeasureSpec, getContentWidth() + getPaddingLeft() + getPaddingRight()),
                TuvUtil.getDefaultSize(heightMeasureSpec, getContentHeight() + getPaddingTop() + getPaddingBottom()));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateLocation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //不变的部分
        textPaint.setColor(textColor);
        canvas.drawText(String.valueOf(texts[0]), textPoints[0].x, textPoints[0].y, textPaint);
        //变化前部分
        textPaint.setColor((Integer) TuvUtil.evaluate(fraction, endTextColor, textColor));
        canvas.drawText(String.valueOf(texts[1]), textPoints[1].x, textPoints[1].y, textPaint);
        //变化后部分
        textPaint.setColor((Integer) TuvUtil.evaluate(fraction, textColor, endTextColor));
        canvas.drawText(String.valueOf(texts[2]), textPoints[2].x, textPoints[2].y, textPaint);

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superData", super.onSaveInstanceState());
        bundle.putInt("count", count);
        bundle.putFloat("textSize", textSize);
        bundle.putInt("textColor", textColor);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle data = (Bundle) state;
        Parcelable superData = data.getParcelable("superData");
        super.onRestoreInstanceState(superData);
        count = data.getInt("count", 0);
        textSize = data.getFloat("textSize", DisplayUtil.dip2px(getContext(), DEFAULT_TEXT_SIZE));
        textColor = data.getInt("textColor", Color.parseColor(DEFAULT_TEXT_COLOR));
        init();

    }

    public void calculateChangeNum(int change) {
        if (change == 0) {
            texts[0] = String.valueOf(count);
            texts[1] = "";
            texts[2] = "";
            return;
        }
        String oldNum = String.valueOf(count);
        String newNum = String.valueOf(count + change);
        for (int i = 0; i < oldNum.length(); i++) {
            char oldC = oldNum.charAt(i);
            char newC = newNum.charAt(i);
            if (oldC != newC) {
                texts[0] = i == 0 ? "" : newNum.substring(0, i);
                texts[1] = oldNum.substring(i);
                texts[2] = newNum.substring(i);
                break;
            }
        }
        count += change;
        startAnim(change > 0);
    }

    private void startAnim(boolean isToBigger) {
        countToBigger = isToBigger;
        ObjectAnimator textOffsetY = ObjectAnimator.ofFloat(this, "minOffsetY", minOffsetY,
                countToBigger ? maxOffsetY : -maxOffsetY);
        textOffsetY.setDuration(COUNT_DURING_TIME);
        textOffsetY.start();


    }
}
