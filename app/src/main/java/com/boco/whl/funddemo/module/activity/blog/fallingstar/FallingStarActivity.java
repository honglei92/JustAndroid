package com.boco.whl.funddemo.module.activity.blog.fallingstar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;
import com.boco.whl.funddemo.module.activity.blog.fallingstar.view.FallingStarView;
import com.boco.whl.funddemo.utils.IntentUT;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wanghonglei@boco.com.cn
 * @desc 描述-流星雨示例
 * @createTime 2018/1/26 0026
 * @updateTime 2018/1/26 0026
 */

public class FallingStarActivity extends BaseActivity {
    @BindView(R.id.testFallingView)
    FallingStarView testFallingView;
    @BindView(R.id.btnStart)
    Button btnStart;

    public static void doIntent(Activity activity, boolean isFinish) {
        IntentUT.getInstance().openActivity(activity, FallingStarActivity.class, isFinish);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallingstar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnStart)
    public void onViewClicked() {

    }
}
