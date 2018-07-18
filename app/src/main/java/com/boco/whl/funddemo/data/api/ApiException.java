package com.boco.whl.funddemo.data.api;


import com.boco.whl.funddemo.entity.ApiBean;

/**
 * 服务器数据返回异常
 * Created by xxx on 2016/10/31.
 */

public class ApiException extends Exception {

    private ApiBean bean;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(ApiBean bean){
        super(bean.getMsg());
        this.bean = bean;
    }

    public ApiBean getBean() {
        return bean;
    }

    public void setBean(ApiBean bean) {
        this.bean = bean;
    }
}
