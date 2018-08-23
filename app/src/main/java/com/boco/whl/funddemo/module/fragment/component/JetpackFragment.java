package com.boco.whl.funddemo.module.fragment.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.component.EasyCommonActivity;
import com.boco.whl.funddemo.module.activity.component.ScrollActivity;
import com.boco.whl.funddemo.module.activity.component.jetpack.databinding.DataBindingTest;
import com.boco.whl.funddemo.module.activity.component.jetpack.navigation.NavigationTestActivity;
import com.boco.whl.funddemo.module.adapter.SpacesItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 安卓jetpack实践
 */
public class JetpackFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.categoryRecyclerView)
    RecyclerView category;
    Unbinder unbinder;

    private String mParam1;
    private String mParam2;

    Intent intent;
    private BaseQuickAdapter<String, BaseViewHolder> adapter;

    public JetpackFragment() {
    }

    public static JetpackFragment newInstance(String param1, String param2) {
        JetpackFragment fragment = new JetpackFragment();
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
        String[] titles = {"DataBinding", "LifeCycles", "LiveData", "Navigation",
                "Paging", "Room", "ViewModel", "WorkManager"};
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
                                intent = new Intent(getActivity(), DataBindingTest.class);
                                break;
                            case 1:
                                intent = new Intent(getActivity(), DataBindingTest.class);
                                break;
                            case 2:
                                intent = new Intent(getActivity(), DataBindingTest.class);
                                break;
                            case 3:
                                intent = new Intent(getActivity(), NavigationTestActivity.class);
                                break;
                            case 4:
                                intent = new Intent(getActivity(), EasyCommonActivity.class);
                                break;
                            case 5:
                                intent = new Intent(getActivity(), ScrollActivity.class);
                                break;
                            case 6:
                                intent = new Intent(getActivity(), ScrollActivity.class);
                                break;
                            case 7:
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
