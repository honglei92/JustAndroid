package com.boco.whl.funddemo.module.fragment.customerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.component.PinnedSectionActivity;
import com.boco.whl.funddemo.module.activity.component.watermark.WaterMarkActivity;
import com.boco.whl.funddemo.module.activity.customerview.LottieActivity;
import com.boco.whl.funddemo.module.activity.customerview.didi.DiDiActivity;
import com.boco.whl.funddemo.module.activity.customerview.fallingstar.FallingStarActivity;
import com.boco.whl.funddemo.module.activity.customerview.henbanse.HenCode1;
import com.boco.whl.funddemo.module.activity.customerview.mi.MiSportActivity;
import com.boco.whl.funddemo.module.activity.customerview.sunxibei.RotateRectActivity;
import com.boco.whl.funddemo.module.activity.customerview.thumbup.ThumbUpActivity;
import com.boco.whl.funddemo.module.adapter.CategoryItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 自定义view
 *
 * @author Administrator
 */
public class ViewFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.category)
    GridView category;
    Unbinder unbinder;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Intent intent;

    public ViewFragment() {
    }

    public static ViewFragment newInstance(String param1, String param2) {
        ViewFragment fragment = new ViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCategory();
        return view;
    }


    private void initCategory() {
        String[] titles = {"HenCode1", "rotateRect", "miSport", "thumbUp"
                , "fallingStar", "didi", "吸顶列表", "水印",
                "Lottie"};
        CategoryItemAdapter adapter = new CategoryItemAdapter(getActivity(), titles);
        category.setAdapter(adapter);
        category.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {
            switch (i) {
                case 0:
                    intent = new Intent(getActivity(), HenCode1.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), RotateRectActivity.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), MiSportActivity.class);
                    break;
                case 3:
                    intent = new Intent(getActivity(), ThumbUpActivity.class);
                    break;
                case 4:
                    FallingStarActivity.doIntent(getActivity(), false);
                    break;
                case 5:
                    DiDiActivity.doIntent(getActivity(), false);
                    break;
                case 6:
                    intent = new Intent(getActivity(), PinnedSectionActivity.class);
                    break;
                case 7:
                    intent = new Intent(getActivity(), WaterMarkActivity.class);
                    break;
                case 8:
                    intent = new Intent(getActivity(), LottieActivity.class);
                    break;
                default:
                    break;
            }
            if (intent != null) {
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
