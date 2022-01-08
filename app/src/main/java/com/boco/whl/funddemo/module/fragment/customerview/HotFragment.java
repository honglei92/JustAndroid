package com.boco.whl.funddemo.module.fragment.customerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.boco.whl.funddemo.R;
import com.bumptech.glide.Glide;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 热门精选
 *
 * @author Administrator
 */
public class HotFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Unbinder unbinder;
    @BindView(R.id.banner_guide_content)
    BGABanner bannerGuideContent;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Intent intent;

    public HotFragment() {
    }

    public static HotFragment newInstance(String param1, String param2) {
        HotFragment fragment = new HotFragment();
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
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        unbinder = ButterKnife.bind(this, view);
        initBanner();
        return view;
    }

    private void initBanner() {
        bannerGuideContent.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
                Glide.with(getContext())
                        .load(model)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });
        String mImageUrl = "http://img.zcool.cn/community/01cd1f55e967786ac7251df858532d.jpg";
        String mImageUrl1 = "http://pic.qiantucdn.com/58pic/17/89/63/55a6b9850b341_1024.jpg";
        String mImageUrl2 = "http://pic94.nipic.com/file/20160410/22818307_133715723000_2.jpg";
        String mImageUrl3 = "http://img.zcool.cn/community/01c2645733e7666ac7252631432cb0.jpg@900w_1l_2o";
        String mImageUrl4 = "http://img.zcool.cn/community/011cc455e9687532f875a1325b55b7.jpg";
        bannerGuideContent.setData(Arrays.asList(mImageUrl, mImageUrl1, mImageUrl2, mImageUrl3, mImageUrl4),
                Arrays.asList("", "", "", "", ""));
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
