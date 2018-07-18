package com.boco.whl.funddemo.data;


import com.boco.whl.funddemo.data.api.Api;
import com.boco.whl.funddemo.data.api.ApiService;
import com.boco.whl.funddemo.data.api.UploadService;

import java.util.List;

import io.reactivex.Observable;


/**
 * 数据管理器
 * Created by xxx on 2017/5/23.
 */

public class DataManager {

    private static DataManager mInstance;

    private ApiService mApiService;
    private UploadService mUploadService;

    public DataManager() {
        mApiService = Api.getInstance().getApiService();
        mUploadService = Api.getInstance().getUploadService();
    }

    public static DataManager getInstance() {
        if (mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }


    public Observable<ApiBean> getRegisterRand(String user_tel) {
        return mApiService.getRegisterRand(user_tel)
                .compose(RxUtils.<ApiBean>io_main());
    }

    public Observable<ApiBean> getRegister(String user_tel, String password, String reg_port, String code, String sex) {
        return mApiService.getRegister(user_tel, password,reg_port,code,sex)

                .compose(RxUtils.<ApiBean>io_main());
    }
    public Observable<ApiBean> getForgetPass(String user_tel, String password, String code) {
        return mApiService.getForgetPass(user_tel, password,code)

                .compose(RxUtils.<ApiBean>io_main());
    }
//
    public Observable<LoginBean> getLogin(String user_tel, String password, String province, String city) {
        return mApiService.getLogin(user_tel, password,province,city)
                .compose(RxUtils.<LoginBean>handleResult())
                .compose(RxUtils.<LoginBean>io_main());
    }


    public Observable<List<HomeBannerBean>> getHomeBanner(boolean isRefresh) {
        return mCacheRepository.getHomeBanner("home_banner", isRefresh)
                .compose(RxUtils.<List<HomeBannerBean>>io_main());
    }

}
