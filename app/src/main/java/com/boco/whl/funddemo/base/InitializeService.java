package com.boco.whl.funddemo.base;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.boco.whl.funddemo.utils.LogUtil;
import com.facebook.stetho.Stetho;

/**
 * Created by xxx on 2016/12/9.
 */

public class InitializeService extends IntentService {

    private boolean isInit;
    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.maomaoda.mmd.service.action.init";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        //intent.setData(Uri.parse("xxx"));
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    private void performInit() {
        if(isInit) return;
        isInit = true;
        LogUtil.e("performInit begin:" + System.currentTimeMillis());
        Stetho.initializeWithDefaults(this.getApplicationContext());
        LogUtil.e("performInit end:" + System.currentTimeMillis());
    }

}
