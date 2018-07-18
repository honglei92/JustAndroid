package com.boco.whl.funddemo.data.api;


import com.boco.whl.funddemo.entity.ApiBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 接口列表
 * Created by xxx on 2016/9/27.
 */
public interface ApiService {

    /**
     * 注册（验证码）
     */
    @FormUrlEncoded
    @POST("Login/duanxin")
    Observable<ApiBean> getRegisterRand(@Field("user_tel") String user_tel);

    /**
     * 注册（
     */
    @FormUrlEncoded
    @POST("Login/register")
    Observable<ApiBean> getRegister(@Field("user_tel") String user_tel,
                                    @Field("password") String password,
                                    @Field("reg_port") String reg_port,
                                    @Field("code") String code,
                                    @Field("sex") String sex);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("Login/update_pwd")
    Observable<ApiBean> getForgetPass(@Field("user_tel") String user_tel,
                                      @Field("password") String password,
                                      @Field("code") String code);


}