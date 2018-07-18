package fusioncharts.draw;

public class PieCustomChartSeries {

    private double[] data;
    private String[] drillstr;
    private String[] color;

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public String[] getDrillstr() {
        return drillstr;
    }

    public void setDrillstr(String[] drillstr) {
        this.drillstr = drillstr;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }


}
