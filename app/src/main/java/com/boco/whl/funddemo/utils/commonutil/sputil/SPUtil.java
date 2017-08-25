package com.boco.whl.funddemo.utils.commonutil.sputil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SPUtil {

	public static void saveObj(Context context, String xmlKey, String objKey,
			Serializable ob) throws Exception {
		SharedPreferences preferences = context.getSharedPreferences(xmlKey,
				Context.MODE_PRIVATE);
		// 创建字节输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 创建对象输出流，并封装字节流
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			// 将对象写入字节流
			oos.writeObject(ob);
			// 将字节流编码成base64的字符窜
			String oAuth_Base64 = new String(Base64.encodeBase64(baos
					.toByteArray()));
			Editor editor = preferences.edit();
			editor.putString(objKey, oAuth_Base64);

			editor.commit();
		} catch (IOException e) {
			// TODO Auto-generated
			throw e;
		}
		Log.i("ok", "存储成功");
	}

	public static Object readObj(Context context, String xmlKey, String objKey)
			throws Exception {
		SharedPreferences preferences = context.getSharedPreferences(xmlKey,
				Context.MODE_PRIVATE);
		String objBase64 = preferences.getString(objKey, "");

		try {
			// 对Base64格式的字符串进行解码
			byte[] base64Bytes = Base64.decodeBase64(objBase64.getBytes());
			ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			// 从ObjectInputStream中读取Product对象
			return ois.readObject();
		} catch (IOException e) {
			throw e;
		}
	}
	
	public static void removeObj(Context context, String xmlKey, String objKey) {
		SharedPreferences preferences = context.getSharedPreferences(xmlKey,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();		
		editor.remove(objKey);
		editor.commit();
	}
	
}
