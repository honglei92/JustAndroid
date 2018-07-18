package fusioncharts.draw;

import java.text.DecimalFormat;
import java.util.List;

public class ChartDraw implements IChartDraw {

    @Override
    /**
     * 说明,单系列柱子默认显示，data值..
     * 多系列可以选择是否显示 display_num 0否、1是.
     */
    public String drawBarChart(String title, List<ChartSeries> seriesList,
                               String[] labels, int display_num, List<ChartTrendlines> trendlines,
                               int is2Dor3D, int isRateChart, int labelDisplay, int labelSte,
                               String isdrill) {

        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");

        str.append(" var myChart = new FusionCharts( ");
        if (is2Dor3D == 1) {
            str.append("'Column2D'");
        } else {
            str.append("'Column3D'");
        }
        str.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");
        str.append(" myChart.setXMLData( \n");
        str.append(" \"<chart caption=' ");
        str.append(title);
        str.append(" ' formatNumber='0' formatnumberscale='0' setAdaptiveYMin='1'  \" + \n");
        str.append(" \" useroundedges='1' bgcolor='999999,FFFFFF' rotatevalues='1' bgalpha='50' \" + \n");
        str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000' numdivlines='3' \" + \n");

        if (display_num == 1) // 表示不显示系列data值
        {
            str.append(" \" showvalues='1' numvdivlines='22' anchorradius='2' ");
        } else {
            str.append(" \" showvalues='0' numvdivlines='22' anchorradius='2' ");
        }

        if (labelDisplay == 0) {
            // str.append(" \"      \" + \n");
        } else if (labelDisplay == 1) {
            str.append("   labeldisplay='WRAP'     ");
        } else if (labelDisplay == 2) {
            str.append("    labeldisplay='ROTATE'    ");
        } else if (labelDisplay == 3) {
            str.append("    labeldisplay='ROTATE'  slantLabels='1'  ");
        } else if (labelDisplay == 4) {
            str.append(" labeldisplay='Stagger' staggerlines='2'   ");
        }

        str.append("  labelStep=' ");
        str.append(labelSte);
        str.append("'  \" + \n ");

        // 根据系列数据定义Y轴上限值
        double yAxisMaxValue = getYAxisMaxValue(seriesList, trendlines);
        str.append(" \"  linethickness='2' palette='4' xtlabelmanagement='0' yAxisMaxValue='");
        if (isRateChart == 1) // 是100%比率图
        {
            if (yAxisMaxValue > 100) {
                yAxisMaxValue = 100;
            }
            str.append(yAxisMaxValue);
        } else {
            str.append(yAxisMaxValue);
        }

        double yAxisMinValue = getYAxisMinValue(seriesList, trendlines);
        str.append("'  yAxisMinValue='");
        str.append(yAxisMinValue);
        str.append("' >\"+\n");

        double[] datas = null; // 得到单个系列数据
        if (seriesList.size() > 0) {
            datas = seriesList.get(0).getData();
        }

        if (datas != null) {
            for (int i = 0; i < datas.length; i++) {
                String labelString = labels[i]; // 横坐标label
                DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                String number = df1.format(datas[i]); // 系列data值

                if (datas[i] == -999) {

                    str.append(" \"<set label='");
                    str.append(labelString);
                    str.append("'  />\"+\n");
                } else {

                    str.append(" \"<set label='");
                    str.append(labelString);
                    str.append("'  value='");
                    str.append(number);

                    if (Float.valueOf(number) != 0 && isdrill != null
                            && !"".equals(isdrill)) {
                        str.append("'  link=\\\"JavaScript: isJavaScriptCall=true; myJS('");
                        str.append(labelString);
                        str.append("','");
                        String changename = seriesList.get(0).getName().trim();
                        // 默认将%号替换成 @1@
                        changename = changename.replace("%", "@1@");
                        str.append(changename);
                        str.append("');\\\"  />\"+\n");

                    } else {

                        str.append("' />\"+\n");
                    }
                }

            }

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");

        // 判断是否添加阀值...
        // 只有单系列 才添加阀值...

        if (trendlines != null) {

            if (trendlines.size() > 0) {
                str.append("  \"<trendlines>\"+\n");

                for (int i = 0; i < trendlines.size(); i++) {

                    ChartTrendlines chartTrendlines = trendlines.get(i);

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(chartTrendlines.getValue()); // 系列data值

                    str.append("  \"<line startValue='");
                    str.append(number);
                    str.append("' displayValue='");
                    str.append(chartTrendlines.getName());

                    // toolText='This trend was calculated last year'
                    str.append("' toolText='");
                    str.append(replaceBr(chartTrendlines.getName()));
                    str.append(":");
                    str.append(number);
                    str.append("'  color='");
                    // 颜色确认.
                    String trendlinecolor = getTrendlinesColor(chartTrendlines
                            .getColor());
                    str.append(trendlinecolor);
                    // 为2D图时，showOnTop属性才有用.
                    str.append("' showOnTop='1'  thickness='2' valueOnRight='1' />\" +\n");
                }

            }
            // 是否在右边显示0左1右 //线的厚度
            // <line startValue="750" displayValue="Average" color="009900"
            // valueOnRight="1" thickness="1" />
            str.append("  \"</trendlines>\"+\n");

        }

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' ); \n");

        str.append(" function myJS(labelName,seriesName) { \n");
        str.append(" window.javatojs.nextChart(labelName,seriesName); \n");
        str.append(" } \n");

        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        return str.toString();
    }

    @Override
    public String drawStackedChart(String title, List<ChartSeries> seriesList,
                                   String[] labels, int display_num, int is2Dor3D, int labelDisplay,
                                   int labelSte) {

        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");

        str.append(" var myChart = new FusionCharts( ");
        if (is2Dor3D == 1) {
            str.append("'StackedColumn2D'");
        } else {
            str.append("'StackedColumn3D'");
        }
        str.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");

        str.append(" myChart.setXMLData( \n");
        str.append(" \"<chart caption='");
        str.append(title);
        str.append(" ' formatNumber='0' formatnumberscale='0' setAdaptiveYMin='1' \" + \n");
        str.append("\" useroundedges='1' bgcolor='999999,FFFFFF' bgalpha='50' basefont='Arial' \" + \n");
        str.append(" \" basefontsize='10' basefontcolor='000000' numdivlines='3' numvdivlines='22' \" + \n");
        str.append(" \" anchorradius='2'  linethickness='2' palette='4' \" + \n");
        str.append(" \" ");
        if (labelDisplay == 0) {
            // str.append(" \"      \" + \n");
        } else if (labelDisplay == 1) {
            str.append("   labeldisplay='WRAP'     ");
        } else if (labelDisplay == 2) {
            str.append("    labeldisplay='ROTATE'    ");
        } else if (labelDisplay == 3) {
            str.append("    labeldisplay='ROTATE'  slantLabels='1'  ");
        } else if (labelDisplay == 4) {
            str.append(" labeldisplay='Stagger' staggerlines='2'   ");
        }

        str.append("  labelStep=' ");
        str.append(labelSte);
        str.append("'  \" + \n ");

        if (display_num == 1) // 表示不显示系列data值
        {
            str.append(" \"  xtlabelmanagement='0' showvalues='1' areaovercolumns='0'  \"+\n");
        } else {
            str.append(" \"  xtlabelmanagement='0' showvalues='0' areaovercolumns='0'  \"+\n");
        }
        str.append("\"  showpercentvalues='0' >\"+\n");

        str.append(" \"<categories> \"+\n");
        if (labels != null) {
            for (int i = 0; i < labels.length; i++) {
                String labelString = labels[i]; // 横坐标label
                str.append(" \"<category label='");
                str.append(labelString);
                str.append("'  /> \"+\n");
            }

        }

        str.append(" \"</categories> \"+\n");

        String[] colors = this.getColorDatas();
        List<ChartSeries> seriesListop = seriesList;
        for (int i = 0; i < seriesListop.size(); i++) {
            ChartSeries serie = seriesListop.get(i);
            double[] datas = serie.getData();
            String seriesname = serie.getName();
            String color = colors[i];

            str.append("\" <dataset seriesname='");
            str.append(seriesname);
            str.append("' color='");
            str.append(color);
            str.append(" ' >\"+\n");

            if (datas != null) {
                for (int i1 = 0; i1 < datas.length; i1++) {

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(datas[i1]); // 系列data值
                    if (datas[i1] == -999) {
                        str.append(" \"<set  value='' />\"+\n ");
                    } else {
                        str.append(" \"<set  value='");
                        str.append(number);
                        str.append("'  />\"+\n");
                    }

                }
            }

            str.append(" \" </dataset> >\"+\n");

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );");
        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        return str.toString();
    }

    @Override
    public String drawSpecialStackedChartForHZC(String title,
                                                List<ChartSeries> seriesList, String[] labels, int display_num,
                                                int is2Dor3D, int labelDisplay, int labelSte, int showLegend) {

        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");

        str.append(" var myChart = new FusionCharts( ");
        if (is2Dor3D == 1) {
            str.append("'StackedColumn2D'");
        } else {
            str.append("'StackedColumn3D'");
        }
        str.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");

        str.append(" myChart.setXMLData( \n");
        str.append(" \"<chart caption='");
        str.append(title);
        str.append(" ' formatNumber='0' formatnumberscale='0' showToolTip='1' setAdaptiveYMin='1' \" + \n");
        str.append("\" useroundedges='1' bgcolor='999999,FFFFFF' bgalpha='50' basefont='Arial' \" + \n");
        str.append(" \" basefontsize='10' basefontcolor='000000' numdivlines='3' numvdivlines='22' \" + \n");
        str.append(" \" anchorradius='2'  linethickness='2' palette='4' \" + \n");
        str.append(" \" ");
        if (labelDisplay == 0) {
            // str.append(" \"      \" + \n");
        } else if (labelDisplay == 1) {
            str.append("   labeldisplay='WRAP'     ");
        } else if (labelDisplay == 2) {
            str.append("    labeldisplay='ROTATE'    ");
        } else if (labelDisplay == 3) {
            str.append("    labeldisplay='ROTATE'  slantLabels='1'  ");
        } else if (labelDisplay == 4) {
            str.append(" labeldisplay='Stagger' staggerlines='2'   ");
        }

        if (showLegend == 0) {
            str.append("    showLegend='0'    ");
        } else if (showLegend == 1) {
            str.append("    showLegend='1'    ");
        }

        str.append("  labelStep=' ");
        str.append(labelSte);
        str.append("'  \" + \n ");

        if (display_num == 1) // 表示不显示系列data值
        {
            str.append(" \"  xtlabelmanagement='0' showvalues='1' areaovercolumns='0'  \"+\n");
        } else {
            str.append(" \"  xtlabelmanagement='0' showvalues='0' areaovercolumns='0'  \"+\n");
        }
        str.append("\"  showpercentvalues='0' >\"+\n");

        str.append(" \"<categories> \"+\n");
        if (labels != null) {
            for (int i = 0; i < labels.length; i++) {
                String labelString = labels[i]; // 横坐标label
                str.append(" \"<category label='");
                str.append(labelString);
                str.append("'  /> \"+\n");
            }

        }

        str.append(" \"</categories> \"+\n");

        List<ChartSeries> seriesListop = seriesList;
        for (int i = 0; i < seriesListop.size(); i++) {
            ChartSeries serie = seriesListop.get(i);
            double[] datas = serie.getData();
            String[] drillstr = serie.getDrillstr();
            String seriesname = serie.getName();
            String color = serie.getColor();

            str.append("\" <dataset seriesname='");
            str.append(seriesname);
            if (seriesname == null || "".equals(seriesname)) {
                str.append("' alpha='0' >\"+\n ");
            } else {

                str.append("' color='");
                str.append(color);
                str.append(" ' >\"+\n");
            }

            if (datas != null) {
                for (int i1 = 0; i1 < datas.length; i1++) {

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(datas[i1]); // 系列data值
                    if (datas[i1] == -999) {
                        str.append(" \"<set  value='' />\"+\n ");
                    } else {

                        str.append(" \"<set  value='");
                        str.append(number);

                        // str.append("'  link=\\\"JavaScript: isJavaScriptCall=true; myJS(\'");
                        // str.append(drillstr[i1]);
                        // str.append("\');\\\"   />\"+\n");

                        // 用于解析ToolTip

                        String[] drillsplit = drillstr[i1].split(",");
                        StringBuffer drillstrl = new StringBuffer();
                        if (drillsplit.length > 5) // K线图
                        {
                            drillstrl.append("(" + drillsplit[0] + ")");
                            drillstrl.append("{br}");
                            drillstrl.append("日&nbsp&nbsp&nbsp&nbsp期:"
                                    + drillsplit[1]);
                            drillstrl.append("{br}");
                            drillstrl.append("性&nbsp&nbsp&nbsp&nbsp能:"
                                    + drillsplit[2]);
                            drillstrl.append("{br}");
                            drillstrl.append("最小值:" + drillsplit[3]);
                            drillstrl.append("{br}");
                            drillstrl.append("最大值:" + drillsplit[4]);
                            drillstrl.append("{br}");
                            drillstrl.append("{br}");
                            drillstrl.append("统计值:" + drillsplit[5]);
                            drillstrl.append("{br}");
                            drillstrl.append("好值占:" + drillsplit[6]);
                            drillstrl.append("{br}");
                            drillstrl.append("中值占:" + drillsplit[7]);
                            drillstrl.append("{br}");
                            drillstrl.append("差值占:" + drillsplit[8]);
                        } else {
                            drillstrl.append("(" + drillsplit[0] + ")");
                            drillstrl.append("{br}");
                            drillstrl.append("性&nbsp&nbsp&nbsp&nbsp能:"
                                    + drillsplit[1]);
                            drillstrl.append("{br}");
                            drillstrl.append("最小值:" + drillsplit[2]);
                            drillstrl.append("{br}");
                            drillstrl.append("最大值:" + drillsplit[3]);
                            drillstrl.append("{br}");
                            drillstrl.append("日&nbsp&nbsp&nbsp&nbsp期:"
                                    + drillsplit[4]);

                        }

                        str.append("'  toolText='");
                        str.append(drillstrl.toString());
                        str.append("'  />\"+\n");

                    }

                }
            }

            str.append(" \" </dataset> >\"+\n");

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");

        str.append(" myChart.render( 'chartContainer' );\n");

        // str.append(" function myJS(isdrill) { \n");
        // str.append(" window.javatojs.nextChart(isdrill); \n");
        // str.append(" } \n");

        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        return str.toString();
    }

    @Override
    public String drawMultiSeriesBarChart(String title,
                                          List<ChartSeries> seriesList, String[] labels, int display_num,
                                          List<ChartTrendlines> trendlines, int is2Dor3D, int isRateChart,
                                          int labelDisplay, int labelSte) {

        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");

        str.append(" var myChart = new FusionCharts( ");
        if (is2Dor3D == 1) {
            str.append("'MSColumn2D'");
        } else {
            str.append("'MSColumn3D'");
        }
        str.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");

        str.append(" myChart.setXMLData( \n");
        str.append(" \"<chart caption='");
        str.append(title);
        str.append(" ' formatNumber='0' formatnumberscale='0' setAdaptiveYMin='1' \" + \n");
        str.append("\" useroundedges='1' bgcolor='999999,FFFFFF' bgalpha='50' basefont='Arial' \" + \n");
        str.append(" \" basefontsize='10' basefontcolor='000000' numdivlines='3' numvdivlines='22' \" + \n");
        str.append(" \" anchorradius='2'  linethickness='2' palette='4'  ");

        if (labelDisplay == 0) {
            // str.append(" \"      \" + \n");
        } else if (labelDisplay == 1) {
            str.append("   labeldisplay='WRAP'     ");
        } else if (labelDisplay == 2) {
            str.append("    labeldisplay='ROTATE'    ");
        } else if (labelDisplay == 3) {
            str.append("    labeldisplay='ROTATE'  slantLabels='1'  ");
        } else if (labelDisplay == 4) {
            str.append(" labeldisplay='Stagger' staggerlines='2'   ");
        }

        str.append("  labelStep=' ");
        str.append(labelSte);
        str.append("'  \" + \n ");

        if (display_num == 1) // 表示不显示系列data值
        {
            str.append(" \"  xtlabelmanagement='0' showvalues='1' areaovercolumns='0'  \"+\n");
        } else {
            str.append(" \"  xtlabelmanagement='0' showvalues='0' areaovercolumns='0'  \"+\n");
        }

        // 根据系列数据定义Y轴上限值
        double yAxisMaxValue = getYAxisMaxValue(seriesList, trendlines);
        str.append(" \"  showpercentvalues='0' yAxisMaxValue='");
        if (isRateChart == 1) // 是100%比率图
        {
            if (yAxisMaxValue > 100) {
                yAxisMaxValue = 100;
            }
            str.append(yAxisMaxValue);
        } else {
            str.append(yAxisMaxValue);
        }

        double yAxisMinValue = getYAxisMinValue(seriesList, trendlines);
        str.append("'  yAxisMinValue='");
        str.append(yAxisMinValue);
        str.append("' >\"+\n");

        str.append(" \"<categories> \"+\n");
        if (labels != null) {
            for (int i = 0; i < labels.length; i++) {
                String labelString = labels[i]; // 横坐标label
                str.append(" \"<category label='");
                str.append(labelString);
                str.append("'  /> \"+\n");
            }

        }

        str.append(" \"</categories> \"+\n");

        String[] colors = this.getColorDatas();
        List<ChartSeries> seriesListop = seriesList;
        for (int i = 0; i < seriesListop.size(); i++) {
            ChartSeries serie = seriesListop.get(i);
            double[] datas = serie.getData();
            String seriesname = serie.getName();
            String color = colors[i];

            str.append("\" <dataset seriesname='");
            str.append(seriesname);
            str.append("' color='");
            str.append(color);
            str.append(" ' >\"+\n");

            if (datas != null) {
                for (int i1 = 0; i1 < datas.length; i1++) {

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(datas[i1]); // 系列data值

                    if (datas[i1] == -999) {
                        str.append(" \"<set  value='' />\"+\n ");
                    } else {
                        str.append(" \"<set  value='");
                        str.append(number);
                        str.append("'  />\"+\n");
                    }
                }
            }

            str.append(" \" </dataset> >\"+\n");

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");

        // 判断是否添加阀值...
        if (trendlines != null) {

            if (trendlines.size() > 0) {
                str.append("  \"<trendlines>\"+\n");

                for (int i = 0; i < trendlines.size(); i++) {

                    ChartTrendlines chartTrendlines = trendlines.get(i);

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(chartTrendlines.getValue()); // 系列data值

                    str.append("  \"<line startValue='");
                    str.append(number);
                    str.append("' displayValue='");
                    str.append(chartTrendlines.getName());

                    str.append("' toolText='");
                    str.append(replaceBr(chartTrendlines.getName()));
                    str.append(":");
                    str.append(number);

                    str.append("' color='");
                    // 颜色确认.
                    String trendlinecolor = getTrendlinesColor(chartTrendlines
                            .getColor());
                    str.append(trendlinecolor);
                    // 为2D图时，showOnTop属性才有用.
                    str.append("' showOnTop='1'  thickness='2' valueOnRight='1' />\" +\n");
                }

            }
            // 是否在右边显示0左1右 //线的厚度
            // <line startValue="750" displayValue="Average" color="009900"
            // valueOnRight="1" thickness="1" />
            str.append("  \"</trendlines>\"+\n");
        }

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );");
        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        return str.toString();
    }

    /**
     * str.append("<html> \n"); str.append(
     * " <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n"
     * ); str.append(
     * " <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n"
     * ); str.append(
     * " <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n"
     * ); str.append(" <body style='height:100%;margin:0'> \n ");
     * str.append(" <div id='chartContainer' style='height:100%;'></div> \n");
     * str.append(" <style type='text/css'>  \n"); str.append(" div {  \n");
     * str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
     * str.append("  }  \n"); str.append("  </style>  \n \n");
     * str.append(" <script type='text/javascript'> \n");
     * str.append(" var myChart = new FusionCharts( ");
     * str.append("'MSColumn2D'");
     * str.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");
     * <p>
     * str.append(" myChart.setXMLData( \n"); str.append(" \"<chart caption='");
     * str.append(title); str.append(
     * " ' formatNumber='0' formatnumberscale='0' setAdaptiveYMin='1' \" + \n");
     * str.append(
     * "\" useroundedges='1' bgcolor='999999,FFFFFF' bgalpha='50' basefont='Arial' \" + \n"
     * ); str.append(
     * " \" basefontsize='10' basefontcolor='000000' numdivlines='3' numvdivlines='22' \" + \n"
     * ); str.append(
     * " \" anchorradius='2'  showpercentvalues='0'  linethickness='2' palette='4'  "
     * );
     * <p>
     * if (labelDisplay == 0) { // str.append(" \"      \" + \n"); } else if
     * (labelDisplay == 1) { str.append("   labeldisplay='WRAP'     "); } else
     * if (labelDisplay == 2) { str.append("    labeldisplay='ROTATE'    "); }
     * else if (labelDisplay == 3) {
     * str.append("    labeldisplay='ROTATE'  slantLabels='1'  "); } else if
     * (labelDisplay == 4) {
     * str.append(" labeldisplay='Stagger' staggerlines='2'   "); }
     * <p>
     * str.append("  labelStep=' "); str.append(labelSte);
     * str.append("'  \" + \n ");
     * <p>
     * if (display_num == 1) // 表示不显示系列data值 { str.append(
     * " \"  xtlabelmanagement='0' showvalues='1' areaovercolumns='0'  \"+\n");
     * } else { str.append(
     * " \"  xtlabelmanagement='0' showvalues='0' areaovercolumns='0'  \"+\n");
     * }
     */

    @Override
    public String drawDIYStyleMultiSeriesBarChart(StringBuffer str,
                                                  List<ChartSeries> seriesList, String[] labels,
                                                  List<ChartTrendlines> trendlines, int isRateChart) {
        // TODO Auto-generated method stub

        // 根据系列数据定义Y轴上限值
        double yAxisMaxValue = getYAxisMaxValue(seriesList, trendlines);
        double yAxisMinValue = getYAxisMinValue(seriesList, trendlines);

        double offset = 0.0;

        if (yAxisMaxValue == yAxisMinValue) {
            offset = Math.abs(yAxisMaxValue) / 10;
            // yAxisMaxValue = yAxisMaxValue + offset;
            yAxisMinValue = yAxisMinValue - offset;
        }

        str.append(" \"  yAxisMaxValue='");
        if (isRateChart == 1) // 是100%比率图
        {
            if (yAxisMaxValue > 100) {
                yAxisMaxValue = 100;
            }
            str.append(yAxisMaxValue);
        } else {
            str.append(yAxisMaxValue);
        }

        str.append("'  yAxisMinValue='");
        str.append(yAxisMinValue);
        str.append("' >\"+\n");

        str.append(" \"<categories> \"+\n");
        if (labels != null) {
            for (int i = 0; i < labels.length; i++) {
                String labelString = labels[i]; // 横坐标label
                str.append(" \"<category label='");
                str.append(labelString);
                str.append("'  /> \"+\n");
            }

        }

        str.append(" \"</categories> \"+\n");

        String[] colors = this.getColorDatas();
        List<ChartSeries> seriesListop = seriesList;
        for (int i = 0; i < seriesListop.size(); i++) {
            ChartSeries serie = seriesListop.get(i);
            double[] datas = serie.getData();
            String seriesname = serie.getName();
            String color = serie.getColor();

            str.append("\" <dataset seriesname='");
            str.append(seriesname);
            str.append("' color='");
            str.append(color);
            str.append(" ' >\"+\n");

            if (datas != null) {
                for (int i1 = 0; i1 < datas.length; i1++) {

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(datas[i1]); // 系列data值(datas->X轴)

                    if (datas[i1] == -999) {
                        str.append(" \"<set  value='' />\"+\n ");
                    } else {
                        str.append(" \"<set  value='");
                        str.append(number);
                        str.append("'  />\"+\n");
                    }
                }
            }

            str.append(" \" </dataset> >\"+\n");

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");

        // 判断是否添加阀值...
        if (trendlines != null) {

            if (trendlines.size() > 0) {
                str.append("  \"<trendlines>\"+\n");

                for (int i = 0; i < trendlines.size(); i++) {

                    ChartTrendlines chartTrendlines = trendlines.get(i);

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(chartTrendlines.getValue()); // 系列data值

                    str.append("  \"<line startValue='");
                    str.append(number);
                    str.append("' displayValue='");
                    str.append(chartTrendlines.getName());

                    str.append("' toolText='");
                    str.append(replaceBr(chartTrendlines.getName()));
                    str.append(":");
                    str.append(number);

                    str.append("' color='");
                    // 颜色确认.
                    String trendlinecolor = getTrendlinesColor(chartTrendlines
                            .getColor());
                    str.append(trendlinecolor);
                    // 为2D图时，showOnTop属性才有用.
                    str.append("' showOnTop='1'  thickness='2' valueOnRight='1' />\" +\n");
                }

            }
            // 是否在右边显示0左1右 //线的厚度
            // <line startValue="750" displayValue="Average" color="009900"
            // valueOnRight="1" thickness="1" />
            str.append("  \"</trendlines>\"+\n");
        }

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );");
        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        return str.toString();
    }

    @Override
    public String drawLineChart(String title, List<ChartSeries> seriesList,
                                String[] labels, int display_num, List<ChartTrendlines> trendlines,
                                int isSplineorline, int isRateChart, int labelDisplay, int labelSte) {

        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");

        str.append(" var myChart = new FusionCharts( ");
        if (isSplineorline == 1) // line
        {
            str.append("'line'");
        } else// spline
        {
            str.append("'Spline'");
        }
        str.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");
        str.append(" myChart.setXMLData( \n");
        str.append("\"<chart caption='");
        str.append(title);
        str.append("' formatNumber='0'  formatnumberscale='0' setAdaptiveYMin='1'  \" + \n");
        str.append("\" useroundedges='1' bgcolor='999999,FFFFFF'  bgalpha='50'   \" + \n");

        if (display_num == 1) // 表示不显示系列data值
        {
            str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000' showvalues='1'  \" + \n");
        } else {
            str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000' showvalues='0'  \" + \n");
        }

        str.append(" \"  divlineisdashed='1' showalternatehgridcolor='0' shadowalpha='90'   \" + \n");
        str.append(" \"  numvdivlines='5' linethickness='2'  anchorradius='2'  ");

        if (labelDisplay == 0) {
            // str.append(" \"      \" + \n");
        } else if (labelDisplay == 1) {
            str.append("   labeldisplay='WRAP'     ");
        } else if (labelDisplay == 2) {
            str.append("    labeldisplay='ROTATE'    ");
        } else if (labelDisplay == 3) {
            str.append("    labeldisplay='ROTATE'  slantLabels='1'  ");
        } else if (labelDisplay == 4) {
            str.append(" labeldisplay='Stagger' staggerlines='2'   ");
        }

        str.append("  labelStep=' ");
        str.append(labelSte);
        str.append("'  \" + \n ");

        str.append(" \"   canvasbgcolor='c0c0c0,F0F8FF' canvasbasedepth='50' canvasbgdepth='90' linecolor='1D8BD1'    \" + \n");
        str.append(" \"   anchorbordercolor='BA55D3' anchorbgcolor='BA55D3'    \" + \n");

        // 根据系列数据定义Y轴上限值
        double yAxisMaxValue = getYAxisMaxValue(seriesList, trendlines);
        str.append(" \"  yAxisMaxValue='");

        if (isRateChart == 1) // 是100%比率图
        {
            if (yAxisMaxValue > 100) {
                yAxisMaxValue = 100;
            }
            str.append(yAxisMaxValue);
        } else {
            str.append(yAxisMaxValue);
        }

        double yAxisMinValue = getYAxisMinValue(seriesList, trendlines);
        str.append("'  yAxisMinValue='");
        str.append(yAxisMinValue);
        str.append("' >\"+\n");

        double[] datas = null; // 得到单个系列数据
        if (seriesList.size() > 0) {
            datas = seriesList.get(0).getData();
        }

        if (datas != null) {
            for (int i = 0; i < datas.length; i++) {
                String labelString = labels[i]; // 横坐标label
                DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                String number = df1.format(datas[i]); // 系列data值

                if (datas[i] == -999 && isSplineorline == 1) // 表示值为空.
                {
                    str.append(" \"<set label='");
                    str.append(labelString);
                    str.append("'  />\"+\n");
                } else {

                    if (datas[i] == -999) {
                        number = "0.00";
                    }

                    str.append(" \"<set label='");
                    str.append(labelString);
                    str.append("'  value='");
                    str.append(number);
                    str.append("'  />\"+\n");
                }

            }

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");

        // 判断是否添加阀值...
        if (trendlines != null) {

            if (trendlines.size() > 0) {
                str.append("  \"<trendlines>\"+\n");

                for (int i = 0; i < trendlines.size(); i++) {

                    ChartTrendlines chartTrendlines = trendlines.get(i);

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(chartTrendlines.getValue()); // 系列data值

                    str.append("  \"<line startValue='");
                    str.append(number);
                    str.append("' displayValue='");
                    str.append(chartTrendlines.getName());

                    str.append("' toolText='");
                    str.append(replaceBr(chartTrendlines.getName()));
                    str.append(":");
                    str.append(number);

                    str.append("' color='");
                    // 颜色确认.
                    String trendlinecolor = getTrendlinesColor(chartTrendlines
                            .getColor());
                    str.append(trendlinecolor);
                    // 为2D图时，showOnTop属性才有用.
                    str.append("' showOnTop='1'  thickness='2' valueOnRight='1' />\" +\n");
                }

            }
            // 是否在右边显示0左1右 //线的厚度
            // <line startValue="750" displayValue="Average" color="009900"
            // valueOnRight="1" thickness="1" />
            str.append("  \"</trendlines>\"+\n");
        }

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );");
        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        // TODO Auto-generated method stub
        return str.toString();
    }

    @Override
    public String drawMultiSeriesLineChart(String title,
                                           List<ChartSeries> seriesList, String[] labels, int display_num,
                                           List<ChartTrendlines> trendlines, int isSplineorline,
                                           int isRateChart, int labelDisplay, int labelSte) {

        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");

        str.append(" var myChart = new FusionCharts( ");

        if (isSplineorline == 1) // line
        {
            str.append("'MSLine'");
        } else// spline
        {
            str.append("'MSSpline'");
        }
        str.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");

        str.append(" myChart.setXMLData( \n");
        str.append("\"<chart caption='");
        str.append(title);
        str.append("' formatNumber='0'  formatnumberscale='0' setAdaptiveYMin='1'  \" + \n");
        str.append("\" useroundedges='1' bgcolor='999999,FFFFFF'  bgalpha='50'   \" + \n");

        if (display_num == 1) // 表示不显示系列data值
        {
            str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000' showvalues='1'  \" + \n");
        } else {
            str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000' showvalues='0'  \" + \n");
        }

        str.append(" \"  divlineisdashed='1' showalternatehgridcolor='0' shadowalpha='90'   \" + \n");
        str.append(" \"  numvdivlines='5' linethickness='2'  anchorradius='2'  ");
        if (labelDisplay == 0) {
            // str.append(" \"      \" + \n");
        } else if (labelDisplay == 1) {
            str.append("   labeldisplay='WRAP'     ");
        } else if (labelDisplay == 2) {
            str.append("    labeldisplay='ROTATE'    ");
        } else if (labelDisplay == 3) {
            str.append("    labeldisplay='ROTATE'  slantLabels='1'  ");
        } else if (labelDisplay == 4) {
            str.append(" labeldisplay='Stagger' staggerlines='2'   ");
        }

        str.append("  labelStep=' ");
        str.append(labelSte);
        str.append("'  \" + \n ");

        str.append(" \"   canvasbgcolor='c0c0c0,F0F8FF' canvasbasedepth='50' canvasbgdepth='90'  showtooltip='0'    \" + \n");

        // 根据系列数据定义Y轴上限值
        double yAxisMaxValue = getYAxisMaxValue(seriesList, trendlines);
        str.append(" \"  yAxisMaxValue='");

        if (isRateChart == 1) // 是100%比率图
        {
            if (yAxisMaxValue > 100) {
                yAxisMaxValue = 100;
            }
            str.append(yAxisMaxValue);
        } else {
            str.append(yAxisMaxValue);
        }

        double yAxisMinValue = getYAxisMinValue(seriesList, trendlines);
        str.append("'  yAxisMinValue='");
        str.append(yAxisMinValue);
        str.append("' >\"+\n");

        str.append(" \"<categories> \"+\n");
        if (labels != null) {
            for (int i = 0; i < labels.length; i++) {
                String labelString = labels[i]; // 横坐标label
                str.append(" \"<category label='");
                str.append(labelString);
                str.append("'  /> \"+\n");
            }

        }
        str.append(" \"</categories> \"+\n");

        String[] colors = this.getColorDatas();
        String[] pointColors = this.getPointColorDatas();

        List<ChartSeries> seriesListop = seriesList;
        for (int i = 0; i < seriesListop.size(); i++) {
            ChartSeries serie = seriesListop.get(i);
            double[] datas = serie.getData();
            String seriesname = serie.getName();
            String color = colors[i];
            String pointcolor = pointColors[i];

            str.append("\" <dataset seriesname='");
            str.append(seriesname);
            str.append("' color='");
            str.append(color);
            str.append("' anchorbordercolor='");
            str.append(pointcolor);
            str.append("' anchorbgcolor='");
            str.append(pointcolor);
            str.append("' >\"+\n");

            if (datas != null) {
                for (int a = 0; a < datas.length; a++) {

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(datas[a]); // 系列data值

                    if (datas[a] == -999 && isSplineorline == 1) // 表示值为空.
                    {
                        str.append(" \"<set value='' />\"+\n ");

                    } else {

                        if (datas[a] == -999) {
                            number = "0.00";
                        }

                        str.append(" \"<set");

                        str.append("  value='");
                        str.append(number);
                        str.append("'  />\"+\n");
                    }

                }

            }

            str.append(" \" </dataset> >\"+\n");

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");

        // 判断是否添加阀值...
        if (trendlines != null) {

            if (trendlines.size() > 0) {
                str.append("  \"<trendlines>\"+\n");

                for (int i = 0; i < trendlines.size(); i++) {

                    ChartTrendlines chartTrendlines = trendlines.get(i);

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(chartTrendlines.getValue()); // 系列data值

                    str.append("  \"<line startValue='");
                    str.append(number);
                    str.append("' displayValue='");
                    str.append(chartTrendlines.getName());

                    str.append("' toolText='");
                    str.append(replaceBr(chartTrendlines.getName()));
                    str.append(":");
                    str.append(number);

                    str.append("' color='");
                    // 颜色确认.
                    String trendlinecolor = getTrendlinesColor(chartTrendlines
                            .getColor());
                    str.append(trendlinecolor);
                    // 为2D图时，showOnTop属性才有用.
                    str.append("' showOnTop='1'  thickness='2' valueOnRight='1' />\" +\n");
                }

            }
            // 是否在右边显示0左1右 //线的厚度
            // <line startValue="750" displayValue="Average" color="009900"
            // valueOnRight="1" thickness="1" />
            str.append("  \"</trendlines>\"+\n");
        }

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );");
        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        // TODO Auto-generated method stub
        return str.toString();

    }

    @Override
    public String drawPieChart(String title, List<ChartSeries> seriesList,
                               String[] labels, String isdrill) {

        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");
        str.append(" var myChart = new FusionCharts( 'Pie3D', 'myChartId', '100%', '100%', '0', '1'); \n ");
        str.append(" myChart.setXMLData( \n");
        str.append("\"<chart caption='");
        str.append(title);
        str.append("'  formatNumber='0' formatnumberscale='0' \" + \n ");
        str.append(" \"showvalues='0' showlabels='0' showlegend='1' legendposition='BOTTOM'\" + \n");

        str.append(" \" pieradius='110' useroundedges='1' bgcolor='999999,FFFFFF' bgalpha='50'\"+\n");

        str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000'  numdivlines='4'\"+\n ");
        str.append(" \"  numvdivlines='22' >\"+\n  ");

        double[] datas = null; // 得到单个系列数据
        if (seriesList.size() > 0) {
            datas = seriesList.get(0).getData();
        }

        if (datas != null) {
            for (int i = 0; i < datas.length; i++) {
                String labelString = labels[i]; // 横坐标label
                DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                String number = df1.format(datas[i]); // 系列data值

                if (datas[i] == -999) { // 代表空值

                    str.append(" \"<set label='");
                    str.append(labelString);
                    str.append("'  />\"+\n");
                } else {
                    str.append(" \"<set label='");
                    str.append(labelString);
                    str.append("(");
                    str.append(number);
                    str.append(")' ");
                    str.append(" value='");
                    str.append(number);
                    str.append("' tooltext='");
                    str.append(labelString);
                    str.append("(");
                    str.append(number);
                    str.append(")'  ");

                    if (Float.valueOf(number) != 0 && isdrill != null
                            && !"".equals(isdrill)) {
                        str.append("  link='JavaScript: isJavaScriptCall=true; myJS(\"");
                        str.append(isdrill);
                        str.append("\"' );  />\"+\n");
                    } else {

                        str.append(" />\"+\n");
                    }

                }

            }

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");
        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );\n");

        str.append(" function myJS(isdrill) { \n");
        str.append(" window.javatojs.nextChart(isdrill); \n");
        str.append(" } \n");

        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        return str.toString();
    }

    @Override
    public String[] getColorDatas() {
        // TODO Auto-generated method stub
        String[] colors = new String[10];
        colors[0] = "1D8BD1";
        colors[1] = "FFFF00";
        colors[2] = "DA70D6";
        colors[3] = "78AE1C";
        colors[4] = "BFDFFF";
        colors[5] = "802040";
        colors[6] = "60FFFF";
        colors[7] = "404080";
        colors[8] = "FF8000";
        colors[9] = "A0A020";

        return colors;
    }

    @Override
    public String getTrendlinesColor(int colorState) {

        String curColor = "";
        switch (colorState) {
            case 1:
                curColor = "FF0080"; // 紫红
                break;
            case 2:
                curColor = "800080"; // 紫色
                break;
            case 3:
                curColor = "FF8040"; // 橘色
                break;

            case 4:
                curColor = "40E944"; // 绿
                break;

            case 5:
                curColor = "008040"; // 草绿
                break;
            case 6:
                curColor = "0080C0"; // 蓝色
                break;
            default:
                curColor = "7FD9CB"; // 青绿

        }

        return curColor;
    }

    @Override
    public int getYAxisMaxValue(List<ChartSeries> seriesList,
                                List<ChartTrendlines> trendlines) {
        // TODO Auto-generated method stub
//        double[] numbersbar = new double[]{};
//        for (int i = 0; i < seriesList.size(); i++) {
//
//            ChartSeries serie = seriesList.get(i);
//            double[] serienums = serie.getData();
//
//            // 整合数组
//            numbersbar = ArrayUtils.addAll(numbersbar, serienums);
//        }
//
//        if (trendlines != null) {
//            double[] trendnumbers = new double[trendlines.size()];
//            for (int i = 0; i < trendlines.size(); i++) {
//                ChartTrendlines chartTrendlines = trendlines.get(i);
//                trendnumbers[i] = chartTrendlines.getValue();
//            }
//
//            numbersbar = ArrayUtils.addAll(numbersbar, trendnumbers);
//
//        }
//
//        double[] numbers = new double[numbersbar.length];
//
//        System.arraycopy(numbersbar, 0, numbers, 0, numbersbar.length);
//
//        // 得到所需组数大小
//        int arraySize = 0;
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] != -999) {
//                arraySize += 1;
//            }
//        }
//
//        double[] newnumbers = new double[arraySize];
//        int a = 0;// 标示
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] != -999) {
//                newnumbers[a] = numbers[i];
//                a++;
//            }
//        }
//
//        SortUtil.sort(newnumbers);
//
//        Double x = Double
//                .valueOf((newnumbers[0] - newnumbers[newnumbers.length - 1]) / 10);
//
//        // Double xtm = Math.abs(x);
//        // if (xtm < 1) {
//        // x = 1.0;
//        // }
//
//        double curnumber = newnumbers[0] + x;
//
//        int renumber = (int) curnumber;
//
//        //renumber = getFinalNum(renumber);
//
//        // // 判断是否有负数的情况，如果没有Y轴最小值为>=0 反之可以<0
//        //
//        int isgtorltZero = 0; // 0 都大于0 1有值小于0
//        for (int i = 0; i < newnumbers.length; i++) {
//            if (newnumbers[i] < 0) {
//                isgtorltZero = 1;
//                break;
//            }
//        }
//
//        if (isgtorltZero == 0) {
//            if (renumber < 0) {
//                renumber = 0;
//            }
//        }
//        return renumber;
        return 0;
    }

