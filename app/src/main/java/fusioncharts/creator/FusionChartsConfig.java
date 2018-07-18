package fusioncharts.creator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 图表属性配置 详细配置见官网：http://www.fusioncharts.com/
 *
 * @author zhang.w.x
 */
public class FusionChartsConfig {
    /**
     * 标题
     */
    public static final String Title = "caption";
    /**
     * X坐标名
     */
    public static final String X_AxisName = "xAxisName";
    /**
     * Y坐标名
     */
    public static final String Y_AxisName = "yAxisName";
    /**
     * 加载动画, 0:去掉加载动画, 1:使用加载动画
     */
    public static final String Animation = "animation";
    /**
     * 默认图标颜色，如给定为绿色，那么折线图或者条形图的颜色就为绿色,多系列可用逗号分隔如：(ffffff,ff00fff)
     */
    public static final String PaletteColors = "paletteColors";
    /**
     * 边框,0,1
     */
    public static final String ShowBorder = "showBorder";
    /**
     * 边框透明度
     */
    public static final String BorderAlpha = "borderAlpha";
    /**
     * 图标边框
     */
    public static final String ShowCanvasBorder = "showCanvasBorder";
    /**
     * 是否显示横坐标(0,1)
     */
    public static final String ShowXAxisLine = "showXAxisLine";
    /**
     * 背景色,例（FE7CE2）
     */
    public static final String CanvasbgColor = "canvasbgColor";
    /**
     * 背景色
     */
    public static final String BgColor = "bgColor";
    /**
     * 分隔线选择
     */
    public static final String DivLineColor = "divLineColor";
    /**
     * 图表上值默认是否显示，可选：0,1
     */
    public static final String ShowValues = "showValues";

    public static final String ShowLabels = "showLabels";
    /**
     * 图表值的后缀，如设置为“%”，“￥”
     */
    public static final String NumberSuffix = "numberSuffix";
    /**
     * 主题
     */
    public static final String Theme = "theme";
    /**
     * 是否显示说明(0,1)
     */
    public static final String ShowLegend = "showLegend";
    /**
     * 显示图形边框（条形统计图，条形边）
     */
    public static final String Showplotborder = "showplotborder";
    /**
     * value旋转显示
     */
    public static final String RotateValues = "rotateValues";
    /**
     * 是否使用渐变色
     */
    public static final String UsePlotGradientColor = "usePlotGradientColor";
    /**
     * 是否使用分隔颜色
     */
    public static final String ShowAlternateHGridColor = "showAlternateHGridColor";
    /**
     * 旋转X轴label,可选值：0，1
     */
    public static final String RotateLabels = "rotateLabels";
    /**
     * X轴label倾斜显示，必须和上面标签配合使用，可选值：0，1
     */
    public static final String SlantLabels = "slantLabels";

    private Map<String, String> params = new HashMap<String, String>();

    private FusionChartsConfig() {

    }

    /**
     * 获取默认配置
     *
     * @return
     */
    public static FusionChartsConfig createDefaultConfig() {
        FusionChartsConfig config = new FusionChartsConfig();

        config.addParams(Animation, "0");
        config.addParams(ShowCanvasBorder, "0");
        config.addParams(Showplotborder, "0");
        config.addParams(ShowBorder, "0");
        config.addParams(BgColor, "FFFFFF");
        config.addParams(ShowLegend, "0");
        config.addParams(Theme, "fint");
        config.addParams(UsePlotGradientColor, "0");
        config.addParams(ShowAlternateHGridColor, "0");

        return config;
    }

    /**
     * 添加配置参数
     *
     * @param name  参数名,已提供默认参数，如{@link #Animation} {@link #ShowBorder}
     *              {@link #ShowLegend}等
     * @param value 规定值
     */
    public void addParams(String name, String value) {
        params.put(name, value);
    }

    @Override
    public String toString() {
        String str = new String();

        Set<String> keys = params.keySet();
        for (String name : keys) {
            str += "'" + name + "':'" + params.get(name) + "',\n";
        }

        return str;
    }
}
