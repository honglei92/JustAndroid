package com.boco.whl.rxjavademo.utils;

import java.io.File;

/**
 * 文件工具类
 * Created by zhang.w.x on 2017/3/29.
 */
public class FileUtil {
    /**
     * 删除文件或者文件夹
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }

        if (file.isDirectory()) {
            //如果是文件夹，先删除子文件
            File[] childs = file.listFiles();
            for (File childFile : childs) {
                deleteFile(childFile);
            }
        }

        file.delete();
    }
}
