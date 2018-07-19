package com.boco.whl.funddemo.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boco.whl.funddemo.R;

/**
 * @author Administrator
 * desc:  基类activity
 * createTime: 2017/8/26 0026
 * updateTime: 2017/8/26 0026
 */

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {
    private Context context = this;
    private Activity activity = this;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //guolin
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            setContentView(getLayoutId());
        }
    }

    protected abstract int getLayoutId();

    /**
     * 自定义toast样式
     *
     * @param toast
     * @param info
     */
    public void showToast(Toast toast, String info) {
        if (toast == null) {
            toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
            LinearLayout layout = (LinearLayout) toast.getView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                layout.setBackground(getDrawable(R.drawable.toast_backgrund));
            } else {
                layout.setBackgroundColor(Color.parseColor("#000000"));
            }
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.parseColor("#eeeeee"));
            v.setPadding(30, 0, 30, 5);
            v.setTextSize(14);
            v.setTypeface(Typeface.SANS_SERIF);
            TextPaint tp = v.getPaint();
            tp.setFakeBoldText(false);
            v.setLayerPaint(tp);
        } else {
            toast.setText(info);
        }
        toast.show();
    }


}