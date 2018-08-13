package com.boco.whl.funddemo.utils.download;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author:honglei92
 * @time:2018/7/30
 */
public class FileUtils {

    public static File createFile(Context context) throws IOException {

        File file = null;
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {

            file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "/fund/test.apk");
        } else {
            file = new File(context.getCacheDir().getAbsolutePath() + "/fund/test.apk");
        }

        Log.d("vivi", "file " + file.getAbsolutePath());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists())
            file.createNewFile();

        return file;

    }

    public static void writeFile2Disk(Response<ResponseBody> response, File file, HttpCallBack httpCallBack) {

        long currentLength = 0;
        OutputStream os = null;

        InputStream is = response.body().byteStream();
        long totalLength = response.body().contentLength();

        try {

            os = new FileOutputStream(file);

            int len;

            byte[] buff = new byte[1024];

            while ((len = is.read(buff)) != -1) {

                os.write(buff, 0, len);
                currentLength += len;
                Log.d("vivi", "当前进度:" + currentLength);
                httpCallBack.onLoading(currentLength, totalLength);
            }
            httpCallBack.onFinish();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public interface HttpCallBack {
        void onLoading(long current, long total);

        void onFinish();
    }

}
