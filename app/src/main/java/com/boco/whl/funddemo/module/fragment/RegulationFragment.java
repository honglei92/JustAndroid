package com.boco.whl.funddemo.module.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.regulation.HandlerActivity;
import com.boco.whl.funddemo.module.activity.regulation.eventdiapatchregulation.EventTransmitActivity;
import com.boco.whl.funddemo.module.activity.regulation.ipc.IPCActivity;
import com.boco.whl.funddemo.module.activity.regulation.lru.PhotoWallActivity;
import com.boco.whl.funddemo.module.activity.regulation.threadcommunication.ThreadCommunicationOne;
import com.boco.whl.funddemo.module.activity.regulation.threadpool.ThreadPoolTestActivity;
import com.boco.whl.funddemo.module.adapter.CategoryItemAdapter;
import com.boco.whl.funddemo.sdk.eventbus.MessageEvent;
import com.boco.whl.funddemo.widgets.MyGridView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 安卓常用机制
 *
 * @author Administrator
 */
public class RegulationFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.queryimage)
    ImageView queryimage;
    @BindView(R.id.search_text)
    EditText searchText;
    @BindView(R.id.tipRL)
    RelativeLayout tipRL;
    @BindView(R.id.category)
    MyGridView category;
    Unbinder unbinder;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Intent intent;

    public RegulationFragment() {
    }

    public static RegulationFragment newInstance(String param1, String param2) {
        RegulationFragment fragment = new RegulationFragment();
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
        View view = inflater.inflate(R.layout.fragment_regulation, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCategory();
        return view;
    }

    private void initCategory() {
        String[] titles = {"线程间通信", "事件传递机制", "eventBus", "LRU缓存", "Handler机制", "Bitmap", "sqlLite"
                , "绘制嵌套滑动", "IPC Binder", "线程池", "ANR"};
        CategoryItemAdapter adapter = new CategoryItemAdapter(getActivity(), titles);
        category.setAdapter(adapter);
        category.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i) {
                case 0:
                    intent = new Intent(getActivity(), ThreadCommunicationOne.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), EventTransmitActivity.class);
                    break;
                case 2:
                    EventBus.getDefault().post(new MessageEvent("afs win"));
                    break;
                case 3:
                    intent = new Intent(getActivity(), PhotoWallActivity.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(), HandlerActivity.class);
                    break;
                case 8:
                    intent = new Intent(getActivity(), IPCActivity.class);
                    break;
                case 9:
                    intent = new Intent(getActivity(), ThreadPoolTestActivity.class);
                    break;
                case 10:
                    intent = new Intent(getActivity(), ThreadPoolTestActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
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
