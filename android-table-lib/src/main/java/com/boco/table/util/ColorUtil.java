package com.boco.table.util;


public class ColorUtil {
    public static String repairColor(String color){
        if (color.indexOf("#")!=0){
            color="#"+color;
        }
        return color;
    }
}
