package com.boco.whl.funddemo.module.activity.annndroid.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.annndroid.mvp.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends Activity implements IUserView {

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
    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        mUserPresenter = new UserPresenter(UserActivity.this);
    }

    @OnClick({R.id.btn_save, R.id.btn_read})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                mUserPresenter.saveUser(editId.getText().toString(), editName.getText().toString(), editPassword.getText().toString());
                break;
            case R.id.btn_read:
                mUserPresenter.loadUser("");
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
}
