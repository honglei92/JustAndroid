package com.boco.whl.funddemo.module.activity.blog.mi.view;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-数据类
 * createTime: 2017/11/6 0006
 * updateTime: 2017/11/6 0006
 */

public class SportData {
    //进度
    public int progress;
    //步数
    public int step;
    //路程 距离
    public float distance;
    //卡路里
    public int calories;

    public SportData() {
        this.progress = 0;
        this.step = 0;
        this.distance = 0;
        this.calories = 0;
    }

    public SportData(SportData sportData) {
        this();
        if (sportData != null) {
            this.progress = sportData.progress;
            this.step = sportData.step;
            this.distance = sportData.distance;
            this.calories = sportData.calories;
        }
    }

}
