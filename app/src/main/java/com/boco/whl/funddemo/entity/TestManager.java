package com.boco.whl.funddemo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author honglei92
 * @createTime 2018/5/14 0014
 */
public class TestManager {
    private List<OnDataArrivedListener> mOnDataArrivedListeners =
            new ArrayList<OnDataArrivedListener>();

    private static class SingletonHolder {
        public static final TestManager INSTANCE = new TestManager();
    }

    private TestManager() {
    }

    public static TestManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public synchronized void registerListener(OnDataArrivedListener listener) {
        if (!mOnDataArrivedListeners.contains(listener)) {
            mOnDataArrivedListeners.add(listener);
        }
    }

    public synchronized void removeListener(OnDataArrivedListener listener) {
        if (mOnDataArrivedListeners.contains(listener)) {
            mOnDataArrivedListeners.remove(listener);
        }
    }


    public interface OnDataArrivedListener {
        void onDataArrived(Object data);
    }
}
