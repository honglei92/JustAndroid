package com.boco.whl.funddemo.module.activity.thirdlib.retrofit;

import android.content.Context;

import com.boco.whl.funddemo.utils.net.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存拦截器
 *
 * @author:honglei92
 * @time:2018/8/10
 */
public class CacheInterceptor implements Interceptor {
    private final Context mContext;

    public CacheInterceptor(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetUtil.getNetWorkState(mContext) == NetUtil.NETWORK_NONE) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);

        if (NetUtil.getNetWorkState(mContext) != NetUtil.NETWORK_NONE) {
            String cacheControl = request.cacheControl().toString();
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28;
            return response.newBuilder()
                    .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
