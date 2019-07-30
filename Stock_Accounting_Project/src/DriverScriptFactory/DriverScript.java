package DriverScriptFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibary.FunctionalLibary;
import Utilites.ExcelFileUtils;

public class DriverScript
{
	WebDriver driver;
	ExtentReports reports;
	ExtentTest test;
	public void startTest() throws Throwable
	{
		ExcelFileUtils excel=new ExcelFileUtils();
		for (int i = 1; i <=excel.rowCount("MasterTestCases"); i++) 
		{
			String moduleStatus="";
			if (excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("y")) 
			{
				String TCModule=excel.getData("MasterTestCases", i, 1);
				int rowcount=excel.rowCount(TCModule);
				reports=new ExtentReports("./Reports/"+TCModule+FunctionalLibary.generateDate()+".html");
				test=reports.startTest(TCModule);
				for (int j = 1; j <=rowcount; j++) 
					{
						String Description=excel.getData(TCModule, j, 0);
						String Object_Type=excel.getData(TCModule, j, 1);
						System.out.println(Object_Type);
						String Locator_Type=excel.getData(TCModule, j, 2);
						String locator_Value=excel.getData(TCModule, j, 3);
						String Test_Data=excel.getData(TCModule, j, 4);
					try
					{
					if (Object_Type.equalsIgnoreCase("startBrowser"))
					{
						driver=FunctionalLibary.startBrowser(driver); 
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("openApplication"))
					{
						FunctionalLibary.openApplication(driver);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("sendKeysAction")) 
					{
						FunctionalLibary.sendKeysAction(driver, Locator_Type, locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("clickActions")) 
					{
						FunctionalLibary.clickActions(driver, Locator_Type, locator_Value);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("waitForElement"))
					{
						FunctionalLibary.waitForElement(driver, Locator_Type, locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);
					}	
					if (Object_Type.equalsIgnoreCase("mouseActions"))
					{
						FunctionalLibary.mouseActions(driver);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("mouseHover"))
					{
						FunctionalLibary.mouseHover(driver);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("captureData")) 
					{
						FunctionalLibary.captureData(driver, Locator_Type, locator_Value);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("closeBrowser")) 
					{
						FunctionalLibary.closeBrowser(driver);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("tableValidation")) 
					{
						FunctionalLibary.tableValidation(driver, Test_Data);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("tableValidation1"))
					{
						FunctionalLibary.tableValidation1(driver,Test_Data);
						test.log(LogStatus.INFO, Description);
					}
					excel.setData(TCModule, j, 5, "PASS");
					moduleStatus="true";
					test.log(LogStatus.PASS, Description);
					}
					catch(Exception e)
					{
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(srcFile, new File("D:\\shravan\\Stock_Accounting_Project\\ScreenShots\\"+" "+Description+FunctionalLibary.generateDate()+".png"));
						excel.setData(TCModule, j, 5, "FAIL");
						moduleStatus="false";
						test.log(LogStatus.FAIL, Description);
						break;
					}
					
				}
				if(moduleStatus.equalsIgnoreCase("true"))
				{
					excel.setData("MasterTestCases", i, 3, "PASS");
				}
				else
					if(moduleStatus.equalsIgnoreCase("false"))
				{
					excel.setData("MasterTestCases", i, 3, "FAIL");
				}
							}
			else
			{
				excel.setData("MasterTestCases", i, 3, "Not Executed");
			}
			reports.endTest(test);
			reports.flush();

		}
	
		
			
		
		
		
		
		
	}
}
