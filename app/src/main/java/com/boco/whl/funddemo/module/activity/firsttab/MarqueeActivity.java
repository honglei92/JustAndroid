package com.boco.whl.funddemo.module.activity.firsttab;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boco.whl.funddemo.R;
import com.sunsky.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

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
public class MarqueeActivity extends Activity {
    @BindView(R.id.upview1)
    MarqueeView upview1;

    private List<String> data;
    List<View> views1 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        ButterKnife.bind(this);
        initdata();
        initView();
    }

    private void initdata() {
        data = new ArrayList<>();
        data.add("git常用命令");
        data.add("Git配置SSH访问GitHub(window)");
        data.add("关于java的抽象和接口");
    }

    private void initView() {
        setViewSingleLine();
        upview1.setViews(views1);
    }

    private void setViewSingleLine() {
        views1.clear();//记得加这句话，不然可能会产生重影现象
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MarqueeActivity.this, position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.size() > position + 1) {
                        Toast.makeText(getApplicationContext(), position + "你点击了" + data.get(position + 1).toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), position + "你点击了" + data.get(0).toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {//奇数条
                tv2.setText(data.get(i + 1).toString());
            } else {//偶数条
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                //moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
                //修改了最后一个没有 将第一个拼接到最后显示
                tv2.setText(data.get(0).toString());
            }

            //添加到循环滚动数组里面去
            views1.add(moreView);
        }
    }

}

