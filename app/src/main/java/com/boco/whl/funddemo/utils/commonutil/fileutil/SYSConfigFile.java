package com.boco.whl.funddemo.utils.commonutil.fileutil;

import java.io.InputStream;
import java.util.Properties;

public class SYSConfigFile { 
    private static Properties prop = null;   
    //private static Context context; 
//    static {    
//        try {    
//            prop = new Properties();       
//            InputStream is = SYSConfigFile.class.getClassLoader().getResourceAsStream("sysconfigfile.properties");   
//            prop.load(is);     
//          
//        }catch (Exception e) {    
//            e.printStackTrace(); 
//        }    
//    } 
    
    public SYSConfigFile(String filename){
    	try {    
            prop = new Properties();       
            InputStream is = SYSConfigFile.class.getClassLoader().getResourceAsStream(filename);   
            prop.load(is);     
          
        }catch (Exception e) {    
            e.printStackTrace(); 
        }  
    }
    
    public Properties getSYSProperties(){
    	return prop ;
    }
    
    public String getCfgPropertiesName(String name){
    	return getSYSProperties().getProperty(name) ;
    }
    
}
