package com.boco.whl.funddemo.module.activity.customerview.thumbup.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.utils.DisplayUtil;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-
 * createTime: 2017/11/13 0013
 * updateTime: 2017/11/13 0013
 */

public class ThumbView extends View {
    //圆圈颜色
    private static final int START_COLOR = Color.parseColor("#00e24d3d");
    private static final int END_COLOR = Color.parseColor("#88e24d3d");
    //缩放动画的时间
    private static final int SCALE_DURING = 150;
    //圆圈扩散动画的时间
    private static final int RADIUS_DURING = 100;

    private static final float SCALE_MIN = 0.9f;
    private final static float SCALE_MAX = 1f;

    private Bitmap mThumbUp;
    private Bitmap mShining;
    private Bitmap mThumbNormal;
    private Paint mBitmapPaint;

    private float mThumbWidth;
    private float mThumbHeight;
    private float mShiningWidth;
    private float mShiningHeight;

    private TuvPoint mShinningPoint;
    private TuvPoint mThumbPoint;
    private TuvPoint mCirclePoint;

    private float mRadiusMax;
    private float mRadiusMin;
    private float mRadius;
    private Path mClipPath;
    private Paint mCirclePaint;

    private boolean mIsThumbUp;
    private long mLastStartTime;
    //点击的回调
    private ThumbUpClickListener mThumbUpClickListener;
    //被点赞的次数，未点击时，为点赞是0，点赞是1
    private int mClickCount;
    private int mEndCount;
    private AnimatorSet mThumbUpAnim;

    public ThumbView(Context context) {
//        super(context);
        this(context, null);
    }

