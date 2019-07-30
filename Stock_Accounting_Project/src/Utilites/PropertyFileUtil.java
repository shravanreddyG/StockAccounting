package Utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil
{
	public static String getValueForKey(String key) throws Throwable, IOException
	{
		Properties configProperties=new Properties();
		configProperties.load(new FileInputStream("D:\\shravan\\Stock_Accounting_Project\\PropertiesFile\\Environment.properties"));
		return configProperties.getProperty(key);	}
}
