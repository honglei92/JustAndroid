package com.boco.whl.funddemo.module.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.fragment.component.TrainingFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 安卓基本组件的使用
 */
public class ComponentFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.queryimage)
    ImageView queryimage;
    @BindView(R.id.search_text)
    EditText searchText;
    @BindView(R.id.tipRL)
    RelativeLayout tipRL;
    Unbinder unbinder;
    @BindView(R.id.mTabCom)
    XTabLayout mTabCom;
    @BindView(R.id.mViewPagerCom)
    ViewPager mViewPagerCom;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Intent intent;
    private BaseQuickAdapter<String, BaseViewHolder> adapter;

    public ComponentFragment() {
    }

    public static ComponentFragment newInstance(String param1, String param2) {
        ComponentFragment fragment = new ComponentFragment();
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
        View view = inflater.inflate(R.layout.fragment_component, container, false);
        unbinder = ButterKnife.bind(this, view);
        initTabLayout();
        return view;
    }

    private void initTabLayout() {
        String[] titles = {"组件", "训练", "跑步", "健走", "骑行"};
        mViewPagerCom.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return TrainingFragment.newInstance("", "");
                    case 1:
                        return TrainingFragment.newInstance("", "");
                    case 2:
                        return TrainingFragment.newInstance("", "");
                    case 3:
                        return TrainingFragment.newInstance("", "");
                    case 4:
                        return TrainingFragment.newInstance("", "");
                    default:
                        break;
                }
                return null;
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabCom.setupWithViewPager(mViewPagerCom);
        mTabCom.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                mViewPagerCom.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
//        mViewPager.setOffscreenPageLimit(titles.length);


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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
