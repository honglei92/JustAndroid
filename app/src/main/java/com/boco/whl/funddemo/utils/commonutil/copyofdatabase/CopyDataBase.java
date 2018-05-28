package com.boco.whl.funddemo.utils.commonutil.copyofdatabase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressLint("SdCardPath")
/**
 * 
 * @author nigang
 * 例子:
 * 	CopyDataBase  cdb=new CopyDataBase();
 try {
 cdb.copyBaseToSystem(context);
 }  

 catch (Exception e) {



 final MyAlertDialog dialog = new MyAlertDialog(context);
 dialog.setCancelable(false);
 dialog.setTitle("错误");
 dialog.setMessage("sqlite数据库拷贝出现异常,请联系管理员");
 dialog.setPositiveButton("关闭", new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 // TODO Auto-generated method stub
 dialog.dismiss();
 context.finish();

 }
 });
 dialog.show();
 return;
 }
 *
 */
public class CopyDataBase {

	// com.boco.bmdp.stationentry/databases/
	public static final String DB_PATH = "/data/data/";
	public static final String DB_NAME = "data_sqlite3.db";

	public void copyBaseToSystem(Activity context)
			throws NameNotFoundException, IOException {

		@SuppressWarnings("static-access")
		SharedPreferences andriodcode = context.getSharedPreferences(
				"copybasetosystem", context.MODE_PRIVATE);

		int versionCode = 0;

		versionCode = context.getPackageManager().getPackageInfo(
				context.getPackageName(), 0).versionCode;

		int andriodCodeId = andriodcode.getInt("code", -1);

		if (andriodCodeId == -1 || andriodCodeId != versionCode) {

			CheckDBFile(context);
			Editor editor = andriodcode.edit();
			editor.putInt("code", versionCode);
			editor.commit();

		}

	}

	private void CheckDBFile(Activity context) throws IOException,
			NameNotFoundException {

		// 获取包名
		String packagename = getAppPackage(context);
		StringBuffer sb = new StringBuffer(DB_PATH);
		sb.append(packagename);
		sb.append("/databases/");

		File f = new File(sb.toString());
		if (!f.exists()) {
			f.mkdirs();
		}

		// System.out.println(context.getBaseContext().getAssets().toString());
		InputStream is = context.getBaseContext().getAssets().open(DB_NAME);
		// Copy the database into the destination
		OutputStream os = new FileOutputStream(sb.toString() + DB_NAME);
		byte[] buffer = new byte[1024];

		int length;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}

		os.flush();
		os.close();
		is.close();

	}

	private String getAppPackage(Activity context) throws NameNotFoundException {

		PackageInfo info;

		info = context.getPackageManager().getPackageInfo(
				context.getPackageName(), 0);
		// 当前应用的版本名称
		// String versionName = info.versionName;
		// 当前版本的版本号
		// int versionCode = info.versionCode;
		// 当前版本的包名
		String packageNames = info.packageName;

		return packageNames;

	}

}
