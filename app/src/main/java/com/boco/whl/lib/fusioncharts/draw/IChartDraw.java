package com.boco.whl.lib.fusioncharts.draw;

import java.util.List;

public interface IChartDraw {

    /**
     * 此接口可以绘制：单系列柱图(可以根据数据生成Y轴因子)
     *
     * @param title        标题
     * @param seriesList   系列集合
     * @param lables       x横坐标label
     * @param display_num  单系列柱图固定显示值， 堆积图，可以选择是否显示 display_num 0否、1是.
     * @param trendlines   趋势线,,如果list为空表示没有趋势线.. value 1紫红、2紫色、3橘色、4绿、5草绿、6蓝色 默认
     *                     为0或其他值的情况.. 青绿
     * @param is2Dor3D     是否是2D还是3D图。。 注意(为2D图的时候，趋势线showOnTop属性才能生效.) 0:3D 、1:2D
     * @param isRateChart  是否是比率图， 0否、1是 如果是比率图，Y轴因子上限为100
     * @param labelDisplay x横坐标label的显示风格, 0:自动排版 1:WRAP(换行) 根据整体挤压.. 2 ROTATE 旋转 3
     *                     ROTATE slantLabels='1' 旋转倾斜. 4 Stagger 交错
     * @param labelSte     设置label的step梯步 1---n 0,1默认没有效果.
     * @param isdrill      如果是null 就不去加link 非空加link 注意：系列集合里面的数据，-999 表示数据为空，图形中用空白柱子显示。。
     * @return
     */
    String drawBarChart(String title, List<ChartSeries> seriesList,
                        String[] labels, int display_num, List<ChartTrendlines> trendlines,
                        int is2Dor3D, int isRateChart, int labelDisplay, int labelSte,
                        String isdrill);

    /**
     * 此接口可以绘制:堆积图
     *
     * @param title       标题
     * @param seriesList  系列集合
     * @param labels      x横坐标label
     * @param display_num 单系列柱图固定显示值， 堆积图，可以选择是否显示 display_num 0否、1是.
     * @param labelSte    设置label的step梯步 1---n 0,1默认没有效果. 注意:堆积图不能设置Y轴因子
     *                    注意：系列集合里面的数据，-999 表示数据为空，图形中用空白柱子显示。。
     * @return
     */
    String drawStackedChart(String title, List<ChartSeries> seriesList,
                            String[] labels, int display_num, int is2Dor3D, int labelDisplay,
                            int labelSte);

    /**
     * 特殊堆积图，用于福建，好中差。。。
     *
     * @param title       标题
     * @param seriesList  系列集合
     * @param labels      x横坐标label
     * @param display_num 单系列柱图固定显示值， 堆积图，可以选择是否显示 display_num 0否、1是.
     * @param labelSte    设置label的step梯步 1---n 0,1默认没有效果.
     * @param showLegend  是否显示图例 0 不显示, 1显示 注意:堆积图不能设置Y轴因子 注意：系列集合里面的数据，-999
     *                    表示数据为空，图形中用空白柱子显示。。
     * @return
     */
    String drawSpecialStackedChartForHZC(String title,
                                         List<ChartSeries> seriesList, String[] labels, int display_num,
                                         int is2Dor3D, int labelDisplay, int labelSte, int showLegend);

    /**
     * 此接口用于绘制:多系列柱图(并排显示)
     *
     * @param title        标题
     * @param seriesList   系列集合
     * @param labels       x横坐标label
     * @param display_num  可以选择是否显示系列值 display_num 0否、1是.
     * @param trendlines   趋势线,,如果list为空表示没有趋势线.. value 1紫红、2紫色、3橘色、4绿、5草绿、6蓝色 默认
     *                     为0或其他值的情况.. 青绿
     * @param is2Dor3D     是否是2D还是3D图。。 注意(为2D图的时候，趋势线showOnTop属性才能生效.) 0:3D 、1:2D
     * @param isRateChart  是否是比率图， 0否、1是 如果是比率图，Y轴因子上限为100
     * @param labelDisplay x横坐标label的显示风格, 0:自动排版 1:WRAP(换行) 根据整体挤压.. 2 ROTATE 旋转 3
     *                     ROTATE slantLabels='1' 旋转倾斜. 4 Stagger 交错
     * @param labelSte     设置label的step梯步 1---n 0,1默认没有效果. 注意：系列集合里面的数据，-999
     *                     表示数据为空，图形中用空白柱子显示。。
     * @return
     */
    String drawMultiSeriesBarChart(String title,
                                   List<ChartSeries> seriesList, String[] labels, int display_num,
                                   List<ChartTrendlines> trendlines, int is2Dor3D, int isRateChart,
                                   int labelDisplay, int labelSte);

