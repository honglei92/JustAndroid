package com.boco.whl.rxjavademo.ui.activity.firsttab.mvpV2;

import com.boco.whl.rxjavademo.ui.activity.firsttab.mvpV2.base.BasePresenter;
import com.boco.whl.rxjavademo.ui.activity.firsttab.mvpV2.base.BaseView;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/5/22 0022 10:44
 *  updatetime : 2017/5/22 0022 10:44
 * </pre>
 */
public interface CustomerContract {
    interface View extends BaseView<Presenter> {
        void setUsername(String username);

        void setPassword(String password);

        void showImage(String url);
    }

    interface Presenter extends BasePresenter {
        void saveUser(String id, String firstName, String lastName);

        void loadUser(String id);

        void loadImage(String url);

    }
}
