package com.boco.whl.rxjavademo.ui.activity.component;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.boco.whl.rxjavademo.R;
import com.boco.whl.rxjavademo.widgets.MyHorizontalScrollView;


/**
 * 固定表头可滑动表格
 */
public class SlideTableActivity extends Activity {
    private Activity context = SlideTableActivity.this;
    MyHorizontalScrollView view_1;
    MyHorizontalScrollView view_2;
    TableLayout id_table_layout;
    TableLayout data_table_layout;

    private int count;
    private String[] cities = new String[]{"北京", "上海", "广州", "深圳"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_table);
        view_1 = (MyHorizontalScrollView) this
                .findViewById(R.id.HorizontalScrollView_1);
        view_2 = (MyHorizontalScrollView) this
                .findViewById(R.id.HorizontalScrollView_2);
        view_1.setScrollView(view_2);
        view_2.setScrollView(view_1);
        init();
        count = cities.length;
        initContent();
    }

    public void init() {
        id_table_layout = (TableLayout) findViewById(R.id.left_table);
        data_table_layout = (TableLayout) findViewById(R.id.data_table);
        view_1.addView(getHead());
    }

    public void initContent() {
        if (id_table_layout.getChildCount() == count) {
            id_table_layout.removeAllViewsInLayout();
        }
        if (data_table_layout.getChildCount() == count) {
            data_table_layout.removeAllViewsInLayout();
        }
        for (int i = 0; i < count; i++) {
            id_table_layout.addView(getIdRow(i));
            data_table_layout.addView(getDataRow(i));
        }
    }

    public TableRow getIdRow(int n) {
        TableRow row = new TableRow(this);
        View v = this.getLayoutInflater().inflate(R.layout.data_xml, null);
        TextView cityTV = (TextView) v.findViewById(R.id.cityTV);
        cityTV.setText(cities[n]);
        cityTV.setTextColor(Color.BLACK);
        setColor(cityTV, n);
        row.addView(v);
        return row;
    }

    private void setColor(TextView TV, int n) {
        if (n % 2 == 1) {
            TV.setBackgroundResource(R.drawable.text_bar_color_bg);
        }
    }

    public TableRow getDataRow(int n) {
        TableRow row = new TableRow(this);
        View v = this.getLayoutInflater().inflate(R.layout.item_wzl, null);
        TextView text_1 = (TextView) v.findViewById(R.id.text_1);
        text_1.setText("11");
        setColor(text_1, n);
        TextView text_2 = (TextView) v.findViewById(R.id.text_2);
        text_2.setText("11");
        setColor(text_2, n);
        TextView text_3 = (TextView) v.findViewById(R.id.text_3);
        text_3.setText("11");
        setColor(text_3, n);
        TextView text_4 = (TextView) v.findViewById(R.id.text_4);
        text_4.setText("11");
        setColor(text_4, n);
        TextView text_5 = (TextView) v.findViewById(R.id.text_5);
        text_5.setText("11");
        setColor(text_5, n);

        TextView text_6 = (TextView) v.findViewById(R.id.text_6);
        text_6.setText("11");
        setColor(text_6, n);
        TextView text_7 = (TextView) v.findViewById(R.id.text_7);
        text_7.setText("11");
        setColor(text_7, n);
        TextView text_8 = (TextView) v.findViewById(R.id.text_8);
        text_8.setText("11");
        setColor(text_8, n);
        TextView text_9 = (TextView) v.findViewById(R.id.text_9);
        text_9.setText("11");
        setColor(text_9, n);
        TextView text_10 = (TextView) v.findViewById(R.id.text_10);
        text_10.setText("11");
        setColor(text_10, n);

        row.addView(v);
        return row;
    }

    public View getHead() {
        View v = this.getLayoutInflater().inflate(R.layout.item_wzl_head, null);
        TextView text_head = (TextView) v.findViewById(R.id.text_head);
        text_head.setText("无线完整率");
        text_head.setTextColor(Color.BLACK);
        text_head.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_1 = (TextView) v.findViewById(R.id.text_1);
        text_1.setText("排名");
        text_1.setTextColor(Color.BLACK);
        text_1.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_2 = (TextView) v.findViewById(R.id.text_2);
        text_2.setText("缺失信息");
        text_2.setTextColor(Color.BLACK);
        text_2.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_3 = (TextView) v.findViewById(R.id.text_3);
        text_3.setText("信息总量");
        text_3.setTextColor(Color.BLACK);
        text_3.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_4 = (TextView) v.findViewById(R.id.text_4);
        text_4.setText("数据完整率");
        text_4.setTextColor(Color.BLACK);
        text_4.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_5 = (TextView) v.findViewById(R.id.text_5);
        text_5.setText("环比增幅");
        text_5.setTextColor(Color.BLACK);
        text_5.setBackgroundResource(R.drawable.text_bar_head_bg);
        // 6-10
        TextView text_head1 = (TextView) v.findViewById(R.id.text_head1);
        text_head1.setText("空间完整率");
        text_head1.setTextColor(Color.BLACK);
        text_head1.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_6 = (TextView) v.findViewById(R.id.text_6);
        text_6.setText("排名");
        text_6.setTextColor(Color.BLACK);
        text_6.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_7 = (TextView) v.findViewById(R.id.text_7);
        text_7.setText("缺失信息");
        text_7.setTextColor(Color.BLACK);
        text_7.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_8 = (TextView) v.findViewById(R.id.text_8);
        text_8.setText("信息总量");
        text_8.setTextColor(Color.BLACK);
        text_8.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_9 = (TextView) v.findViewById(R.id.text_9);
        text_9.setText("数据完整率");
        text_9.setTextColor(Color.BLACK);
        text_9.setBackgroundResource(R.drawable.text_bar_head_bg);
        TextView text_10 = (TextView) v.findViewById(R.id.text_10);
        text_10.setText("环比增幅");
        text_10.setTextColor(Color.BLACK);
        text_10.setBackgroundResource(R.drawable.text_bar_head_bg);
        return v;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
