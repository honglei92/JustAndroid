package com.boco.whl.funddemo.module.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.base.BaseApplication;
import com.boco.whl.funddemo.module.main.fragment.BlogFragment;
import com.boco.whl.funddemo.module.main.fragment.ComponentFragment;
import com.boco.whl.funddemo.module.main.fragment.MainFragment;
import com.boco.whl.funddemo.module.main.fragment.MyFragment;
import com.boco.whl.funddemo.module.main.fragment.SoftWareFragment;
import com.boco.whl.funddemo.utils.PermissionsUT;
import com.boco.whl.funddemo.utils.StringUtil;
import com.boco.whl.funddemo.widgets.MyRadioButton;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

/**
 * 首页activity
 */
public class IndexActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener
        , MyFragment.OnFragmentInteractionListener
        , SoftWareFragment.OnFragmentInteractionListener
        , ComponentFragment.OnFragmentInteractionListener
        , BlogFragment.OnFragmentInteractionListener {

    @BindView(R.id.contentfragment)
    FrameLayout contentfragment;
    @BindView(R.id.radio1)
    MyRadioButton radio1;
    @BindView(R.id.radio2)
    MyRadioButton radio2;
    @BindView(R.id.radio3)
    MyRadioButton radio3;
    @BindView(R.id.radio4)
    MyRadioButton radio4;
    @BindView(R.id.radio5)
    MyRadioButton radio5;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        PermissionsUT.getInstance().checkPermissions(this, true);
        initView();
        Logger.i("onCreate: " + StringUtil.getNull(BaseApplication.VALUE));
        Logger.e("onCreate: " + StringUtil.getNull(BaseApplication.VALUE));
    }

    private void initView() {
        radio1.setSelected(true);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MainFragment fragment1 = new MainFragment();
        transaction.add(R.id.contentfragment, fragment1);
        transaction.commit();
        radiogroup.setOnCheckedChangeListener((RadioGroup radioGroup, @IdRes int i) -> {
            FragmentManager manager0 = getSupportFragmentManager();
            switch (i) {
                case R.id.radio1:
                    radio1.setSelected(true);
                    radio2.setSelected(false);
                    radio3.setSelected(false);
                    radio4.setSelected(false);
                    radio5.setSelected(false);
                    FragmentTransaction transaction0 = manager0.beginTransaction();
                    MainFragment fragment0 = new MainFragment();
                    transaction0.replace(R.id.contentfragment, fragment0);
                    transaction0.commit();
                    break;
                case R.id.radio2:
                    radio1.setSelected(false);
                    radio2.setSelected(true);
                    radio3.setSelected(false);
                    radio4.setSelected(false);
                    radio5.setSelected(false);
                    FragmentTransaction transaction2 = manager.beginTransaction();
                    SoftWareFragment fragment2 = new SoftWareFragment();
                    transaction2.replace(R.id.contentfragment, fragment2);
                    transaction2.commit();
                    break;
                case R.id.radio3:
                    radio1.setSelected(false);
                    radio2.setSelected(false);
                    radio3.setSelected(true);
                    radio4.setSelected(false);
                    radio5.setSelected(false);
                    FragmentTransaction transaction3 = manager.beginTransaction();
                    ComponentFragment fragment3 = new ComponentFragment();
                    transaction3.replace(R.id.contentfragment, fragment3);
                    transaction3.commit();
                    break;
                case R.id.radio4:
                    radio1.setSelected(false);
                    radio2.setSelected(false);
                    radio3.setSelected(false);
                    radio4.setSelected(true);
                    radio5.setSelected(false);
                    FragmentTransaction transaction4 = manager.beginTransaction();
                    BlogFragment fragment4 = new BlogFragment();
                    transaction4.replace(R.id.contentfragment, fragment4);
                    transaction4.commit();
                    break;
                case R.id.radio5:
                    radio1.setSelected(false);
                    radio2.setSelected(false);
                    radio3.setSelected(false);
                    radio4.setSelected(false);
                    radio5.setSelected(true);
                    FragmentTransaction transaction5 = manager.beginTransaction();
                    MyFragment fragment5 = new MyFragment();
                    transaction5.replace(R.id.contentfragment, fragment5);
                    transaction5.commit();
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
