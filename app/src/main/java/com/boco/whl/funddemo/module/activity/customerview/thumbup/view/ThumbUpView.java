package com.boco.whl.funddemo.module.activity.customerview.thumbup.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.utils.DisplayUtil;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-
 * createTime: 2017/11/13 0013
 * updateTime: 2017/11/13 0013
 */

public class ThumbUpView extends LinearLayout implements View.OnClickListener {
    private final float DEFAULT_DRAWABLE_PADDING = 4f;
    private ThumbView mThumbView;
    private CountView mCountView;
    private float drawablePadding;
    private int textColor;
    private int count;

    private float textSize;
    private boolean isThumbUp;
    private int topMargin;
    private boolean needChangeChildView;


    public ThumbUpView(Context context) {
//        super(context);
        this(context, null);
    }

    public ThumbUpView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }


    public ThumbUpView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ThumbUpView);
        drawablePadding = typedArray.getDimension(R.styleable.ThumbUpView_tuv_drawable_padding,
                DisplayUtil.dip2px(context, DEFAULT_DRAWABLE_PADDING));
        count = typedArray.getInt(R.styleable.ThumbUpView_tuv_count, 0);
        textColor = typedArray.getColor(R.styleable.ThumbUpView_tuv_text_color,
                Color.parseColor(CountView.DEFAULT_TEXT_COLOR));
        textSize = typedArray.getDimension(R.styleable.ThumbUpView_tuv_text_size,
                DisplayUtil.sp2px(context, CountView.DEFAULT_TEXT_SIZE));
        isThumbUp = typedArray.getBoolean(R.styleable.ThumbUpView_tuv_isThumbUp, false);
        typedArray.recycle();
        init();
    }

    private void init() {
        removeAllViews();
        setClipChildren(false);
        setOrientation(LinearLayout.HORIZONTAL);
        addThumbView();
        addCountView();
        //把设置的padding分解到子view，否则对超出view范围的动画显示不全
        setPadding(0, 0, 0, 0, false);
        setOnClickListener(this);

    }

    public ThumbUpView setCount(int mCount) {
        this.count = mCount;
        mCountView.setCount(mCount);
        return this;
    }

    public ThumbUpView setTextColor(int mTextColor) {
        this.textColor = mTextColor;
        mCountView.setTextColor(textColor);
        return this;
    }

    public ThumbUpView setTextSize(float mTextSize) {
        this.textSize = mTextSize;
        mCountView.setTextSize(mTextSize);
        return this;
    }

    public ThumbUpView setThumbUp(boolean mIsThumbUp) {
        this.isThumbUp = mIsThumbUp;
        mThumbView.setIsThumbUp(isThumbUp);
        return this;
    }

    public void setThumbUpClickListener(ThumbView.ThumbUpClickListener listener) {
        mThumbView.setThumbUpClickListener(listener);
    }

    private void setPadding(int left, int right, int top, int bottom, boolean needChange) {
        this.needChangeChildView = needChange;
        setPadding(left, right, top, bottom);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        if (needChangeChildView) {
            resetThumbParams();
            resetCountViewParams();
            needChangeChildView = false;
        } else {
            super.setPadding(left, top, right, bottom);
        }
    }

    private void resetCountViewParams() {
        LayoutParams params = (LayoutParams) mCountView.getLayoutParams();
        if (topMargin > 0) {
            params.topMargin = topMargin;
        }
        params.leftMargin = (int) drawablePadding;
        params.topMargin += getPaddingTop();
        params.bottomMargin = getPaddingBottom();
        params.rightMargin = getPaddingRight();
        mCountView.setLayoutParams(params);
    }

    private void resetThumbParams() {
        LayoutParams params = (LayoutParams) mThumbView.getLayoutParams();
        if (topMargin < 0) {
            params.topMargin = topMargin;
        }
        params.leftMargin = getPaddingLeft();
        params.topMargin += getPaddingTop();
        params.bottomMargin = getPaddingBottom();
        mThumbView.setLayoutParams(params);
    }

    private void addCountView() {
        mCountView = new CountView(getContext());
        mCountView.setTextColor(textColor);
        mCountView.setTextSize(textSize);
        mCountView.setCount(count);
        addView(mCountView, getCountParams());
    }

    private void addThumbView() {
        mThumbView = new ThumbView(getContext());
        mThumbView.setIsThumbUp(isThumbUp);
        TuvPoint circlePoint = mThumbView.getCirclePoint();
        topMargin = (int) ((circlePoint.y - textSize) / 3);
        addView(mThumbView, getThumbParams());
    }

    private LayoutParams getThumbParams() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (topMargin < 0) {
            params.topMargin = topMargin;
        }
        params.leftMargin = getPaddingLeft();
        params.topMargin = getPaddingTop();
        params.bottomMargin = getPaddingBottom();
        return params;
    }

    public LayoutParams getCountParams() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (topMargin > 0) {
            params.topMargin = topMargin;
        }
        params.leftMargin = (int) drawablePadding;
        params.topMargin += getPaddingTop();
        params.bottomMargin = getPaddingBottom();
        params.rightMargin = getPaddingRight();
        return params;
    }

    @Override
    public void onClick(View v) {
        isThumbUp = !isThumbUp;
        if (isThumbUp) {
            mCountView.calculateChangeNum(1);
        } else {
            mCountView.calculateChangeNum(-1);
        }
        mThumbView.startAnim();
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle data = new Bundle();
        data.putParcelable("superData", super.onSaveInstanceState());
        data.putInt("count", count);
        data.putFloat("textSize", textSize);
        data.putInt("textColor", textColor);
        data.putBoolean("isThumbUp", isThumbUp);
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle data = (Bundle) state;
        Parcelable superData = data.getParcelable("superData");
        super.onRestoreInstanceState(superData);
        count = data.getInt("count");
        textSize = data.getFloat("textSize", DisplayUtil.dip2px(getContext(), CountView.DEFAULT_TEXT_SIZE));
        textColor = data.getInt("textColor", Color.parseColor(CountView.DEFAULT_TEXT_COLOR));
        isThumbUp = data.getBoolean("isThumbUp", false);
        drawablePadding = data.getFloat("drawablePadding", DisplayUtil.sp2px(getContext(), DEFAULT_DRAWABLE_PADDING));
        init();
    }
}
