package com.boco.whl.funddemo.data.upload;

/**
 * 外部使用的上传监听器
 *
 * Created by xxx on 2017/9/7.
 */

public interface UploadListener {

    /**
     * 进度显示
     * @param tag               方法的标记，通过判断它来挑选符合自己所用的数据
     * @param bytesWritten      已写的字节数
     * @param contentLength     总的字节数
     */
    void onRequestProgress(String tag, long bytesWritten, long contentLength);

}
