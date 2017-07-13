package com.boco.whl.rxjavademo.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boco.whl.rxjavademo.R;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/7/7 0007 11:28
 *  updatetime : 2017/7/7 0007 11:28
 * </pre>
 */
public class FavoriteAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<Map<String, String>> resourceDBs;

    public FavoriteAdapter(Activity activity, Context context, List<Map<String, String>> resourceDBs) {
        this.context = context;
        this.activity = activity;
        this.resourceDBs = resourceDBs;
    }

    @Override
    public int getCount() {
        if (resourceDBs.size() % 2 == 0) {
            return resourceDBs.size() / 2;
        } else {
            return (resourceDBs.size() + 1) / 2;
        }
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.favorite_griv_item, null);
            viewHolder.title_left = (TextView) convertView.findViewById(R.id.tv_collection_name_left);
            viewHolder.title_right = (TextView) convertView.findViewById(R.id.tv_collection_name_right);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title_left.setText(resourceDBs.get(position * 2).get(""));
        if (position * 2 + 2 <= resourceDBs.size()) {
            viewHolder.title_right.setText(resourceDBs.get(position * 2 + 1).get(""));
        } else {
            viewHolder.title_right.setText("");
        }
        viewHolder.title_left.setOnClickListener(v1 -> {

        });
        viewHolder.title_right.setOnClickListener(v1 -> {
            if (position * 2 + 2 <= resourceDBs.size()) {

            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView title_left;
        TextView title_right;
    }
}
