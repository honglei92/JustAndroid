package com.boco.whl.rxjavademo.utils.commonutil.encrypt;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

	public static String getMd5(Context context) {
		String md5Str = null;
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(
					context.getPackageName(), PackageManager.GET_SIGNATURES);

			Signature signatures = pi.signatures[0];

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(signatures.toByteArray());
			byte[] digest = md.digest();

			md5Str = toHexString(digest);

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return md5Str;
	}

	private static String toHexString(byte[] block) {

		StringBuffer buf = new StringBuffer();

		int len = block.length;

		for (int i = 0; i < len; i++) {

			byte2hex(block[i], buf);

			if (i < len - 1) {

				buf.append(":");

			}

		}

		return buf.toString();

	}

	private static void byte2hex(byte b, StringBuffer buf) {

		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',

		'9', 'A', 'B', 'C', 'D', 'E', 'F' };

		int high = ((b & 0xf0) >> 4);

		int low = (b & 0x0f);

		buf.append(hexChars[high]);

		buf.append(hexChars[low]);

	}
}
