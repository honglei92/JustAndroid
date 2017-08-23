package com.boco.whl.rxjavademo.module.fragment;

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
import com.boco.whl.rxjavademo.module.activity.firsttab.rxjava.RxImageActivity;
import com.boco.whl.rxjavademo.module.adapter.CategoryItemAdapter;
import com.boco.whl.rxjavademo.sdk.eventbus.MessageEvent;
import com.boco.whl.rxjavademo.module.activity.firsttab.glide.GlideTestActivity;
import com.boco.whl.rxjavademo.module.activity.firsttab.rxjava.RxjavaTestActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RankFragment extends Fragment {
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

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RankFragment() {
    }

    public static RankFragment newInstance(String param1, String param2) {
        RankFragment fragment = new RankFragment();
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
        initCatgory();
        return view;
    }

    private void initCatgory() {
        String[] titles = {"Rank", "Rank", "Rank", "Rank", "Rank", "Rank"};
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
                        Intent intent2 = new Intent(getActivity(), RxjavaTestActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        EventBus.getDefault().post(new MessageEvent("afs win"));
                        break;
                    case 3:
                        Intent intent = new Intent(getActivity(), RxImageActivity.class);
                        startActivity(intent);
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
