package com.boco.whl.funddemo.utils.commonutil.sharesaveobject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 保存的对象必须序列号
 * @author nigang
 *
 */
public class ShareSaveObject {

	@SuppressLint("CommitPrefEdits")
	public static void saveObject(String objkey, Object object,
			SharedPreferences.Editor editor) throws Exception {

		ByteArrayOutputStream toByte = new ByteArrayOutputStream();
		ObjectOutputStream oos;

		oos = new ObjectOutputStream(toByte);
		oos.writeObject(object);

		// 对byte[]进行Base64编码
		String payCityMapBase64 = new String(Base64Coder.encode(toByte
				.toByteArray()));
		editor.putString(objkey, payCityMapBase64);

	}

	public static Object getObject(String objket, SharedPreferences share)
			throws Exception {

		String flag = share.getString(objket, null);
		Object obj = null;

		if (flag != null && !"".equals(flag)) {

			byte[] base64Bytes = Base64Coder.decode(share.getString(objket,
					null));
			ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
			ObjectInputStream ois;

			ois = new ObjectInputStream(bais);
			obj = ois.readObject();

		}

		return obj;
	}

}
