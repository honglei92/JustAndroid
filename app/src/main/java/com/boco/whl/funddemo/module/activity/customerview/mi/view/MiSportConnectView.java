package com.boco.whl.funddemo.module.activity.customerview.mi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.utils.DisplayUtil;


/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-小米手环view
 * createTime: 2017/11/6 0006
 * updateTime: 2017/11/6 0006
 */

public class MiSportConnectView extends View {
    /*控件默认宽度*/
    static final int DEFAULT_WIDTH = 400;
    /*控件默认高度*/
    static final int DEFAULT_HEIGHT = 300;
    /*初始圆心位置X与canvas宽度之比*/
    static final float START_CIRCLE_X_SCALE = 0.5f;
    /*初始圆心位置Y与canvas宽度之比*/
    static final float START_CIRCLE_Y_SCALE = 0.5f;

    /*圆环半径大小 画笔大小dp*/
    private static final int BIG_CIRCLE_SIZE = 16;
    /*圆环半径与canvas宽度之比*/
    private static final float BIG_CIRCLE_RADIUS_SCALE = 0.38f;
    /*圆环抖动效果半径*/
    private static final float BIG_CIRCLE_SHAKE_RADIUS = 20;
    /*圆环抖动效果偏移*/
    private static final float BIG_CIRCLE_SHAKE_OFFSET = 0.4f;
    /*圆环旋转速度*/
    private static final float BIG_CIRCLE_ROTATE_SPEED = 0.5f;
    /*圆环光晕效果层数*/
    private static final int CIRCLE_BLUR_LAYER_AMOUNT = 4;
    /*圆环光晕效果大小*/
    private static final float CIRCLE_BLUR_SIZE = 16;


    /*虚线/实线与canvas宽度之比*/
    private static final float DOTTED_SOLID_CIRCLE_SIZE = 0.32f;
    /*虚线画笔大小 dp*/
    private static final float DOTTED_CIRCLR_WIDTH = 2f;
    /*虚线间隔大小 dp*/
    private static final float DOTTED_CIRCLE_GAG = 1f;
    /*实线画笔大小 dp*/
    private static final float SOLID_CIRCLR_WIDTH = 2f;
    /*实线头的圆点大小 dp*/
    private static final float DOT_SIZE = 8f;

    /*主栏字体大小 sp*/
    private static final int MAIN_TITLE_FONT_SIZE_SP = 64;
    /*副栏字体大小 sp*/
    private static final int SUB_TITLE_FONT_SIZE_SP = 14;
    /*副栏字体偏移 dp*/
    private static final int SUB_TITLE_FONT_OFFSET_DP = 50;
    /*手表图标偏移 dp*/
    private static final int WATCH_OFFSET_DP = 84;
    /*手表图标大小 dp*/
    private static final int WATCH_SIZE = 24;

    /*圆心控制变化变量 scale*/
    private float circleOffSetY = 0;
    /*圆半径控制变量 scale*/
    private float circleRadiusIncrement = 0;
    /*圆环行透明到实体的现实进度 progress*/
    private float circleAlaphaProgress = 0;
    /*步数离圆心的Y轴偏移 dp*/
    private float mainTitleOffsetY;
    /*副标题离圆心的Y轴偏移dp*/
    private float subTitleOffsetY;
    /*手表离圆心的Y轴偏移 dp*/
    private float watchOffsetY;

    /*步数画笔 paint*/
    private Paint mainTitlePaint;
    /*副标题画笔 paint*/
    private Paint subTitlePaint;
    /*圆环画笔 paint*/
    private Paint bigCirclePaint;
    /*光晕画笔 paint*/
    private Paint blurPaint;
    /*虚线画笔 paint*/
    private Paint dottedCirclePaint;
    /*实线画笔 paint*/
    private Paint solidCirclePaint;
    /*点画笔 paint*/
    private Paint dotPaint;

    private boolean needRefreshText = true;
    private AnimationThread animationThread;
    private AnimationState animationState = AnimationState.LOADING;
    private FireworksCircleGraphics fireworksCircleGraphics;
    /*旋转度数*/
    private float degree = 0;
    /*复用对象减少GC*/
    private String mainTitleStr = "";
    private String subTitleStr = "";
    private String subTitleSeparator = "";
    private int circleCorlor = 0;
    private float blurSize = 0;
    private RectF solidCircleRectf = new RectF();
    private RectF blurOvalRectf = new RectF();

