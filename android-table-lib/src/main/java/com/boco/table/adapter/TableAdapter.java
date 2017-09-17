package com.boco.table.adapter;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boco.table.R;
import com.boco.table.po.PageData;
import com.boco.table.util.ColorUtil;
import com.boco.table.util.DisplayUtil;
import com.boco.table.util.TableUtil;
import com.boco.table.view.CHScrollView;
import com.boco.table.view.InterceptView;

import java.util.List;

import static com.boco.table.R.id.viewPortraitSplitline1;


public class TableAdapter extends BaseAdapter {

    protected List<CHScrollView> mHScrollViews;
    private Activity context;
    private ListView listView;
    private PageData pageData;
    private TypedArray typearray;
    private boolean isTransverseScroll;
    private int cellWidth;
    private int[] cellWidths;

    private int TablHeigh; //表高度
    private int TableHeaderWidth; //固定表头宽度
    private int TableTextSize; //表字体大小
    private int TableTextColor;  //表字体颜色
    private int TableSplitLineSize; //表分割线大小
    private int TableSplitLineColor;  //表分割线颜色
    private int TableFirstBg;  //表第一种颜色
    private int TableSecondBg;  //表第一种颜色
    private int TableTextLine;//字行数，超过省略号显示
    protected boolean isCustomClolor;//是否自定义每个字体颜色
    protected String delimiter;//分隔符(表格数据与字体颜色拼接)

    private TableDataAdapter tableDataAdapter;

    public void setTableDataAdapter(TableDataAdapter tableDataAdapter) {
        this.tableDataAdapter = tableDataAdapter;
    }

    public TableAdapter(Activity context, PageData pageData, ListView listView, List<CHScrollView> mHScrollViews, TypedArray typearray, boolean isTransverseScroll, int cellWidth, int[] cellWidths) {
        this.context = context;
        this.pageData = pageData;
        this.listView = listView;
        this.mHScrollViews = mHScrollViews;
        this.typearray = typearray;
        this.isTransverseScroll = isTransverseScroll;
        this.cellWidth = cellWidth;
        this.cellWidths = cellWidths;

        TablHeigh = (int) typearray.getDimension(R.styleable.FixedHeaderTableView_TablHeigh, context.getResources().getDimension(R.dimen.table_min_height)); //表高度
        TableHeaderWidth = (int) typearray.getDimension(R.styleable.FixedHeaderTableView_TableHeaderWidth, context.getResources().getDimension(R.dimen.table_fixed_min_width)); //固定表头宽度
        TableTextSize = (int) typearray.getDimension(R.styleable.FixedHeaderTableView_TableTextSize, context.getResources().getDimension(R.dimen.text_data_size)); //表字体大小
        TableTextColor = typearray.getColor(R.styleable.FixedHeaderTableView_TableTextColor, context.getResources().getColor(R.color.text_data_color));  //表字体颜色
        TableSplitLineSize = (int) typearray.getDimension(R.styleable.FixedHeaderTableView_TableSplitLineSize, DisplayUtil.dip2px(context, context.getResources().getDimension(R.dimen.split_line))); //表分割线大小
        TableSplitLineColor = typearray.getColor(R.styleable.FixedHeaderTableView_TableSplitLineColor, context.getResources().getColor(R.color.split_line_bg));  //表分割线颜色
        TableTextLine = typearray.getInteger(R.styleable.FixedHeaderTableView_TableTextLine, 2);
    }

    public void setCustomTextColor(boolean isCustomClolor, String delimiter) {
        this.isCustomClolor = isCustomClolor;
        this.delimiter = delimiter;
    }

    @Override
    public int getCount() {
        return pageData.getRowData().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        InterceptView view = null;
        ViewHold hold = null;

        if (convertView == null) {
            hold = new ViewHold();
            view = (InterceptView) View.inflate(context,
                    R.layout.boco_fixed_header_table_item, null);
            view.setIsNeedIntercept(false);
            view.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT
                    , TablHeigh));

            hold.viewPortraitSplitline1 = view.findViewById(viewPortraitSplitline1);
            LayoutParams lp = (LayoutParams) hold.viewPortraitSplitline1.getLayoutParams();
            lp.width = TableSplitLineSize;
            hold.viewPortraitSplitline1.setLayoutParams(lp);
            hold.viewPortraitSplitline1.setBackgroundColor(TableSplitLineColor);

