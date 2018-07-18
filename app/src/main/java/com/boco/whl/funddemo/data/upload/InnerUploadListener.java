package com.boco.whl.funddemo.data.upload;

/**
 * 内部使用上传监听器
 *
 * Created by xxx on 2017/9/8.
 */

public interface InnerUploadListener {

    void onRequestProgress(long bytesWritten, long contentLength);

}
