package com.boco.whl.funddemo.module.activity.firsttab.mvp.model;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/5/15 0015 9:59
 *  updatetime : 2017/5/15 0015 9:59
 * </pre>
 */
public class UserModel implements IUserModel {
    private String id;
    private String username;
    private String password;

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setUsername(String name) {
        this.username = name;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public UserBean loadUser(String id) {
        return new UserBean(username, password);
    }

}
