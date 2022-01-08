package com.boco.whl.funddemo.module.activity.customerview.didi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author honglei92
 * @createTime 2018/4/26 0026
 */
public class InfoWindowView extends View {
    private int radius = 30;
    private int width = 130;
    private int triLong = 30;
    private int triShort = 15;

    Path path1 = new Path();
    Path path2 = new Path();
    Path path3 = new Path();

    public InfoWindowView(Context context) {
        super(context);
    }

    public InfoWindowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InfoWindowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InfoWindowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(0x2200ff00);
        Path path = new Path();
        path.addRect(radius, 0, width + radius, radius * 2, Path.Direction.CW);
        path1.addCircle(radius, radius, radius, Path.Direction.CW);
        path2.addCircle(radius + width, radius, radius, Path.Direction.CW);
        path3.moveTo(radius + (width - triLong) / 2, radius * 2);
        path3.lineTo(radius + (width + triLong) / 2, radius * 2);
        path3.lineTo(radius + width / 2, radius * 2 + triShort);
        path3.close();
        path.op(path1, Path.Op.UNION);
        path.op(path2, Path.Op.UNION);
        path.op(path3, Path.Op.UNION);
        canvas.drawPath(path, paint);
        //文字
        paint.setColor(0xff000000);
        Paint.FontMetrics metrics = paint.getFontMetrics();
        paint.setTextSize(22);
        float baseLine = -(metrics.ascent + metrics.descent) / 2;
        float textWidth = paint.measureText("确定在此打点");
        canvas.drawText("确定在此打点", radius + (width - textWidth) / 2, radius + baseLine, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