            hold.regionNameTv = (TextView) view
                    .findViewById(R.id.tv_region_name);
            hold.regionNameTv.setLayoutParams(new LayoutParams(TableHeaderWidth, LayoutParams.MATCH_PARENT));
            hold.regionNameTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, TableTextSize);
            hold.regionNameTv.setLines(TableTextLine);
            hold.regionNameTv.setTextColor(TableTextColor);

            hold.container = (LinearLayout) view
                    .findViewById(R.id.vg_container);

            CHScrollView chs_item = (CHScrollView) view.findViewById(R.id.chs_item);
            chs_item.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT
                    , TablHeigh));
            chs_item.addView(chs_item, listView, mHScrollViews);

            view.setTag(hold);
        } else {
            view = (InterceptView) convertView;
            hold = (ViewHold) convertView.getTag();
        }

        hold.container.removeAllViews();

        int size = pageData.getRowData().get(position).getRowData().get(TableDataAdapter.SCROLLABLE_COLUMN).size();
        hold.valueTvs = new TextView[size];
        hold.layouts = new RelativeLayout[size];
        for (int i = 0; i < hold.valueTvs.length; i++) {

            hold.layouts[i] = new RelativeLayout(context);

            hold.valueTvs[i] = new TextView(context);
            hold.valueTvs[i].setTextColor(TableTextColor);
            hold.valueTvs[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, TableTextSize);
            hold.valueTvs[i].setLines(TableTextLine);
            hold.valueTvs[i].setEllipsize(TextUtils.TruncateAt.END);
            hold.valueTvs[i].setGravity(Gravity.CENTER);

            RelativeLayout.LayoutParams childTVParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            childTVParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            childTVParams.addRule(RelativeLayout.CENTER_VERTICAL);
            hold.layouts[i].addView(hold.valueTvs[i], childTVParams);


            LayoutParams params = null;
            if (cellWidth == -1) {
                if (cellWidths != null && i < cellWidths.length) {
                    params = TableUtil.getLayoutParams(
                            context, size + 1, TableHeaderWidth, TableSplitLineSize, isTransverseScroll, cellWidths[i]);
                } else {
                    params = TableUtil.getLayoutParams(
                            context, size + 1, TableHeaderWidth, TableSplitLineSize, isTransverseScroll, 0);
                }
            } else {
                params = TableUtil.getLayoutParams(
                        context, size + 1, TableHeaderWidth, TableSplitLineSize, isTransverseScroll, cellWidth);
            }

            hold.container.addView(hold.layouts[i], params);

            if (hold.valueTvs.length > 1 && i < hold.valueTvs.length - 1) {
                View divider = new View(context);
                divider.setBackgroundColor(TableSplitLineColor);
                LayoutParams divierParams = new LayoutParams(TableSplitLineSize,
                        LayoutParams.MATCH_PARENT);
                hold.container.addView(divider, divierParams);
            }

        }

        for (int i = 0; i < hold.valueTvs.length; i++) {
            hold.layouts[i].setOnClickListener(pageData.getCellOnClick().get(position).get(i));

            final RelativeLayout relativeLayout = hold.layouts[i];
            final int index = i;

            if (tableDataAdapter != null && tableDataAdapter.getImgResId(position, index) != 0) {

                ImageView childImageView = new ImageView(context);

                childImageView
                        .setImageResource(tableDataAdapter.getImgResId(position, index));

                RelativeLayout.LayoutParams childParams = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(
                        context, 15), DisplayUtil.dip2px(context, 15));
                childParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                childParams.addRule(RelativeLayout.CENTER_VERTICAL);

                relativeLayout.addView(childImageView, childParams);
            }

            if (tableDataAdapter != null && tableDataAdapter.addCellView(position, index) != null) {

                relativeLayout.addView(tableDataAdapter.addCellView(position, index));
            }

        }

        if (isCustomClolor) {
            String title = pageData.getRowData().get(position).getRowData().get(TableDataAdapter.FIXED_COLUMN).size() > 0 ? pageData.getRowData().get(position).getRowData().get(TableDataAdapter.FIXED_COLUMN).get(0) : "";
            String[] textAndColor = (title == null || "".equals(title)) ? new String[]{"", ""} : title.split(delimiter);
            if (textAndColor.length > 0) {
                hold.regionNameTv.setText(textAndColor[0]);

            }
            if (textAndColor.length > 1) {
                try {
                    hold.regionNameTv.setTextColor((textAndColor[1] == null || "".equals(textAndColor[1])) ? TableTextColor : Color.parseColor(ColorUtil.repairColor(textAndColor[1])));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            hold.regionNameTv.setText(pageData.getRowData().get(position).getRowData().get(TableDataAdapter.FIXED_COLUMN).size() > 0 ? pageData.getRowData().get(position).getRowData().get(TableDataAdapter.FIXED_COLUMN).get(0) : "");
        }
        if (hold.regionNameTv.getText() != null) {
            /*if (!hold.regionNameTv.getText().toString().equals("全省") && hold.regionNameTv.getText().toString().contains("市") && hold.regionNameTv.getText().toString().length() < 5 && hold.regionNameTv.getText().toString().indexOf(" ") < 0) {
                hold.regionNameTv.setTextColor(Color.parseColor("#181DEC"));
            } else {
                hold.regionNameTv.setTextColor(TableTextColor);
            }*/
            if (hold.regionNameTv.getText().toString().equals("全省") || hold.regionNameTv.getText().toString().indexOf(" ") > 0) {
                hold.regionNameTv.setTextColor(TableTextColor);
            } else {
                hold.regionNameTv.setTextColor(Color.parseColor("#181DEC"));
            }
        }

        for (int i = 0; i < size; i++) {
            String neValue = pageData.getRowData().get(position).getRowData().get(TableDataAdapter.SCROLLABLE_COLUMN).get(i);

            if (isCustomClolor) {
                String[] textAndColor = (neValue == null || "".equals(neValue)) ? new String[]{"", ""} : neValue.split(delimiter);
                if (textAndColor.length > 0) {
                    if (textAndColor[0] == null || "".equals(textAndColor[0]) || "null".equals(textAndColor[0])) {
                        hold.valueTvs[i].setText("" + "-");
                    } else {
                        hold.valueTvs[i].setText("" + textAndColor[0]);
                    }
                }

                if (textAndColor.length > 1) {
                    try {
                        hold.valueTvs[i].setTextColor((textAndColor[1] == null || "".equals(textAndColor[1])) ? TableTextColor : Color.parseColor(ColorUtil.repairColor(textAndColor[1])));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (neValue == null || "".equals(neValue) || "null".equals(neValue)) {
                    hold.valueTvs[i].setText("" + "-");
                } else {
                    hold.valueTvs[i].setText("" + neValue);
                }
            }

        }


        if (position % 2 == 0) {
            TableFirstBg = typearray.getResourceId(R.styleable.FixedHeaderTableView_TableFirstBg, 0);
            if (TableFirstBg == 0) {//未取到资源值
                TableFirstBg = typearray.getColor(R.styleable.FixedHeaderTableView_TableFirstBg, 0);
                if (TableFirstBg == 0) {//未取到颜色值
                    TableFirstBg = R.drawable.selector_list_item_gay;
                    view.setBackgroundResource(TableFirstBg);
                } else {
                    view.setBackgroundColor(TableFirstBg);
                }
            } else {
                view.setBackgroundResource(TableFirstBg);
            }
        } else {
            TableSecondBg = typearray.getResourceId(R.styleable.FixedHeaderTableView_TableSecondBg, 0);
            if (TableSecondBg == 0) {//未取到资源值
                TableSecondBg = typearray.getColor(R.styleable.FixedHeaderTableView_TableSecondBg, 0);
                if (TableSecondBg == 0) {//未取到颜色值
                    TableSecondBg = R.drawable.selector_list_item_white;
                    view.setBackgroundResource(TableSecondBg);
                } else {
                    view.setBackgroundColor(TableSecondBg);
                }
            } else {
                view.setBackgroundResource(TableSecondBg);
            }
        }

//        int bgResource = position % 2 == 0 ?
//                R.drawable.selector_list_item_gay
//                : R.drawable.selector_list_item_white;

        view.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

//        if (position < pageData.getRowData().size()) {
//            hold.regionNameTv.setBackgroundResource(bgResource);
//			hold.regionNameTv.setEnabled(false);
//            for (int i = 0; i < hold.valueTvs.length; i++) {
//                hold.valueTvs[i].setBackgroundResource(bgResource);
//                hold.valueTvs[i].setEnabled(false);
//            }
//
//        }

//        view.setBackgroundColor(Color.TRANSPARENT);
//        view.setBackgroundResource(bgResource);
        view.setOnClickListener(pageData.getRowOnClick().get(position));

        return view;
    }


    private class ViewHold {
        private TextView regionNameTv;
        private TextView[] valueTvs;
        private View viewPortraitSplitline1;
        private RelativeLayout[] layouts;
        private LinearLayout container;
    }

}


