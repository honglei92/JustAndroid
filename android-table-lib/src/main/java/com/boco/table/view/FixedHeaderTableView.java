package com.boco.table.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boco.table.R;
import com.boco.table.adapter.TableAdapter;
import com.boco.table.adapter.TableDataAdapter;
import com.boco.table.po.PageData;
import com.boco.table.util.ColorUtil;
import com.boco.table.util.DisplayUtil;
import com.boco.table.util.TableUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JY
 * @date 2016/11/9
 */

public class FixedHeaderTableView extends LinearLayout {

    private LayoutInflater layoutInflater;

    protected ListView listView;
    protected LinearLayout table_header;
    protected LinearLayout headTabContainer;
    protected TextView tv_header_name;
    protected CHScrollView chs_header;
    protected RelativeLayout tv_header_layout;

    private TypedArray typearray;

    private TableAdapter tableAdapter;

    private int sortState[];

    private int TableHeaderHeigh; //表头高度
    private int TablHeigh; //表高度
    private int TableHeaderWidth; //固定表头宽度
    private int TableHeaderTextSize; //表头字体大小
    private int TableHeaderTextColor;  //表头字体颜色
    private int TableTextSize; //表字体大小
    private int TableTextColor;  //表字体颜色
    private int TableSplitLineSize; //表分割线大小
    private int TableSplitLineColor;  //表分割线颜色
    private int TableHeaderColor;  //表头颜色
    private int TableTextLine;//字行数，超过省略号显示
    private boolean TableScroll;//是否可滑动 默认true

    protected List<CHScrollView> mHScrollViews = new ArrayList<CHScrollView>();

    private TableDataAdapter adapter = null; // 使用方传入的数据适配器对象

    private Activity mContext;

    private boolean isTransverseScroll=false;//是否可以滑动
    private int cellWidth;//滑动单元格宽度
    private int[] cellWidths;//滑动单元格宽度集合
    private boolean isSelfAdaption=false;//是否自适应

    private boolean isSort=false;//是否排序
    private boolean isSortInitialization=false;//是否初始化

    protected List defaultList;//原始数据

    protected boolean isCustomClolor;//是否自定义每个字体颜色
    protected String delimiter;//分隔符(表格数据与字体颜色拼接)

    protected PageData pageData;

    public boolean isSort() {
        return isSort;
    }

    public void setSort(boolean sort) {
        isSort = sort;
    }

    public void setTransverseScroll(boolean transverseScroll, int cellWidth) {
        this.isTransverseScroll = transverseScroll;
        this.cellWidth=cellWidth;
    }

    public void setTransverseScroll(boolean transverseScroll) {
        this.isTransverseScroll = transverseScroll;
        this.cellWidth=-1;
        this.isSelfAdaption=true;
    }

    public void setTransverseScroll(boolean transverseScroll,int[] cellWidths) {
        this.isTransverseScroll = transverseScroll;
        this.cellWidths=cellWidths;
        this.cellWidth=-1;
    }

    public void setCustomTextColor(boolean isCustomClolor,String delimiter){
        this.isCustomClolor=isCustomClolor;
        this.delimiter=delimiter;
    }

    public Activity getmContext() {
        return mContext;
    }

    public void setmContext(Activity mContext) {
        this.mContext = mContext;
    }

    public TableDataAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(TableDataAdapter adapter) {
        this.adapter = adapter;
    }

    public FixedHeaderTableView(Context context) {
        this(context, null);
    }

    public FixedHeaderTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixedHeaderTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        initattrs(context,attrs);

        // 从 Layout XML文件中加载控件
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (TableScroll){
            layoutInflater.inflate(R.layout.boco_fixed_header_table, this);
        }else {
            layoutInflater.inflate(R.layout.boco_fixed_header_table_scroll, this);
        }


        listView= (ListView) findViewById(R.id.table_list_view);

