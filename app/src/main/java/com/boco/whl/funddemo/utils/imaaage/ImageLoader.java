package com.boco.whl.funddemo.utils.imaaage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author:wanghonglei@boco.com.cn
 * desc:图片加载类
 * createTime:2017/8/26 0026
 * updateTime:2017/8/26 0026
 */

public class ImageLoader {
    private ImageCache mImageCache = new ImageCache();
    //线程池,线程数量为cpu的数量
    ExecutorService mExcutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageLoader() {
        initImageLoader();
    }

    private void initImageLoader() {

    }

    public void displayImage(final String url, final ImageView imageView) {
        imageView.setTag(url);
        if (mImageCache.get(url) != null) {
            imageView.setImageBitmap(mImageCache.get(url));
        } else {
            downloadImage(imageView, url);
        }
    }

    private void downloadImage(ImageView imageView, String imageUrl) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                //请求数据
                Bitmap bitmap = null;
                try {
                    //通过openConnection 连接
                    URL url = new URL(imageUrl);
                    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5 * 10 * 1000);
                    conn.setRequestMethod("GET");
                    //设置输入和输出流
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == 200) {
                        bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                        //关闭连接
                        conn.disconnect();
                        return bitmap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                super.onPostExecute(result);
                if (result != null) {
                    mImageCache.put(imageUrl, result);
                    imageView.setImageBitmap(result);
                } else {
                    System.out.print("下载失败");
                }
            }
        }.execute();
    }

    public static String convertStreamToString(InputStream is) {
        /*
          * To convert the InputStream to String we use the BufferedReader.readLine()
          * method. We iterate until the BufferedReader return null which means
          * there's no more data to read. Each line will appended to a StringBuilder
          * and returned as String.
          */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