    /**
     * 此接口用于绘制:柱图 //用于自定义样式 使用例子
     * <p>
     * <p>
     * <p>
     * List<ChartSeries> tfSeriesList = new ArrayList<ChartSeries>();
     * <p>
     * ChartSeries tf = new ChartSeries();
     * tf.setColor("e3591f");
     * int faultGSMNum = importData.getFaultGSMNum();
     * int faultTDNum = importData.getFaultTDNum();
     * int faultLteNum = importData.getFaultLteNum();
     * double[] tfdate = new double[3];
     * tfdate[0] = faultGSMNum;
     * tfdate[1] = faultTDNum;
     * tfdate[2] = faultLteNum;
     * String[] tfdrill = new String[3];
     * tfdrill[0] = groupName + "," + "201" + "," + neLeve + "," + 2;
     * tfdrill[1] = groupName + "," + "9201" + "," + neLeve + "," + 2;
     * tfdrill[2] = groupName + "," + "8104" + "," + neLeve + "," + 2;
     * tf.setDrillstr(tfdrill);
     * tf.setName("退服基站数");
     * tf.setData(tfdate);
     * <p>
     * <p>
     * ChartSeries totalcs = new ChartSeries();
     * totalcs.setColor("a0a0a0");
     * int gsmNum = importData.getGsmNum();
     * int tdNUM = importData.getTdNUM();
     * int lteNum = importData.getLteNum();
     * double[] totaldate = new double[3];
     * totaldate[0] = gsmNum;
     * totaldate[1] = tdNUM;
     * totaldate[2] = lteNum;
     * String[] totaldrill = new String[3];
     * totaldrill[0] = groupName + "," + "201" + "," + neLeve + "," + 1;
     * totaldrill[1] = groupName + "," + "9201" + "," + neLeve + "," + 1;
     * totaldrill[2] = groupName + "," + "8104" + "," + neLeve + "," + 1;
     * totalcs.setDrillstr(totaldrill);
     * totalcs.setName("基站总数");
     * totalcs.setData(totaldate);
     * <p>
     * <p>
     * tfSeriesList.add(tf);
     * tfSeriesList.add(totalcs);
     * <p>
     * String[] labels = new String[] { "GSM", "TD", "LTE" };
     * <p>
     * <p>
     * StringBuffer strbar = new StringBuffer();
     * <p>
     * strbar.append("<html> \n");
     * strbar.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
     * strbar.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
     * strbar.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
     * strbar.append(" <body style='height:100%;margin:0'> \n ");
     * strbar.append(" <div id='chartContainer' style='height:100%;'></div> \n");
     * <p>
     * strbar.append(" <style type='text/css'>  \n");
     * strbar.append(" div {  \n");
     * strbar.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
     * strbar.append("  }  \n");
     * strbar.append("  </style>  \n \n");
     * <p>
     * strbar.append(" <script type='text/javascript'> \n");
     * <p>
     * strbar.append(" var myChart = new FusionCharts( ");
     * strbar.append("'MSColumn2D'");
     * strbar.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");
     * <p>
     * strbar.append(" myChart.setXMLData( \n");
     * strbar.append(" \"<chart caption='");
     * strbar.append("' divLineDashLen='0' showShadow='0' showColumnShadow='1' showPlotBorder='0'  \" + \n");
     * strbar.append("\" divLineColor='d9d9d9' divLineAlpha='50'  alternateHGridAlpha='0' showAlternateHGridColor='0'   plotGradientColor='' \" + \n");
     * strbar.append(" \" showLegend='1'  legendBorderThickness='0.2' legendBorderColor='7d7c7c' legendShadow='0' plotSpacePercent='60' \" + \n");
     * strbar.append(" \" numDivLines='5' adjustDiv='0' palette='2'  canvasbgColor='d9d9d9' canvasBgAlpha='0' canvasBorderAlpha='40' canvasBorderThickness='1'  \" + \n");
     * <p>
     * strbar.append(" \"   formatnumberscale='0'  forceYAxisValueDecimals='0' yAxisValueDecimals='0' decimals='2'  setAdaptiveYMin='1'  \" + \n");
     * strbar.append(" \" useroundedges='0' bgcolor='d9d9d9' bgalpha='50' basefont='Arial' toolTipBorderColor='7d7c7c'  \" + \n");
     * strbar.append(" \" baseFontSize='15' baseFontColor='7d7c7c'  anchorradius='2' labelStep='0' \" + \n");
     * strbar.append(" \" xtlabelmanagement='0' showvalues='1' areaovercolumns='0' showpercentvalues='0' \" + \n");
     * <p>
     * StringBuffer strbarStyle = new StringBuffer();
     * strbarStyle.append(" \"<styles>\"+\n");
     * strbarStyle.append(" \"<definition>\"+\n");
     * strbarStyle
     * .append(" \"<style name='myCaptionFont' type='font' font='Arial' size='14' />\"+\n");
     * strbarStyle
     * .append(" \"<style name='myLabelsFont' type='font' font='Arial' size='13' color='868686' />\"+\n");
     * strbarStyle
     * .append(" \"<style name='myLegendFont' type='font' font='Arial' size='10' color='868686' />\"+\n");
     * strbarStyle.append(" \"</definition>\"+\n");
     * strbarStyle.append(" \"<application>\"+\n");
     * strbarStyle
     * .append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
     * strbarStyle
     * .append(" \"<apply toobject='DataLabels' styles='myLabelsFont' />\"+\n");
     * strbarStyle
     * .append(" \"<apply toobject='Legend' styles='myLegendFont' />\"+\n");
     * <p>
     * strbarStyle.append("  \"</application>\"+\n");
     * strbarStyle.append("  \"</styles>\"+\n");
     * <p>
     * String tfbar = draw.drawCustomMultiSeriesBarChart(strbar, tfSeriesList,
     * labels, null, 0, strbarStyle);
     *
     * @param str         自定义风格
     * @param seriesList  系列
     * @param labels      标签
     * @param trendlines  趋势线
     * @param isRateChart 是否是比率图， 0否、1是 如果是比率图，Y轴因子上限为100%
     * @param chartStyle  自定义样式
     * @return
     */
    String drawCustomMultiSeriesBarChart(StringBuffer str,
                                         List<ChartSeries> seriesList, String[] labels,
                                         List<ChartTrendlines> trendlines, int isRateChart,
                                         StringBuffer chartStyle);

