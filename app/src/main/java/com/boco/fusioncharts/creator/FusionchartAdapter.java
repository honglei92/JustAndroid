package com.boco.fusioncharts.creator;

import com.boco.fusioncharts.bean.Category;
import com.boco.fusioncharts.bean.SerieData;

import java.util.List;

/**
 * Created by zhang.w.x on 2017/2/15.
 */
public interface FusionchartAdapter {

    /**
     * 设置图标类型
     * @return
     */
    public  FusionChartsType getFusionChartsType();

    /**
     * x轴名称, 多系列需要实现此方法
     * @return
     */
    public List<Category> getCategoryList();

    /**
     * 系列数,单系列为1,多系列大于1
     * @return
     */
    public  int getSeriesCount();

    /**
     * 生成数据
     * @param serieIndex
     * @return
     */
    public  SerieData getSerieData(int serieIndex);

    /**
     * 样式配置
     * @param config
     */
    public void initFusionChartStyle(FusionChartsConfig config);
}
