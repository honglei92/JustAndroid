package com.boco.whl.funddemo.utils.download;

import android.app.Activity;
import android.os.Message;
import android.util.Log;

import com.boco.whl.funddemo.module.activity.thirdlib.retrofit.DownloadApkActivity;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import static java.lang.Thread.currentThread;

/**
 * @author:honglei92
 * @time:2018/7/31
 */
public class DownloadApi {
    private int progress;

    public void download(Activity activity, String downloadUrl, DownloadApkActivity.MyHandler handler) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://surl.qq.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DownloadService downloadService = retrofit.create(DownloadService.class);

        Call<ResponseBody> responseBodyCall = downloadService.downloadFile(downloadUrl);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("vivi", response.message() + "  length  " + response.body().
                        contentLength()
                        + "  type " + response.body().contentType());
                try {
                    saveFile(activity, response, handler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Log.d("vivi", t.getMessage() + "  " + t.toString());
            }
        });
    }

    private void saveFile(Activity activity, Response<ResponseBody> response, DownloadApkActivity.MyHandler handler) throws IOException {
        //建立一个文件
        File file = FileUtils.createFile(activity);

        //下载文件放在子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileUtils.writeFile2Disk(response, file, new FileUtils.HttpCallBack() {

                    @Override
                    public void onLoading(long current, long total) {
                        Log.d("vivi", current + " to " + total);
                        Log.d("vivi", " runOnUiThread  " + currentThread().getName());
                        progress = (int) (((double) current / (double) total) * 100);
                        Message message = new Message();
                        message.arg1 = 123;
                        message.what = progress;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFinish() {
                        InstallUtil.installNormal(activity, file.getPath());
                        activity.finish();
                    }
                });
            }
        }).start();
    }

    interface DownloadService {
        @Streaming //大文件时要加不然会OOM
        @GET
        Call<ResponseBody> downloadFile(@Url String fileUrl);
    }
}
