package com.boco.whl.funddemo.sdk.eventbus;

/**
 * Created by honglei92 on 2017/3/31 0031.
 */

public class MessageEvent {
    public void setMessage(String message) {
        this.message = message;
    }

    public String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
