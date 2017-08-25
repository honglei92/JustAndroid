package com.boco.whl.funddemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

public class MyHorizontalScrollView extends HorizontalScrollView {
	private static final String TAG = "SyncScrollView";
	View mView;

	public MyHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyHorizontalScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mView != null) {
			mView.scrollTo(l, t);
		}
	}

	public void setScrollView(View view) {
		mView = view;
	}
}
