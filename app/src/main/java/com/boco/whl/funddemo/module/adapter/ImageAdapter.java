package com.boco.whl.funddemo.module.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.utils.image.ImageLoader;
import com.boco.whl.funddemo.widgets.SquareImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 照片墙适配器
 *
 * @author honglei92
 * @createTime 2018/5/17 0017
 */
public class ImageAdapter extends BaseAdapter {
    private List<String> mUrList = new ArrayList<>();
    private Context mContext;
    private Drawable mDefaultBitmapDrawable;
    private Boolean mIsGridViewIdle;
    private int mImageWidth = 200;
    ImageLoader mImageLoader = new ImageLoader();
    private final static int IMAGE_COUNT = 100;

    String[] urlArray = new String[]{
            "https://wx4.sinaimg.cn/mw1024/6b6562f4gy1fo4mo6z7h2j20qo0zkwi1.jpg",
            "https://wx4.sinaimg.cn/mw1024/6b6562f4gy1fo4morhss4j20qo0zk457.jpg",
            "https://wx1.sinaimg.cn/mw1024/6b6562f4gy1fmy3ause49j20qo0zk46b.jpg",
            "https://wx3.sinaimg.cn/mw1024/6b6562f4gy1flgf6bk2eej20qo0zkqaz.jpg",
            "https://wx2.sinaimg.cn/mw1024/6b6562f4ly1ff1jy0qf99j20qo0zktdz.jpg",
    };

    public ImageAdapter(Context context, boolean mIsGridViewIdle) {
        for (int i = 0; i < IMAGE_COUNT; i++) {
            mUrList.add(urlArray[i % 5]);
        }
        this.mContext = context;
        this.mIsGridViewIdle = mIsGridViewIdle;
        mDefaultBitmapDrawable = context.getResources().getDrawable(R.drawable.app);
    }

    @Override
    public int getCount() {
        return mUrList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUrList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_photo_wall, null);
            holder = new ViewHolder();
            holder.imageView = (SquareImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SquareImageView imageView = holder.imageView;
        final String tag = (String) imageView.getTag();
        final String uri = (String) getItem(position);
        if (!uri.equals(tag)) {
            imageView.setImageDrawable(mDefaultBitmapDrawable);
        }
        if (mIsGridViewIdle) {
            imageView.setTag(uri);
//            mImageLoader.bindBitmap(uri, imageView, mImageWidth, mImageWidth);
            mImageLoader.displayImage(uri, imageView);
        }
        return convertView;
    }

    class ViewHolder {
        SquareImageView imageView;

    }
}
