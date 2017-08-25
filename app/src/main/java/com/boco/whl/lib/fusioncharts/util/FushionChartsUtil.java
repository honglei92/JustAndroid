package com.boco.whl.lib.fusioncharts.util;


public class FushionChartsUtil {

//	public static String getChartHtml(FushionChartsParams params,
//			List<ChartData> datas) {
//
//		StringBuffer str = new StringBuffer();
//		str.append("<html> \n");
//		str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
//		str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
//		str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
//		str.append(" <body style='height:100%;margin:0'> \n ");
//		str.append(" <div id='chartContainer' style='height:100%;'></div> \n");
//
//		str.append(" <style type='text/css'>  \n");
//		str.append(" div {  \n");
//		str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
//		str.append("  }  \n");
//		str.append("  </style>  \n \n");
//
//		str.append(" <script type='text/javascript'> \n");
//
//		str.append("var myChart = new FusionCharts(\"" + params.getType()
//				+ "\", \"myChartId\", \"100%\", \"100%\", \"0\", \"1\");\n");
//
//		// + "\"pieRadius\" : \"70\",\n" manageLabelOverflow='1'
//		// setDataColor(datas);
//		str.append("myChart.setJSONData({\"chart\" : {\n"
//				+ params.getParamsStr() + "},\"data\" :"
//				+ JSON.toJSONString(datas) + "});");
//
//		str.append(" myChart.render( 'chartContainer' );");
//		str.append(" </script>\n");
//		str.append(" </body>\n");
//		str.append(" </html>");
//
//		return str.toString();
//	}
//
//	public static String getMultiChartHtml(FushionChartsParams params,
//			MultiChartData datas) {
//
//		StringBuffer str = new StringBuffer();
//		str.append("<html> \n");
//		str.append(" <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> \n");
//		str.append(" <meta  name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' /> \n");
//		str.append(" <head><script type='text/javascript' src='file:///android_asset/FusionCharts.js' ></script></head> \n");
//		str.append(" <body style='height:100%;margin:0'> \n ");
//		str.append(" <div id='chartContainer' style='height:100%;'></div> \n");
//
//		str.append(" <style type='text/css'>  \n");
//		str.append(" div {  \n");
//		str.append(" -webkit-tap-highlight-color: rgba(0, 0, 0, 0);  \n");
//		str.append("  }  \n");
//		str.append("  </style>  \n \n");
//
//		str.append(" <script type='text/javascript'> \n");
//
//		str.append("var myChart = new FusionCharts(\"" + params.getType()
//				+ "\", \"myChartId\", \"100%\", \"100%\", \"0\", \"1\");\n");
//
//		// + "\"pieRadius\" : \"70\",\n" manageLabelOverflow='1'
//		// setDataColor(datas);
//		str.append("myChart.setJSONData({\"chart\" : {\n"
//				+ params.getParamsStr() + "},\"categories\" :"
//				+ JSON.toJSONString(datas.getCategory()) + ", \"dataset\":"
//				+ JSON.toJSONString(datas.getDataset()) + "});");
//
//		str.append(" myChart.render( 'chartContainer' );");
//		str.append(" </script>\n");
//		str.append(" </body>\n");
//		str.append(" </html>");
//
//		return str.toString();
//	}


    // private static final String[] COLOR_LIST = { "25ADDF", "B975DC",
    // "8ABD00",
    // "FFAE17", "F13031", "D3EA92", "FFE3A1", "FFAFB0", "A7E0F4",
    // "DC0CEE", "3ADFAD", "EA293D", "2AA13E", "A85B93", "F303B7",
    // "13A7FB", "540EF2", "AA5988", "880FFA", "231FAE", "90EE03",
    // "OF2FEF", "2FDCAD", "BE0DC6" };

    // private static void setDataColor(List<PieChartData> datas) {
    // PieChartData data = null;
    // int j = 0;
    // for (int i = 0; i < datas.size(); i++) {
    // if (j == COLOR_LIST.length) {
    // j = i == datas.size() - 1 ? 0 : 1;
    // }
    // data = datas.get(i);
    // data.setColor(COLOR_LIST[j++]);
    // }
    // }

    // private static List<PieChartData> getPieChartDatas(
    // List<StatisticsDataOfFault> datas, float totalPending) {
    // List<PieChartData> pieChartDatas = new LinkedList<PieChartData>();
    //
    // NumberFormat format = NumberFormat.getPercentInstance();
    // format.setMaximumIntegerDigits(3);
    // format.setMaximumFractionDigits(2);
    // pieChartDatas = new ArrayList<PieChartData>();
    // PieChartData pieChartData = null;
    // StatisticsDataOfFault data = null;
    // for (int i = 0; i < datas.size() - 1; i++) {
    // data = datas.get(i);
    // pieChartData = new PieChartData();
    // pieChartData.setLabel(data.getName() + ":"
    // + format.format(data.getPending() / (totalPending + 0.0f)));
    // pieChartData.setValue(data.getPending());
    //
    // pieChartDatas.add(pieChartData);
    // }
    //
    // return pieChartDatas;
    // }
}
