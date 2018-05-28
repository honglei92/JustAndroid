package com.boco.whl.funddemo.module.activity.main.fragment;

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
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.annndroid.LoadImageActivity;
import com.boco.whl.funddemo.module.activity.annndroid.MarqueeActivity;
import com.boco.whl.funddemo.module.activity.annndroid.baidumap.LocationActivity;
import com.boco.whl.funddemo.module.activity.annndroid.eventbus.EventBusTestActivity;
import com.boco.whl.funddemo.module.activity.annndroid.fusionchart.FusionChartTestActivity;
import com.boco.whl.funddemo.module.activity.annndroid.glide.GlideTestActivity;
import com.boco.whl.funddemo.module.activity.annndroid.listview.ListTest;
import com.boco.whl.funddemo.module.activity.annndroid.mvp.view.UserActivity;
import com.boco.whl.funddemo.module.activity.annndroid.mvpV2.CustomerActivity;
import com.boco.whl.funddemo.module.activity.annndroid.rxjava.RxGDActivity;
import com.boco.whl.funddemo.module.activity.annndroid.rxjava.RxImageActivity;
import com.boco.whl.funddemo.module.activity.my.SearchActivity;
import com.boco.whl.funddemo.module.activity.thirdlib.ffmpeg.HelloJni;
import com.boco.whl.funddemo.module.activity.thirdlib.ffmpeg.PlayActivity;
import com.boco.whl.funddemo.module.activity.thirdlib.rxjava.RxjavaTestActivity;
import com.boco.whl.funddemo.module.adapter.CategoryItemAdapter;
import com.boco.whl.funddemo.utils.IntentUT;
import com.boco.whl.funddemo.widgets.ObserableScrollView;
import com.orhanobut.logger.Logger;
import com.sunsky.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

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
    ObserableScrollView mainScrollView;
    @BindView(R.id.upView)
    MarqueeView upView;
    @BindView(R.id.tipLL)
    LinearLayout tipLL;

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
    private List<View> mMarqueeViews = new ArrayList<>();

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
        initMarquee();
        return view;
    }

    private void initMarquee() {
        List<String> data = new ArrayList<>();
        data.add("中美贸易：中国采取强硬立场的历史原因");
        data.add("输球却获4大好消息，勇士开始绝地反击");
        data.add("大城市掀强热大战，最需要人才的农村怎么办");

        mMarqueeViews.clear();//记得加这句话，不然可能会产生重影现象
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.size() > position + 1) {
                        Toast.makeText(getActivity(), position + "你点击了" + data.get(position + 1).toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), position + "你点击了" + data.get(0).toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //奇数条
                tv2.setText(data.get(i + 1).toString());
            } else {
                //偶数条
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                //moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
                //修改了最后一个没有 将第一个拼接到最后显示
                tv2.setText(data.get(0).toString());
            }

            //添加到循环滚动数组里面去
            mMarqueeViews.add(moreView);

            upView.setViews(mMarqueeViews);
        }
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

                        //缩放代码
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

        ViewTreeObserver observer = guideIv.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (guideIv != null) {

                    guideIv.getViewTreeObserver().removeOnGlobalLayoutListener(this::onGlobalLayout);
                    int imageHeight = guideIv.getHeight();
                    //增加上滑显示操作
                    mainScrollView.setScrollViewListener(new ObserableScrollView.ScrollViewListener() {
                        @Override
                        public void onScrollChanged(ObserableScrollView obserableScrollView, int x, int y, int oldX, int oldY) {
                            if (y <= 0) {
                                tipLL.setAlpha(0);
                            } else if (y < imageHeight) {
                                tipLL.setAlpha((float) y / imageHeight);
                            } else {
                                tipLL.setAlpha(1);
                            }
                        }

                    });
                }
            }
        });

        mainScrollView.scrollTo(0, 0);

    }

    private void initCategory() {
        String[] titles = {"Glide", "RxJava", "EventBus", "RxImage", "RxOperator", "Retrofit", "OkHttp"
                , "Baidu", "Map", "FusionChart", "MVP1", "MVP2", "kotlin", "listview",
                "imgCompress", "marquee", "loadImage"
                , "JNI", "FFMPEG", "updating", "updating", "updating", "updating", "updating"
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
                        intent = new Intent(getActivity(), RxjavaTestActivity.class);
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
                        intent = new Intent(getActivity(), PlayActivity.class);
                        break;
                    case 15:
                        intent = new Intent(getActivity(), MarqueeActivity.class);
                        break;
                    case 16:
                        intent = new Intent(getActivity(), LoadImageActivity.class);
                        break;
                    case 17:
                        intent = new Intent(getActivity(), HelloJni.class);
                        break;
                    case 18:
                        intent = new Intent(getActivity(), PlayActivity.class);
                        break;
                    default:
                        break;
                }
                if (intent != null) {
                    startActivity(intent);
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
