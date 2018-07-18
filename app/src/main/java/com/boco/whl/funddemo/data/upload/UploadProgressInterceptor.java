package com.boco.whl.funddemo.data.upload;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xxx on 2017/9/7.
 */

public class UploadProgressInterceptor implements Interceptor {

    private InnerUploadListener mUploadListener;

    public UploadProgressInterceptor(InnerUploadListener uploadListener) {
        mUploadListener = uploadListener;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (null == request.body()) {
            return chain.proceed(request);
        }

        Request build = request.newBuilder()
                .method(request.method(),
                        new CountingRequestBody(request.body(), mUploadListener))
                .build();
        return chain.proceed(build);
    }

}