    public ThumbView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public ThumbView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initBitmapInfo();
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 2));
        mCirclePoint = new TuvPoint();
        mCirclePoint.x = mThumbPoint.x + mThumbWidth / 2;
        mCirclePoint.y = mThumbPoint.y + mThumbHeight / 2;

        mRadiusMax = Math.max(mCirclePoint.x - getPaddingLeft(), mCirclePoint.y - getPaddingTop());
        mRadiusMin = DisplayUtil.dip2px(getContext(), 8);
        mClipPath = new Path();
        mClipPath.addCircle(mCirclePoint.x, mCirclePoint.y, mRadiusMax, Path.Direction.CW);

    }

    private void initBitmapInfo() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        resetBitmap();
        mThumbWidth = mThumbUp.getWidth();
        mThumbHeight = mThumbUp.getHeight();

        mShiningWidth = mShining.getWidth();
        mShiningHeight = mShining.getHeight();

        mShinningPoint = new TuvPoint();
        mThumbPoint = new TuvPoint();
        //这个相对位置是在布局中试出来的
        mShinningPoint.x = getPaddingLeft() + DisplayUtil.dip2px(getContext(), 2);
        mShinningPoint.y = getPaddingTop();
        mThumbPoint.x = getPaddingLeft();
        mThumbPoint.y = getPaddingTop() + DisplayUtil.dip2px(getContext(), 8);
    }

    private void resetBitmap() {
        mThumbUp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected);
        mThumbNormal = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_unselected);
        mShining = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected_shining);
    }

    public void setIsThumbUp(boolean isThumbUp) {
        this.mIsThumbUp = isThumbUp;
        mClickCount = mIsThumbUp ? 1 : 0;
        mEndCount = mClickCount;
        postInvalidate();
    }

    public boolean isThumbUp() {
        return mIsThumbUp;
    }

    public void setThumbUpClickListener(ThumbUpClickListener thumbUpClickListener) {
        this.mThumbUpClickListener = thumbUpClickListener;
    }

    public TuvPoint getCirclePoint() {
        return mCirclePoint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(TuvUtil.getDefaultSize(widthMeasureSpec, getContentWidth() + getPaddingLeft() + getPaddingRight()),
                TuvUtil.getDefaultSize(heightMeasureSpec, getContentHeight() + getPaddingTop() + getPaddingBottom()));
    }

    private int getContentHeight() {
        float minTop = Math.min(mShinningPoint.y, mThumbPoint.y);
        float maxBottom = Math.max(mShinningPoint.y + mShiningHeight, mThumbPoint.y + mThumbHeight);
        return (int) (maxBottom - minTop);
    }

    private int getContentWidth() {
        float minLeft = Math.min(mShinningPoint.x, mThumbPoint.x);
        float maxRight = Math.max(mShinningPoint.x + mShiningWidth, mThumbPoint.x + mThumbWidth);
        return (int) (maxRight - minLeft);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle data = new Bundle();
        data.putParcelable("superData", super.onSaveInstanceState());
        data.putBoolean("isThumbUp", mIsThumbUp);
        return data;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle data = (Bundle) state;
        Parcelable superData = data.getParcelable("superData");
        super.onRestoreInstanceState(superData);
        mIsThumbUp = data.getBoolean("isThumbUp", false);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIsThumbUp) {
            if (mClipPath != null) {
                canvas.save();
                canvas.clipPath(mClipPath);
                canvas.drawBitmap(mShining, mShinningPoint.x, mShinningPoint.y, mBitmapPaint);
                canvas.restore();
                canvas.drawCircle(mCirclePoint.x, mCirclePoint.y, mRadius, mCirclePaint);
            }
            canvas.drawBitmap(mThumbUp, mThumbPoint.x, mThumbPoint.y, mBitmapPaint);
        } else {
            canvas.drawBitmap(mThumbNormal, mThumbPoint.x, mThumbPoint.y, mBitmapPaint);
        }
    }

    public void startAnim() {
        mClickCount++;
        boolean isFastAnim = false;
        long currentTimeMills = System.currentTimeMillis();
        if (currentTimeMills - mLastStartTime < 300) {
            isFastAnim = true;
        }
        mLastStartTime = currentTimeMills;
        if (mIsThumbUp) {
            if (isFastAnim) {
                startFastAnim();
                return;
            }
            startThumbDownAnim();
            mClickCount = 0;
        } else {
            if (mThumbUpAnim != null) {
                mClickCount = 0;
            } else {
                startThumbUpAnim();
                mClickCount = 1;
            }
        }
        mEndCount = mClickCount;
    }

    private void startThumbUpAnim() {
        ObjectAnimator notThumbUpScale = ObjectAnimator.ofFloat(this, "notThumbUpScale", SCALE_MAX, SCALE_MIN);
        notThumbUpScale.setDuration(SCALE_DURING);
        notThumbUpScale.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mIsThumbUp = true;
            }
        });
        ObjectAnimator thumbUpScale = ObjectAnimator.ofFloat(this, "thumbUpScale", SCALE_MIN, SCALE_MAX);
        thumbUpScale.setDuration(SCALE_DURING);
        thumbUpScale.setInterpolator(new OvershootInterpolator());

        ObjectAnimator circleScale = ObjectAnimator.ofFloat(this, "circleScale", mRadiusMin, mRadiusMax);
        circleScale.setDuration(RADIUS_DURING);

        mThumbUpAnim = new AnimatorSet();
        mThumbUpAnim.play(thumbUpScale).with(circleScale);
        mThumbUpAnim.play(thumbUpScale).after(notThumbUpScale);
        mThumbUpAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mThumbUpAnim = null;
                if (mThumbUpClickListener != null) {
                    mThumbUpClickListener.thumbUpFinish();
                }
            }
        });
        mThumbUpAnim.start();
    }

    private void startThumbDownAnim() {
        ObjectAnimator thumbUpScale = ObjectAnimator.ofFloat(this, "thumbUpScale", SCALE_MIN, SCALE_MAX);
        thumbUpScale.setDuration(SCALE_DURING);
        thumbUpScale.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mIsThumbUp = false;
                setNotThumbUpScale(SCALE_MAX);
                if (mThumbUpClickListener != null) {
                    mThumbUpClickListener.thumbDownFinish();
                }
            }
        });
        thumbUpScale.start();
    }

    private void startFastAnim() {
        ObjectAnimator thumbUpScale = ObjectAnimator.ofFloat(this, "thumbUpScale", SCALE_MIN, SCALE_MAX);
        thumbUpScale.setDuration(SCALE_DURING);
        thumbUpScale.setInterpolator(new OvershootInterpolator());

        ObjectAnimator circleScale = ObjectAnimator.ofFloat(this, "circleScale", mRadiusMin, mRadiusMax);
        circleScale.setDuration(SCALE_DURING);
        AnimatorSet set = new AnimatorSet();
        set.play(thumbUpScale).with(circleScale);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mEndCount++;
                if (mClickCount != mEndCount) {
                    return;
                }
                if (mClickCount % 2 == 0) {
                    startThumbDownAnim();
                } else {
                    if (mThumbUpClickListener != null) {
                        mThumbUpClickListener.thumbUpFinish();
                    }
                }
            }
        });
        set.start();
    }

    public void setNotThumbUpScale(float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        mThumbNormal = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_unselected);
        mThumbNormal = Bitmap.createBitmap(mThumbNormal, 0, 0, mThumbNormal.getWidth(), mThumbNormal.getHeight(), matrix, true);
        postInvalidate();
    }

    private void setThumbUpScale(float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        mThumbUp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected);
        mThumbUp = Bitmap.createBitmap(mThumbUp, 0, 0, mThumbUp.getWidth(), mThumbUp.getHeight(), matrix, true);
        postInvalidate();
    }

    private void setShiningScale(float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        mShining = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected_shining);
        mShining = Bitmap.createBitmap(mShining, 0, 0, mShining.getWidth(), mShining.getHeight(), matrix, true);
        postInvalidate();
    }

    private void setCircleScale(float radius) {
        mRadius = radius;
        mClipPath = new Path();
        mClipPath.addCircle(mCirclePoint.x, mCirclePoint.y, mRadius, Path.Direction.CW);
        float fraction = (mRadiusMax - radius) / (mRadiusMax - mRadiusMin);
        mCirclePaint.setColor((Integer) TuvUtil.evaluate(fraction, START_COLOR, END_COLOR));
        postInvalidate();
    }

    public interface ThumbUpClickListener {
        //点赞回调
        void thumbUpFinish();

        //取消回调
        void thumbDownFinish();
    }
}
