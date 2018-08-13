package com.boco.whl.funddemo.module.activity.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.module.fragment.ComponentFragment;
import com.boco.whl.funddemo.module.fragment.CustomerViewFragment;
import com.boco.whl.funddemo.module.fragment.MainFragment;
import com.boco.whl.funddemo.module.fragment.MyFragment;
import com.boco.whl.funddemo.module.fragment.RegulationFragment;
import com.boco.whl.funddemo.utils.PermissionsUT;
import com.boco.whl.funddemo.widgets.MyRadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;

import static cn.jpush.android.api.JPushInterface.init;
import static cn.jpush.android.api.JPushInterface.setDebugMode;

/**
 * 首页activity
 *
 * @author Administrator
 */
public class IndexActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener
        , MyFragment.OnFragmentInteractionListener
        , RegulationFragment.OnFragmentInteractionListener
        , ComponentFragment.OnFragmentInteractionListener
        , CustomerViewFragment.OnFragmentInteractionListener {

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
    private long mExitTime;
    private static final long PRESS_BACK_INTERVAL = 2000;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        // 设置开启日志,发布时请关闭日志
        setDebugMode(true);
        // 初始化 JPush
        init(this);
        PermissionsUT.getInstance().checkPermissions(this, true);
        initView();
        Log.d("honglei-process", android.os.Process.myPid() + "");
        Log.d("honglei-process-thread", Thread.currentThread().getName()
                + Thread.currentThread().getId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
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
                    RegulationFragment fragment2 = new RegulationFragment();
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
                    CustomerViewFragment fragment4 = new CustomerViewFragment();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > PRESS_BACK_INTERVAL) {
                showToast(toast, "再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000则退出程序
                if (toast != null) {
                    toast.cancel();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
