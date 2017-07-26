package com.boco.table.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.widget.LinearLayout.LayoutParams;

import com.boco.table.R;

public class TableUtil {

    public static LayoutParams getLayoutParams(Activity context, int length,int TableHeaderWidth,int TableSplitLineSize,boolean isTransverseScroll,int cellWidth) {
        LayoutParams params = null;

        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels; // 获取屏幕宽度

        if (isTransverseScroll) {
//            if (cellWidth==0){
//                params = new LayoutParams(DisplayUtil.dip2px(context,cellWidth),
//                        LayoutParams.MATCH_PARENT);
//            }else {
//                params = new LayoutParams(DisplayUtil.dip2px(context,cellWidth+context.getResources().getDimension(R.dimen.table_padding)*2),
//                        LayoutParams.MATCH_PARENT);
//            }

            if (cellWidth==0){
                params = new LayoutParams(cellWidth,
                        LayoutParams.MATCH_PARENT);
            }else {
                params = new LayoutParams((int)(cellWidth+context.getResources().getDimension(R.dimen.table_padding)*2),
                        LayoutParams.MATCH_PARENT);
            }

        } else {
            float surplusWidth = TableHeaderWidth +
                    TableSplitLineSize* length;
            params = new LayoutParams((screenWidth - (int) surplusWidth) / (length - 1),
                    LayoutParams.MATCH_PARENT);
        }
        return params;
    }

    public static LayoutParams getMultipleLayoutParams(Activity context, int length,int TableHeaderWidth,int TableHeaderHeigh,int TableSplitLineSize,boolean isTransverseScroll,int cellWidth,int count) {
        LayoutParams params = null;

        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels; // 获取屏幕宽度

        if (isTransverseScroll) {
            if (cellWidth==0){
                params = new LayoutParams(cellWidth,
                        0,1.0f);
            }else {
                params = new LayoutParams((int)(cellWidth+context.getResources().getDimension(R.dimen.table_padding)*2*count+TableSplitLineSize* (count-1)),
                        (TableHeaderHeigh-TableSplitLineSize)/2);
            }

        } else {
            float surplusWidth = TableHeaderWidth +
                    TableSplitLineSize* length;
            params = new LayoutParams(((screenWidth - (int) surplusWidth) / (length - 1))*count+TableSplitLineSize* (count-1),
                    (TableHeaderHeigh-TableSplitLineSize)/2);
        }
        return params;
    }

    public static LayoutParams getChildLayoutParams(Activity context, int length) {
        LayoutParams params = null;

        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels; // 获取屏幕宽度
        if (length > 4) {
            params = new LayoutParams(DisplayUtil.dip2px(context, 140) - DisplayUtil.dip2px(context, 20),
                    LayoutParams.MATCH_PARENT);
        } else {
            params = new LayoutParams((screenWidth - DisplayUtil.dip2px(context, 81 + (length - 1))) / (length - 1) - DisplayUtil.dip2px(context, 20),
                    LayoutParams.MATCH_PARENT);
        }
        return params;
    }
}