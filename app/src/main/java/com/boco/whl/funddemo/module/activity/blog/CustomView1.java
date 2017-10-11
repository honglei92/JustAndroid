package com.boco.whl.funddemo.module.activity.blog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.boco.whl.funddemo.R;

/**
 * author: wanghonglei@boco.com.cn
 * desc:自定义view1
 * createTime: 2017/9/8 0008
 * updateTime: 2017/9/8 0008
 */

public class CustomView1 extends View {
    private int mRadius;
    private TypedArray typearray;
    private Paint mPaint = new Paint();

    public CustomView1(Context context) {
        super(context);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        typearray = context.obtainStyledAttributes(attrs, R.styleable.CustomView1);
        mRadius = (int) typearray.getDimension(R.styleable.CustomView1_Radius, getResources().getDimension(R.dimen.circle_radius)); //半径长度
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置背景色
        canvas.drawColor(0x88880000);
//        onCircle(canvas);
//        onRect(canvas);
//        onPoint(canvas);
//        onPoints(canvas);
//        onOval(canvas);
//        onLine(canvas);
//        onLines(canvas);
//        onRoundRect(canvas);
//        onArc(canvas);
//        onPath(canvas);
//        onBitmap(canvas);
//        onText(canvas);
//        onClipRect(canvas);
//        onClipPath(canvas);
//        onCanvas(canvas);
        onCamera(canvas);
    }

    /**
     * @param canvas canmera变换
     */
    private void onCamera(Canvas canvas) {
        canvas.save();
        Camera camera = new Camera();
        camera.rotateX(50);
        canvas.translate(300, 200);
        camera.applyToCanvas(canvas);
        canvas.translate(-300, -200);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head_150);
        canvas.drawBitmap(bitmap, 200, 100, mPaint);
        canvas.restore();
    }

    /**
     * @param canvas canvas变换
     */
    private void onCanvas(Canvas canvas) {
        canvas.save();
        canvas.translate(100, 50);
        canvas.rotate(45, 400, 200);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head_150);
        canvas.drawBitmap(bitmap, 200, 100, mPaint);
        canvas.restore();
    }

    /**
     * @param canvas 裁切path
     */
    private void onClipPath(Canvas canvas) {
        canvas.save();
        Path path = new Path();
        path.addCircle(320, 240, 100, Path.Direction.CCW);
        canvas.clipPath(path);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head_150);
        canvas.drawBitmap(bitmap, 200, 100, mPaint);
        canvas.restore();
    }

    /**
     * @param canvas 裁切矩形
     */
    private void onClipRect(Canvas canvas) {
        canvas.save();
        canvas.clipRect(250, 150, 350, 250);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head_150);
        canvas.drawBitmap(bitmap, 200, 100, mPaint);
        canvas.restore();
    }

    /**
     * @param canvas 画文本
     */
    private void onText(Canvas canvas) {
        mPaint.setTextSize(20);
        canvas.drawText("hello henry", 100, 20, mPaint);
        mPaint.setTextSize(40);
        canvas.drawText("hello henry", 100, 70, mPaint);
        mPaint = new Paint();
        mPaint.setTextSize(60);
        mPaint.setUnderlineText(true);
        canvas.drawText("hello henry", 100, 140, mPaint);
        mPaint = new Paint();
        mPaint.setTextSize(80);
        mPaint.setStrikeThruText(true);
        canvas.drawText("hello henry", 100, 240, mPaint);
    }

    /**
     * @param canvas 画位图
     */
    private void onBitmap(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head_150);
        canvas.drawBitmap(bitmap, 200, 100, mPaint);
    }

    /**
     * @param canvas 画path
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onPath(Canvas canvas) {
        Path path = new Path();
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
        canvas.drawPath(path, mPaint);
    }

    /**
     * @param canvas 画扇形
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onArc(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawArc(200, 100, 800, 500, -110, 100, true, paint);
        canvas.drawArc(200, 100, 800, 500, 20, 140, false, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(200, 100, 800, 500, 180, 60, false, paint);
    }

    /**
     * @param canvas 画圆角矩形
     */
    private void onRoundRect(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rectF = new RectF();
        rectF.set(500, 50, 600, 100);
        canvas.drawRoundRect(rectF, 10, 10, paint);
    }

    /**
     * @param canvas 画多个点
     */
    private void onLines(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float[] lines = {400, 100, 500, 100, 500, 100, 400, 200, 400, 200, 400, 100};
        canvas.drawLines(lines, 0, 12, paint);
    }

    /**
     * @param canvas 画直线
     */
    private void onLine(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawLine(300, 20, 400, 90, paint);
    }

    /**
     * @param canvas 画椭圆
     */
    private void onOval(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawOval(200, 300, 400, 400, paint);
        RectF rectF = new RectF();
        rectF.set(200, 300, 400, 400);
        canvas.drawOval(rectF, paint);
    }

    /**
     * @param canvas 画多个点
     */
    private void onPoints(Canvas canvas) {
        Paint paintPoint = new Paint();
        paintPoint.setStrokeWidth(20);
        paintPoint.setStrokeCap(Paint.Cap.ROUND);
        float[] points = {200, 200, 200, 300, 300, 200, 300, 300};
        canvas.drawPoints(points, 0, 8, paintPoint);
    }

    /**
     * @param canvas 画点
     */
    private void onPoint(Canvas canvas) {
        Paint paintPoint = new Paint();
        paintPoint.setStrokeWidth(20);
        paintPoint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(300, 100, paintPoint);
    }

    /**
     * @param canvas 画矩形
     */
    private void onRect(Canvas canvas) {
        Paint paintRect = new Paint();
        paintRect.setColor(0xffff0000);
        paintRect.setStyle(Paint.Style.STROKE);
        paintRect.setStrokeWidth(2);
        paintRect.setAntiAlias(true);
        canvas.drawRect(180, 0, 280, 100, paintRect);
    }

    /**
     * @param canvas 画圆
     */
    private void onCircle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(0xffff0000);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        canvas.drawCircle(80, 80, mRadius, paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
