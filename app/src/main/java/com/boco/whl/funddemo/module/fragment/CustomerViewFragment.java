package com.boco.whl.funddemo.module.fragment;

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
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.module.activity.main.SplashActivity;
import com.boco.whl.funddemo.module.activity.selfview.didi.DiDiActivity;
import com.boco.whl.funddemo.module.activity.selfview.fallingstar.FallingStarActivity;
import com.boco.whl.funddemo.module.activity.selfview.henbanse.HenCode1;
import com.boco.whl.funddemo.module.activity.selfview.mi.MiSportActivity;
import com.boco.whl.funddemo.module.activity.selfview.sunxibei.RotateRectActivity;
import com.boco.whl.funddemo.module.activity.selfview.thumbup.ThumbUpActivity;
import com.boco.whl.funddemo.module.activity.thirdlib.retrofit.RetrofitTest;
import com.boco.whl.funddemo.module.adapter.CategoryItemAdapter;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 博客实验  hencoder自定义view
 *
 * @author Administrator
 */
public class CustomerViewFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.category)
    GridView category;
    Unbinder unbinder;
    @BindView(R.id.mLottieAnimationView)
    LottieAnimationView mLottieAnimationView;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CustomerViewFragment() {
    }

    public static CustomerViewFragment newInstance(String param1, String param2) {
        CustomerViewFragment fragment = new CustomerViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_customerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCategory();
        initLottie();
        return view;
    }

    private void initLottie() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                String mJson = null;
                try {
                    mJson = RetrofitTest.getJson("https://raw.githubusercontent.com/18380438200/LottieAnim/master/app/src/main/assets/", "likeanim.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                emitter.onNext(mJson);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "LOAD ERROR!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String result) {
                        if (result != null && mLottieAnimationView != null) {
                            mLottieAnimationView.setAnimationFromJson(result);
                        }
                    }
                });
    }

    private void initCategory() {
        String[] titles = {"HenCode1", "rotateRect", "miSport", "thumbUp"
                , "fallingStar", "didi"};
        CategoryItemAdapter adapter = new CategoryItemAdapter(getActivity(), titles);
        category.setAdapter(adapter);
        category.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {
            switch (i) {
                case 0:
                    Intent intent1 = new Intent(getActivity(), HenCode1.class);
                    startActivity(intent1);
                    break;
                case 1:
                    Intent intent2 = new Intent(getActivity(), RotateRectActivity.class);
                    startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent(getActivity(), MiSportActivity.class);
                    startActivity(intent3);
                    break;
                case 3:
                    Intent intent4 = new Intent(getActivity(), ThumbUpActivity.class);
                    startActivity(intent4);
                    break;
                case 4:
                    FallingStarActivity.doIntent(getActivity(), false);
                    break;
                case 5:
                    DiDiActivity.doIntent(getActivity(), false);
                    break;
                default:
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

    @Override
    public void onResume() {
        super.onResume();
    }
}
