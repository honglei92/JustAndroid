package com.boco.whl.funddemo.module.activity.annndroid.rxjava.iciba;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author honglei92
 * @createTime 2018/5/8 0008
 */
public interface GetRequestInterface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation> getCall();
}
