package com.boco.whl.funddemo.data.api;


import com.boco.whl.funddemo.entity.ApiBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 上传图片和视频
 * Created by xxx on 2017/6/4.
 */

public interface UploadService {
    @Multipart
    @POST("Userinfo/upd_img")
    Observable<ApiBean> sendEditInfoImgAndVideo(@Header("token") String token,
                                                @Part MultipartBody.Part image,
                                                @Part MultipartBody.Part video);

    /**
     * 测试图片
     * Created by xxx on 2017/6/4.
     */
    @Multipart
    @POST("Prove/image")
    Observable<ApiBean> sendImg(@Header("token") String token,
                                @Part MultipartBody.Part image);


    /**
     * 测试视频
     * Created by xxx on 2017/6/4.
     */
    @Multipart
    @POST("Prove/video")
    Observable<ApiBean> sendVideo(@Header("token") String token,
                                  @Part MultipartBody.Part video);
}