package com.boco.whl.funddemo.module.activity.thirdlib.retrofit;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
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

public class SampleTest {
    private static String API_URL = "https://api.github.com";

    public interface Github {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

    public static void main(String... args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Github github = retrofit.create(Github.class);
        Call<List<Contributor>> call = github.contributors("whlei01", "fundDemo");
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