    @Override
    public int getYAxisMinValue(List<ChartSeries> seriesList,
                                List<ChartTrendlines> trendlines) {
        // TODO Auto-generated method stub

//        double[] numbersbar = new double[]{};
//        for (int i = 0; i < seriesList.size(); i++) {
//
//            ChartSeries serie = seriesList.get(i);
//            double[] serienums = serie.getData();
//            // 整合数组
//            numbersbar = ArrayUtils.addAll(numbersbar, serienums);
//        }
//        if (trendlines != null) {
//            double[] trendnumbers = new double[trendlines.size()];
//            for (int i = 0; i < trendlines.size(); i++) {
//                ChartTrendlines chartTrendlines = trendlines.get(i);
//                trendnumbers[i] = chartTrendlines.getValue();
//            }
//
//            numbersbar = ArrayUtils.addAll(numbersbar, trendnumbers);
//
//        }
//
//        double[] numbers = new double[numbersbar.length];
//
//        System.arraycopy(numbersbar, 0, numbers, 0, numbersbar.length);
//
//        // 得到所需组数大小
//        int arraySize = 0;
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] != -999) {
//                arraySize += 1;
//            }
//        }
//
//        double[] newnumbers = new double[arraySize];
//
//        int a = 0;// 标示
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] != -999) {
//                newnumbers[a] = numbers[i];
//                a++;
//            }
//        }
//
//        SortUtil.sortMin(newnumbers);
//
//        Double x = Double
//                .valueOf((newnumbers[0] - newnumbers[newnumbers.length - 1]) / 10);
//
//        // Double xtm = Math.abs(x);
//        // if (xtm < 1) {
//        // x = -1.0;
//        // }
//
//        double curnumber = newnumbers[0] + x;
//
//        int renumber = (int) curnumber;
//
//        //renumber = getFinalNum(renumber);
//
//        // 判断是否有负数的情况，如果没有Y轴最小值为>=0 反之可以<0
//
//        int isgtorltZero = 0; // 0 都大于0 1有值小于0
//        for (int i = 0; i < newnumbers.length; i++) {
//            if (newnumbers[i] < 0) {
//                isgtorltZero = 1;
//                break;
//            }
//        }
//
//        if (isgtorltZero == 0) {
//            if (renumber < 0) {
//                renumber = 0;
//            }
//        }
//        return renumber;
        return 0;
    }

    // 补0
    private String addZero(String str, int num) {
        int number = num - 2;

        StringBuffer sb = new StringBuffer(str);

        for (int i = 0; i < number; i++) {
            sb.append("0");
        }

        return sb.toString();
    }

    // 得到上限下限标准值..
    public int getFinalNum(int number) {

        String s = String.valueOf(number);

        int t = s.length();

        // 判断位数

        int finalNumber = number;

        if (t > 2) {

            String s1 = String.valueOf(number);
            s1 = s1.substring(0, 2);

            String finalStr = addZero(s1, t);

            finalNumber = Integer.parseInt(finalStr);

        }

        return finalNumber;

    }

    @Override
    public String[] getPointColorDatas() {

        String[] colors = new String[10];
        colors[0] = "BA55D3";
        colors[1] = "00FA9A";
        colors[2] = "FFCC00";
        colors[3] = "60FFFF";
        colors[4] = "802040";
        colors[5] = "BFDFFF";
        colors[6] = "78AE1C";
        colors[7] = "015887";
        colors[8] = "48D1CC";
        colors[9] = "9370DB";

        return colors;
    }

    @Override
    public String drawAllChart(int chartType, String title,
                               List<ChartSeries> seriesList, String[] labels, int display_num,
                               List<ChartTrendlines> trendlines, int is2Dor3D, int isRateChart,
                               int labelDisplay, int labelSte, int isSplineorline, String isdrill) {

        String chartStr = "";
        switch (chartType) {
            case 1:
                chartStr = this.drawBarChart(title, seriesList, labels,
                        display_num, trendlines, is2Dor3D, isRateChart,
                        labelDisplay, labelSte, isdrill);
                break;
            case 2:
                chartStr = this.drawStackedChart(title, seriesList, labels,
                        display_num, is2Dor3D, labelDisplay, labelSte);
                break;
            case 3:
                chartStr = this.drawMultiSeriesBarChart(title, seriesList, labels,
                        display_num, trendlines, is2Dor3D, isRateChart,
                        labelDisplay, labelSte);
                break;
            case 4:
                chartStr = this.drawLineChart(title, seriesList, labels,
                        display_num, trendlines, isSplineorline, isRateChart,
                        labelDisplay, labelSte);
                break;

            case 5:
                chartStr = this.drawMultiSeriesLineChart(title, seriesList, labels,
                        display_num, trendlines, isSplineorline, isRateChart,
                        labelDisplay, labelSte);
                break;
            case 6:
                chartStr = this.drawPieChart(title, seriesList, labels, isdrill);

            default:
                break;
        }

        return chartStr;
    }

    private String replaceBr(String str) {
        String repstr = str;
        repstr = repstr.replace("{br}", "");

        return repstr;
    }

    @Override
    public String drawMultiSeriesScrollLineChart(String title,
                                                 List<ChartSeries> seriesList, String[] labels, int display_num,
                                                 List<ChartTrendlines> trendlines, int isRateChart,
                                                 int labelDisplay, int labelSte) {

        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");

        str.append(" var myChart = new FusionCharts( ");

        str.append("'ScrollLine2D'");

        str.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");

        str.append(" myChart.setXMLData( \n");
        str.append("\"<chart caption='");
        str.append(title);
        str.append("' formatNumber='0'  formatnumberscale='0' setAdaptiveYMin='1'  \" + \n");
        str.append("\" useroundedges='1' bgcolor='999999,FFFFFF'  bgalpha='50'   \" + \n");

        if (display_num == 1) // 表示不显示系列data值
        {
            str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000' showvalues='1'  \" + \n");
        } else {
            str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000' showvalues='0'  \" + \n");
        }

        str.append(" \"  divlineisdashed='1' showalternatehgridcolor='0' shadowalpha='90'   \" + \n");
        str.append(" \"  numvdivlines='5' linethickness='4'  anchorradius='6'  anchorBgAlpha='45' numVDivLines='24' animation='0' ");
        if (labelDisplay == 0) {
            // str.append(" \"      \" + \n");
        } else if (labelDisplay == 1) {
            str.append("   labeldisplay='WRAP'     ");
        } else if (labelDisplay == 2) {
            str.append("    labeldisplay='ROTATE'    ");
        } else if (labelDisplay == 3) {
            str.append("    labeldisplay='ROTATE'  slantLabels='1'  ");
        } else if (labelDisplay == 4) {
            str.append(" labeldisplay='Stagger' staggerlines='2'   ");
        }

        str.append("  labelStep=' ");
        str.append(labelSte);
        str.append("'  \" + \n ");

        str.append(" \"   canvasbgcolor='c0c0c0,F0F8FF' canvasbasedepth='50' canvasbgdepth='90'  showtooltip='0'    \" + \n");

        // 根据系列数据定义Y轴上限值
        double yAxisMaxValue = getYAxisMaxValue(seriesList, trendlines);
        str.append(" \"  yAxisMaxValue='");

        if (isRateChart == 1) // 是100%比率图
        {
            if (yAxisMaxValue > 100) {
                yAxisMaxValue = 100;
            }
            str.append(yAxisMaxValue);
        } else {
            str.append(yAxisMaxValue);
        }

        double yAxisMinValue = getYAxisMinValue(seriesList, trendlines);
        str.append("'  yAxisMinValue='");
        str.append(yAxisMinValue);
        str.append("' >\"+\n");

        str.append(" \"<categories> \"+\n");
        if (labels != null) {
            for (int i = 0; i < labels.length; i++) {
                String labelString = labels[i]; // 横坐标label
                str.append(" \"<category label='");
                str.append(labelString);
                str.append("'  /> \"+\n");
            }

        }
        str.append(" \"</categories> \"+\n");

        String[] colors = this.getColorDatas();
        String[] pointColors = this.getPointColorDatas();

        List<ChartSeries> seriesListop = seriesList;
        for (int i = 0; i < seriesListop.size(); i++) {
            ChartSeries serie = seriesListop.get(i);
            double[] datas = serie.getData();
            String[] drillstr = serie.getDrillstr();
            String seriesname = serie.getName();
            String color = colors[i];
            String pointcolor = pointColors[i];

            str.append("\" <dataset seriesname='");
            str.append(seriesname);
            str.append("' color='");
            str.append(color);
            str.append("' anchorbordercolor='");
            str.append(pointcolor);
            str.append("' anchorbgcolor='");
            str.append(pointcolor);
            str.append("' >\"+\n");

            if (datas != null) {
                for (int a = 0; a < datas.length; a++) {

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(datas[a]); // 系列data值

                    if (datas[a] == -999) // 表示值为空.
                    {
                        str.append(" \"<set value='' />\"+\n ");

                    } else {

                        if (datas[a] == -999) {
                            number = "0.00";
                        }

                        str.append(" \"<set");

                        str.append("  value='");
                        str.append(number);

                        str.append("'  link=\\\"JavaScript: isJavaScriptCall=true; myJS(\'");
                        str.append(drillstr[a]);
                        str.append("\');\\\"   />\"+\n");

                    }

                }

            }

            str.append(" \" </dataset> >\"+\n");

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");

        // 判断是否添加阀值...
        if (trendlines != null) {

            if (trendlines.size() > 0) {
                str.append("  \"<trendlines>\"+\n");

                for (int i = 0; i < trendlines.size(); i++) {

                    ChartTrendlines chartTrendlines = trendlines.get(i);

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(chartTrendlines.getValue()); // 系列data值

                    str.append("  \"<line startValue='");
                    str.append(number);
                    str.append("' displayValue='");
                    str.append(chartTrendlines.getName());

                    str.append("' toolText='");
                    str.append(replaceBr(chartTrendlines.getName()));
                    str.append(":");
                    str.append(number);

                    str.append("' color='");
                    // 颜色确认.
                    String trendlinecolor = getTrendlinesColor(chartTrendlines
                            .getColor());
                    str.append(trendlinecolor);
                    // 为2D图时，showOnTop属性才有用.
                    str.append("' showOnTop='1'  thickness='2' valueOnRight='1' />\" +\n");
                }

            }
            // 是否在右边显示0左1右 //线的厚度
            // <line startValue="750" displayValue="Average" color="009900"
            // valueOnRight="1" thickness="1" />
            str.append("  \"</trendlines>\"+\n");
        }

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );\n");

        str.append(" function myJS(isdrill) { \n");
        str.append(" window.javatojs.nextChart(isdrill); \n");
        str.append(" } \n");

        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        // TODO Auto-generated method stub
        return str.toString();

    }

    @Override
    public String drawMultiSeriesLineChartofTool(String title,
                                                 List<ChartSeries> seriesList, String[] labels, int display_num,
                                                 List<ChartTrendlines> trendlines, int isSplineorline,
                                                 int isRateChart, int labelDisplay, int labelSte) {
        // TODO Auto-generated method stub

        StringBuffer str = new StringBuffer();
        str.append("<html> \n");
        str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
        str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
        str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
        str.append(" <body style='height:100%;margin:0'> \n ");
        str.append(" <div id='chartContainer' style='height:100%;'></div> \n");

        str.append(" <style type='text/css'>  \n");
        str.append(" div {  \n");
        str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
        str.append("  }  \n");
        str.append("  </style>  \n \n");

        str.append(" <script type='text/javascript'> \n");

        str.append(" var myChart = new FusionCharts( ");

        if (isSplineorline == 1) // line
        {
            str.append("'MSLine'");
        } else// spline
        {
            str.append("'MSSpline'");
        }

        str.append(" , 'myChartId', '100%', '100%', '0', '1' ); \n");

        str.append(" myChart.setXMLData( \n");
        str.append("\"<chart caption='");
        str.append(title);
        str.append("' formatNumber='0'  formatnumberscale='0' setAdaptiveYMin='1'  \" + \n");
        str.append("\" useroundedges='1' bgcolor='999999,FFFFFF'  bgalpha='50'   \" + \n");

        if (display_num == 1) // 表示不显示系列data值
        {
            str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000' showvalues='1'  \" + \n");
        } else {
            str.append(" \" basefont='Arial' basefontsize='10' basefontcolor='000000' showvalues='0'  \" + \n");
        }

        str.append(" \"  divlineisdashed='1' showalternatehgridcolor='0' shadowalpha='90'   \" + \n");
        str.append(" \"  numvdivlines='5' linethickness='2'  anchorradius='2'  anchorBgAlpha='70' numVDivLines='4' animation='0' ");
        if (labelDisplay == 0) {
            // str.append(" \"      \" + \n");
        } else if (labelDisplay == 1) {
            str.append("   labeldisplay='WRAP'     ");
        } else if (labelDisplay == 2) {
            str.append("    labeldisplay='ROTATE'    ");
        } else if (labelDisplay == 3) {
            str.append("    labeldisplay='ROTATE'  slantLabels='1'  ");
        } else if (labelDisplay == 4) {
            str.append(" labeldisplay='Stagger' staggerlines='2'   ");
        }

        str.append("  labelStep=' ");
        str.append(labelSte);
        str.append("'  \" + \n ");

        str.append(" \"   canvasbgcolor='c0c0c0,F0F8FF' canvasbasedepth='50' canvasbgdepth='90'  showtooltip='1'    \" + \n");

        // 根据系列数据定义Y轴上限值
        double yAxisMaxValue = getYAxisMaxValue(seriesList, trendlines);
        str.append(" \"  yAxisMaxValue='");

        if (isRateChart == 1) // 是100%比率图
        {
            if (yAxisMaxValue > 100) {
                yAxisMaxValue = 100;
            }
            str.append(yAxisMaxValue);
        } else {
            str.append(yAxisMaxValue);
        }

        double yAxisMinValue = getYAxisMinValue(seriesList, trendlines);
        str.append("'  yAxisMinValue='");
        str.append(yAxisMinValue);
        str.append("' >\"+\n");

        str.append(" \"<categories> \"+\n");
        if (labels != null) {
            for (int i = 0; i < labels.length; i++) {
                String labelString = labels[i]; // 横坐标label
                str.append(" \"<category label='");
                str.append(labelString);
                str.append("'  /> \"+\n");
            }

        }
        str.append(" \"</categories> \"+\n");

        String[] colors = this.getColorDatas();
        String[] pointColors = this.getPointColorDatas();

        List<ChartSeries> seriesListop = seriesList;
        for (int i = 0; i < seriesListop.size(); i++) {
            ChartSeries serie = seriesListop.get(i);
            double[] datas = serie.getData();
            String[] drillstr = serie.getDrillstr();
            String seriesname = serie.getName();
            String color = colors[i];
            String pointcolor = pointColors[i];

            str.append("\" <dataset seriesname='");
            str.append(seriesname);
            str.append("' color='");
            str.append(color);
            str.append("' anchorbordercolor='");
            str.append(pointcolor);
            str.append("' anchorbgcolor='");
            str.append(pointcolor);
            str.append("' >\"+\n");

            if (datas != null) {
                for (int a = 0; a < datas.length; a++) {

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(datas[a]); // 系列data值

                    if (datas[a] == -999 && isSplineorline == 1) // 表示值为空.
                    {
                        str.append(" \"<set value='' />\"+\n ");

                    } else {

                        if (datas[a] == -999) {
                            number = "0.00";
                        }

                        str.append(" \"<set");

                        str.append("  value='");
                        str.append(number);

                        String[] drillsplit = drillstr[a].split(",");
                        StringBuffer drillstrl = new StringBuffer();

                        drillstrl.append("(" + drillsplit[0] + ")");
                        drillstrl.append("{br}");
                        drillstrl.append("时间:" + drillsplit[1]);
                        drillstrl.append("{br}");
                        drillstrl.append("性能:" + drillsplit[2]);
                        drillstrl.append("{br}");
                        drillstrl.append("&nbsp&nbsp&nbsp&nbsp值:"
                                + drillsplit[3]);

                        str.append("'  toolText='");
                        str.append(drillstrl.toString());
                        str.append("'  />\"+\n");
                        // str.append("'  link=\\\"JavaScript: isJavaScriptCall=true; myJS(\'");
                        // str.append(drillstr[a]);
                        // str.append("\');\\\"   />\"+\n");

                    }

                }

            }

            str.append(" \" </dataset> >\"+\n");

        }

        str.append(" \"<styles>\"+\n");
        str.append(" \"<definition>\"+\n");
        str.append(" \"<style name='myCaptionFont' type='font' font='Arial' size='12' />\"+\n");
        str.append(" \"</definition>\"+\n");
        str.append(" \"<application>\"+\n");
        str.append(" \"<apply toobject='Caption' styles='myCaptionFont' />\"+\n");
        str.append("  \"</application>\"+\n");
        str.append("  \"</styles>\"+\n");

        // 判断是否添加阀值...
        if (trendlines != null) {

            if (trendlines.size() > 0) {
                str.append("  \"<trendlines>\"+\n");

                for (int i = 0; i < trendlines.size(); i++) {

                    ChartTrendlines chartTrendlines = trendlines.get(i);

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(chartTrendlines.getValue()); // 系列data值

                    str.append("  \"<line startValue='");
                    str.append(number);
                    str.append("' displayValue='");
                    str.append(chartTrendlines.getName());

                    str.append("' toolText='");
                    str.append(replaceBr(chartTrendlines.getName()));
                    str.append(":");
                    str.append(number);

                    str.append("' color='");
                    // 颜色确认.
                    String trendlinecolor = getTrendlinesColor(chartTrendlines
                            .getColor());
                    str.append(trendlinecolor);
                    // 为2D图时，showOnTop属性才有用.
                    str.append("' showOnTop='1'  thickness='2' valueOnRight='1' />\" +\n");
                }

            }
            // 是否在右边显示0左1右 //线的厚度
            // <line startValue="750" displayValue="Average" color="009900"
            // valueOnRight="1" thickness="1" />
            str.append("  \"</trendlines>\"+\n");
        }

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );\n");
        //
        // str.append(" function myJS(isdrill) { \n");
        // str.append(" window.javatojs.nextChart(isdrill); \n");
        // str.append(" } \n");

        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        // TODO Auto-generated method stub
        return str.toString();

    }

    @Override
    public String drawCustomPieChart(StringBuffer str,
                                     PieCustomChartSeries series, String[] labels, StringBuffer chartStyle) {

        double[] datas = null; // 得到单个系列数据 //饼图就是单系列..
        if (series.getData() != null) {
            datas = series.getData();
        }

        String[] isdrill = series.getDrillstr();
        String[] colors = series.getColor();

        if (datas != null) {
            for (int i = 0; i < datas.length; i++) {
                String labelString = labels[i]; // 横坐标label
                DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                String number = df1.format(datas[i]); // 系列data值

                if (datas[i] == -999) {

                    str.append(" \"<set label='");
                    str.append(labelString);
                    str.append("'  />\"+\n");
                } else {
                    str.append(" \"<set label='");
                    str.append(labelString);
                    str.append("(");
                    str.append(number);
                    str.append(")' ");
                    str.append(" value='");
                    str.append(number);
                    str.append("' tooltext='");
                    str.append(labelString);
                    str.append("(");
                    str.append(number);
                    str.append(")'  ");
                    str.append(" color='");
                    str.append(colors[i]);
                    str.append("' ");

                    if (Float.valueOf(number) != 0 && isdrill != null
                            && isdrill.length != 0) {
                        str.append("  link='JavaScript: isJavaScriptCall=true; myJS( \\\"");
                        str.append(isdrill[i].toString().trim());
                        str.append("\\\" );'  />\"+\n");
                    } else {

                        str.append(" />\"+\n");
                    }

                }

            }

        }

        str = str.append(chartStyle); // 融合样式

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );\n");
        str.append(" function myJS(isdrill) { \n");
        str.append(" window.javatojs.nextChart(isdrill); \n");
        str.append(" } \n");
        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        return str.toString();
    }

    @Override
    public String drawCustomMultiSeriesBarChart(StringBuffer str,
                                                List<ChartSeries> seriesList, String[] labels,
                                                List<ChartTrendlines> trendlines, int isRateChart,
                                                StringBuffer chartStyle) {

        // 根据系列数据定义Y轴上限值
        double yAxisMaxValue = getCustomBarYAxisMaxValue(seriesList, trendlines);
        str.append(" \"   yAxisMaxValue='");
        if (isRateChart == 1) // 是100%比率图
        {
            if (yAxisMaxValue > 100) {
                yAxisMaxValue = 100;
            }
            str.append(yAxisMaxValue);
        } else {
            str.append(yAxisMaxValue);
        }

        double yAxisMinValue = getCustomBarYAxisMinValue(seriesList, trendlines);

        if (yAxisMinValue < 0) {
            yAxisMinValue = 0;

        }
        str.append("'  yAxisMinValue='");

        str.append(yAxisMinValue);
        str.append("' >\"+\n");

        str.append(" \"<categories> \"+\n");
        if (labels != null) {
            for (int i = 0; i < labels.length; i++) {
                String labelString = labels[i]; // 横坐标label
                str.append(" \"<category label='");
                str.append(labelString);
                str.append("'  /> \"+\n");
            }

        }

        str.append(" \"</categories> \"+\n");

        List<ChartSeries> seriesListop = seriesList;
        for (int i = 0; i < seriesListop.size(); i++) {
            ChartSeries serie = seriesListop.get(i);
            double[] datas = serie.getData();
            String[] drillstrs = serie.getDrillstr();
            String seriesname = serie.getName();
            String color = serie.getColor();

            str.append("\" <dataset seriesname='");
            str.append(seriesname);
            str.append("' color='");
            str.append(color);
            str.append("' >\"+\n");

            if (datas != null) {
                for (int i1 = 0; i1 < datas.length; i1++) {

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(datas[i1]); // 系列data值

                    if (datas[i1] == -999) {
                        str.append(" \"<set  value='' />\"+\n ");
                    } else {
                        str.append(" \"<set  value='");
                        str.append(number);
                        str.append("' ");

                        if (Float.valueOf(number) != 0 && drillstrs != null
                                && drillstrs.length != 0) {
                            str.append("  link='JavaScript: isJavaScriptCall=true; myJS( \\\"");
                            str.append(drillstrs[i1].toString().trim());
                            str.append("\\\" );'  />\"+\n");
                        } else {

                            str.append("  />\"+\n");
                        }
                    }
                }
            }

            str.append(" \" </dataset> >\"+\n");

        }

        // 判断是否添加阀值...
        if (trendlines != null) {

            if (trendlines.size() > 0) {
                str.append("  \"<trendlines>\"+\n");

                for (int i = 0; i < trendlines.size(); i++) {

                    ChartTrendlines chartTrendlines = trendlines.get(i);

                    DecimalFormat df1 = new DecimalFormat("0.00"); // 保留小数点后两位
                    String number = df1.format(chartTrendlines.getValue()); // 系列data值

                    str.append("  \"<line startValue='");
                    str.append(number);
                    str.append("' displayValue='");
                    str.append(chartTrendlines.getName());

                    str.append("' toolText='");
                    str.append(replaceBr(chartTrendlines.getName()));
                    str.append(":");
                    str.append(number);

                    str.append("' color='");
                    // 颜色确认.
                    String trendlinecolor = getTrendlinesColor(chartTrendlines
                            .getColor());
                    str.append(trendlinecolor);
                    // 为2D图时，showOnTop属性才有用.
                    str.append("' showOnTop='1'  thickness='2' valueOnRight='1' />\" +\n");
                }

            }
            // 是否在右边显示0左1右 //线的厚度
            // <line startValue="750" displayValue="Average" color="009900"
            // valueOnRight="1" thickness="1" />
            str.append("  \"</trendlines>\"+\n");
        }

        str = str.append(chartStyle); // 融合样式

        str.append(" \"</chart> \"\n  ");
        str.append(" ); \n");
        str.append(" myChart.render( 'chartContainer' );\n");
        str.append(" function myJS(isdrill) { \n");
        str.append(" window.javatojs.nextChart(isdrill); \n");
        str.append(" } \n");
        str.append(" </script>\n");
        str.append(" </body>\n");
        str.append(" </html>");

        return str.toString();
    }

    @Override
    public double getCustomBarYAxisMaxValue(List<ChartSeries> seriesList,
                                            List<ChartTrendlines> trendlines) {
        // TODO Auto-generated method stub
//        double[] numbersbar = new double[]{};
//        for (int i = 0; i < seriesList.size(); i++) {
//
//            ChartSeries serie = seriesList.get(i);
//            double[] serienums = serie.getData();
//
//            // 整合数组
//            numbersbar = ArrayUtils.addAll(numbersbar, serienums);
//        }
//
//        if (trendlines != null) {
//            double[] trendnumbers = new double[trendlines.size()];
//            for (int i = 0; i < trendlines.size(); i++) {
//                ChartTrendlines chartTrendlines = trendlines.get(i);
//                trendnumbers[i] = chartTrendlines.getValue();
//            }
//
//            numbersbar = ArrayUtils.addAll(numbersbar, trendnumbers);
//
//        }
//
//        double[] numbers = new double[numbersbar.length];
//
//        System.arraycopy(numbersbar, 0, numbers, 0, numbersbar.length);
//
//        // 得到所需组数大小
//        int arraySize = 0;
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] != -999) {
//                arraySize += 1;
//            }
//        }
//
//        double[] newnumbers = new double[arraySize];
//        int a = 0;// 标示
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] != -999) {
//                newnumbers[a] = numbers[i];
//                a++;
//            }
//        }
//
//        SortUtil.sort(newnumbers);
//
//        Double x = Double
//                .valueOf((newnumbers[0] - newnumbers[newnumbers.length - 1]) / 10);
//
//        // Double xtm = Math.abs(x);
//        // if (xtm < 1) {
//        // x = 1.0;
//        // }
//
//        double curnumber = newnumbers[0] + x;
//
//        // 柱图不用加
//        // if(curnumber==0)
//        // {
//        // curnumber=curnumber-1;
//        // }
//
//        return curnumber;
        return 0;
    }

    @Override
    public double getCustomBarYAxisMinValue(List<ChartSeries> seriesList,
                                            List<ChartTrendlines> trendlines) {
        // TODO Auto-generated method stub
//        double[] numbersbar = new double[]{};
//        for (int i = 0; i < seriesList.size(); i++) {
//
//            ChartSeries serie = seriesList.get(i);
//            double[] serienums = serie.getData();
//            // 整合数组
//            numbersbar = ArrayUtils.addAll(numbersbar, serienums);
//        }
//        if (trendlines != null) {
//            double[] trendnumbers = new double[trendlines.size()];
//            for (int i = 0; i < trendlines.size(); i++) {
//                ChartTrendlines chartTrendlines = trendlines.get(i);
//                trendnumbers[i] = chartTrendlines.getValue();
//            }
//
//            numbersbar = ArrayUtils.addAll(numbersbar, trendnumbers);
//
//        }
//
//        double[] numbers = new double[numbersbar.length];
//
//        System.arraycopy(numbersbar, 0, numbers, 0, numbersbar.length);
//
//        // 得到所需组数大小
//        int arraySize = 0;
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] != -999) {
//                arraySize += 1;
//            }
//        }
//
//        double[] newnumbers = new double[arraySize];
//
//        int a = 0;// 标示
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] != -999) {
//                newnumbers[a] = numbers[i];
//                a++;
//            }
//        }
//
//        SortUtil.sortMin(newnumbers);
//
//        Double x = Double
//                .valueOf((newnumbers[0] - newnumbers[newnumbers.length - 1]) / 10);
//
//        double curnumber = newnumbers[0] + x;
//
//        // 柱图不用加
//        // if(curnumber==0)
//        // {
//        // curnumber=curnumber-1;
//        // }
//
//        return curnumber;
        return 0;
    }

}