    /**
     * 此接口用于绘制:多系列柱图(并排显示)
     *
     * @param title        标题
     * @param seriesList   系列集合
     * @param labels       x横坐标label
     * @param display_num  可以选择是否显示系列值 display_num 0否、1是.
     * @param trendlines   趋势线,,如果list为空表示没有趋势线.. value 1紫红、2紫色、3橘色、4绿、5草绿、6蓝色 默认
     *                     为0或其他值的情况.. 青绿
     * @param is2Dor3D     是否是2D还是3D图。。 注意(为2D图的时候，趋势线showOnTop属性才能生效.) 0:3D 、1:2D
     * @param isRateChart  是否是比率图， 0否、1是 如果是比率图，Y轴因子上限为100
     * @param labelDisplay x横坐标label的显示风格, 0:自动排版 1:WRAP(换行) 根据整体挤压.. 2 ROTATE 旋转 3
     *                     ROTATE slantLabels='1' 旋转倾斜. 4 Stagger 交错
     * @param labelSte     设置label的step梯步 1---n 0,1默认没有效果. 注意：系列集合里面的数据，-999
     *                     表示数据为空，图形中用空白柱子显示。。
     * @return
     */
    String drawDIYStyleMultiSeriesBarChart(StringBuffer str,
                                           List<ChartSeries> seriesList, String[] labels,
                                           List<ChartTrendlines> trendlines, int isRateChart);

