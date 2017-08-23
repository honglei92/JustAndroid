package com.boco.whl.rxjavademo.module.activity.firsttab.mvp.model;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/5/15 0015 11:08
 *  updatetime : 2017/5/15 0015 11:08
 * </pre>
 */
public class UserBean {
    private String username;
    private String password;

    UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
