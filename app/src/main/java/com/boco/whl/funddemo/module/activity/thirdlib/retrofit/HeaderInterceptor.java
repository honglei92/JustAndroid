package com.boco.whl.funddemo.module.activity.thirdlib.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 应用拦截器
 *
 * @author:honglei92
 * @time:2018/8/10
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = processRequest(chain.request());
        Request.Builder builder = request.newBuilder();
        System.out.println("HeaderInterceptor:\n" + request.url().toString() + "==\n" + request.url().host());
        Response response = processResponse(chain.proceed(builder.build()));
        return response;

    }

    private static Response processResponse(Response proceed) {
        return proceed;
    }

    private static Request processRequest(Request request) {
        return request.newBuilder()
                .addHeader("Cookie", "SessionId whl")
                .build();
    }
}
