package com.disney.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReading {
	String filePath;
	Properties prop;
	
	public PropertyReading(String path){
	   prop = new Properties();
		try{
		FileInputStream fs = new FileInputStream(path);
		prop.load(fs);
		}catch(Exception e){
			
		}
	}

	
	
	public String getProperty(String key){
		return prop.getProperty(key);
	}
	

}
