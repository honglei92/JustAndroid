package com.boco.whl.funddemo.module.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boco.whl.funddemo.R;

/**
 * Created by honglei92 on 2017/4/6 0006.
 */

public class CategoryItemAdapter extends BaseAdapter {
    private String[] titles;
    private Context context;

    public CategoryItemAdapter(Context context, String[] titles) {
        this.titles = titles;
        this.context = context;
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
            convertView = mInflater.inflate(R.layout.item_category_item, null);

            viewHolder.title = (TextView) convertView.findViewById(R.id.category_text);
            viewHolder.image = (ImageView) convertView
                    .findViewById(R.id.category_image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(titles[i]);
        viewHolder.image.setImageResource(R.drawable.icon_tec_point);
        return convertView;
    }

    class ViewHolder {
        TextView title;
        ImageView image;
    }
}