    /*副标题文字偏移量*/
    private float subTitleOffSetX = 0;
    private Bitmap backgroundBitmap;
    private Bitmap watchBitmap;

    /*外部接口相关*/
    private SportData sportData = new SportData();
    private boolean isConnected = false;
    private int width;
    private int height;
    private float circleX;
    private float circleY;


    public MiSportConnectView(Context context) {
        super(context);
        init(context);
    }


    public MiSportConnectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MiSportConnectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mainTitlePaint = new Paint();
        mainTitlePaint.setColor(ContextCompat.getColor(context, android.R.color.white));
        mainTitlePaint.setTextAlign(Paint.Align.CENTER);
        mainTitlePaint.setTextSize(DisplayUtil.sp2px(context, MAIN_TITLE_FONT_SIZE_SP));
        mainTitleOffsetY = -(mainTitlePaint.getFontMetrics().ascent + mainTitlePaint.getFontMetrics().descent) / 2;
        mainTitlePaint.setAntiAlias(true);

        circleCorlor = ContextCompat.getColor(context, R.color.whiteTransparent);
        subTitlePaint = new Paint();
        subTitlePaint.setColor(circleCorlor);
        subTitlePaint.setTextSize(DisplayUtil.sp2px(context, SUB_TITLE_FONT_SIZE_SP));
        subTitleOffsetY = DisplayUtil.sp2px(context, SUB_TITLE_FONT_OFFSET_DP);
        subTitleSeparator = getResources().getString(R.string.sub_title_separetor);
        subTitlePaint.setAntiAlias(true);

        bigCirclePaint = new Paint();
        bigCirclePaint.setStrokeWidth(DisplayUtil.dip2px(context, BIG_CIRCLE_SIZE));
        bigCirclePaint.setStyle(Paint.Style.STROKE);
        bigCirclePaint.setAntiAlias(true);

        blurPaint = new Paint();
        blurSize = DisplayUtil.dip2px(context, CIRCLE_BLUR_SIZE);

        PathEffect pathEffect1 = new CornerPathEffect(DisplayUtil.dip2px(context, BIG_CIRCLE_SHAKE_RADIUS));
        PathEffect pathEffect2 = new DiscretePathEffect(DisplayUtil.dip2px(context, BIG_CIRCLE_SHAKE_RADIUS),
                DisplayUtil.dip2px(context, BIG_CIRCLE_SHAKE_OFFSET));
        PathEffect pathEffect = new ComposePathEffect(pathEffect1, pathEffect2);
        bigCirclePaint.setPathEffect(pathEffect);

        dottedCirclePaint = new Paint();
        dottedCirclePaint.setStrokeWidth(DisplayUtil.dip2px(context, DOTTED_CIRCLR_WIDTH));
        dottedCirclePaint.setColor(ContextCompat.getColor(context, R.color.whiteTransparent));
        dottedCirclePaint.setStyle(Paint.Style.STROKE);
        float gagPx = DisplayUtil.dip2px(context, DOTTED_CIRCLE_GAG);
        dottedCirclePaint.setPathEffect(new DashPathEffect(new float[]{gagPx, gagPx}, 0));
        dottedCirclePaint.setAntiAlias(true);

        solidCirclePaint = new Paint();
        solidCirclePaint.setStrokeWidth(DisplayUtil.dip2px(context, SOLID_CIRCLR_WIDTH));
        solidCirclePaint.setColor(ContextCompat.getColor(context, android.R.color.white));
        solidCirclePaint.setStyle(Paint.Style.STROKE);
        solidCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        solidCirclePaint.setAntiAlias(true);

        dotPaint = new Paint();
        dotPaint.setStrokeWidth(DisplayUtil.dip2px(context, DOT_SIZE));
        dotPaint.setStrokeCap(Paint.Cap.ROUND);
        dotPaint.setColor(ContextCompat.getColor(context, android.R.color.white));
        dotPaint.setAntiAlias(true);

        backgroundBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bg_step_law);

        //设置手表watch的大小
        watchBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_headview_watch);
        float scale = DisplayUtil.dip2px(context, WATCH_SIZE) / watchBitmap.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        watchBitmap = Bitmap.createBitmap(watchBitmap, 0, 0, watchBitmap.getWidth(), watchBitmap.getHeight(), matrix, true);
        watchOffsetY = DisplayUtil.dip2px(context, WATCH_OFFSET_DP);

        fireworksCircleGraphics = new FireworksCircleGraphics(context);
        animationThread = new AnimationThread();
        animationThread.start();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE && animationThread.isInterrupted()) {
            animationThread.start();
        } else {
            animationThread.interrupt();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        resetDataIfNeeded(canvas);
        //背景左下角对齐
        canvas.drawBitmap(backgroundBitmap, 0, height - backgroundBitmap.getHeight(), null);

        /*平移动画效果*/
        canvas.save();
        canvas.translate(0, width * circleOffSetY);
        switch (animationState) {
            case LOADING:
                drawFireworks(canvas);
                drawText(canvas);
                break;
            case UP1:
                drawText(canvas);
                drawBigCircle(canvas);
                break;
            case DOWN1:
                drawText(canvas);
                drawBigCircle(canvas);
                break;
            case STOP:
            case UP2:
                drawText(canvas);
                drawBigCircle(canvas);
                drawProgressCircle(canvas);
                break;
            case FINISH:
                drawText(canvas);
                drawBigCircle(canvas);
                drawProgressCircle(canvas);
                break;
            case DISCONNECTED:
                drawText(canvas);
                drawBigCircle(canvas);
                drawProgressCircle(canvas);
                break;
        }
        canvas.restore();
        invalidate();

    }

    private void drawFireworks(Canvas canvas) {
        fireworksCircleGraphics.draw(canvas);
    }

    private void drawProgressCircle(Canvas canvas) {
        float dottedCircleRadius = width * DOTTED_SOLID_CIRCLE_SIZE;
        solidCircleRectf.set(circleX - dottedCircleRadius, circleY - dottedCircleRadius, circleX + dottedCircleRadius,
                circleY + dottedCircleRadius);
        canvas.drawCircle(circleX, circleY, dottedCircleRadius, dottedCirclePaint);
        canvas.drawArc(solidCircleRectf, -90, 3.6f * sportData.progress, false, solidCirclePaint);
        /*计算进度点位置*/
        canvas.drawPoint(circleX + dottedCircleRadius * (float) Math.cos((3.6f * sportData.progress - 90) * Math.PI / 180),
                circleY + dottedCircleRadius * (float) Math.sin((3.6f * sportData.progress - 90) * Math.PI / 180), dotPaint);
    }

    private void drawBigCircle(Canvas canvas) {
        float bigCircleRadius = width * BIG_CIRCLE_RADIUS_SCALE;
        /*扩大和旋转动画效果*/
        canvas.save();
        canvas.scale(1 + circleRadiusIncrement / BIG_CIRCLE_RADIUS_SCALE, 1 + circleRadiusIncrement / BIG_CIRCLE_RADIUS_SCALE, circleX, circleY);
        canvas.rotate(degree, circleX, circleY);
        //光晕
        blurPaint.setAlpha((int) (Color.alpha(circleCorlor) * circleAlaphaProgress));
        for (int i = 0; i < CIRCLE_BLUR_LAYER_AMOUNT; i++) {
            blurPaint.setAlpha(0xff * (CIRCLE_BLUR_LAYER_AMOUNT - 1) / (CIRCLE_BLUR_LAYER_AMOUNT * 3));
            blurOvalRectf.set(circleX - bigCircleRadius, circleY - bigCircleRadius, circleX + bigCircleRadius
                    + i * blurSize / CIRCLE_BLUR_LAYER_AMOUNT, circleY + bigCircleRadius);
            canvas.drawOval(blurOvalRectf, blurPaint);
        }
        bigCirclePaint.setAlpha((int) (0xff * circleAlaphaProgress));
        canvas.drawCircle(circleX, circleY, bigCircleRadius, bigCirclePaint);
    }

    /**
     * @param canvas 字块和哲别图标
     */
    private void drawText(Canvas canvas) {
        canvas.drawText(mainTitleStr, circleX, circleY + mainTitleOffsetY, mainTitlePaint);
        canvas.drawText(subTitleStr, circleX + subTitleOffSetX, circleY + subTitleOffsetY, subTitlePaint);
        canvas.drawBitmap(watchBitmap, circleX - watchBitmap.getWidth() / 2, circleY - watchBitmap.getHeight() / 2 + watchOffsetY, null);

    }

    private void resetDataIfNeeded(Canvas canvas) {
        if (needRefreshText) {
            refreshText();
            needRefreshText = false;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        if (width == this.width && height == this.height) {
            return;
        }
        this.width = width;
        this.height = height;

        circleX = width * START_CIRCLE_X_SCALE;
        circleY = height * START_CIRCLE_Y_SCALE;
        //设置背景大小，让其可以覆盖整个view
        float scaleX = (float) width / backgroundBitmap.getWidth();
        float scaleY = (float) height / backgroundBitmap.getHeight();
        float scale = Math.max(scaleX, scaleY);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        backgroundBitmap = Bitmap.createBitmap(backgroundBitmap, 0, 0, backgroundBitmap.getWidth(),
                backgroundBitmap.getHeight(), matrix, true);

        float bigCircleRadius = width * BIG_CIRCLE_RADIUS_SCALE;
        Shader bigCircleLinearGradient = new LinearGradient(
                circleX - bigCircleRadius, circleY,
                circleX + bigCircleRadius, circleY,
                ContextCompat.getColor(getContext(), R.color.whiteTransparent),
                ContextCompat.getColor(getContext(), R.color.white),
                Shader.TileMode.CLAMP);
        bigCirclePaint.setShader(bigCircleLinearGradient);
        Shader blurLinearGradient = new LinearGradient(
                circleX, circleY,
                circleX + bigCircleRadius, circleY,
                ContextCompat.getColor(getContext(), R.color.transparent),
                ContextCompat.getColor(getContext(), R.color.white),
                Shader.TileMode.CLAMP);
        blurPaint.setShader(blurLinearGradient);
    }

    private void refreshText() {
        //字块
        mainTitleStr = Integer.toString(sportData.step);
        String format = getResources().getString(R.string.sub_title_format);
        subTitleStr = String.format(format, sportData.distance / 1000, sportData.calories);
        //副标题文字居中
        float indexBefore = subTitlePaint.measureText(subTitleStr, 0, subTitleStr.indexOf(subTitleSeparator));
        float indexAfter = subTitlePaint.measureText(subTitleStr, 0, subTitleStr.indexOf(subTitleSeparator) + 1);
        subTitleOffSetX = -(indexBefore + indexAfter) / 2;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = this.getMeasuredSize(widthMeasureSpec, true);
        int height = this.getMeasuredSize(heightMeasureSpec, false);
        setMeasuredDimension(width, height);
    }

    private int getMeasuredSize(int length, boolean isWidth) {
        int specMode = MeasureSpec.getMode(length);
        int specSize = MeasureSpec.getSize(length);
        int retSize;
        int padding = (isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom());
        if (specMode == MeasureSpec.EXACTLY) {
            retSize = specSize;
        } else {
            retSize = isWidth ? DEFAULT_WIDTH + padding : DEFAULT_HEIGHT + padding;
            if (specMode == MeasureSpec.UNSPECIFIED) {
                retSize = Math.min(retSize, specSize);
            }
        }
        return retSize;
    }

    /**
     * @param sportData 设置数据
     */
    public void setSportData(SportData sportData) {
        this.sportData = new SportData(sportData);
        refreshText();
    }

    /**
     * @param isConnected 设置连接状态
     */
    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
        invalidate();
    }

    /**
     * 动画状态机
     */
    private enum AnimationState {
        LOADING,
        UP1,
        DOWN1,
        STOP,
        UP2,
        FINISH,
        DISCONNECTED;

        AnimationState nextState() {
            switch (this) {
                case LOADING:
                    return UP1;
                case UP1:
                    return DOWN1;
                case DOWN1:
                    return STOP;
                case STOP:
                    return UP2;
                case UP2:
                    return FINISH;
                case FINISH:
                    return DISCONNECTED;
                case DISCONNECTED:
                    return LOADING;
                default:
                    return LOADING;
            }
        }
    }

    /**
     * 动画控制线程
     */
    private class AnimationThread extends Thread {
        /**
         * 动画刷新时间间隔每秒60帧
         */
        private static final int INTERVAL_MILL = 17;
        private static final int LOADING_TIME_MILL = -INTERVAL_MILL;
        private static final int UP1_TIME_NILL = 250;
        private static final int DOWM1_TIME_MILL = UP1_TIME_NILL;
        private static final int STOP_TIME_MILL = 120;
        private static final int UP2_TIME_MILL = 300;
        private static final int FINISH_TIME_MILL = -INTERVAL_MILL;
        private static final int DISCONNECTED_MILL = 200;

        /*圆环淡入淡出时间 */
        private static final float APPEAR_MILLS = 10 * INTERVAL_MILL;
        /*UP1阶段高度变化百分比 */
        private static final float UP1_SCALE = -0.05f;
        /*DOWN1阶段高度变化百分比 */
        private static final float DOWN1_SCALE = -UP1_SCALE;
        /*UP2阶段高度变化百分比 */
        private static final float UP2_SCALE = -UP1_SCALE;
        /*DISCONNECTED阶段高度变化百分比 */
        private static final float DISCONNECTED_DOWN2_SCALE = -UP2_SCALE;

        private final int[] ANIMATION_TIME_LIST = {
                LOADING_TIME_MILL, UP1_TIME_NILL, DOWM1_TIME_MILL, STOP_TIME_MILL,
                UP2_TIME_MILL, FINISH_TIME_MILL, DISCONNECTED_MILL
        };

        @SuppressWarnings("InfiniteLoopStatement")
        public void run() {
            int index = 0;
            while (true) {
                int durationTime = ANIMATION_TIME_LIST[index];
                index = (index + 1) % ANIMATION_TIME_LIST.length;

                //每个阶段的动画帧数
                int times = durationTime / INTERVAL_MILL;
                int count = 0;
                nextAnimation:
                while (times < 0 || count++ < times) {
                    long startTime = System.currentTimeMillis();
                    switch (animationState) {
                        case LOADING:
                            fireworksCircleGraphics.next();
                            if (isConnected) {
                                break nextAnimation;
                            }
                            break;
                        case UP1:
                            if (count <= APPEAR_MILLS / INTERVAL_MILL) {
                                circleAlaphaProgress = count / (APPEAR_MILLS / INTERVAL_MILL);
                            }
                            circleOffSetY = (float) (UP1_SCALE * Math.sin((float) count / times * Math.PI / 2));
                            circleRadiusIncrement = -circleOffSetY;
                            break;
                        case DOWN1:
                            circleOffSetY = (float) (DOWN1_SCALE * -Math.sin(Math.PI / 2 + (float) count / times * Math.PI / 2));
                            circleRadiusIncrement = -circleOffSetY;
                            break;
                        case STOP:
                            break;
                        case UP2:
                            circleOffSetY = (float) (UP2_SCALE * Math.sin((float) count / times * Math.PI / 2));
                            break;
                        case FINISH:
                            if (!isConnected) {
                                break nextAnimation;
                            }
                            break;
                        case DISCONNECTED:
                            if (times - count <= APPEAR_MILLS / INTERVAL_MILL) {
                                circleAlaphaProgress = (times - count) / (APPEAR_MILLS / INTERVAL_MILL);
                            }
                            circleOffSetY = (float) (DISCONNECTED_DOWN2_SCALE * -Math.sin(Math.PI / 2 + (float) count / times * Math.PI / 2));
                            break;
                        default:
                            break;
                    }
                    degree = (degree + BIG_CIRCLE_ROTATE_SPEED) % 360;
                    long usedTime = System.currentTimeMillis() - startTime;
                    try {
                        if (usedTime > INTERVAL_MILL) {
                            continue;
                        }
                        sleep(INTERVAL_MILL - usedTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                animationState = animationState.nextState();
            }
        }
    }
}

