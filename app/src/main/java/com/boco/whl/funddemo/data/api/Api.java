package com.boco.whl.funddemo.data.api;

import com.boco.whl.funddemo.data.upload.InnerUploadListener;
import com.boco.whl.funddemo.data.upload.UploadListener;
import com.boco.whl.funddemo.data.upload.UploadProgressInterceptor;
import com.boco.whl.funddemo.utils.UIUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static final String BASE_URL = "http://api.liaobaner.com/api1_0_1/";
    private static final long DEFAULT_TIMEOUT = 10000L;// 10s

    private static volatile Api instance;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private OkHttpClient uploadOkHttpClient;
    private Retrofit uploadRetrofit;
    private ApiService apiService;
    private UploadService uploadService;
    private InnerUploadListener uploadListener;
    private UploadListener tempUploadListener;// 临时上传监听器
    private String tempTag;// 临时tag

    //构造方法私有
    private Api() {
        initCommonService();
        initUploadService();
    }

    /**
     * 初始化普通Service
     */
    private void initCommonService() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(3 * DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    /**
     * 初始化上传Service
     */
    private void initUploadService() {
        uploadListener = new InnerUploadListener() { // 当前回调在子线程
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength) {
                // L.e("bytesWritten：" + bytesWritten + ", contentLength：" + contentLength);
                if (tempUploadListener == null) return;
                UIUtils.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        tempUploadListener.onRequestProgress(tempTag, bytesWritten, contentLength);
                    }
                });
            }
        };

        UploadProgressInterceptor interceptor = new UploadProgressInterceptor(uploadListener);
        uploadOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(3 * DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(interceptor)
                .addInterceptor(new LoggingInterceptor())
                .build();

        uploadRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(uploadOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        uploadService = uploadRetrofit.create(UploadService.class);
    }

    public static Api getInstance() {
        if (instance == null) {
            synchronized (Api.class) {
                if (instance == null) {
                    instance = new Api();
                }
            }
        }
        return instance;
    }

    public void setUploadListener(String tag, UploadListener listener) {
        this.tempTag = tag;
        this.tempUploadListener = listener;
    }

    /**
     * 获取OkHttpClient实例
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * 获取Retrofit实例
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    public OkHttpClient getUploadOkHttpClient() {
        return uploadOkHttpClient;
    }

    public Retrofit getUploadRetrofit() {
        return uploadRetrofit;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public UploadService getUploadService() {
        return uploadService;
    }

}