        table_header= (LinearLayout) findViewById(R.id.table_header);
        table_header.setVisibility(View.INVISIBLE);
        table_header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT
                ,TableHeaderHeigh));
        table_header.setBackgroundColor(TableHeaderColor);

        headTabContainer= (LinearLayout) findViewById(R.id.vg_head_tab_container);

        tv_header_name= (TextView) findViewById(R.id.tv_header_name);

        tv_header_layout= (RelativeLayout) findViewById(R.id.tv_header_layout);
        tv_header_layout.setLayoutParams(new RelativeLayout.LayoutParams(TableHeaderWidth, RelativeLayout.LayoutParams.MATCH_PARENT));

        chs_header= (CHScrollView) findViewById(R.id.chs_head);

        View viewTransverseSplitline1=findViewById(R.id.viewTransverseSplitline1);
        viewTransverseSplitline1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,TableSplitLineSize));
        viewTransverseSplitline1.setBackgroundColor(TableSplitLineColor);

        View viewTransverseSplitline2=findViewById(R.id.viewTransverseSplitline2);
        viewTransverseSplitline2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,TableSplitLineSize));
        viewTransverseSplitline2.setBackgroundColor(TableSplitLineColor);

        View viewPortraitSplitline1=findViewById(R.id.viewPortraitSplitline1);
        RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) viewPortraitSplitline1.getLayoutParams();
        lp.width=TableSplitLineSize;
        viewPortraitSplitline1.setLayoutParams(lp);
        viewPortraitSplitline1.setBackgroundColor(TableSplitLineColor);

        chs_header.addView(chs_header,listView,mHScrollViews);

    }

    /**
     * 初始化排序状态
     * //0 默认排序　1升序   2降序　
     */
    private void initSortData(){
        if (isSort&&!isSortInitialization){
            Map<Integer, String[]> headerMap=adapter.getColHeaders();
            int headerLength=headerMap.get(TableDataAdapter.FIXED_COLUMN).length+headerMap.get(TableDataAdapter.SCROLLABLE_COLUMN).length;
            sortState=new int[headerLength];
            for (int i = 0; i <sortState.length ; i++) {
                sortState[i]=0;
            }

            initDefaultData();

            isSortInitialization=true;

        }
    }

    /**
     * 记录表格默认数据
     */
    private void initDefaultData(){
        defaultList = new ArrayList<>();
        if (adapter.getData()!=null&&adapter.getData().size()>0){
            for (int i = 0; i < adapter.getData().size(); i++) {
                defaultList.add(adapter.getData().get(i));
            }
        }
    }

    private void initattrs(Context context,AttributeSet attrs){
        typearray = context.obtainStyledAttributes(attrs, R.styleable.FixedHeaderTableView);
        TableHeaderHeigh= (int) typearray.getDimension(R.styleable.FixedHeaderTableView_TableHeaderHeigh,getResources().getDimension(R.dimen.table_min_height)); //表头高度
        TablHeigh=(int) typearray.getDimension(R.styleable.FixedHeaderTableView_TablHeigh,getResources().getDimension(R.dimen.table_min_height)); //表高度
        TableHeaderWidth=(int) typearray.getDimension(R.styleable.FixedHeaderTableView_TableHeaderWidth,getResources().getDimension(R.dimen.table_fixed_min_width)); //固定表头宽度
        TableHeaderTextSize=(int) typearray.getDimension(R.styleable.FixedHeaderTableView_TableHeaderTextSize,getResources().getDimension(R.dimen.text_header_size)); //表头字体大小
        TableHeaderTextColor=typearray.getColor(R.styleable.FixedHeaderTableView_TableHeaderTextColor,getResources().getColor(R.color.text_header_color));  //表头字体颜色
        TableTextSize=(int) typearray.getDimension(R.styleable.FixedHeaderTableView_TableTextSize,getResources().getDimension(R.dimen.text_data_size)); //表字体大小
        TableTextColor=typearray.getColor(R.styleable.FixedHeaderTableView_TableTextColor,getResources().getColor(R.color.text_data_color));  //表字体颜色
        TableSplitLineSize=(int) typearray.getDimension(R.styleable.FixedHeaderTableView_TableSplitLineSize, DisplayUtil.dip2px(context,getResources().getDimension(R.dimen.split_line))); //表分割线大小
        TableSplitLineColor=typearray.getColor(R.styleable.FixedHeaderTableView_TableSplitLineColor,getResources().getColor(R.color.split_line_bg));  //表分割线颜色
        TableHeaderColor=typearray.getColor(R.styleable.FixedHeaderTableView_TableHeaderColor,getResources().getColor(R.color.text_header_bg));  //表头颜色
        TableTextLine=typearray.getInteger(R.styleable.FixedHeaderTableView_TableTextLine,1);
        TableScroll=typearray.getBoolean(R.styleable.FixedHeaderTableView_TableScroll,true);
    }

    private void setDatas(Activity context, final String title, final String[] titles) {
        initHead(context,title,titles);
        setmContext(context);
    }

    /**
     * 初始化表头
     *
     * @param titles
     *            返回的表头数据
     */
    private void initHead(Activity context, final String title, final String[] titles) {
        headTabContainer.removeAllViews();
        tv_header_name.setTextColor(TableHeaderTextColor);
        if (isCustomClolor){
            String[] textAndColor=(title==null||"".equals(title))?new String[]{"",""}:title.split(delimiter);
            if (textAndColor.length>0){
                tv_header_name.setText(textAndColor[0]);
            }
            if (textAndColor.length>1){
                try {
                    tv_header_name.setTextColor((textAndColor[1]==null||"".equals(textAndColor[1]))?TableHeaderTextColor:Color.parseColor(ColorUtil.repairColor(textAndColor[1])));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            tv_header_name.setText(title);

        }
        tv_header_name.setTextSize(TypedValue.COMPLEX_UNIT_PX,TableHeaderTextSize);
        tv_header_name.setLines(TableTextLine);

        if (isSort){
            ImageView sequence_img= (ImageView) findViewById(R.id.sequence_img);
            if (sortState!=null&&sortState.length>0){
                if (sortState[0] == 0) {
                    sequence_img.setVisibility(View.GONE);
                } else if (sortState[0] == 1) {
                    sequence_img.setVisibility(View.VISIBLE);
                    sequence_img
                            .setImageResource(R.drawable.ico_sequence_ascending);
                } else if (sortState[0] == 2) {
                    sequence_img.setVisibility(View.VISIBLE);
                    sequence_img
                            .setImageResource(R.drawable.ico_sequence_descending);
                }

                tv_header_layout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int selectIndex=0;
                        if (sortState[selectIndex] == 0) {//升序
                            if (adapter.addComparator()!=null&&adapter.addComparator().size()>0){
                                Comparator comparator= (Comparator) adapter.addComparator().get(0);
                                if (comparator!=null){
                                    for (int j = 0; j < sortState.length; j++) {
                                        if (j == selectIndex) {
                                            sortState[selectIndex] = 1;
                                        } else {
                                            sortState[j] = 0;
                                        }
                                    }

                                    Collections.sort(adapter.getData(), comparator);
                                    refreshTable();
                                }
                            }

                        } else if (sortState[selectIndex] == 1) {//降序

                            if (adapter.addComparator()!=null&&adapter.addComparator().size()>0){
                                Comparator comparator= (Comparator) adapter.addComparator().get(0);
                                if (comparator!=null){
                                    for (int j = 0; j < sortState.length; j++) {
                                        if (j == selectIndex) {
                                            sortState[selectIndex] = 2;
                                        } else {
                                            sortState[j] = 0;
                                        }
                                    }

                                    comparator = Collections.reverseOrder(comparator);
                                    Collections.sort(adapter.getData(), comparator);
                                    refreshTable();
                                }
                            }

                        } else if (sortState[selectIndex] == 2) {//还原默认
                            for (int j = 0; j < sortState.length; j++) {
                                sortState[j] = 0;
                            }
                            adapter.getData().clear();
                            for (int i = 0; i < defaultList.size(); i++) {
                                adapter.getData().add(defaultList.get(i));
                            }
                            refreshTable();
                        }
//                        refreshTable();
                    }
                });
            }

        }


        if (isTransverseScroll&&isSelfAdaption){
            cellWidths=new int[titles.length];

            for (int i = 0; i <titles.length ; i++) {
                TextView childTv = new TextView(context);
                childTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,TableHeaderTextSize);
                TextPaint textPaint = childTv.getPaint();
                int wdith=(int)textPaint.measureText(titles[i]);
                cellWidths[i]=(wdith/TableTextLine)<getResources().getDimension(R.dimen.table_fixed_min_width)?(int)getResources().getDimension(R.dimen.table_fixed_min_width):wdith/TableTextLine;
            }

            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);
            int screenWidth = dm.widthPixels; // 获取屏幕宽度

            float surplusWidth = TableHeaderWidth +
                    TableSplitLineSize* (titles.length+1);//多余宽度
            float scrollWidth=screenWidth - surplusWidth;//实际屏幕剩余宽度
            float textSumWidth=0;
            for (int i = 0; i <cellWidths.length ; i++) {
                textSumWidth=textSumWidth+cellWidths[i]+context.getResources().getDimension(R.dimen.table_padding)*2;
            }

            if (textSumWidth<scrollWidth){
                int addWidth=(int)((scrollWidth-textSumWidth)/titles.length);
                for (int i = 0; i <cellWidths.length ; i++){
                    cellWidths[i]=cellWidths[i]+addWidth;
                }
            }

        }

        for (int i = 0; i < titles.length; i++) {

            final int selectIndex = i+1;

//            LinearLayout layout=new LinearLayout(context);
//            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//            layout.setLayoutParams(layoutParams);
//            layout.setOrientation(LinearLayout.HORIZONTAL);

            RelativeLayout layout = new RelativeLayout(context);

            TextView childTv = new TextView(context);
            childTv.setTextColor(TableHeaderTextColor);
            if (isCustomClolor){
                String[] textAndColor=(titles[i]==null||"".equals(titles[i]))?new String[]{"",""}:titles[i].split(delimiter);
                if (textAndColor.length>0){
                    childTv.setText(textAndColor[0]);
                }
                if (textAndColor.length>1){
                    try {
                        childTv.setTextColor((textAndColor[1]==null||"".equals(textAndColor[1]))?TableHeaderTextColor:Color.parseColor(ColorUtil.repairColor(textAndColor[1])));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                childTv.setText(titles[i]);
            }

            childTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,TableHeaderTextSize);
            childTv.setLines(TableTextLine);
            childTv.setEllipsize(TextUtils.TruncateAt.END);
            childTv.setGravity(Gravity.CENTER);


//            if (isTransverseScroll&&isSelfAdaption){
//                TextPaint textPaint = childTv.getPaint();
//                int wdith=(int)textPaint.measureText(titles[i]);
//                cellWidths[i]=(wdith/TableTextLine)<getResources().getDimension(R.dimen.table_fixed_min_width)?(int)getResources().getDimension(R.dimen.table_fixed_min_width):wdith/TableTextLine;
//            }

            if (isSort){

                RelativeLayout.LayoutParams childTVParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                childTVParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                childTVParams.addRule(RelativeLayout.CENTER_VERTICAL);
                layout.addView(childTv, childTVParams);

                final ImageView childImageView = new ImageView(context);
                if (sortState[selectIndex] == 0) {
                } else if (sortState[selectIndex] == 1) {
                    childImageView
                            .setImageResource(R.drawable.ico_sequence_ascending);
                } else if (sortState[selectIndex] == 2) {
                    childImageView
                            .setImageResource(R.drawable.ico_sequence_descending);
                }

                RelativeLayout.LayoutParams childParams = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(
                        context, 15), DisplayUtil.dip2px(context, 15));
                childParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//            childParams.rightMargin = 10;
                childParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                layout.addView(childImageView, childParams);

                final int j=i;
                layout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (sortState[selectIndex] == 0) {//升序
                            if (adapter.addComparator()!=null&&adapter.addComparator().size()>0) {
                                Comparator comparator = (Comparator) adapter.addComparator().get(j + 1);
                                if (comparator!=null){
                                    for (int j = 0; j < sortState.length; j++) {
                                        if (j == selectIndex) {
                                            sortState[selectIndex] = 1;
                                        } else {
                                            sortState[j] = 0;
                                        }
                                    }

                                    Collections.sort(adapter.getData(), comparator);
                                    refreshTable();
                                }

                            }

                        } else if (sortState[selectIndex] == 1) {//降序
                            if (adapter.addComparator()!=null&&adapter.addComparator().size()>0) {
                                Comparator comparator = (Comparator) adapter.addComparator().get(j + 1);
                                if (comparator!=null){
                                    for (int j = 0; j < sortState.length; j++) {
                                        if (j == selectIndex) {
                                            sortState[selectIndex] = 2;
                                        } else {
                                            sortState[j] = 0;
                                        }
                                    }

                                    comparator=Collections.reverseOrder(comparator);
                                    Collections.sort(adapter.getData(), comparator);
                                    refreshTable();
                                }

                            }

                        } else if (sortState[selectIndex] == 2) {//还原默认
                            for (int j = 0; j < sortState.length; j++) {
                                sortState[j] = 0;
                            }
                            adapter.getData().clear();
                            for (int i = 0; i < defaultList.size(); i++) {
                                adapter.getData().add(defaultList.get(i));
                            }
                            refreshTable();
                        }
//                        refreshTable();

                    }
                });
            }else {
                layout.addView(childTv);
                layout.setGravity(Gravity.CENTER);
            }

            LayoutParams params=null;
            if (cellWidth==-1){
                if (cellWidths!=null&&i<cellWidths.length){
                    params = TableUtil.getLayoutParams(context, titles.length + 1,TableHeaderWidth,TableSplitLineSize,isTransverseScroll,cellWidths[i]);
                }else {
                    params = TableUtil.getLayoutParams(context, titles.length + 1,TableHeaderWidth,TableSplitLineSize,isTransverseScroll,0);
                }
//                layout.setPadding((int)getResources().getDimension(R.dimen.table_padding),0,(int)getResources().getDimension(R.dimen.table_padding),0);
            }else {
                params = TableUtil.getLayoutParams(context, titles.length + 1,TableHeaderWidth,TableSplitLineSize,isTransverseScroll,cellWidth);
            }
            headTabContainer.addView(layout, params);

            if (titles.length >= 2 && i < titles.length-1) {
                View divider = new View(context);
                divider.setBackgroundColor(TableSplitLineColor);
                LayoutParams divierParams = new LayoutParams(TableSplitLineSize,
                        LayoutParams.MATCH_PARENT);
                headTabContainer.addView(divider, divierParams);
            }
        }

        table_header.setVisibility(View.VISIBLE);
        if (title!=null&&titles!=null){
            LinearLayout layoutParent= (LinearLayout) findViewById(R.id.layoutParent);
            layoutParent.setVisibility(View.VISIBLE);
        }

    }


    /**
     * 强制重置并刷新表格
     */
    public void refreshTable() {
        initSortData();
        new InitTableStruct().execute();
    }

    private class InitTableStruct extends AsyncTask<Void, Void, PageData> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected PageData doInBackground(Void... params) {
            int endRow = adapter.getTotalRows()-1;
            int startRow = 0;

            if (pageData==null){
                pageData = new PageData();
            }
            pageData.setHeadData(adapter.getColHeaders()) ;
            pageData.setRowData(adapter.getRows(startRow, endRow));
            List<OnClickListener> rowOnClick = new ArrayList<OnClickListener>();
            Map<Integer,List<OnClickListener>> cellOnClick = new HashMap<Integer,List<OnClickListener>>();
            int dataSize = pageData.getRowData().size();
            for (int i = 0; i < dataSize; ++i) {
                rowOnClick.add(adapter
                        .getOnClickListener(startRow + i));
                List<OnClickListener> listener=new ArrayList<OnClickListener>();
                for (int j = 0; j < pageData.getHeadData().get(TableDataAdapter.SCROLLABLE_COLUMN).length; j++) {
                    listener.add(adapter
                            .getCellOnClickListener(startRow + i,j));
                }
                cellOnClick.put(i,listener);
            }
            pageData.setRowOnClick(rowOnClick);
            pageData.setCellOnClick(cellOnClick);
            return pageData;
        }

        @Override
        protected void onPostExecute(PageData pageData) {

            setDatas(adapter.getMContext(),pageData.getHeadData().get(TableDataAdapter.FIXED_COLUMN).length>0?pageData.getHeadData().get(TableDataAdapter.FIXED_COLUMN)[0]:"",pageData.getHeadData().get(TableDataAdapter.SCROLLABLE_COLUMN));

            setTableData(pageData);

        }

    }

    private void setTableData(PageData pageData) {
        if (tableAdapter==null){
            tableAdapter=new TableAdapter(mContext,pageData,listView,mHScrollViews,typearray,isTransverseScroll,cellWidth,cellWidths);
            tableAdapter.setTableDataAdapter(adapter);
            listView.setAdapter(tableAdapter);
        }else {
            tableAdapter.setTableDataAdapter(adapter);
            tableAdapter.notifyDataSetChanged();
        }
        if (isCustomClolor){
            tableAdapter.setCustomTextColor(isCustomClolor,delimiter);
        }

        listView.setDivider(new ColorDrawable(TableSplitLineColor));
        listView.setDividerHeight(TableSplitLineSize);
    }

}
