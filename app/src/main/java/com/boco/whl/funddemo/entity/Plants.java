package com.boco.whl.funddemo.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import android.text.TextUtils;
import android.widget.ImageView;

import com.boco.whl.funddemo.utils.image.ImageLoader;


/**
 * @author:honglei92
 * @time:2018/8/21
 */
public class Plants extends BaseObservable {
    private String name;
    private String color;


    private String imageUrl;
    private boolean hasFlower;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.test);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHasFlower() {
        return hasFlower;
    }

    public void setHasFlower(boolean hasFlower) {
        this.hasFlower = hasFlower;
        notifyPropertyChanged(BR.hasFlower);

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.test);
    }

    @BindingAdapter("show")
    public static void showIcon(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            ImageLoader imageLoader = new ImageLoader();
            imageLoader.displayImage(url, imageView);
        }
    }
}
