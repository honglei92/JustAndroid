package com.boco.whl.funddemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils
{
	/**
	 * Returns whether the network is available.
	 */
	public static boolean isNetworkAvailable(Context context)
	{
		ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivity == null)
		{
			LogUtil.w("couldn't get connectivity manager");
		}
		else
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if(info != null)
			{
				for(int i = 0, length = info.length; i < length; i++)
				{
					if(info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 判断WIFI网络是否可用
	 *
	 * @param context
	 * @return
	 */
	public boolean isWifiConnected(Context context)
	{
		if(context != null)
		{
			ConnectivityManager mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if(mWiFiNetworkInfo != null)
			{
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static int dipToPx(Context context, int dipValue)
	{
		return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
	}

	public static void showToastShort(Context con, Object obj)
	{
		Toast.makeText(con, obj.toString(), Toast.LENGTH_SHORT).show();
	}

	public static void showToastLong(Context con, Object obj)
	{
		Toast.makeText(con, obj.toString(), Toast.LENGTH_LONG).show();
	}

	/**
	 * 获取手机IMEI
	 *
	 * @return
	 */
	public static String getIMEI(Context context)
	{
		TelephonyManager mTelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		return mTelephonyMgr.getDeviceId();
	}

	//	/**
	//	 * 创建Tab中的只包含TextView的View
	//	 */
	//	public static View createTabView(Context context, String text) {
	//		TextView view = (TextView) LayoutInflater.from(context).inflate(
	//				R.layout.shome_common_tab_view, null);
	//		view.setText(text);
	//		return view;
	//	}

	/**
	 * 图片异步加载
	 **/
	public static void CopyStream(InputStream is, OutputStream os)
	{
		final int buffer_size = 1024;
		try
		{
			byte[] bytes = new byte[buffer_size];
			for(; ; )
			{
				int count = is.read(bytes, 0, buffer_size);
				if(count == -1)
					break;
				os.write(bytes, 0, count);
			}
		}
		catch(Exception ex)
		{
		}
	}

	/**
	 * 获取当前时间
	 **/
	@SuppressLint("SimpleDateFormat")
	public static String getDateNonce()
	{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(new Date());
	}

	@SuppressLint("SimpleDateFormat")
	public static String getDateNonceYYYMMDD()
	{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date());
	}

	public static String round(double d, int position)
	{
		if(position < 0)
			return null;

		BigDecimal b = new BigDecimal(Double.toString(d));
		BigDecimal one = new BigDecimal("1");
		String ret = Double.toString(b.divide(one,position,BigDecimal.ROUND_HALF_UP).doubleValue());

		int index = ret.indexOf(".");
		if(index == -1)
			return ret;

		if(position == 0)
			return ret.substring(0, index);

		return ret;
	}
}
