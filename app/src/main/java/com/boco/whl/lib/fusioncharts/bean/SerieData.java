package com.boco.whl.lib.fusioncharts.bean;

import java.util.List;

/**
 * Created by zhang.w.x
 */
public class SerieData {
    private String seriesname;

    private List<ChartData> data;
    private String color;
    private String anchorBorderColor;
    private String anchorBgColor;
    private String renderAs;
    private String parentYAxis;

    public SerieData() {

    }

    public SerieData(String seriesname, List<ChartData> data) {
        this.seriesname = seriesname;
        this.data = data;
    }

    public String getAnchorBorderColor() {
        return anchorBorderColor;
    }

    public void setAnchorBorderColor(String anchorBorderColor) {
        this.anchorBorderColor = anchorBorderColor;
    }

    public String getAnchorBgColor() {
        return anchorBgColor;
    }

    public void setAnchorBgColor(String anchorBgColor) {
        this.anchorBgColor = anchorBgColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSeriesname() {
        return seriesname;
    }

    public void setSeriesname(String seriesname) {
        this.seriesname = seriesname;
    }

    public List<ChartData> getData() {
        return data;
    }

    public void setData(List<ChartData> data) {
        this.data = data;
    }

    public String getRenderAs() {
        return renderAs;
    }

    public void setRenderAs(String renderAs) {
        this.renderAs = renderAs;
    }

    public String getParentYAxis() {
        return parentYAxis;
    }

    public void setParentYAxis(String parentYAxis) {
        this.parentYAxis = parentYAxis;
    }

}
