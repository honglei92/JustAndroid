package com.boco.whl.funddemo.module.activity.selfview.mi.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.RectF;

import com.boco.whl.funddemo.utils.DisplayUtil;

import java.util.Random;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-特效canvas  烟花圆环控制类
 * createTime: 2017/11/10 0010
 * updateTime: 2017/11/10 0010
 */

class FireGraphics {
    //圆环旋转速度
    private static final int ROTETE_RATE = 3;
    //圆环半径与canvas宽度之比
    private static final float RADIUS_SCALE = 0.32f;
    //线条数目
    private static final int LINE_ACCOUNT = 30;
    //线条弧长
    private static final float LINE_DEGREE = 345;
    //线条大小
    private static final float LINE_SIZE = 0.5f;
    //线条半径偏离最大值
    private static final float LINE_MAX_DR = 4f;
    //线条圆心偏离最大值
    private static final float LINE_MAX_DC = 4f;
    //线条弧长变化速率范围
    private static final float LINE_MAX_CHANGE_RATE = 0.015f;
    //线条弧长变化速率衰减速率
    private static final float LINE_DECAY_RATE = LINE_MAX_CHANGE_RATE / 180;
    //线条便捷反向率比例
    private static final float LINE_SIDE_RATIO = LINE_DECAY_RATE * 10;
    //线条随机摆动触发间隔
    private static final int LINE_RANDOM_ALTER_FRAME = 60;
    //星星数目
    private static final int STARP_AMOUNT = 30;
    //星星大小
    private static final float STAR_SIZE = 8f;
    //星星逃离X轴最大速度
    private static final float STAR_MAX_VX = 2.5f;
    //星星逃离Y轴最大速度
    private static final float STAR_MAX_VY = 2.5f;
    //星星速率衰减速率
    private static final float STAR_DECAY_RATE = 0.005f;
    //星星速率衰减常量
    private static final float STAR_DECAY_RATE_CONST = 0.001f;
    //星星消失林临界距离
    private static final float STAR_DISPEAR_DISTANCE = 60f;
    //星星消失临界亮度
    private static final float STAR_DISPEAR_ALAPH = 0.05f;

    //线条半径偏离最大值
    private float lineMaxDxy;
    //线条圆心偏离最大值
    private float lineMaxDr;
    //线条弧长变化速率范围
    private float lineMaxChangeRate;
    //线条弧长变化速率衰减速率
    private float lineDecayRate;
    //星星逃离X轴最大速度
    private float starMaxVx;
    //星星逃离Y轴最大速率
    private float starMaxVy;
    //星星速率衰减速率
    private float starDecayRate;
    //星星速率衰减常量
    private float starDecayRateConst;
    //星星消失临界距离
    private float starDisappearDistance;

    private int startColor;
    private int endColor;

    private Paint linePaint;
    private Paint starPaint;
    private Random random;
    private int rotateDeree = -90;

    //对象复用减少 GC
    private int width;
    private int height;
    private int circleX;
    private int circleY;
    private RectF lineRectf = new RectF(0, 0, 0, 0);
    private boolean needRefresh = false;

    //线条参数列表
    private LineArgument[] lineArgumentList;
    //星星参数列表
    private StarArgument[] starArgumentList;

    FireGraphics(Context context) {
        lineMaxDxy = DisplayUtil.dip2px(context, LINE_MAX_DR);
        lineMaxDr = DisplayUtil.dip2px(context, LINE_MAX_DC);
        lineMaxChangeRate = DisplayUtil.dip2px(context, LINE_MAX_CHANGE_RATE);

    }


    private class LineArgument {

    }

    private class StarArgument {
    }
}
