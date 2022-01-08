package com.boco.whl.funddemo.module.activity.component.jetpack.navigation.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.component.jetpack.lifecycle.LifeCycleObserverTest;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author:honglei92
 * @time:2018/8/23
 */
public class MainPage1Fragment extends Fragment {
    @BindView(R.id.btnGoto2)
    Button btnGoto2;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getLifecycle().addObserver(new LifeCycleObserverTest());
        View view = inflater.inflate(R.layout.fragment_mainpage1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnGoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_mainPage1Fragment_to_mainPage2Fragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnGoto2)
    public void onViewClicked() {
    }
}
