package com.boco.whl.funddemo.utils.commonutil.fileutil;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 功能描述：创建TXT文件并进行读、写、修改操作
 * 
 * @author wangtiancong
 * @version 1.0 Creation date: 2009-11-13 - 下午06:48:45
 */
public class ReadWriteFile {

	// 指定文件路径和名称
	BufferedWriter out;

	public ReadWriteFile() {

	}

	public ReadWriteFile(String filename, String dir) {
		File file = new File(dir);
		try {
			if (!file.isDirectory()) {
				file.mkdirs();
				System.out.println(dir + "目录被创建！");
			}
			filename = dir + filename;
			file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(filename, true));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addFile(String filename, String dir) {
		File file = new File(dir);
		try {
			if (!file.isDirectory()) {
				file.mkdirs();
				System.out.println(dir + "目录被创建！");
			}
			filename = dir + filename;
			file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(filename, true));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void writefile(String str) {
		try {
			out.write(str);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delFile(String filename, String dir) {
		String filename1 = dir + filename;
		File file = new File(filename1);
		if (file.exists()) {
			file.delete();
			// file.deleteOnExit();
		}
	}

	public void close() {
		try {
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static void readFileByLines(String fileName, String writeDir,
			String writeFileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			ReadWriteFile writefile = new ReadWriteFile(writeFileName, writeDir);
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				writefile.writefile(tempString + "\n");
				line++;
			}
			System.out.println("read file " + fileName + " row " + line);
			reader.close();
			writefile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static StringBuffer readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer linestr = new StringBuffer();
		;
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				linestr.append(tempString + "\n");
				line++;
			}
			System.out.println("read file " + fileName + " row " + line);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return linestr;
	}

	// 写文件操作

	/**
	 * 
	 * 写文件 读文件
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static void writeFile(File fromfile, File tofile)
			throws IOException, FileNotFoundException {
		OutputStream outStream = new FileOutputStream(fromfile);
		FileInputStream fis = new FileInputStream(tofile);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.flush();
		outStream.close();
		fis.close();
	}

	public static void writeFile(byte[] fdata, File tofile) throws IOException,
			FileNotFoundException {
		Log.i("执行 写方法", "成功" + tofile.getPath());
		if (!tofile.exists())
			tofile.createNewFile();
		OutputStream outStream = new FileOutputStream(tofile);
		outStream.write(fdata, 0, fdata.length);
		outStream.flush();
		outStream.close();
		Log.i("执行 写方法 结束", "成功");
	}

	public static void writeFile(byte[] fdata, String dir, String fname)
			throws IOException, FileNotFoundException {
		File ldir = new File(dir);
		if (!ldir.exists())
			ldir.mkdir();
		File tofile = new File(dir + "" + fname);
		Log.i("执行 写方法", "成功" + tofile.getPath());
		if (!tofile.exists())
			tofile.createNewFile();
		OutputStream outStream = new FileOutputStream(tofile);
		outStream.write(fdata, 0, fdata.length);
		outStream.flush();
		outStream.close();
		Log.i("执行 写方法 结束", "成功");
	}

	// 文件转化btye
	public static byte[] getBytesFromFile(File f) throws IOException,
			FileNotFoundException {
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			byte[] b = new byte[1024];
			int n = 0;
			while ((n = stream.read(b)) != -1) {
				out.write(b, 0, n);
			}
			stream.close();
			out.close();

			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("文件流输入输出异常");
		}
		// return null;
	}

	public static void getPicList(File path, List<String> liststr) {
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
			if (fileName.matches(".*\\.png") || fileName.matches(".*\\.jpg")
					|| fileName.matches(".*\\.bmp")
					|| fileName.matches(".*\\.jpeg")
					|| fileName.matches(".*\\.gif")
					|| fileName.matches(".*\\.jpe")) {
				// Log.i(TAG, " dir:" + fileName);
				liststr.add(filePath);
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件不存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * @param fromFile
	 *            被复制的文件
	 * @param toFile
	 *            复制的目录文件
	 * @param rewrite
	 *            是否重新创建文件
	 * 
	 *            <p>
	 *            文件的复制操作方法
	 */
	public static void copyfile(File fromFile, File toFile, Boolean rewrite) {

		if (!fromFile.exists()) {
			return;
		}

		if (!fromFile.isFile()) {
			return;
		}
		if (!fromFile.canRead()) {
			return;
		}
		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		if (toFile.exists() && rewrite) {
			toFile.delete();
		}

		try {
			FileInputStream fosfrom = new FileInputStream(fromFile);
			FileOutputStream fosto = new FileOutputStream(toFile);

			byte[] bt = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			// 关闭输入、输出流
			fosfrom.close();
			fosto.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 得到文件路径
	public static String getFileName(String path) {
		String fileName = "";

		fileName = path.substring(path.lastIndexOf("/") + 1, path.length());

		return fileName;
	}

	public static void main(String args[]) {
		// ReadWriteFile wr = new ReadWriteFile("temp.dat","load/888/");
		// int i = 0 ;
		// while(i <10){
		// wr.writefile("dddddddddddddddddd\r\n");
		// System.out.println("dddddddd");
		// i++ ;
		// }
		// wr.close();
	}

	/**
	 * @param path
	 *            获取当前目录的 所有文件。。或者子目录的所有文件 文件路径
	 * @param suffix
	 *            后缀名, 为空则表示所有文件
	 * @param isdepth
	 *            是否遍历子目录
	 * @return list
	 */
	public static List<String> getListFiles(String path, String suffix,
			boolean isdepth) {
		List<String> lstFileNames = new ArrayList<String>();
		File file = new File(path);
		return listFile(lstFileNames, file, suffix, isdepth);
	}

	private static List<String> listFile(List<String> lstFileNames, File f,
			String suffix, boolean isdepth) {
		// 若是目录, 采用递归的方法遍历子目录
		if (f.isDirectory()) {
			File[] t = f.listFiles();

			for (int i = 0; i < t.length; i++) {
				if (isdepth || t[i].isFile()) {
					listFile(lstFileNames, t[i], suffix, isdepth);
				}
			}
		} else {
			String filePath = f.getAbsolutePath();
			if (!suffix.equals("")) {
				int begIndex = filePath.lastIndexOf("."); // 最后一个.(即后缀名前面的.)的索引
				String tempsuffix = "";

				if (begIndex != -1) {
					tempsuffix = filePath.substring(begIndex + 1,
							filePath.length());
					if (tempsuffix.equals(suffix)) {
						lstFileNames.add(filePath);
					}
				}
			} else {
				lstFileNames.add(filePath);
			}
		}
		return lstFileNames;
	}

	public static void readtoWriteFile(String readfile, String writefile,boolean first) {

		try {

			FileReader fr = new FileReader(readfile);
		

			BufferedReader br = new BufferedReader(fr);

			RandomAccessFile randomFile = new RandomAccessFile(writefile, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			if(!first)
			{
			randomFile.write(System.getProperty("line.separator").getBytes()); //换行
			}

			// int i = 0;// 记录行数的标识。
			String temp = br.readLine();
			// 当读取六行，则通过bw写到写文件中。
			while (temp != null) {

				randomFile.write((temp + System.getProperty("line.separator"))
						.getBytes());
				temp = br.readLine();
			}
			// 如果最后不到六行，且写到末尾，则把所有余下的写到文件中。

			br.close();
			randomFile.close();
			System.out.println("转写文件完成");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
