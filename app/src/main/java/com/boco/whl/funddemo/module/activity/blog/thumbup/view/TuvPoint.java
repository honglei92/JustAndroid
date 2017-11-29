package com.boco.whl.funddemo.module.activity.blog.thumbup.view;

import android.graphics.PointF;


/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-
 * createTime: 2017/11/13 0013
 * updateTime: 2017/11/13 0013
 */

public class TuvPoint {
    public float x;
    public float y;

    public TuvPoint() {
    }

    public TuvPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() != obj.getClass()) {
            return false;
        }
        PointF point = (PointF) obj;
        if (x != point.x) {
            return false;

        }
        if (y != point.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Float.floatToIntBits(x);
        result = 31 * result + Float.floatToIntBits(y);
        return result;
    }

    @Override
    public String toString() {
        return "Point(" + x + "," + y + ")";
    }
}
