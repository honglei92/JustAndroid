package com.boco.whl.rxjavademo.utils.commonutil.dimconvertor;

import android.content.Context;
import android.util.TypedValue;

public class DimConvertor {

	public static int sp2Pix(Context context, int sp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
				context.getResources().getDisplayMetrics());
	}

	public static int dip2Pix(Context context, int dip) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dip, context.getResources().getDisplayMetrics());
	}

	public static int Px2Dp(Context context, float px) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	public static int dip2Pix(Context context, float dip) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dip, context.getResources().getDisplayMetrics());
	}

	public static int sp2Pix(Context context, float sp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
				context.getResources().getDisplayMetrics());
	}

}