    /**
     * 此接口用于绘制:单系列折线图
     *
     * @param title          标题
     * @param seriesList     系列集合
     * @param labels         x横坐标label
     * @param display_num    可以选择是否显示系列值 display_num 0否、1是.
     * @param trendlines     趋势线,,如果list为空表示没有趋势线.. value 1紫红、2紫色、3橘色、4绿、5草绿、6蓝色 默认
     *                       为0或其他值的情况.. 青绿
     * @param isSplineorline 0表示spline 1:line 使用spline连接线不能用断线表示， line连接线可以用断线表示.
     * @param isRateChart    是否是比率图， 0否、1是 如果是比率图，Y轴因子上限为100
     * @param labelDisplay   x横坐标label的显示风格, 0:自动排版 1:WRAP(换行) 根据整体挤压.. 2 ROTATE 旋转 3
     *                       ROTATE slantLabels='1' 旋转倾斜. 4 Stagger 交错
     * @param labelSte       设置label的step梯步 1---n 0,1默认没有效果. 注意：系列集合里面的数据，-999
     *                       表示数据为空，图形中用断线表示.. 是否是断线 还要以isSplineorline 属性确认.
     * @return
     */
    String drawLineChart(String title, List<ChartSeries> seriesList,
                         String[] labels, int display_num, List<ChartTrendlines> trendlines,
                         int isSplineorline, int isRateChart, int labelDisplay, int labelSte);

    /**
     * 此接口用于绘制:多系列折线图
     *
     * @param title          标题
     * @param seriesList     系列集合
     * @param labels         x横坐标label
     * @param display_num    可以选择是否显示系列值 display_num 0否、1是.
     * @param trendlines     趋势线,,如果list为空表示没有趋势线.. value 1紫红、2紫色、3橘色、4绿、5草绿、6蓝色 默认
     *                       为0或其他值的情况.. 青绿
     * @param isSplineorline 0表示spline 1:line 使用spline连接线不能用断线表示， line连接线可以用断线表示.
     * @param isRateChart    是否是比率图， 0否、1是 如果是比率图，Y轴因子上限为100
     * @param labelDisplay   x横坐标label的显示风格, 0:自动排版 1:WRAP(换行) 根据整体挤压.. 2 ROTATE 旋转 3
     *                       ROTATE slantLabels='1' 旋转倾斜. 4 Stagger 交错
     * @param labelSte       设置label的step梯步 1---n 0,1默认没有效果. 注意：系列集合里面的数据，-999
     *                       表示数据为空，图形中用断线表示.. 是否是断线 还要以isSplineorline 属性确认.
     * @return
     */
    String drawMultiSeriesLineChart(String title,
                                    List<ChartSeries> seriesList, String[] labels, int display_num,
                                    List<ChartTrendlines> trendlines, int isSplineorline,
                                    int isRateChart, int labelDisplay, int labelSte);

    /**
     * 此接口用于绘制:多系列折线图 | 可横向拖动..
     *
     * @param title          标题
     * @param seriesList     系列集合
     * @param labels         x横坐标label
     * @param display_num    可以选择是否显示系列值 display_num 0否、1是.
     * @param trendlines     趋势线,,如果list为空表示没有趋势线.. value 1紫红、2紫色、3橘色、4绿、5草绿、6蓝色 默认
     *                       为0或其他值的情况.. 青绿
     * @param isSplineorline 0表示spline 1:line 使用spline连接线不能用断线表示， line连接线可以用断线表示.
     * @param isRateChart    是否是比率图， 0否、1是 如果是比率图，Y轴因子上限为100
     * @param labelDisplay   x横坐标label的显示风格, 0:自动排版 1:WRAP(换行) 根据整体挤压.. 2 ROTATE 旋转 3
     *                       ROTATE slantLabels='1' 旋转倾斜. 4 Stagger 交错
     * @param labelSte       设置label的step梯步 1---n 0,1默认没有效果. 注意：系列集合里面的数据，-999
     *                       表示数据为空，图形中用断线表示.. 是否是断线 还要以isSplineorline 属性确认.
     * @return
     */
    String drawMultiSeriesScrollLineChart(String title,
                                          List<ChartSeries> seriesList, String[] labels, int display_num,
                                          List<ChartTrendlines> trendlines, int isRateChart,
                                          int labelDisplay, int labelSte);

