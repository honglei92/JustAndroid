package com.boco.whl.funddemo.module.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.component.PinnedSectionActivity;
import com.boco.whl.funddemo.module.activity.component.ScrollActivity;
import com.boco.whl.funddemo.module.activity.component.slidetable.SlideTableActivity;
import com.boco.whl.funddemo.module.activity.component.watermark.WaterMarkActivity;
import com.boco.whl.funddemo.module.adapter.CategoryItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基本组件的使用
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
    @BindView(R.id.category)
    GridView category;
    Unbinder unbinder;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCatgory();
        return view;
    }

    private void initCatgory() {
        String[] titles = {"Pinned Section", "SlideTable", "WaterMark", "ScrollBy", "Game", "Game"};
        CategoryItemAdapter adapter = new CategoryItemAdapter(getActivity(), titles);
        category.setAdapter(adapter);
        category.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i) {
                case 0:
                    Intent intent1 = new Intent(getActivity(), PinnedSectionActivity.class);
                    startActivity(intent1);
                    break;
                case 1:
                    Intent intent2 = new Intent(getActivity(), SlideTableActivity.class);
                    startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent(getActivity(), WaterMarkActivity.class);
                    startActivity(intent3);
                    break;
                case 3:
                    Intent intent4 = new Intent(getActivity(), ScrollActivity.class);
                    startActivity(intent4);
                    break;
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
