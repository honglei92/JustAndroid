package com.boco.whl.funddemo.entity;

public class ApiBean<T> {

    private int status;//状态码
    private T data;//返回成功数据
    private String msg;//错误提示信息
    public int count;

    public ApiBean() {
    }

    public ApiBean(int status, String msg) {
        this.status = status;
        this.msg = msg;

    }

    public ApiBean(int status, String msg, int count) {
        this.status = status;
        this.msg = msg;
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
