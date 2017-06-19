package com.boco.whl.rxjavademo.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

/**
 * 视频工具类
 * Created by zhang.w.x on 2017/3/16.
 */
public class VideoUtil {
    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

}
