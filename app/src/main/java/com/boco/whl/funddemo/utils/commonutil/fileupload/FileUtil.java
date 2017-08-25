package com.boco.whl.funddemo.utils.commonutil.fileupload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

//import android.util.Log;

public class FileUtil 
{
	/**
	 * 
	 * 写文�?
	 * 读文�?
	 * @param f
	 * @return
	 * @throws IOException 
	 */
	public static void writeFile(File fromfile,File tofile) throws IOException,FileNotFoundException{
		OutputStream outStream = new FileOutputStream(tofile,true);//fromfile
    	FileInputStream    fis = new FileInputStream(fromfile);//tofile
    	byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) != -1) {
        	outStream.write(buffer, 0, len);
        } 
        outStream.flush();
        outStream.close();
        fis.close();
	}
	
	public static void writeFile(byte[] fdata,File tofile) throws IOException,FileNotFoundException{
		//Log.i("执行 写方�?,"成功"+tofile.getPath());
		if(!tofile.exists())
			tofile.createNewFile();
		OutputStream outStream = new FileOutputStream(tofile);
        outStream.write(fdata,0,fdata.length);
        outStream.flush();
        outStream.close();
        //Log.i("执行 写方�?结束","成功");
	}
	
	public static void writeFile(byte[] fdata,String dir,String fname) throws IOException,FileNotFoundException{
		File ldir = new File(dir);
		if(!ldir.exists())
			ldir.mkdir();
		File tofile = new File(dir+""+fname);
		//Log.i("执行 写方�?,"成功"+tofile.getPath());
		if(!tofile.exists())
			tofile.createNewFile();
		OutputStream outStream = new FileOutputStream(tofile);
        outStream.write(fdata,0,fdata.length);
        outStream.flush();
        outStream.close();
        //Log.i("执行 写方�?结束","成功");
	}
	// 文件转化btye
	public static byte[] getBytesFromFile(File f) throws IOException,FileNotFoundException{
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			byte[] b = new byte[1024];
			int n = 0 ;
			while ((n = stream.read(b)) != -1) {
				out.write(b, 0, n);
			}
			stream.close();
			out.close();

			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("文件流输入输出异");
		}
		//return null;
	}
	
	public static void getPicList(File path,List<String> liststr) {
		// Map<String, String> map = new HashMap<String, String>();
		// 如果是文件夹的话
		if (path.isDirectory()) {
			// 返回文件夹中有的数据
			File[] files = path.listFiles();
			// 先判断下有没有权限，如果没有权限的话，就不执行了
			if (null == files)
				return;
			for (int i = 0; i < files.length; i++) {
				getPicList(files[i], liststr);
			}
		}
		// 如果是文件的话直接加
		else {
			// 进行文件的处
			String filePath = path.getAbsolutePath();
			// 文件
			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
			if (fileName.matches(".*\\.png")
					|| fileName.matches(".*\\.jpg")
					|| fileName.matches(".*\\.bmp") 
				    || fileName.matches(".*\\.jpeg")
				    || fileName.matches(".*\\.gif")
				    || fileName.matches(".*\\.jpe")
				    || fileName.matches(".*\\.log")
				) 
			{
				//Log.i(TAG, " dir:" + fileName);
				liststr.add(filePath);
			}
		}
	}
	
	/*
	 *  删除文件以及文件夹
	 */
	public static void RecursionDeleteFile(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }

}
