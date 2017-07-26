package com.boco.whl.rxjavademo.ui.fragment;

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

import com.boco.whl.rxjavademo.R;
import com.boco.whl.rxjavademo.ui.activity.SearchActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.MarqueeActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.baidumap.LocationActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.eventbus.EventBusTestActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.fusionchart.FusionChartTestActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.glide.GlideTestActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.imagecompress.ImageCompressActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.listview.ListTest;
import com.boco.whl.rxjavademo.ui.activity.firsttab.mvp.view.UserActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.mvpV2.CustomerActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.rxjava.RxGDActivity;
import com.boco.whl.rxjavademo.ui.activity.firsttab.rxjava.RxImageActivity;
import com.boco.whl.rxjavademo.ui.adapter.CategoryItemAdapter;
import com.boco.whl.rxjavademo.utils.IntentUT;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
        String[] titles = {"Glide", "RxJava", "EventBus", "RxImage", "Retrofit", "OkHttp"
                , "Tinker", "Baidu", "Map", "FusionChart", "MVP1", "MVP2", "kotlin", "listview", "imgCompress", "marquee"};
        CategoryItemAdapter adapter = new CategoryItemAdapter(getActivity(), titles);
        category.setAdapter(adapter);
        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent1 = new Intent(getActivity(), GlideTestActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), RxGDActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(getActivity(), EventBusTestActivity.class);
                        startActivity(intent3);

                        break;
                    case 3:
                        Intent intent = new Intent(getActivity(), RxImageActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        Intent intent7 = new Intent(getActivity(), SearchActivity.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(getActivity(), LocationActivity.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(getActivity(), FusionChartTestActivity.class);
                        startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10 = new Intent(getActivity(), UserActivity.class);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(getActivity(), CustomerActivity.class);
                        startActivity(intent11);
                        break;
                    case 12:
                        break;
                    case 13:
                        Intent intent13 = new Intent(getActivity(), ListTest.class);
                        startActivity(intent13);
                        break;
                    case 14:
                        Intent intent14 = new Intent(getActivity(), ImageCompressActivity.class);
                        startActivity(intent14);
                        break;
                    case 15:
                        Intent intent15 = new Intent(getActivity(), MarqueeActivity.class);
                        startActivity(intent15);
                        break;
                }
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
