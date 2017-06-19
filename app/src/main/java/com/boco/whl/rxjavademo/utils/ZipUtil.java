package com.boco.whl.rxjavademo.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by vincent on 2016/11/29.
 */

public class ZipUtil {
    private static File file = null;
    private static ZipOutputStream outZip = null;

    public static File zip(List<String> list, String zipPath) {
        if (list != null && list.size() > 0) {
            try {
                //压缩
                file = new File(zipPath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }

                file.createNewFile();
                outZip = new ZipOutputStream(new FileOutputStream(zipPath));
                for (int i = 0;i<list.size();i++){
                    file = new File(list.get(i));
                    if (!file.exists()||!file.isFile())
                        continue;
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    FileInputStream inputStream = new FileInputStream(file);
                    outZip.putNextEntry(zipEntry);

                    int len;
                    byte[] buffer = new byte[4096];

                    while ((len = inputStream.read(buffer)) != -1) {
                        outZip.write(buffer, 0, len);
                    }
                    inputStream.close();
                    outZip.closeEntry();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (outZip != null)
                    try {
                        outZip.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
        return file;
    }
    //获得指定文件的byte数组
    public static byte[] getBytes(File file){
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static byte[] File2byte(String filePath)
    {
        byte[] buffer = null;
        try
        {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);

            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }
}
