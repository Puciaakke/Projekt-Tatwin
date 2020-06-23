package wyswietlanie;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.http.impl.client.BasicCookieStore;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
public class wyswietlanie {
	public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public ExtentTest testPositive;
	public ExtentTest testNegative;
	public ExtentTest testSkipped;
	public ExtentTest logger;
	public String driverPath;
	public static WebDriver driver;
	public testWyswietlanie pageObject;
	static String PATH = "D:\\zajęcia zdalne\\TATWin\\ćw\\skiduli\\";
	String correctUsername = "qwerty1234";
	String uncorrectUsername = "qw";
	String correctPassword = "123456qa";
	String uncorrectPassword = "123";
	String correctEmail = "jan@wp.pl";
	String uncorrectEmail = "coś@tam";
		@BeforeTest
		public void startTest() throws InterruptedException
		{
			
			driverPath = "D:\\Prace\\chromedriver_win32\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	        driver.get("https://lowcygier.pl");
	        pageObject = new testWyswietlanie(driver);
	        Thread.sleep(5000);
	        pageObject.cookieAccept();
	        
			htmlReport = new ExtentHtmlReporter("test-output//testLowcywyswietlanie.html");
			htmlReport.config().setDocumentTitle("Raport wyswietlania lowcygier");
			htmlReport.config().setReportName("Automatic reports generation");
			htmlReport.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReport.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(htmlReport);
			extent.setSystemInfo("UserName", "Wojciech Nowak");
		}

		@AfterMethod
		public void getResult(ITestResult result) {
			
			if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(Status.PASS, "Affirmative \""+ result.getName()+ "\"");
			} 
			else if (result.getStatus() == ITestResult.FAILURE) {
				logger.log(Status.FAIL, "Failed \""+result.getName()+"\" Reason:"+ result.getThrowable());
			}
			else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(Status.SKIP, "Skipped test \""+ result.getName());
			}
		}
		
	
		@AfterTest
		public void endTest()
		{
			//testPositive.log(Status.PASS, "Passed");
			//testNegative.log(Status.FAIL, "Failed");
			//testSkipped.log(Status.SKIP, "Skipped");
			extent.flush();
			driver.quit();
		}
		

		@Test
		public void TestFirstPage() throws InterruptedException
		{
			logger = extent.createTest("test zliczania na 1 stronie");
			driver.get("https://lowcygier.pl");

			List<WebElement> lst = driver.findElements(By.xpath("//*/article"));

			//here '' is an empty char 

			int size = lst.size();
			System.out.println(size);
			
			if (size == 20)
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false,"Na stronie znajduje sie mniej niz 20 artykulow");
			}
			
			Thread.sleep(3000);
		}
		
		@Test
		public void TestLastPage() throws InterruptedException
		{
			logger = extent.createTest("test zliczania na 1 stronie");
			driver.get("https://lowcygier.pl");
			
			driver.findElement(By.xpath("//*[@id=\"page\"]/div/div/div[2]/main/nav/ul/li[4]/a")).click();
			List<WebElement> lst = driver.findElements(By.xpath("//*/article"));

			//here '' is an empty char 

			int size = lst.size();
			System.out.println(size);
			
			if (size <= 20)
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false,"Na stronie nie znajduje sie mniej lub rowno 20 artykulow");
			}
			
			Thread.sleep(3000);
		}
		@Test
		public void TestCheckArticle() throws Exception
		{
			Thread.sleep(3000);
			logger = extent.createTest("test wyszukiwanie artykułów");
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	       // driver.manage().window().maximize();
			driver.get("https://lowcygier.pl/");
			//Thread.sleep(5000);
			//pageObject.cookieAccept();
			// post-widget post entry inactive clearfix nieaktywny
			// post-widget post entry clearfix
			WebElement active = driver.findElement(By.xpath("//*/article[@class = \"post-widget post entry clearfix\"]"));
			
			// STRONA NIE ZNAJDUJE ELEMENTU 
			//WebElement inactive = driver.findElement(By.xpath("//*/article[@class = \"post-widget post entry inactive clearfix\"]")); 
			
			WebElement nextPage = driver.findElement(By.xpath("//a[@class='next page-numbers']"));
			if(active.isDisplayed())
		    {
				//while(!inactive.isDisplayed())
				{
					nextPage.click();
					Thread.sleep(1000);
				}
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false,"nie znaleziono oczekiwanego artykułu ");
		    }
			
			
		}
		
}