    /**
     * 此接口用于绘制:多系列折线图 |这里用于特殊需求。。。
     *
     * @param title          标题
     * @param seriesList     系列集合
     * @param labels         x横坐标label
     * @param display_num    可以选择是否显示系列值 display_num 0否、1是.
     * @param trendlines     趋势线,,如果list为空表示没有趋势线.. value 1紫红、2紫色、3橘色、4绿、5草绿、6蓝色 默认
     *                       为0或其他值的情况.. 青绿
     * @param isSplineorline 0表示spline 1:line 使用spline连接线不能用断线表示， line连接线可以用断线表示.
     * @param isRateChart    是否是比率图， 0否、1是 如果是比率图，Y轴因子上限为100
     * @param labelDisplay   x横坐标label的显示风格, 0:自动排版 1:WRAP(换行) 根据整体挤压.. 2 ROTATE 旋转 3
     *                       ROTATE slantLabels='1' 旋转倾斜. 4 Stagger 交错
     * @param labelSte       设置label的step梯步 1---n 0,1默认没有效果. 注意：系列集合里面的数据，-999
     *                       表示数据为空，图形中用断线表示.. 是否是断线 还要以isSplineorline 属性确认.
     * @return
     */
    String drawMultiSeriesLineChartofTool(String title,
                                          List<ChartSeries> seriesList, String[] labels, int display_num,
                                          List<ChartTrendlines> trendlines, int isSplineorline,
                                          int isRateChart, int labelDisplay, int labelSte);

    /**
     * 此接口用于绘制:饼图
     *
     * @param title      标题
     * @param seriesList 系列集合
     * @param labels     系列label
     * @param isdrill    如果是null 就不去加link 非空加link
     * @return
     */
    String drawPieChart(String title, List<ChartSeries> seriesList,
                        String[] labels, String isdrill);

    /**
     * 此接口用于绘制:饼图 //用于自定义样式 使用例子
     * <p>
     * <p>
     * <p>
     * IChartDraw draw = new ChartDraw();
     * <p>
     * String[] returnStr = new String[2];
     * <p>
     * String[] labels = new String[] { "GSM", "TD", "LTE" };
     * <p>
     * // 退服
     * PieCustomChartSeries tfpcs = new PieCustomChartSeries();
     * String[] tfcolors = new String[] { "e85738", "f3a49b", "cd1f15" };
     * <p>
     * int faultGSMNum = importData.getFaultGSMNum();
     * int faultTDNum = importData.getFaultTDNum();
     * int faultLteNum = importData.getFaultLteNum();
     * double[] tfdate = new double[3];
     * tfdate[0] = faultGSMNum;
     * tfdate[1] = faultTDNum;
     * tfdate[2] = faultLteNum;
     * String[] tfdrill = new String[3];
     * tfdrill[0] = groupName + "," + "201" + "," + neLeve + "," + 2;
     * tfdrill[1] = groupName + "," + "9201" + "," + neLeve + "," + 2;
     * tfdrill[2] = groupName + "," + "8104" + "," + neLeve + "," + 2;
     * tfpcs.setColor(tfcolors);
     * tfpcs.setData(tfdate);
     * tfpcs.setDrillstr(tfdrill);
     * <p>
     * <p>
     * <p>
     * StringBuffer str = new StringBuffer();
     * str.append("<html> \n");
     * str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
     * str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
     * str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
     * str.append(" <body style='height:100%;margin:0'> \n ");
     * str.append(" <div id='chartContainer' style='height:100%;'></div> \n");
     * str.append(" <style type='text/css'>  \n");
     * str.append(" div {  \n");
     * str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
     * str.append("  }  \n");
     * str.append("  </style>  \n \n");
     * str.append(" <script type='text/javascript'> \n");
     * str.append(" var myChart = new FusionCharts( 'Doughnut2D', 'myChartId', '100%', '100%', '0', '1'); \n ");
     * str.append(" myChart.setXMLData( \n");
     * str.append("\"<chart caption='' formatNumber='0' formatnumberscale='0'  showShadow='0'  \" + \n  ");
     * str.append("\" pieradius='70' useroundedges='0' bgcolor='d9d9d9' bgalpha='50'  \" + \n  ");
     * str.append("\"enableSmartLabels='0' smartLineThickness='1' showpercentvalues='1' smartLineColor='7d7c7c' isSmartLineSlanted='0'  \" + \n  ");
     * str.append(" \" showvalues='0' showlabels='1' showlegend='1' legendposition='BOTTOM'   \" + \n");
     * str.append(" \" legendBorderThickness='0.2' legendBorderColor='7d7c7c' legendShadow='0'   \" + \n");
     * str.append(" \" basefont='Arial' use3DLighting='0'  baseFontSize='14' baseFontColor='7d7c7c'  > \" + \n");
     * <p>
     * StringBuffer strStyle = new StringBuffer();
     * strStyle.append(" \"<styles>\"+\n");
     * strStyle.append(" \"<definition>\"+\n");
     * strStyle.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
     * strStyle.append(" \"<style name='myLegendFont' type='font' font='Arial' size='10' color='868686' />\"+\n");
     * strStyle.append(" \"</definition>\"+\n");
     * strStyle.append(" \"<application>\"+\n");
     * strStyle.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
     * strStyle.append(" \"<apply toobject='Legend' styles='myLegendFont' />\"+\n");
     * strStyle.append("  \"</application>\"+\n");
     * strStyle.append("  \"</styles>\"+\n");
     * <p>
     * String tfpie = draw.drawCustomPieChart(str, tfpcs, labels, strStyle);
     *
     * @param str        自定义风格
     * @param seriesList 系列
     * @param labels     标签
     * @param colors     值颜色
     * @param chartStyle 自定义样式
     * @return
     */
    String drawCustomPieChart(StringBuffer str,
                              PieCustomChartSeries series, String[] labels, StringBuffer chartStyle);

