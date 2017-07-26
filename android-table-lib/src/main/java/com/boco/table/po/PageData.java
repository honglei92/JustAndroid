package com.boco.table.po;

import android.view.View;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by JY on 2016/11/9.
 */

public class PageData implements Serializable {
    private Map<Integer, String[]> headData;
    private List<RowModel> rowData;
    private List<View.OnClickListener> rowOnClick;
    private Map<Integer,List<View.OnClickListener>> cellOnClick;
    private Map<String,List<String>> viceHeaderData;

    public Map<Integer, String[]> getHeadData() {
        return headData;
    }

    public void setHeadData(Map<Integer, String[]> headData) {
        this.headData = headData;
    }

    public List<RowModel> getRowData() {
        return rowData;
    }

    public void setRowData(List<RowModel> rowData) {
        this.rowData = rowData;
    }

    public List<View.OnClickListener> getRowOnClick() {
        return rowOnClick;
    }

    public void setRowOnClick(List<View.OnClickListener> rowOnClick) {
        this.rowOnClick = rowOnClick;
    }

    public Map<Integer, List<View.OnClickListener>> getCellOnClick() {
        return cellOnClick;
    }

    public void setCellOnClick(Map<Integer, List<View.OnClickListener>> cellOnClick) {
        this.cellOnClick = cellOnClick;
    }

    public Map<String, List<String>> getViceHeaderData() {
        return viceHeaderData;
    }

    public void setViceHeaderData(Map<String, List<String>> viceHeaderData) {
        this.viceHeaderData = viceHeaderData;
    }
}
