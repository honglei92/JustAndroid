package com.boco.whl.funddemo.module.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.GuideViewActivity;
import com.boco.whl.funddemo.utils.IntentUT;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Unbinder unbinder;
    @BindView(R.id.update_ll)
    LinearLayout updateLl;
    @BindView(R.id.help_ll)
    LinearLayout helpLl;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MyFragment() {
    }

    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.update_ll, R.id.help_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update_ll:
//                TinkerInstaller.onReceiveUpgradePatch(getActivity().getApplicationContext(),
//                        Environment.getExternalStorageDirectory().getAbsolutePath()
//                                + "/patch_signed_7zip.apk");
                break;
            case R.id.help_ll:
                IntentUT.getInstance().openActivity(getActivity(), GuideViewActivity.class, false);
                break;
        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
