package CommonFunLibary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadingData
{
	public static void main(String[] args) throws IOException
	{
	        Properties pr=new Properties();
			FileInputStream fis=new FileInputStream("D:\\shravan\\Stock_Accounting_shravan\\PropertiesFile\\Environment.properties");
			pr.load(fis);
			System.out.println(pr.getProperty("browser"));
			System.out.println(pr.getProperty("url"));
			System.out.println(pr.getProperty("username"));
			System.out.println(pr.getProperty("password"));
	}

}
