package com.boco.fusioncharts.draw;

public class ChartSeries {

    private String name;
    private double[] data;
    private int type;
    private String[] drillstr;
    private String color;


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String[] getDrillstr() {
        return drillstr;
    }

    public void setDrillstr(String[] drillstr) {
        this.drillstr = drillstr;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
