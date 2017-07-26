package com.boco.whl.rxjavademo.ui.activity.firsttab.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.boco.whl.rxjavademo.R;
import com.boco.whl.rxjavademo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/12 0012 11:14
 *  updatetime : 2017/6/12 0012 11:14
 * </pre>
 */
public class ListTest extends Activity {
    @BindView(R.id.lv_test)
    ListView lvTest;
    MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtest);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String[] titles = {"眼镜", "椅子", "多肉", "水杯", "纸巾"};
        adapter = new MyAdapter(ListTest.this, titles);
        lvTest.setAdapter(adapter);
        lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                view.findViewById(R.id.sresult_item_detail).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.showToast(ListTest.this, "detail" + i);
                    }
                });
                view.findViewById(R.id.sresult_item_location).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.showToast(ListTest.this, "location" + i);
                    }
                });
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        private String[] titles;

        public MyAdapter(Context context, String[] titles) {
            this.context = context;
            this.titles = titles;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (null == convertView) {
                viewHolder = new ViewHolder();
                LayoutInflater mInflater = LayoutInflater.from(context);
                convertView = mInflater.inflate(R.layout.sresult_lisv_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.sresult_item_name);
                viewHolder.ll_detail = (LinearLayout) convertView.findViewById(R.id.sresult_item_detail);
                viewHolder.ll_location = (LinearLayout) convertView.findViewById(R.id.sresult_item_location);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.title.setText(titles[i]);
            viewHolder.ll_detail.setOnClickListener(v -> ToastUtil.showToast(ListTest.this, "detail"));
            viewHolder.ll_location.setOnClickListener(v -> ToastUtil.showToast(ListTest.this, "location"));
            return convertView;
        }

        class ViewHolder {
            TextView title;
            LinearLayout ll_detail;
            LinearLayout ll_location;
        }
    }
}

