package com.boco.whl.funddemo.utils.commonutil.fileupload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUpLoad {
	final static String TAG = "FileUpLoad";
	/**
	 * 上传图片到服务器
	 * @param imageFile
	 *            包含路径
	 */
	
	
	public boolean upload(String filepath,String urlstr) throws Exception {
		String boundary = "---------------------------7db1c523809b2";
		// 分割线
		File file = new File(filepath);
		if(!file.exists())
            file.createNewFile();
		// 要上传的文件 
		String actionUrl = urlstr;//"http://10.185.20.81:8081/androidfileup/servlet/UploadFileServlet";
		URL url = new URL(actionUrl);
		// 用来解析主机名和端口
		//URL url = new URL(uri);
		// 用来开启连接 
		StringBuilder sb = new StringBuilder();
		// 用来拼装请求


//		// username字段
//		sb.append("--" + boundary + "\r\n");
//		sb.append("Content-Disposition: form-data; name=\"username\"" + "\r\n");
//		sb.append("\r\n");
//		sb.append(username + "\r\n");
//
//
//		// password字段
//		sb.append("--" + boundary + "\r\n");
//		sb.append("Content-Disposition: form-data; name=\"password\"" + "\r\n");
//		sb.append("\r\n");
//		sb.append(password + "\r\n");


		// 文件部分
		sb.append("--" + boundary + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + filepath + "\"" + "\r\n");
		sb.append("Content-Type: application/octet-stream" + "\r\n");
		sb.append("\r\n");


		// 将开头和结尾部分转为字节数组，因为设置Content-Type时长度是字节长度
		byte[] before = sb.toString().getBytes("UTF-8");
		byte[] after = ("\r\n--" + boundary + "--\r\n").getBytes("UTF-8");

		// 打开连接, 设置请求头
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(3000);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		conn.setRequestProperty("Content-Length", before.length + file.length() + after.length + "");
		conn.setRequestProperty("HOST", url.getHost() + ":" + url.getPort());
		// 192.168.1.100:8080
		conn.setDoOutput(true);


		// 获取输入输出流
		OutputStream out = conn.getOutputStream();
		FileInputStream fis = new FileInputStream(file);


		// 将开头部分写出
		out.write(before);


		// 写出文件数据
		byte[] buf = new byte[1024];
		int len;
		while ((len = fis.read(buf)) != -1)
			out.write(buf, 0, len);


		// 将结尾部分写出
		out.write(after);

		fis.close();
		out.close();
		return conn.getResponseCode() == 200;
		}
	
	
	
	
	
	 //照片上传
	public String uploadform(String filepath,String urlstr) throws Exception {
		String boundary = "---------------------------7db1c523809b2";
		// 分割线
		File file = new File(filepath);
		if(!file.exists())
            file.createNewFile();
		// 要上传的文件 
		String actionUrl = urlstr;//"http://10.185.20.81:8081/androidfileup/servlet/UploadFileServlet";
		URL url = new URL(actionUrl);
		// 用来解析主机名和端口
		//URL url = new URL(uri);
		// 用来开启连接 
		StringBuilder sb = new StringBuilder();
		// 用来拼装请求


//		// username字段
//		sb.append("--" + boundary + "\r\n");
//		sb.append("Content-Disposition: form-data; name=\"username\"" + "\r\n");
//		sb.append("\r\n");
//		sb.append(username + "\r\n");
//
//
//		// password字段
//		sb.append("--" + boundary + "\r\n");
//		sb.append("Content-Disposition: form-data; name=\"password\"" + "\r\n");
//		sb.append("\r\n");
//		sb.append(password + "\r\n");


		// 文件部分
		sb.append("--" + boundary + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + filepath + "\"" + "\r\n");
		sb.append("Content-Type: application/octet-stream" + "\r\n");
		sb.append("\r\n");


		// 将开头和结尾部分转为字节数组，因为设置Content-Type时长度是字节长度
		byte[] before = sb.toString().getBytes("UTF-8");
		byte[] after = ("\r\n--" + boundary + "--\r\n").getBytes("UTF-8");

		// 打开连接, 设置请求头
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(3000); //连接超时
		conn.setReadTimeout(120000); //网络超时
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		conn.setRequestProperty("Content-Length", before.length + file.length() + after.length + "");
		conn.setRequestProperty("HOST", url.getHost() + ":" + url.getPort());
		// 192.168.1.100:8080
		conn.setDoOutput(true);



		// 获取输入输出流
		OutputStream out = conn.getOutputStream();
		FileInputStream fis = new FileInputStream(file);


		// 将开头部分写出
		out.write(before);


		// 写出文件数据
		byte[] buf = new byte[1024];
		int len;
		while ((len = fis.read(buf)) != -1)
			out.write(buf, 0, len);


		// 将结尾部分写出
		out.write(after);

		
		fis.close();
		out.close();
	int code=conn.getResponseCode();
	
	String sCurrentLine="";
	String sTotalString="";
		if(code==200)
		{
			java.io.InputStream is=conn.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(is));
			while((sCurrentLine=reader.readLine())!=null)
			{
				if(sCurrentLine.length()>0)
				{
					sTotalString=sTotalString+sCurrentLine.trim();
				}
			}
				
		}
		else
		{
			
		}
		return sTotalString;
		
		
		}
	
	public static void main(String args[]) {
		FileUpLoad fd = new FileUpLoad();
		
//		try {
//			fd.upload("E:/ButtonBar.ini");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}

