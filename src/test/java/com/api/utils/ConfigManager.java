package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class ConfigManager {

	private static Properties prop = new Properties();
	private static String  path="config\\config.properties";
	private static String env;

	static
	{


				try {
					env=System.getProperty("env","qa");
					switch (env)
					{
					case "dev":
					{
						path="config\\configDev.properties";
						break;
					}
					case "qa":
					{
						path="config\\configQA.properties";
						break;
					}
					case "uat":
					{
						path="config\\configUAT.properties";
					}
					case "Default":
					{
						path="config\\config.properties";
						break;
					}

					}
					InputStream input=	Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

					prop.load(input);
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
