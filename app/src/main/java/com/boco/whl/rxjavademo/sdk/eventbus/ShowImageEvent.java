package com.boco.whl.rxjavademo.sdk.eventbus;

/**
 * Created by honglei92 on 2017/3/31 0031.
 */

public class ShowImageEvent {


    public byte[] bytes;

    public ShowImageEvent(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
