package com.boco.whl.funddemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/16 0016 9:14
 *  updatetime : 2017/6/16 0016 9:14
 * </pre>
 */
public class ImageCompressUtils {
    /**
     * 质量压缩
     *
     * @param bmp
     * @param quality
     * @param file
     */
    public static void compressByQuality(Bitmap bmp, int quality, File file) {
        // quality 0-100 100为不压缩
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        writeToFile(baos, file);
        log(file, bmp);
    }


    /**
     * 尺寸压缩
     *
     * @param bmp
     * @param file
     */
    public static void compressByScale(Bitmap bmp, int ratio, File file) {// 尺寸压缩倍数,值越大，图片尺寸越小
        // 压缩Bitmap到对应尺寸
        Bitmap result = Bitmap.createBitmap(bmp.getWidth() / ratio, bmp.getHeight() / ratio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, bmp.getWidth() / ratio, bmp.getHeight() / ratio);
        canvas.drawBitmap(bmp, null, rect, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        result.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        result = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        writeToFile(baos, file);
        log(file, result);
    }

    /**
     * 采样率压缩
     *
     * @param inSampleSize
     * @param file
     */
    public static void compresseBySampleSize(int inSampleSize, File file) {// 数值越高，图片像素越低
        BitmapFactory.Options options = new BitmapFactory.Options();
        //采样率
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        writeToFile(baos, file);
        log(file, bitmap);
    }

    /**
     * 色值压缩
     *
     * @param config
     * @param file
     */
    public static void compresseByConfig(Bitmap.Config config, File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //色值
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        writeToFile(baos, file);
        log(file, bitmap);
    }

    private static void writeToFile(ByteArrayOutputStream baos, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(File file, Bitmap bmp) {
        LogUtil.d("压缩后文件的大小:" + file.length() / 1024 + "KB"
                + "    图片大小:" + (bmp.getByteCount() / 1024 / 1024) + "M"
                + "    宽度为:" + bmp.getWidth()
                + "    高度为:" + bmp.getHeight()
        );
    }
}
