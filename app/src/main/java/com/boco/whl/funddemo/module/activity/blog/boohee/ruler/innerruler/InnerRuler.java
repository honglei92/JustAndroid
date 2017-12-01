package com.boco.whl.funddemo.module.activity.blog.boohee.ruler.innerruler;

import android.content.Context;
import android.graphics.Paint;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.OverScroller;

import com.boco.whl.funddemo.module.activity.blog.boohee.ruler.BooheeRuler;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-
 * createTime: 2017/11/29 0029
 * updateTime: 2017/11/29 0029
 */

public abstract class InnerRuler extends View {
    protected Context mContext;
    protected BooheeRuler mParent;
    protected Paint mSmallScalePaint, mBigScalePaint, mTextPaint, mOutLinePAint;
    //当前刻度值
    protected float mCurrentScale;
    //最大刻度数
    protected int mMaxLength = 0;
    //长度，最小滑动值，最大可滑动值
    protected int mLength, mMinPosition, mMaxPosition;
    //控制滑动
    protected OverScroller mOverScroller;
    //一个大刻度多少格小刻度
    protected int mCount = 10;
    //提前刻画量
    protected int mDrawOffset = 0;
    //速度获取
    protected VelocityTracker mVelocityTracker;
    //惯性最大最小速度
    protected int mMaximumVelocity, mMinimumVelocity;
    //回调接口
    protected RulerCallBack mRulerCallBack;
    //边界效果
    protected EdgeEffect mStartEdgeEffect, mEndEdgeEffect;
    //是否显示边界效果
    protected boolean mIsShowEdge = false;
    //边缘效应长度
    protected int mEdgeLength;


    public InnerRuler(Context context, BooheeRuler booheeRuler) {
        super(context);
        mParent = booheeRuler;
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mMaxLength = mParent.getMaxScale() - mParent.getMinScale();
    }
}
