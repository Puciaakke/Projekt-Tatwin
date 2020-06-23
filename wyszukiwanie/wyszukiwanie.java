package wyszukiwanie;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
public class wyszukiwanie {
	public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public ExtentTest testPositive;
	public ExtentTest testNegative;
	public ExtentTest testSkipped;
	public ExtentTest logger;
	public String driverPath;
	public static WebDriver driver;
	public testWyszukiwanie pageObject;
	static String PATH = "D:\\zajęcia zdalne\\TATWin\\ćw\\skiduli\\";
	String nameGame = "Fifa";
	String nameGame1 = "Grand Theft Auto";
	String nameGame2 = "Rocket League";
		@BeforeTest
		public void startTest() throws InterruptedException
		{
			
			driverPath = "D:\\Prace\\chromedriver_win32\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	        driver.get("https://lowcygier.pl/");
	        pageObject = new testWyszukiwanie(driver);
	        Thread.sleep(5000);
	        pageObject.cookieAccept();
	        
			htmlReport = new ExtentHtmlReporter("test-output//testLowcywyszukanie.html");
			htmlReport.config().setDocumentTitle("Raport wyszukiwania lowcygier");
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
		public void TestACategoryPlatform() throws InterruptedException
		{
			logger = extent.createTest("test pozytywny - kategoria, platforma, fraza");
			driver.get("https://lowcygier.pl/");
			pageObject.gameName(nameGame);
			Thread.sleep(1000);
			pageObject.category();
			Thread.sleep(1000);
			pageObject.categoryFree();
			Thread.sleep(1000);
			pageObject.platform();
			Thread.sleep(1000);
			pageObject.platformXboxOne();
			Thread.sleep(5000);
			
			String znaleziono = pageObject.getResult().toString();
			System.out.println(znaleziono);
						
			if(znaleziono.contains("Znaleziono"))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false,"nie znaleziono oczekiwanego wyniku ");
		    }
			/*if(pageObject.getResult().contains("Znaleziono"))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false);
		    }*/
			Thread.sleep(3000);
		}
		
		@Test
		public void TestBALL() throws InterruptedException
		{
			logger = extent.createTest("test pozytywny - wszystko");
			driver.get("https://lowcygier.pl/");
			pageObject.gameName(nameGame1);
			Thread.sleep(1000);
			pageObject.category();
			Thread.sleep(1000);
			pageObject.categoryFree();
			Thread.sleep(1000);
			pageObject.platform();
			Thread.sleep(1000);
			pageObject.platformPC();
			Thread.sleep(1000);
			pageObject.news();
			Thread.sleep(1000);
			pageObject.newsPolecane();
			Thread.sleep(5000);
			
			String znaleziono1 = pageObject.getResult().toString();
			System.out.println(znaleziono1);
			
			if(znaleziono1.contains("Znaleziono"))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false,"nie znaleziono oczekiwanego wyniku ");
		    }
			/*
			if(pageObject.getResult().contains("Znaleziono"))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false);
		    }*/
			Thread.sleep(3000);
		}
		
		@Test
		public void TestCOnlyName() throws InterruptedException
		{
			logger = extent.createTest("Test pozytywny - sama nazwa ");
			driver.get("https://lowcygier.pl/");
			pageObject.gameName(nameGame2);
			Thread.sleep(5000);
			
			String znaleziono2 = pageObject.getResult().toString();
			System.out.println(znaleziono2);
			
			if(znaleziono2.contains("Znaleziono"))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false,"nie znaleziono oczekiwanego wyniku ");
		    }
		}
		/*
		
		@Test
		public void TestWrongPasswordAgain() throws Exception
		{
			Thread.sleep(3000);
			logger = extent.createTest("test negatywny - password again");
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	       // driver.manage().window().maximize();
			driver.get("https://lowcygier.pl/forum/ucp.php?mode=register");
			//Thread.sleep(5000);
			//pageObject.cookieAccept();
			Thread.sleep(500);
			pageObject.accept();
			Thread.sleep(1000);
			pageObject.enterUsername(correctUsername);
			Thread.sleep(1000);
			pageObject.enterEmail(correctEmail);
			Thread.sleep(1000);
			pageObject.enterPassword(correctPassword);
			Thread.sleep(1000);
			pageObject.enterConfirmPassword(uncorrectPassword);
			Thread.sleep(1000);
			pageObject.chooseGender();
			Thread.sleep(1000);
			Thread.sleep(1000);
						
			pageObject.sendUser();
			Thread.sleep(5000);
			String passAgain = pageObject.getResult().toString();
			System.out.println(passAgain);
						
			if(passAgain.contains("Potwierdzone hasło jest inne."))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false,"nie znaleziono oczekiwanego wyniku ");
		    }
		}
		@Test
		public void TestWrongemail() throws Exception
		{
			Thread.sleep(3000);
			logger = extent.createTest("test negatywny - email");
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	       // driver.manage().window().maximize();
			driver.get("https://lowcygier.pl/forum/ucp.php?mode=register");
			//Thread.sleep(5000);
			//pageObject.cookieAccept();
			Thread.sleep(500);
			pageObject.accept();
			Thread.sleep(1000);
			pageObject.enterUsername(correctUsername);
			Thread.sleep(1000);
			pageObject.enterEmail(uncorrectEmail);
			Thread.sleep(1000);
			pageObject.enterPassword(correctPassword);
			Thread.sleep(1000);
			pageObject.enterConfirmPassword(correctPassword);
			Thread.sleep(1000);
			pageObject.chooseGender();
			Thread.sleep(1000);
			Thread.sleep(1000);
						
			pageObject.sendUser();
			Thread.sleep(5000);
			String email = pageObject.getResult().toString();
			System.out.println(email);
						
			if(email.contains("Adres e-mail jest za krótki."))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false,"nie znaleziono oczekiwanego wyniku ");
		    }
		}
		@Test
		public void TestWrongGender() throws Exception
		{
			Thread.sleep(3000);
			logger = extent.createTest("test negatywny - gender");
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	       // driver.manage().window().maximize();
			driver.get("https://lowcygier.pl/forum/ucp.php?mode=register");
			//Thread.sleep(5000);
			//pageObject.cookieAccept();
			Thread.sleep(500);
			pageObject.accept();
			Thread.sleep(1000);
			pageObject.enterUsername(correctUsername);
			Thread.sleep(1000);
			pageObject.enterEmail(correctEmail);
			Thread.sleep(1000);
			pageObject.enterPassword(correctPassword);
			Thread.sleep(1000);
			pageObject.enterConfirmPassword(correctPassword);
			Thread.sleep(1000);
			//pageObject.chooseGender();
			Thread.sleep(1000);
			Thread.sleep(1000);
						
			pageObject.sendUser();
			Thread.sleep(5000);
			String gender = pageObject.getResult().toString();
			System.out.println(gender);
						
			if(gender.contains("Pole „Płeć” musi być wypełnione."))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false,"nie znaleziono oczekiwanego wyniku ");
		    }
		}*/
}