package com.boco.whl.funddemo.utils.commonutil.fileutil;

import java.io.InputStream;
import java.util.Properties;

public class ConfigFile { 
    private static Properties prop = null;   
    //private static Context context; 
    static {    
        try {    
            prop = new Properties();       
            InputStream is = ConfigFile.class.getClassLoader().getResourceAsStream("server.properties");   
            prop.load(is);     
          
        }catch (Exception e) {    
            e.printStackTrace(); 
        }    
    } 
     
    public static String getBmdpServerUrl(){ 
    	String url = prop.getProperty("BMDP_SERVER");
        return url.substring(0,url.length()-"service".length()); 
    } 
    public static String getBmdpServerRUrl(){ 
    	String url = prop.getProperty("BMDP_SERVER");
        return url.substring(0,url.length()-"bmdp/service".length()); 
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("----- "+ConfigFile.getBmdpServerUrl());
	}

}
