package com.boco.whl.funddemo.module.activity.thirdlib.imagecompress;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.config.Constant;
import com.boco.whl.funddemo.utils.ImageCompressUtils;
import com.boco.whl.funddemo.utils.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/16 0016 9:19
 *  updatetime : 2017/6/16 0016 9:19
 * </pre>
 */
public class ImageCompressActivity extends Activity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_none);
        ButterKnife.bind(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initCompress(int i) {
        FileUtils.deleteFile(Constant.FILE);
        File file = new File(Constant.FILE);
        Bitmap bitmap = ImageUtils.drawable2Bitmap(getDrawable(R.drawable.advertising));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.d("压缩前文件的大小:" + (file.length() / 1024) + "KB"
                + "    图片大小:" + (bitmap.getByteCount() / 1024 / 1024) + "M"
                + "    宽度为:" + bitmap.getWidth()
                + "    高度为:" + bitmap.getHeight());
        switch (i) {
            case 1:
                ImageCompressUtils.compressByQuality(bitmap, 80, file);
                break;
            case 2:
                ImageCompressUtils.compressByScale(bitmap, 2, file);
                break;
            case 3:
                ImageCompressUtils.compresseBySampleSize(3, file);
                break;
            case 4:
                ImageCompressUtils.compresseByConfig(Bitmap.Config.ARGB_8888, file);
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                initCompress(1);
                break;
            case R.id.btn2:
                initCompress(2);
                break;
            case R.id.btn3:
                initCompress(3);
                break;
            case R.id.btn4:
                initCompress(4);
                break;
        }
    }
}
