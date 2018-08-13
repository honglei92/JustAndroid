package com.boco.whl.funddemo.module.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.entity.MineOperationItem;
import com.boco.whl.funddemo.module.activity.my.GuideViewActivity;
import com.boco.whl.funddemo.utils.IntentUT;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.bugly.beta.Beta;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的
 *
 * @author Administrator
 */
public class MyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Unbinder unbinder;
    @BindView(R.id.AppFragment_Toolbar)
    Toolbar AppFragmentToolbar;
    @BindView(R.id.AppFragment_CollapsingToolbarLayout)
    CollapsingToolbarLayout AppFragmentCollapsingToolbarLayout;
    @BindView(R.id.AppFragment_AppBarLayout)
    AppBarLayout AppFragmentAppBarLayout;
    @BindView(R.id.MyFragment_recyclerView)
    RecyclerView MyFragmentRecyclerView;
    @BindView(R.id.main_head_back)
    ImageView mainHeadBack;
    static String[] operations = {"检查更新", "使用帮助", "安全中心", "通用", "关于1614",
            "检查更新", "检查更新", "检查更新", "检查更新", "检查更新",};

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private BaseQuickAdapter<MineOperationItem, BaseViewHolder> adapter;

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
        List<MineOperationItem> list = new ArrayList<>();
        MineOperationItem mineOperationItem;
        for (int i = 0; i < operations.length; i++) {
            mineOperationItem = new MineOperationItem();
            mineOperationItem.setMineOperationItemName(operations[i]);
            mineOperationItem.setMineOperationItemImage(R.mipmap.ic_launcher);
            list.add(mineOperationItem);
        }
        adapter = new BaseQuickAdapter<MineOperationItem, BaseViewHolder>(R.layout.item_fragment_my, list) {
            @Override
            protected void convert(BaseViewHolder helper, MineOperationItem item) {
                helper.setText(R.id.operation_text, item.getMineOperationItemName());
                helper.setImageResource(R.id.operation_image, item.getMineOperationItemImage());
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (list.get(position).getMineOperationItemName()) {
                    case "检查更新":
                       /* TinkerInstaller.onReceiveUpgradePatch(getActivity().getApplicationContext(),
                                Environment.getExternalStorageDirectory().getAbsolutePath()
                                        + "/patch_signed_7zip.apk");*/

                        Beta.checkUpgrade(false,false);
                        break;
                    case "使用帮助":
                        IntentUT.getInstance().openActivity(getActivity(), GuideViewActivity.class, false);
                        break;
                    default:
                        break;
                }
            }
        });
        MyFragmentRecyclerView.setAdapter(adapter);
        MyFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
