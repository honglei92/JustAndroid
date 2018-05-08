package com.boco.whl.funddemo.module.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.SearchActivity;
import com.boco.whl.funddemo.module.activity.annndroid.LoadImageActivity;
import com.boco.whl.funddemo.module.activity.annndroid.MarqueeActivity;
import com.boco.whl.funddemo.module.activity.annndroid.baidumap.LocationActivity;
import com.boco.whl.funddemo.module.activity.annndroid.eventbus.EventBusTestActivity;
import com.boco.whl.funddemo.module.activity.annndroid.fusionchart.FusionChartTestActivity;
import com.boco.whl.funddemo.module.activity.annndroid.glide.GlideTestActivity;
import com.boco.whl.funddemo.module.activity.annndroid.imagecompress.ImageCompressActivity;
import com.boco.whl.funddemo.module.activity.annndroid.listview.ListTest;
import com.boco.whl.funddemo.module.activity.annndroid.mvp.view.UserActivity;
import com.boco.whl.funddemo.module.activity.annndroid.mvpV2.CustomerActivity;
import com.boco.whl.funddemo.module.activity.annndroid.rxjava.RxGDActivity;
import com.boco.whl.funddemo.module.activity.annndroid.rxjava.RxImageActivity;
import com.boco.whl.funddemo.module.activity.annndroid.rxjava.RxjavaTestActivity;
import com.boco.whl.funddemo.module.adapter.CategoryItemAdapter;
import com.boco.whl.funddemo.utils.IntentUT;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 *
 * @author Administrator
 */
public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.queryimage)
    ImageView queryimage;
    @BindView(R.id.search_text)
    EditText searchText;
    @BindView(R.id.fault_diagnosis_query_rl)
    RelativeLayout faultDiagnosisQueryRl;
    @BindView(R.id.category)
    GridView category;
    Unbinder unbinder;
    @BindView(R.id.guide_iv)
    ImageView guideIv;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Intent intent;

    public MainFragment() {
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        Glide.with(getActivity()).load("https://timgsa.baidu.com/advertising?image&quality=80&size=b9999_10000&sec=1492360831762&di=0ae748d3dba288e5ac3a1f046bdacc6c&imgtype=0&src=http%3A%2F%2Fnews.xinhuanet.com%2Fhouse%2Fwuxi%2F2013-12-26%2F118723855_111n.jpg").into(guideIv);
        initCatgory();
        return view;
    }

    private void initCatgory() {
        String[] titles = {"Glide", "RxJava", "EventBus", "RxImage", "RxOperator", "Retrofit", "OkHttp"
                , "Tinker", "Baidu", "Map", "FusionChart", "MVP1", "MVP2", "kotlin", "listview", "" +
                "imgCompress", "marquee", "loadImage"};
        Logger.d(titles);
        CategoryItemAdapter adapter = new CategoryItemAdapter(getActivity(), titles);
        category.setAdapter(adapter);
        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        intent = new Intent(getActivity(), GlideTestActivity.class);
                        break;
                    case 1:
                        //rxjava 广东接口
                        intent = new Intent(getActivity(), RxGDActivity.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), EventBusTestActivity.class);
                        break;
                    case 3:
                        //rxjava 图片测试
                        intent = new Intent(getActivity(), RxImageActivity.class);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), RxjavaTestActivity.class);
                        break;
                    case 5:
                        intent = new Intent(getActivity(), RxImageActivity.class);
                        break;
                    case 7:
                        intent = new Intent(getActivity(), SearchActivity.class);
                        break;
                    case 8:
                        intent = new Intent(getActivity(), LocationActivity.class);
                        break;
                    case 9:
                        intent = new Intent(getActivity(), FusionChartTestActivity.class);
                        break;
                    case 10:
                        intent = new Intent(getActivity(), UserActivity.class);
                        break;
                    case 11:
                        intent = new Intent(getActivity(), CustomerActivity.class);
                        break;
                    case 12:
                        break;
                    case 13:
                        intent = new Intent(getActivity(), ListTest.class);
                        break;
                    case 14:
                        intent = new Intent(getActivity(), ImageCompressActivity.class);
                        break;
                    case 15:
                        intent = new Intent(getActivity(), MarqueeActivity.class);
                        break;
                    case 16:
                        intent = new Intent(getActivity(), LoadImageActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
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

    @OnClick(R.id.queryimage)
    public void onViewClicked() {
        IntentUT.getInstance().openActivity(getActivity(), SearchActivity.class, false);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
