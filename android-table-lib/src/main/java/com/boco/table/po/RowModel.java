package com.boco.table.po;

import android.support.annotation.ColorRes;

import java.util.List;
import java.util.Map;

/**
 * Created by jy on 2016/11/9.
 */

public class RowModel {

    private
    @ColorRes
    int rowColor;

    private Map<Integer, List<String>> rowData;

    public RowModel(int rowColor, Map<Integer, List<String>> rowData) {
        this.rowColor = rowColor;
        this.rowData = rowData;
    }

    public int getRowColor() {
        return rowColor;
    }

    public void setRowColor(int rowColor) {
        this.rowColor = rowColor;
    }

    public Map<Integer, List<String>> getRowData() {
        return rowData;
    }

    public void setRowData(Map<Integer, List<String>> rowData) {
        this.rowData = rowData;
    }
}
