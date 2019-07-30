package CommonFunLibary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import Utilites.PropertyFileUtil;
import junit.framework.Assert;

public class FunctionalLibary
{
	WebDriver driver;
	Actions action=new Actions(driver);
	//start browser
	public static WebDriver startBrowser(WebDriver driver) throws Throwable, Throwable
	{
		if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Firefox")) 
			{
			driver=new FirefoxDriver();
			}
			else
				if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome")) 
				{
					System.setProperty("webdriver.chrome.driver", "CommonJarFiles/chromedriver.exe");
					driver=new ChromeDriver();
				}
		return driver;	
	}
	//open application
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("URL"));
		driver.manage().window().maximize();
	}
	
	//click action
	public static void clickActions(WebDriver driver,String locatorType,String locatorValue)
	{
		if (locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).click();
		}
		else
			if (locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).click();
			}
			else
				if (locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).click();
				}
	}
	
	//send keys action
	public static void sendKeysAction(WebDriver driver,String locatorType,String locatorValue,String data)
	{
		if (locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(data);
		}
		else
			if (locatorType.equalsIgnoreCase("name"))
				{
					driver.findElement(By.id(locatorValue)).clear();
					driver.findElement(By.id(locatorValue)).sendKeys(data);
				}
			else
				if (locatorType.equalsIgnoreCase("xpath"))
					{
						driver.findElement(By.id(locatorValue)).clear();
						driver.findElement(By.id(locatorValue)).sendKeys(data);
					}
	}	
	
	//mouse actions
	public static void mouseActions(WebDriver driver)
	{
		Actions action=new Actions(driver);
		 action.sendKeys(Keys.PAGE_DOWN).perform();
	}
	public static void mouseHover(WebDriver driver)
	{
		Actions action=new Actions(driver);
		WebElement element=driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a"));
		action.moveToElement(element).perform();
		
	}
	
	//capturedata
	public static void captureData(WebDriver driver,String locatorType,String locatorValue) throws Throwable
	{
		String suppnum="";
		if (locatorType.equalsIgnoreCase("id"))
		{
			suppnum=driver.findElement(By.id(locatorValue)).getAttribute("value");
		}
		else
			if (locatorType.equalsIgnoreCase("name"))
			{
				suppnum=driver.findElement(By.name(locatorValue)).getAttribute("value");
			}
			else
				if (locatorType.equalsIgnoreCase("xpath")) 
				{
					suppnum=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
				}
		FileWriter fw=new FileWriter("D:\\shravan\\Stock_Accounting_Project\\CaptureData\\Data.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(suppnum);
		bw.flush();
		bw.close();
			
	}
	//Table validation
	public static void tableValidation(WebDriver driver,String colNum) throws Throwable
	{
		FileReader fr=new FileReader("D:\\shravan\\Stock_Accounting_Project\\CaptureData\\Data.txt");
		BufferedReader br=new BufferedReader(fr);
		String exp_data=br.readLine();
		int colNum1=Integer.parseInt(colNum);
		if (driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).isDisplayed())
		{
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.button"))).click();
		}
		else
		{
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.panel"))).click();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.button"))).click();
		}
		WebElement webtable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path")));
		List<WebElement> rows=webtable.findElements(By.tagName("tr"));
		for (int i = 1; i <=rows.size(); i++) 
		{
			String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colNum1+"]/div/span/span")).getText();
			Assert.assertEquals(exp_data, act_data);
			System.out.println("actuval" +" "+ act_data);
			System.out.println("expected" +" "+ exp_data);
			break;
		}
	}
	
	
	//Table Validation stock category
	public static void tableValidation1(WebDriver driver,String exp_data) throws Throwable
	{
		if (driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).isDisplayed())
		{
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.button"))).click();
		}
		else
		{
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.panel1"))).click();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.button"))).click();
		}
		WebElement webtable=driver.findElement(By.xpath("//*[@id='tbl_a_stock_categorieslist']"));
		List<WebElement> rows=webtable.findElements(By.tagName("tr"));
		for (int i = 1; i <rows.size(); i++)
		{
			String act_data=driver.findElement(By.xpath("//*[@id='tbl_a_stock_categorieslist']/tbody/tr["+i+"]/td[4]/div/span/span")).getText();
			Assert.assertEquals(exp_data, act_data);
			System.out.println("actuval" +" "+ act_data);
			System.out.println("expected" +" "+ exp_data);
			
		}
		
	}
	
	//generate date
	public static String generateDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy_mm_dd");
		return sdf.format(date);
	}
	
	
	//close browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
		
	//wait
	public static void waitForElement(WebDriver driver,String locatorType,String locatorValue,String waitTime)
	{
		WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(waitTime));
		if (locatorType.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}
		else
			if(locatorType.equalsIgnoreCase("name"))
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				}
			else
				if (locatorType.equalsIgnoreCase("xpath"))
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				}
	}
		
	
	
	
}
