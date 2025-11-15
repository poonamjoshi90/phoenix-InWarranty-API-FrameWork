package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public  class ConfigManagerOld {
	
	private static Properties prop = new Properties();
	
	static
	{

		File configfile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties");
		FileReader fileReader= null;
				try {
					fileReader= new FileReader(configfile);
					prop.load(fileReader);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     
//System.out.print(prop.getProperty("BASE_URI"));



	}
	
	public static  String getProperties(String key)
	{
		return prop.getProperty("BASE_URI");
			
	}

}
