package com.boco.whl.funddemo.module.activity.thirdlib.retrofit;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    private static String MOVIE_URL = "https://api.douban.com/v2/movie/";
    private static String TAOBAO_URL = "https://tcc.taobao.com/cc/json/";
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

    /**
     * 2定义获取动画接口
     */
    public interface Lottie {
        @GET("{path}")
        Call<JSONObject> getJson(
                @Path("path") String path);
    }

    /**
     * 3定义豆瓣250接口
     */
    public interface MovieService {
        @GET("top250")
        Observable<MovieResult> getTop250(
                @Query("start") int start,
                @Query("count") int count);
    }

    public interface TaobaoService {
        @GET("mobile_tel_segment.htm")
        Observable<String> getPhoneInfo(
                @Query("tel") String tel);
    }

    public static void main(String... args) throws IOException {
//        githubTest();
//        lottieTest(apiUrl, path);
//        callAdapterTest();
        interceptorTest();

    }

    /**
     * 拦截器测试
     */
    private static void interceptorTest() {
        //初始化拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println("Retrofit Log" + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //配置OKHttp
        File direction = new File("D:\\retrofit\\interceptor");
        if (!direction.exists()) {
            direction.mkdir();
        }
        Cache cache = new Cache(direction, 1024 * 1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .build();
        //配置retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TAOBAO_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .build();
        //声明接口
        TaobaoService taobaoService = retrofit.create(TaobaoService.class);
        taobaoService.getPhoneInfo("13982268713")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(String result) {
//                        System.out.println("onNext:\n" + result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });


    }

    /**
     * callAdapter测试 豆瓣250
     *
     * @throws IOException
     */
    private static void callAdapterTest() throws IOException {
        //2声明retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //3声明接口
        MovieService movieService = retrofit.create(MovieService.class);
        //设置监听
        movieService.getTop250(0, 50)
                .subscribe(new Observer<MovieResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieResult result) {
                        System.out.println("onNext");
                        for (Movie movie : result.subjects) {
                            System.out.println(movie.rating.max + "," + movie.rating.average + "," + movie.rating.stars + "," + movie.rating.min
                                    + "\n" + Arrays.toString(movie.genres) + "\n"
                                    + movie.title + "\n"
                                    + movie.collect_count + "\n"
                                    + movie.year + "\n"
                            );
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError" + t.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");

                    }
                });

    }

    /**
     * lottie动画测试
     *
     * @param apiUrl
     * @param path
     * @return
     * @throws IOException
     */
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
//        System.out.println("RetrofitLog:" + result);
        return result;


    }

    /**
     * github测试
     *
     * @throws IOException
     */
    private static void githubTest() throws IOException {
        //2声明retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //3声明接口
        Github github = retrofit.create(Github.class);
        //4声明Call
        Call<List<Contributor>> call = github.contributors("honglei92", "AndroidFundDemo");
        //5执行并返回结果
        List<Contributor> contributors = call.execute().body();
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + "(" + contributor.contributions + ")");
        }
    }

    /**
     * 仓库贡献者
     */
    private static class Contributor {
        private String login;
        private int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    private static class MovieResult {
        private int count;
        private int start;
        private int total;
        private List<Movie> subjects;
    }

    private static class Movie {
        private Rating rating;
        private String[] genres;
        private String title;
        private int collect_count;
        private String year;
        private List<Cast> casts;

        public Movie(Rating rating, String[] genres, String title) {
            this.rating = rating;
            this.genres = genres;
            this.title = title;
        }
    }

    private static class Rating {
        private int max;
        private double average;
        private String stars;
        private int min;
    }

    private static class Cast {
        private String alt;
        private String name;
        private String id;
    }
}
