package com.boco.whl.funddemo.data;


import com.boco.whl.funddemo.data.api.Api;
import com.boco.whl.funddemo.data.api.ApiService;
import com.boco.whl.funddemo.data.api.UploadService;
import com.boco.whl.funddemo.entity.ApiBean;

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

}
