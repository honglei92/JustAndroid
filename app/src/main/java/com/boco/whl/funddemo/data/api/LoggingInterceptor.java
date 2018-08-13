package com.boco.whl.funddemo.data.api;

import com.boco.whl.funddemo.base.BaseTinkerApplication;
import com.boco.whl.funddemo.utils.LogUtil;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by xxx on 2017/2/22.
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间
        LogUtil.e(String.format("发送请求 %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间
        int time = (int) ((t2 - t1) / 1e6d);
        BaseTinkerApplication.setNetResponseTime(time);

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        // %.1f 换为 %d
        LogUtil.e(String.format(Locale.CHINA, "接收响应: [%s] %n返回json:【%s】 %dms%n%s",
                response.request().url(),
                responseBody.string(),
                time, response.headers()));

        return response;
    }
}
