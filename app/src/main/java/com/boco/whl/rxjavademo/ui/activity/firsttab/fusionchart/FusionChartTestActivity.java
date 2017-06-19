package com.boco.whl.rxjavademo.ui.activity.firsttab.fusionchart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.boco.fusioncharts.bean.Category;
import com.boco.fusioncharts.bean.ChartData;
import com.boco.fusioncharts.bean.SerieData;
import com.boco.fusioncharts.creator.FusionChartsConfig;
import com.boco.fusioncharts.creator.FusionChartsCreator;
import com.boco.fusioncharts.creator.FusionChartsType;
import com.boco.fusioncharts.creator.FusionchartAdapter;
import com.boco.whl.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhang.w.x on 2017/2/17.
 */
public class FusionChartTestActivity extends Activity {
    private int num = 0;
    private WebView test1Wv;
    private WebView test2Wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acctivity_fusionchart);

        test1Wv = (WebView) findViewById(R.id.wv_test1);
        test2Wv = (WebView) findViewById(R.id.wv_test2);

        // 这条设置不能省略
        test1Wv.getSettings().setJavaScriptEnabled(true);
        test2Wv.getSettings().setJavaScriptEnabled(true);

        FusionChartsCreator creator = FusionChartsCreator.getInstance(this);

        test1Wv.loadDataWithBaseURL("about:blank", creator.createFusionChartsStr(new SingleSerieChartAdapter(0)), "text/html", "utf-8",
                null);

        test2Wv.loadDataWithBaseURL("about:blank", creator.createFusionChartsStr(new MultiSerieChartAdapter()), "text/html", "utf-8",
                null);
        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                int j = num % 21;
                FusionChartsCreator creator = FusionChartsCreator.getInstance(FusionChartTestActivity.this);
                test1Wv.loadDataWithBaseURL("about:blank", creator.createFusionChartsStr(new SingleSerieChartAdapter(j)), "text/html", "utf-8",
                        null);

            }
        });
    }

    /**
     * 单系列
     */
    private class SingleSerieChartAdapter implements FusionchartAdapter {
        private int type;

        public SingleSerieChartAdapter(int j) {
            this.type = j;
        }

        /**
         * FusionChart图标类型
         *
         * @return
         */
        @Override
        public FusionChartsType getFusionChartsType() {
            List<FusionChartsType> list = new ArrayList<FusionChartsType>();
            list.add(FusionChartsType.Area2D);
            list.add(FusionChartsType.Bar2D);
            list.add(FusionChartsType.Column2D);
            list.add(FusionChartsType.Column3D);
            list.add(FusionChartsType.Doughunt2D);
            list.add(FusionChartsType.Doughunt3D);
            list.add(FusionChartsType.Line);
            list.add(FusionChartsType.MsBar2D);
            list.add(FusionChartsType.MsBar3D);
            list.add(FusionChartsType.MsColumn2D);
            list.add(FusionChartsType.MsColumn3D);
            list.add(FusionChartsType.MsColumnbi2D);
            list.add(FusionChartsType.MsLine);
            list.add(FusionChartsType.MsStackedColumn2d);
            list.add(FusionChartsType.MultiAxisLine);
            list.add(FusionChartsType.Pie2D);
            list.add(FusionChartsType.Pie3D);
            list.add(FusionChartsType.StackedBar2D);
            list.add(FusionChartsType.StackedBar3D);
            list.add(FusionChartsType.StackedColumn2D);
            list.add(FusionChartsType.StackedColumn3D);
            return list.get(type);
        }

        @Override
        public List<Category> getCategoryList() {
            //单系列此方法可以无具体实现
            return null;
        }

        /**
         * 系列数
         *
         * @return 单系列返回1，多系列大于1
         */
        @Override
        public int getSeriesCount() {
            return 1;
        }

        /**
         * 具体数据
         *
         * @param serieIndex 系列编号
         * @return
         */
        @Override
        public SerieData getSerieData(int serieIndex) {
            List<ChartData> list = new ArrayList<ChartData>();
            list.add(new ChartData("1号", "15", "F6BD0F"));
            list.add(new ChartData("2号", "20", "8BBA00"));
            list.add(new ChartData("3号", "11", "FF8E46"));
            list.add(new ChartData("4号", "8", "008E8E"));
            list.add(new ChartData("5号", "18", "D64646"));
            list.add(new ChartData("6号", "10", "8E468E"));

            return new SerieData("", list);
        }

        /**
         * 设置图标样式
         *
         * @param config
         */
        @Override
        public void initFusionChartStyle(FusionChartsConfig config) {
            //如使用默认样式可无具体实现，也可以在应用中创建一个基础adapter，
            // 其他图标适配器继承，在基类中实现此方法，从而达到应用样式一直的目的
            config.addParams(FusionChartsConfig.CanvasbgColor, "#FFFFFF"); //设置背景为红色
            config.addParams(FusionChartsConfig.PaletteColors, "#8E468E");//设置图形为蓝色
        }
    }

    /**
     * 多系列
     */
    private class MultiSerieChartAdapter implements FusionchartAdapter {

        /**
         * FusionChart图标类型
         *
         * @return
         */
        @Override
        public FusionChartsType getFusionChartsType() {
            return FusionChartsType.MsColumn2D;
        }

        /**
         * 多系类x轴数据
         *
         * @return
         */
        @Override
        public List<Category> getCategoryList() {
            List<Category> categoryList = new ArrayList<Category>();
            categoryList.add(new Category("1班"));
            categoryList.add(new Category("2班"));
            categoryList.add(new Category("3班"));
            categoryList.add(new Category("4班"));
            categoryList.add(new Category("5班"));

            return categoryList;
        }

        /**
         * 系列数
         *
         * @return 单系列返回1，多系列大于1
         */
        @Override
        public int getSeriesCount() {
            return 2;
        }

        /**
         * 具体数据
         *
         * @param serieIndex 系列编号
         * @return
         */
        @Override
        public SerieData getSerieData(int serieIndex) {
            SerieData serieData = null;

            if (serieIndex == 0) {
                List<ChartData> dataList = new ArrayList<ChartData>();
                dataList.add(new ChartData("20"));
                dataList.add(new ChartData("18"));
                dataList.add(new ChartData("30"));
                dataList.add(new ChartData("25"));
                dataList.add(new ChartData("26"));

                serieData = new SerieData("男生", dataList);
            } else {
                List<ChartData> dataList = new ArrayList<ChartData>();
                dataList.add(new ChartData("24"));
                dataList.add(new ChartData("28"));
                dataList.add(new ChartData("26"));
                dataList.add(new ChartData("22"));
                dataList.add(new ChartData("30"));

                serieData = new SerieData("女生", dataList);
                serieData.setColor("#00FF00");//设置图形颜色为绿色
            }
            return serieData;
        }

        /**
         * 设置图标样式
         *
         * @param config
         */
        @Override
        public void initFusionChartStyle(FusionChartsConfig config) {
            //如使用默认样式可无具体实现，也可以在应用中创建一个基础adapter，
            // 其他图标适配器继承，在基类中实现此方法，从而达到应用样式一直的目的
        }
    }
}