    /**
     * 此接口可以绘制会有的图形..
     *
     * @param chartType      1单系列柱图、2堆积图、3多系列柱图(横向)、4单系列折线图、5多系列折线图、6饼图
     * @param title          标题
     * @param seriesList     系列集合
     * @param lables         x横坐标label
     * @param display_num    单系列柱图固定显示值， 堆积图，可以选择是否显示 display_num 0否、1是.
     * @param trendlines     趋势线,,如果list为空表示没有趋势线.. value 1紫红、2紫色、3橘色、4绿、5草绿、6蓝色 默认
     *                       为0或其他值的情况.. 青绿
     * @param is2Dor3D       是否是2D还是3D图。。 注意(为2D图的时候，趋势线showOnTop属性才能生效.) 0:3D 、1:2D
     * @param isRateChart    是否是比率图， 0否、1是 如果是比率图，Y轴因子上限为100
     * @param labelDisplay   x横坐标label的显示风格, 0:自动排版 1:WRAP(换行) 根据整体挤压.. 2 ROTATE 旋转 3
     *                       ROTATE slantLabels='1' 旋转倾斜. 4 Stagger 交错
     * @param labelSte       设置label的step梯步 1---n 0,1默认没有效果.
     * @param isSplineorline 0表示spline 1:line 使用spline连接线不能用断线表示， line连接线可以用断线表示.
     * @param isdrill        如果是null 就不去加link 非空加link 注意：系列集合里面的数据，-999
     *                       表示数据为空，在柱图中用空白柱子显示。。 在线图中用断线表示.. 是否是断线 还要以isSplineorline 属性确认.
     * @return
     */
    String drawAllChart(int chartType, String title,
                        List<ChartSeries> seriesList, String[] labels, int display_num,
                        List<ChartTrendlines> trendlines, int is2Dor3D, int isRateChart,
                        int labelDisplay, int labelSte, int isSplineorline, String isdrill);

    /**
     * 得到折线线或柱子颜色
     *
     * @return
     */
    String[] getColorDatas();

    /**
     * 得到折线圆点颜色
     *
     * @return
     */
    String[] getPointColorDatas();

    /**
     * 趋势线的颜色..
     *
     * @param colorState
     * @return
     */
    String getTrendlinesColor(int colorState);

    /**
     * 根据系列数据，得到Y轴上限值，一般用于单系列.
     *
     * @param seriesList
     * @return
     */
    int getYAxisMaxValue(List<ChartSeries> seriesList,
                         List<ChartTrendlines> trendlines);


    /**
     * 用于自定义图形
     *
     * @param seriesList
     * @return
     */
    double getCustomBarYAxisMaxValue(List<ChartSeries> seriesList,
                                     List<ChartTrendlines> trendlines);

    /**
     * 根据系列数据，得到Y轴下限值,一般用于单系列. 由于有趋势线的问题..这里还要另加判断
     *
     * @param seriesList
     * @return
     */
    int getYAxisMinValue(List<ChartSeries> seriesList,
                         List<ChartTrendlines> trendlines);

    /**
     * 用于自定义图形
     *
     * @param seriesList
     * @return
     */
    double getCustomBarYAxisMinValue(List<ChartSeries> seriesList,
                                     List<ChartTrendlines> trendlines);

    /**
     * 得到Y轴上限下限标准值（整数）.. 传入的数据如 1243223转换后 1200000 .. 从百位数开始转换..小于百位数调用
     * 此方法返回原始值.. 注意由于调用此方法之前，参数已经经过算法+10% 所以这里只取前两位没有问题.
     *
     * @param number
     * @return
     */
    int getFinalNum(int number);

}
