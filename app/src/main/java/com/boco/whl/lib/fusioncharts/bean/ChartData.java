package com.boco.whl.lib.fusioncharts.bean;

public class ChartData {

    private String label;
    private String value;
    private String tooltext;
    private String color;

    public ChartData() {

    }

    public ChartData(String value) {
        this.value = value;
    }


    public ChartData(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public ChartData(String label, String value, String color) {
        this.label = label;
        this.value = value;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTooltext() {
        return tooltext;
    }

    public void setTooltext(String tooltext) {
        this.tooltext = tooltext;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
