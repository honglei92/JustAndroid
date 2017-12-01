package com.boco.whl.funddemo.module.activity.blog.boohee.ruler;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.blog.boohee.ruler.innerruler.InnerRuler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-包裹尺子的外壳
 * createTime: 2017/11/29 0029
 * updateTime: 2017/11/29 0029
 */

public class BooheeRuler extends ViewGroup {
    private final String TAG = "ruler";
    private Context mContext;
    public static final int TOP_HEAD = 1, BOTTOM_HEAD = 2, LEFT_HEAD = 3, RIGHT_HEAD = 4;
    //尺子style定义
    @IntDef({TOP_HEAD, BOTTOM_HEAD, LEFT_HEAD, RIGHT_HEAD})
    @Retention((RetentionPolicy.SOURCE))
    public
    @BooheeRuler.RulerStyle
    int mStyle = TOP_HEAD;
    //内部的尺子
    private InnerRuler mInnerRuler;
    //最小最大刻度值 以0.1kg为单位
    private int mMinScale = 461, mMaxScale = 1000;
    //中间光标画笔
    private Paint mCPaint, mOutLinePaint;
    //光标宽度、高度
    private int mCursorWidth = 8, mCursorHeight = 70;
    //大小刻度的长度
    private int mSmallScaleLength = 30, mBigScaleLength = 60;
    //大小刻度的粗细
    private int mSmallScaleWidth = 3, mBigScaleWidth = 5;
    //数字字体大小
    private int mTextSize = 28;
    //数字text距离顶部高度
    private int mTextMarginHead = 120;
    //刻度间隔
    private int mInterval = 18;
    //数字text颜色
    private
    @ColorInt
    int mScaleColor = getResources().getColor(R.color.colorLightBlack);
    //刻度颜色
    private
    @ColorInt
    int mTextColor = getResources().getColor(R.color.colorGray);
    //初始的当前刻度
    private float mCurrentScale = 0;
    //一个大刻度多少小刻度
    private int mCount = 10;
    //光标drawable
    private Drawable mCursorDrawable;
    //尺子两端padding
    private int mPaddingStartAndEnd = 0;
    private int mPaddingLedt = 0, mPaddingRight = 0, mPaddingTop = 0, mPaddingBottom = 0;
    //尺子背景
    private Drawable mRulerBackGround;
    private int mRulerBackGroundColor = getResources().getColor(R.color.colorDirtyWhite);
    //是否应用边缘效应
    private boolean mCanEdgeEffect;
    //边缘颜色
    private
    @ColorInt
    int mEdgeColor = getResources().getColor(R.color.colorForgiven);

    public BooheeRuler(Context context) {
        super(context);
        initRuler(context);
    }


    public BooheeRuler(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initRuler(context);
    }


    public BooheeRuler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initRuler(context);
    }

    private void initRuler(Context context) {

    }

    private void initAttrs(Context context, AttributeSet attrs) {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
