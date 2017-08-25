package com.boco.whl.funddemo.module.activity.firsttab.mvp.model;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/5/15 0015 10:03
 *  updatetime : 2017/5/15 0015 10:03
 * </pre>
 */
public interface IUserModel {
    void setId(String id);

    void setUsername(String name);

    void setPassword(String password);

    UserBean loadUser(String id);
}
