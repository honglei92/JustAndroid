package com.boco.whl.funddemo.module.activity.thirdlib.retrofit;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-retrofit test
 * createTime: 2017/12/25 0025
 * updateTime: 2017/12/25 0025
 *
 * @author Administrator
 */

public class RetrofitTest {
    private static String API_URL = "https://api.github.com";
    private static String apiUrl = "http://cdn.trojx.me/blog_raw/";
    private static String path = "lottie_data_origin.json";

    public static String getJson(String apiUrl, String path) throws IOException {
        return lottieTest(apiUrl, path);

    }

    /**
     * 1定义接口
     */
    public interface Github {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

    public interface Lottie {
        @GET("{path}")
        Call<JSONObject> getJson(
                @Path("path") String path);
    }

    public static void main(String... args) throws IOException {
//        githubTest();
//        lottieTest(apiUrl, path);

    }

    private static String lottieTest(String apiUrl, String path) throws IOException {
        //1 initial HttpLoggingInterceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //println retrofit log
//                System.out.println("RetrofitLog:" + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //2 config okhttp
        long mTimeOut = 1000 * 20;
        File direction = new File("D:\\retrofit");
        if (!direction.exists()) {
            direction.mkdir();
        }
        Cache cache = new Cache(direction, 1024 * 1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(mTimeOut, TimeUnit.SECONDS)
                .readTimeout(mTimeOut, TimeUnit.SECONDS)
                .writeTimeout(mTimeOut, TimeUnit.SECONDS)
                .build();
        //3 config Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //3声明接口
        Lottie lottie = retrofit.create(Lottie.class);
        //4声明Call
        Call<JSONObject> call = lottie.getJson(path);
        //5执行并返回结果
        String result = JSONObject.toJSONString(call.execute().body());
        System.out.println("RetrofitLog:" + result);
        return result;


    }

    private static void githubTest() throws IOException {
        //2声明retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //3声明接口
        Github github = retrofit.create(Github.class);
        //4声明Call
        Call<List<Contributor>> call = github.contributors("whlei01", "fundDemo");
        //5执行并返回结果
        List<Contributor> contributors = call.execute().body();
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + "(" + contributor.contributions + ")");
        }
    }

    /**
     * 贡献者
     */
    private static class Contributor {
        private String login;
        private int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }
}
