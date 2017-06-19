package com.boco.whl.rxjavademo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {
	static int pictureSize = 200;
	
	public static boolean saveImg(Context context, String tempPathName, String pathName, String remark) {
//		if (!new File(tempPathName).exists()) {
//			return false;
//		}
		return compressImage(context, tempPathName, pathName, remark);
	}

	@SuppressLint("NewApi")
	static boolean compressImage(Context context, String tempPathName, String pathName, String remark) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap inputBitMap = BitmapFactory.decodeFile(tempPathName, options);
		options.inJustDecodeBounds = false;
		int w = options.outWidth;
		int h = options.outHeight;
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {
			be = (int) (options.outWidth / ww);
		} else if (w < h && h > hh) {
			be = (int) (options.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		options.inSampleSize = be;// 设置缩放比例
//		L.i(BocoConstants.isDebug, TAG, "options.inSampleSize1  " + options.inSampleSize);
		try {
			inputBitMap = BitmapFactory.decodeFile(tempPathName, options);
		} catch (NullPointerException e) {
			if (null != inputBitMap) {
				inputBitMap.recycle();
			}
			e.printStackTrace();
//			EHelper.sendException(context, e);
			return false;
		}
		try {
			if (null != inputBitMap) {
				inputBitMap.copy(Bitmap.Config.ARGB_8888, true);
			}
		} catch (NullPointerException e) {
			if (null != inputBitMap) {
				inputBitMap.recycle();
			}
			e.printStackTrace();
//			EHelper.sendException(context, e);
			return false;
		}
		if (null == inputBitMap) {
//			EHelper.sendException(context, new NullPointerException());
			return false;
		}
//		int rotate = PreferenceUtils.getInt(context, "rotate", 0);
//		int rotate = 0;
//		Matrix matrix = new Matrix();
//		matrix.reset();
//		matrix.postRotate(rotate);
		inputBitMap = Bitmap.createBitmap(inputBitMap, 0, 0, inputBitMap.getWidth(), inputBitMap.getHeight()).copy(Bitmap.Config.ARGB_8888, true);
		// 添加水印开始
		if (remark!=null&&!remark.isEmpty()) {
			int width = inputBitMap.getWidth();
			int height = inputBitMap.getHeight();
			Canvas canvas = null;
			try {
				canvas = new Canvas(inputBitMap);
				TextPaint paint = new TextPaint();
				String fontType = "宋体";
				Typeface font = Typeface.create(fontType, Typeface.NORMAL);
				paint.setColor(Color.CYAN);
				paint.setAntiAlias(true);
				paint.setTypeface(font);
				paint.setTextSize(30f);
				if (null != canvas) {
					StaticLayout layout = new StaticLayout(remark, paint, (width-100), Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
					canvas.save(Canvas.ALL_SAVE_FLAG);
					canvas.translate(5, 5);
					layout.draw(canvas);
					canvas.restore();
				}
				canvas = null;
				paint = null;
			} catch (IllegalStateException e) {
				e.printStackTrace();
//				EHelper.sendException(context, e);
			} catch (Exception e) {
				e.printStackTrace();
//				EHelper.sendException(context, e);
			}
		}
		// 添加水印结束
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int compressRate = 100;
		inputBitMap.compress(Bitmap.CompressFormat.JPEG, compressRate, baos);
		while (baos.toByteArray().length / 1024 > pictureSize) {
			baos.reset();
			inputBitMap.compress(Bitmap.CompressFormat.JPEG, compressRate, baos);
			compressRate -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		try {
			File file = new File(pathName);
			if (null != file && file.exists()) {
				file.delete();
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[5120];
			int rc = 0;
			while ((rc = isBm.read(buffer)) > 0) {
				fos.write(buffer, 0, rc);
				fos.flush();
			}
			if (null != fos) {
				fos.flush();
				fos.close();
				fos = null;
			}
			if (null != inputBitMap) {
				inputBitMap.recycle();
				inputBitMap = null;
			}
//			File tempFile = new File(tempPathName);
//			if (null != tempFile && tempFile.exists()) {
//				tempFile.delete();
//			}
			return true;
		} catch (FileNotFoundException e) {
			if (null != inputBitMap) {
				inputBitMap.recycle();
			}
			e.printStackTrace();
//			EHelper.sendException(context, e);
			return false;
		} catch (IOException e) {
			if (null != inputBitMap) {
				inputBitMap.recycle();
			}
			e.printStackTrace();
//			EHelper.sendException(context, e);
			return false;
		} finally {
			if (null != isBm) {
				try {
					isBm.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != baos) {
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
