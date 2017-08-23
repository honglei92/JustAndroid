package com.boco.whl.rxjavademo.module.activity.component.slidetable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.boco.table.adapter.MultipleHeaderTableAdapter;
import com.boco.whl.rxjavademo.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 固定表头可滑动表格
 */
public class SlideTableActivity extends Activity {
    @BindView(R.id.fixed_table)
    com.boco.table.view.FixedMultipleHeaderTableView fixedTable;
    private Activity context = SlideTableActivity.this;
    private List<Model> modelList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_table);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

        modelList = new ArrayList<>();
        modelList.add(new Model("zaaaa", "23", "1994-09-23", "85", "成都"));
        modelList.add(new Model("bbbbb", "25", "1994-09-23", "80", "成都"));
        modelList.add(new Model("ccccccccccccccc", "27", "1994-09-23", "90", "成都"));
        modelList.add(new Model("ddddd", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("eeeee", "23", "1994-09-23", "85", "成都"));
        modelList.add(new Model("fffff", "25", "1994-09-23", "80", "成都"));
        modelList.add(new Model("ggggg", "27", "1994-09-23", "90", "成都"));
        modelList.add(new Model("hhhhh", "28", "1994-09-23", "88", "成都"));

        modelList.add(new Model("iiiii", "27", "1994-09-23", "90", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));

        modelList.add(new Model("iiiii", "27", "1994-09-23", "90", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));
        modelList.add(new Model("jjjjj", "28", "1994-09-23", "88", "成都"));

        fixedTable.setOnClickListener(v -> {
        });

        ListView listView = new ListView(this);
        listView.setOnItemClickListener((adapterView, view, i, l) ->
                adapterView.getItemAtPosition(i));

        Map<String, List<String>> viceHeaders = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("年龄");
        viceHeaders.put("1", list);

        list = new ArrayList<>();
        list.add("出生日期出生日期出生日期出生日期出生日期");
        list.add("成绩");
        viceHeaders.put("2", list);

        list = new ArrayList<>();
        list.add("住址");
        viceHeaders.put("3", list);

        List<String> headerList = new ArrayList<>();
        headerList.add("姓名");
        headerList.add("1");
        headerList.add("2");
        headerList.add("3");


        fixedTable.setAdapter(new MultipleHeaderTableAdapter<Model>(this, headerList, viceHeaders, modelList) {
            @Override
            public void onItemClickListener(int row) {
                Toast.makeText(SlideTableActivity.this, modelList.get(row).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCellClickListener(int row, int column) {
                Toast.makeText(SlideTableActivity.this, "row=" + row + "\n column=" + column, Toast.LENGTH_SHORT).show();
            }

            @Override
            public Map<Integer, Comparator<Model>> addComparator() {
                return null;
            }

            @Override
            public int getImgResId(int row, int column) {
                if (modelList.get(row).getAge().equals("23") && column == 0) {
                    return com.boco.table.R.drawable.ico_sequence_descending;
                }
                return com.boco.table.R.drawable.ico_sequence_ascending;
            }


            @Override
            protected List<String> convertRowModel(Model model) {

                List<String> sCols = new ArrayList<>();
                sCols.add(model.getName());
                sCols.add(model.getAge());
                sCols.add(model.getBirth());
                sCols.add(model.getGrade());
                sCols.add(model.getAddress());

                return sCols;
            }
        });

        fixedTable.setAdapter(new MultipleHeaderTableAdapter<Model>(this,headerList,viceHeaders, modelList) {
            @Override
            public void onItemClickListener(int row) {
                Toast.makeText(SlideTableActivity.this, modelList.get(row).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCellClickListener(int row, int column) {
                Toast.makeText(SlideTableActivity.this, "row="+row+"\n column="+column, Toast.LENGTH_SHORT).show();
            }

            @Override
            public Map<Integer, Comparator<Model>> addComparator() {
                Map<Integer, Comparator<Model>> map=new HashMap<Integer, Comparator<Model>>();
                Comparator comparator0=new Comparator() {
                    @Override
                    public int compare(Object o, Object t1) {
                        Model m1= (Model) o;
                        Model m2= (Model) t1;
                        return m1.getName().compareTo(m2.getName());
                    }
                };
                Comparator comparator1=new Comparator() {
                    @Override
                    public int compare(Object o, Object t1) {
                        Model m1= (Model) o;
                        Model m2= (Model) t1;
                        return m1.getAge().compareTo(m2.getAge());
                    }
                };
                Comparator comparator2=new Comparator() {
                    @Override
                    public int compare(Object o, Object t1) {
                        Model m1= (Model) o;
                        Model m2= (Model) t1;
                        return m1.getGrade().compareTo(m2.getGrade());
                    }
                };
                map.put(0,comparator0);
                map.put(1,comparator1);
                map.put(3,comparator2);
                return map ;
            }


            @Override
            public View addCellView(int row, int column) {
                ImageView childImageView = new ImageView(SlideTableActivity.this);

                if (row%2==0){
                    childImageView
                            .setImageResource(R.mipmap.boco_ic_ind_down);
                }else {
                    childImageView
                            .setImageResource(R.mipmap.boco_ic_ind_up);
                }
                RelativeLayout.LayoutParams childParams = new RelativeLayout.LayoutParams(20, 30);
                childParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                childParams.addRule(RelativeLayout.CENTER_VERTICAL);
                childImageView.setLayoutParams(childParams);
                Log.e("addCellView=========","row===="+row+"------column======"+column);
                return childImageView;
            }

            @Override
            protected List<String> convertRowModel(Model model) {

                List<String> sCols = new ArrayList<>();
                sCols.add(model.getName());
                sCols.add(model.getAge());
                sCols.add(model.getBirth());
                sCols.add(model.getGrade());
                sCols.add(model.getAddress());

                return sCols;
            }
        });
        //是否横向滑动
        fixedTable.setTransverseScroll(true);
        fixedTable.setSort(true);
//        test.setTransverseScroll(true,140);
        // 刷新表格，展现数据
        fixedTable.refreshTable();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
