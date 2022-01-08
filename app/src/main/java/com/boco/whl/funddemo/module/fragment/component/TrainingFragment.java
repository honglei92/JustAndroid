package com.boco.whl.funddemo.module.fragment.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.component.EasyCommonActivity;
import com.boco.whl.funddemo.module.activity.component.PinnedSectionActivity;
import com.boco.whl.funddemo.module.activity.component.ScrollActivity;
import com.boco.whl.funddemo.module.activity.component.service.ServiceActivity;
import com.boco.whl.funddemo.module.adapter.SpacesItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 安卓基本组件的使用
 */
public class TrainingFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.categoryRecyclerView)
    RecyclerView category;
    Unbinder unbinder;

    private String mParam1;
    private String mParam2;

    Intent intent;
    private BaseQuickAdapter<String, BaseViewHolder> adapter;

    public TrainingFragment() {
    }

    public static TrainingFragment newInstance(String param1, String param2) {
        TrainingFragment fragment = new TrainingFragment();
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
        View view = inflater.inflate(R.layout.fragment_training, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCategory();
        return view;
    }

    private void initCategory() {
        String[] titles = {"Activity", "Service", "BroadcastReceiver", "ContentProvider",
                "jsBridge", "recycleView", "ScrollView"};
        List<String> list = Arrays.asList(titles);
        //设置adapter
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_category_item, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.category_text, item);
                helper.setImageResource(R.id.category_image, R.drawable.icon_tec_point);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (helper.getAdapterPosition()) {
                            case 0:
                                intent = new Intent(getActivity(), PinnedSectionActivity.class);
                                break;
                            case 1:
                                intent = new Intent(getActivity(), ServiceActivity.class);
                                break;
                            case 2:
                                intent = new Intent(getActivity(), PinnedSectionActivity.class);
                                break;
                            case 3:
                                intent = new Intent(getActivity(), EasyCommonActivity.class);
                                break;
                            case 4:
                                intent = new Intent(getActivity(), EasyCommonActivity.class);
                                break;
                            case 6:
                                intent = new Intent(getActivity(), ScrollActivity.class);
                                break;
                            default:
                                break;
                        }
                        startActivity(intent);
                    }
                });
            }
        };
        category.setAdapter(adapter);
        //设置layoutManager
        category.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        //设置ItemDecoration
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(16);
        category.addItemDecoration(itemDecoration);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
