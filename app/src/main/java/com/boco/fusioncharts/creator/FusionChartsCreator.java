package com.boco.fusioncharts.creator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;
import com.boco.fusioncharts.bean.Categories;
import com.boco.fusioncharts.bean.ChartData;
import com.boco.fusioncharts.bean.MultiSeriesData;
import com.boco.fusioncharts.bean.SerieData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * FusionCharts HTML生成器
 *
 * @author zhang.w.x
 */
public class FusionChartsCreator {

    private static final String[] JS_FILES = {"firebug-lite.js",
            "FusionCharts.HC.Charts.js", "FusionCharts.HC.js",
            "FusionCharts.HC.PowerCharts.js", "FusionCharts.HC.Widgets.js",
            "FusionCharts.jqueryplugin.js", "FusionCharts.js",
            "FusionChartsExportComponent.js", "jquery.min.js"};

    private static final String KEY_FILE_COPY = "fileCopy";
    private static FusionChartsCreator creator;
    private String path = null;
    private SharedPreferences sp;


    @SuppressLint("SdCardPath")
    private FusionChartsCreator(Context context) {

        path = "/data/data/" + context.getPackageName() + "/js/";

        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean isCopyed = sp.getBoolean(KEY_FILE_COPY, false);

        if (isCopyed) {
            return;
        }

        copyFiles();
    }

    public static FusionChartsCreator getInstance(Context context) {
        if (creator == null) {
            creator = new FusionChartsCreator(context);
        }

        return creator;
    }

    private void copyFiles() {
        try {
            byte[] buffer = new byte[1024];
            InputStream is;
            FileOutputStream fos;
            int len;
            for (String name : JS_FILES) {
                is = getClass().getClassLoader().getResourceAsStream("assets/" + name);

                File file = new File(path + name);
                file.getParentFile().mkdirs();

                fos = new FileOutputStream(file);

                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }

                is.close();
                fos.close();
            }

            Editor editor = sp.edit();

            editor.putBoolean(KEY_FILE_COPY, true);

            editor.commit();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成单系列图表
     *
     * @param chartType
     * @param config
     * @param datas
     * @return
     * @deprecated 推荐使用 {@link #createFusionChartsStr(com.boco.fusioncharts.creator.FusionchartAdapter)}
     */
    @Deprecated
    public String createChartHtml(FusionChartsType chartType, FusionChartsConfig config,
                                  List<ChartData> datas) {


        return getHtml(chartType, config, "\"data\":" + JSON.toJSONString(datas));
    }

    /**
     * 生成多系列图表
     *
     * @param type
     * @param config
     * @param multiSeriesData
     * @return
     * @deprecated 推荐使用 {@link #createFusionChartsStr(com.boco.fusioncharts.creator.FusionchartAdapter)}
     */
    @Deprecated
    public String createMultiChartHtml(FusionChartsType type,
                                       FusionChartsConfig config, MultiSeriesData multiSeriesData) {

        return getHtml(
                type,
                config,
                "\"categories\" :"
                        + JSON.toJSONString(multiSeriesData.getCategories())
                        + ", \"dataset\":"
                        + JSON.toJSONString(multiSeriesData.getDataset()));
    }

    public String getHtml(FusionChartsType chartType, FusionChartsConfig config,
                          String dataStr) {
        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///" + path
                + "FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");

        str.append("var myChart = new FusionCharts(\"" + chartType
                + "\", \"myChartId\", \"100%\", \"100%\", \"0\", \"1\");\n");

        str.append("myChart.setJSONData({\"chart\" : {\n" + config + "},"
                + dataStr + "});");

        str.append(" myChart.render( 'chartContainer' );");
        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        return str.toString();
    }

    /**
     * 生成Fsionchart的HTML字符串
     *
     * @param adapter
     * @return
     */
    public String createFusionChartsStr(FusionchartAdapter adapter) {
        if (adapter.getSeriesCount() <= 0) {

            throw new RuntimeException("SeriesCount系列数必须大于0");
        }

        FusionChartsConfig config = FusionChartsConfig.createDefaultConfig();
        adapter.initFusionChartStyle(config);
        if (adapter.getSeriesCount() == 1) {
            //单系列
            List<ChartData> datas = adapter.getSerieData(0).getData();
            return createChartHtml(adapter.getFusionChartsType(), config, datas);
        } else {
            //多系列
            MultiSeriesData multiSeriesData = new MultiSeriesData();

            Categories categories = new Categories();
            categories.setCategory(adapter.getCategoryList());

            List<SerieData> dataset = new ArrayList<SerieData>();

            for (int i = 0; i < adapter.getSeriesCount(); i++) {
                dataset.add(adapter.getSerieData(i));
            }

            multiSeriesData.setCategories(categories);
            multiSeriesData.setDataset(dataset);

            return createMultiChartHtml(adapter.getFusionChartsType(), config, multiSeriesData);
        }

    }

}
