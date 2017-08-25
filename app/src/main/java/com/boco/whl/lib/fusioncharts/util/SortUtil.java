package com.boco.whl.lib.fusioncharts.util;

public class SortUtil {

    public static void sort(double[] values) {
        double temp;
        for (int i = 0; i < values.length; ++i) {
            for (int j = 0; j < values.length - i - 1; ++j) {
                if (values[j] < values[j + 1]) {
                    temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }
    }

    public static void sortMin(double[] values) {
        double temp;
        for (int i = 0; i < values.length; ++i) {
            for (int j = 0; j < values.length - i - 1; ++j) {
                if (values[j] > values[j + 1]) {
                    temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }
    }

}
