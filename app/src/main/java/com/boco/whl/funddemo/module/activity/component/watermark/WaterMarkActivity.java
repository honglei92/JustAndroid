package com.boco.whl.funddemo.module.activity.component.watermark;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.boco.whl.funddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 水印
 */
public class WaterMarkActivity extends Activity {

    @BindView(R.id.sour_pic)
    ImageView mSourImage;
    @BindView(R.id.wartermark_pic)
    ImageView mWartermarkImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_mark);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mSourImage = (ImageView) findViewById(R.id.sour_pic);
        mWartermarkImage = (ImageView) findViewById(R.id.wartermark_pic);
        Bitmap sourBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.group9);
        mSourImage.setImageBitmap(sourBitmap);

        Bitmap waterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.group9);

        Bitmap watermarkBitmap = ImageUtil.createWaterMaskCenter(sourBitmap, waterBitmap);
        watermarkBitmap = ImageUtil.createWaterMaskLeftBottom(this, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = ImageUtil.createWaterMaskRightBottom(this, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = ImageUtil.createWaterMaskLeftTop(this, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = ImageUtil.createWaterMaskRightTop(this, watermarkBitmap, waterBitmap, 0, 0);

        Bitmap textBitmap = ImageUtil.drawTextToLeftTop(this, watermarkBitmap, "左上角", 16, Color.RED, 0, 0);
        textBitmap = ImageUtil.drawTextToRightBottom(this, textBitmap, "右下角", 16, Color.RED, 0, 0);
        textBitmap = ImageUtil.drawTextToRightTop(this, textBitmap, "右上角", 16, Color.RED, 0, 0);
        textBitmap = ImageUtil.drawTextToLeftBottom(this, textBitmap, "左下角", 16, Color.RED, 0, 0);
        textBitmap = ImageUtil.drawTextToCenter(this, textBitmap, "中间", 16, Color.RED);

        mWartermarkImage.setImageBitmap(textBitmap);
    }

}
