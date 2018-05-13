package com.boco.whl.funddemo.module.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

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
    @BindView(R.id.category)
    GridView category;
    Unbinder unbinder;
    @BindView(R.id.guide_iv)
    ImageView guideIv;
    @BindView(R.id.tipRL)
    RelativeLayout tipRL;
    @BindView(R.id.mainScrollView)
    ScrollView mainScrollView;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Intent intent;
    /**
     *
     */
    private float initTouchY = 0;
    private DisplayMetrics metrics;
    private boolean mScaling = false;

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
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCategory();
        //
        initScrollScale();

        return view;
    }

    private void initScrollScale() {
        metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mainScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) guideIv.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mScaling = false;
                        lp.width = metrics.widthPixels;
                        lp.height = metrics.widthPixels * 237 / 421;
                        guideIv.setLayoutParams(lp);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int upY = v.getScrollY();
                        float touchY = event.getY();
                        if (!mScaling) {
                            if (upY == 0) {
                                initTouchY = event.getY();
                            } else {
                                break;
                            }
                        }
                        float deltaY = touchY - initTouchY;
                        if (deltaY < 0) {
                            break;
                        }
                        mScaling = true;
                        lp.width = (int) (metrics.widthPixels + deltaY * 0.45);
                        lp.height = (int) ((metrics.widthPixels + deltaY * 0.45) * 237 / 421);
                        guideIv.setLayoutParams(lp);

                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
        mainScrollView.scrollTo(0, 0);

    }

    private void initCategory() {
        String[] titles = {"Glide", "RxJava", "EventBus", "RxImage", "RxOperator", "Retrofit", "OkHttp"
                , "Baidu", "Map", "FusionChart", "MVP1", "MVP2", "kotlin", "listview",
                "imgCompress", "marquee", "loadImage"
                , "updating", "updating", "updating", "updating", "updating", "updating", "updating"
                , "updating", "updating", "updating", "updating", "updating", "updating", "updating"
                , "updating", "updating", "updating", "updating", "updating", "updating", "updating"
                , "updating", "updating", "updating", "updating", "updating", "updating", "updating"
        };
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
