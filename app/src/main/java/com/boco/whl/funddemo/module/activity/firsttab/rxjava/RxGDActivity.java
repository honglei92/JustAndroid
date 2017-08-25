package com.boco.whl.funddemo.module.activity.firsttab.rxjava;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.boco.bmdp.core.pojo.common.CommMsgResponse;
import com.boco.bmdp.towerwalkmanage.entity.PersonOverTimeSheetRequst;
import com.boco.bmdp.towerwalkmanage.service.ITowerwalkmanageService;
import com.boco.transnms.server.bo.base.ServiceUtils;
import com.boco.transnms.server.bo.base.SeviceException;
import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.utils.NetworkUT;
import com.boco.whl.funddemo.utils.PrintfUT;
import com.boco.whl.funddemo.utils.ToastUtil;

import java.lang.reflect.UndeclaredThrowableException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RxGDActivity extends Activity {
    ProgressDialog dialog2;
    private CommMsgResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_gd);
        getOverTime();
    }

    private void getOverTime() {
        final boolean flag = NetworkUT.getInstance().isConnected(RxGDActivity.this) &&
                NetworkUT.getInstance().isAvailable(RxGDActivity.this);
        if (flag) {
            Observable.create(new ObservableOnSubscribe<CommMsgResponse>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter<CommMsgResponse> emitter) throws Exception {
                    String errorMessage = "";
                    Thread.sleep(2000);
                    try {
                        PersonOverTimeSheetRequst request = new PersonOverTimeSheetRequst();
                        request.setEndTime("2017-05-12");
                        request.setStartTime("2016-05-12");
                        request.setUnitId("7");
                        response = new CommMsgResponse();
                        ServiceUtils.initClient();
                        response = ServiceUtils.getBO(ITowerwalkmanageService.class).personOverTimeSheet((PersonOverTimeSheetRequst) request);
                    } catch (SeviceException e) {
                        e.printStackTrace();
                        errorMessage = "服务调用失败";
                    } catch (UndeclaredThrowableException e) {
                        e.printStackTrace();
                        errorMessage = "服务调用失败";
                    } catch (Exception e) {
                        e.printStackTrace();
                        errorMessage = "服务调用失败";
                    }
                    if (response.getServiceFlag() == false) {
                        emitter.onError(new Throwable(errorMessage));
                    } else {
                        if (response.getDataList() == null || response.getDataList().size() == 0) {
                            emitter.onError(new Throwable("返回数据为空"));
                        } else {
                            emitter.onNext(response);
                            dialog2.dismiss();
                        }
                    }
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CommMsgResponse>() {
                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(RxGDActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
                            dialog2.dismiss();
                        }

                        @Override
                        public void onComplete() {
                            dialog2.dismiss();
                        }

                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            dialog2 = ProgressDialog.show(RxGDActivity.this, "", "正在查询超时工单...");
                        }

                        @Override
                        public void onNext(CommMsgResponse result) {
                            ToastUtil.showToast(RxGDActivity.this, "返回了" + result.getDataList().size() + "条数据");
                        }

                    });
        } else {
            PrintfUT.getInstance().showShortToast(RxGDActivity.this, "网络无连接");
        }
    }
}
