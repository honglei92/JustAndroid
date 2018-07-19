package com.boco.whl.funddemo.module.activity.thirdlib.mvpV2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.boco.whl.funddemo.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class CustomerActivity extends Activity implements CustomerContract.View {

    @BindView(R.id.edit_id)
    EditText editId;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_read)
    Button btnRead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.iv_mvp2)
    ImageView ivMvp2;
    @BindView(R.id.btn_showIv)
    Button btnShowIv;
    private CustomerContract.Presenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ButterKnife.bind(this);
        mUserPresenter = new CustomerPresenter(CustomerActivity.this);
    }

    @OnClick({R.id.btn_save, R.id.btn_read, R.id.btn_showIv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                mUserPresenter.saveUser(editId.getText().toString(), editName.getText().toString(), editPassword.getText().toString());
                break;
            case R.id.btn_read:
                mUserPresenter.loadUser("");
                break;
            case R.id.btn_showIv:
                String url = "http://img1.mp.oeeee.com/201704/25/caa7c6ca32fef5a3.jpg";
                mUserPresenter.loadImage(url);
                break;
        }
    }

    @Override
    public void setUsername(String username) {
        tvName.setText(username);
    }

    @Override
    public void setPassword(String password) {
        tvPassword.setText(password);
    }

    @Override
    public void showImage(String url) {
        Glide.with(CustomerActivity.this).load(url).into(ivMvp2);
    }

    @Override
    public void setPresenter(CustomerContract.Presenter presenter) {
        mUserPresenter = checkNotNull(presenter);
    }
}
