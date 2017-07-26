package com.boco.table.adapter;

import android.app.Activity;
import android.view.View;

import com.boco.table.po.RowModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jy on 2016/11/9.
 */

public abstract class BaseTableDataAdapter<T> implements TableDataAdapter<T> {

    List<T> mList;
    Map<Integer, String[]> colHeaders;
    Activity mContext;

    public BaseTableDataAdapter(Activity mContext, String[] headers, List<T> mList) {
        colHeaders = new HashMap<>();
        if (headers != null && headers.length > 0) {
            colHeaders.put(TableDataAdapter.FIXED_COLUMN, new String[]{headers[0]});
            if (headers.length > 0) {
                String[] scrollables = new String[headers.length - 1];
                for (int i = 0; i < headers.length; i++) {
                    if (i > 0) {
                        scrollables[i - 1] = headers[i];
                    }
                }
                colHeaders.put(TableDataAdapter.SCROLLABLE_COLUMN, scrollables);
            }
        } else {
            colHeaders.put(TableDataAdapter.FIXED_COLUMN, new String[]{});
            colHeaders.put(TableDataAdapter.SCROLLABLE_COLUMN, new String[]{});
        }
        this.mList = mList;
        this.mContext = mContext;
    }


    @Override
    public Activity getMContext() {
        return mContext;
    }

    @Override
    public Map<Integer, String[]> getColHeaders() {
        return colHeaders;
    }

    @Override
    public int getTotalRows() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public List<T> getData() {
        return mList;
    }

    @Override
    public List<RowModel> getRows(int startRow, int endRow) {
        List<RowModel> rows = new ArrayList<>();
        if (mList != null) {
            for (int i = startRow; i <= endRow && i < mList.size(); ++i) {

                List<String> strList = convertRowModel(mList.get(i));

                Map<Integer, List<String>> row = new HashMap<>();

                if (strList != null && strList.size() > 0) {
                    List<String> fCols = new ArrayList<>();
                    fCols.add(strList.get(0));
                    int fColsSize = fCols.size();
                    if (fColsSize < colHeaders.get(TableDataAdapter.FIXED_COLUMN).length) {
                        for (int j = 0; j < colHeaders.get(TableDataAdapter.FIXED_COLUMN).length - fColsSize; j++) {
                            fCols.add("");
                        }
                    } else if (fColsSize > colHeaders.get(TableDataAdapter.FIXED_COLUMN).length) {
                        for (int j = 0; j < fColsSize - colHeaders.get(TableDataAdapter.FIXED_COLUMN).length; j++) {
                            fCols.remove(fColsSize - j - 1);
                        }

                    }
                    row.put(TableDataAdapter.FIXED_COLUMN, fCols);

                    List<String> sCols = new ArrayList<>();
                    for (int j = 0; j < strList.size(); j++) {
                        if (j > 0) {
                            sCols.add(strList.get(j));
                        }
                    }
                    int sColsSize = sCols.size();
                    if (sColsSize < colHeaders.get(TableDataAdapter.SCROLLABLE_COLUMN).length) {
                        for (int j = 0; j < colHeaders.get(TableDataAdapter.SCROLLABLE_COLUMN).length - sColsSize; j++) {
                            sCols.add("");
                        }
                    } else if (sColsSize > colHeaders.get(TableDataAdapter.SCROLLABLE_COLUMN).length) {
                        for (int j = 0; j < sColsSize - colHeaders.get(TableDataAdapter.SCROLLABLE_COLUMN).length; j++) {
                            sCols.remove(sColsSize - j - 1);
                        }

                    }
                    row.put(TableDataAdapter.SCROLLABLE_COLUMN, sCols);
                } else {
                    List<String> fCols = new ArrayList<>();
                    for (int j = 0; j < colHeaders.get(TableDataAdapter.FIXED_COLUMN).length; j++) {
                        fCols.add("");
                    }
                    row.put(TableDataAdapter.FIXED_COLUMN, fCols);

                    List<String> sCols = new ArrayList<>();
                    for (int j = 0; j < colHeaders.get(TableDataAdapter.SCROLLABLE_COLUMN).length; j++) {
                        sCols.add("");
                    }
                    row.put(TableDataAdapter.SCROLLABLE_COLUMN, sCols);
                }


                rows.add(new RowModel(0, row));
            }
        }
        return rows;
    }

    @Override
    public View.OnClickListener getOnClickListener(final int row) {
        View.OnClickListener itemOnClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener(row);
            }
        };
        return itemOnClickListener;
    }

    @Override
    public View.OnClickListener getCellOnClickListener(final int row, final int column) {
        View.OnClickListener cellOnClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCellClickListener(row,column);
            }
        };
        return cellOnClickListener;
    }

    @Override
    public Map<String, List<String>> getViceColHeaders() {
        return null;
    }

    @Override
    public View addCellView(int row, int column) {
        return null;
    }

    @Override
    public int getImgResId(int row, int column) {
        return 0;
    }


    protected abstract List<String> convertRowModel(T t);

}
