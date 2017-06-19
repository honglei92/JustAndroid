package com.boco.fusioncharts.creator;

public enum FusionChartsType {
    /**
     * 柱状图
     */
    Column2D("column2d"),
    /**
     * 3D柱状图
     */
    Column3D("column3d"),
    /**
     * 多列3D柱状图
     */
    MsColumn3D("mscolumn3d"),
    /**
     * 折线图
     */
    Line("line"),
    /**
     * 多折线图
     */
    MsLine("msline"),
    /**
     * 多轴折线图
     */
    MultiAxisLine("MultiAxisLine"),
    /**
     * 多列堆积图
     */
    MsStackedColumn2d("msstackedcolumn2d"),
    /**
     * 横向柱状图
     */
    Bar2D("bar2d"),
    /**
     * 多列横向柱状图
     */
    MsBar2D("msbar2d"),
    /**
     * 多列3D横向柱状图
     */
    MsBar3D("msbar3d"),
    /**
     * 横向堆积图
     */
    StackedBar2D("stackedbar2d"),
    /**
     * 堆积图
     */
    StackedColumn2D("stackedcolumn2d"),
    /**
     * 3D堆积图
     */
    StackedColumn3D("stackedcolumn3d"),
    /**
     * 横向3D堆积图
     */
    StackedBar3D("stackedbar3d"),
    /**
     * 饼图
     */
    Pie2D("pie2d"),
    /**
     * 3D饼图
     */
    Pie3D("pie3d"),
    /**
     * 环形饼图
     */
    Doughunt2D("doughnut2d"),
    /**
     * 3D环形饼图
     */
    Doughunt3D("doughnut3d"),
    /**
     * 多列柱状图
     */
    MsColumn2D("mscolumn2d"),
    /**
     * 单Y轴多柱图和多折线组合图
     */
    MsColumnbi2D("mscombi2d"),
    /**
     * 多系列多Y轴图
     */


    Area2D("Area2D");


    private String value;

    FusionChartsType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
