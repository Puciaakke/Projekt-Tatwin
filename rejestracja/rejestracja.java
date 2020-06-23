package rejestracja;

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
public class rejestracja {
	public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public ExtentTest testPositive;
	public ExtentTest testNegative;
	public ExtentTest testSkipped;
	public ExtentTest logger;
	public String driverPath;
	public static WebDriver driver;
	public testRejestracja pageObject;
	static String PATH = "D:\\zajęcia zdalne\\TATWin\\ćw\\skiduli\\";
	String correctUsername = "qwerty1234";
	String uncorrectUsername = "qw";
	String correctPassword = "123456qa";
	String uncorrectPassword = "123";
	String correctEmail = "jan@wp.pl";
	String uncorrectEmail = "cos@tam";
		@BeforeTest
		public void startTest() throws InterruptedException
		{
			
			driverPath = "D:\\Prace\\chromedriver_win32\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //driver.manage().window().maximize();
	        driver.get("https://lowcygier.pl/forum/ucp.php?mode=register");
	        pageObject = new testRejestracja(driver);
	        Thread.sleep(5000);
	        pageObject.cookieAccept();
	        
			htmlReport = new ExtentHtmlReporter("test-output//testLowcyrejestracja.html");
			htmlReport.config().setDocumentTitle("Raport rejestracji lowcygier");
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
			//driver.quit();
		}
		

		@Test
		public void TestNoAccept() throws InterruptedException
		{
			logger = extent.createTest("test negatywny - brak akceptacji regulaminu");
			driver.get("https://lowcygier.pl/forum/ucp.php?mode=register");
			pageObject.notAccept();
			Assert.assertEquals(pageObject.promocja(), "Promocje");
			/*if(pageObject.promocja().contains("Nie"))
		    {
		    	Assert.assertFalse(true);
		    }
		    else {
		    	Assert.assertFalse(false);
		    }*/
			Thread.sleep(3000);
		}
		
		@Test
		public void TestWrongUsername() throws Exception
		{
			Thread.sleep(3000);
			logger = extent.createTest("test negatywny - username");
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	       // driver.manage().window().maximize();
			driver.get("https://lowcygier.pl/forum/ucp.php?mode=register");
			//Thread.sleep(5000);
			//pageObject.cookieAccept();
			Thread.sleep(500);
			pageObject.accept();
			Thread.sleep(1000);
			pageObject.enterUsername(uncorrectUsername);
			Thread.sleep(1000);
			pageObject.enterEmail(correctEmail);
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
			String login = pageObject.getResult().toString();
			System.out.println(login);
						
			if(login.contains("Nazwa użytkownika jest za krótka."))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false,"nie znaleziono oczekiwanego wyniku ");
		    }
		}
		@Test
		public void TestWrongPassword() throws Exception
		{
			Thread.sleep(3000);
			logger = extent.createTest("test negatywny - password");
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
			pageObject.enterPassword(uncorrectPassword);
			Thread.sleep(1000);
			pageObject.enterConfirmPassword(uncorrectPassword);
			Thread.sleep(1000);
			pageObject.chooseGender();
			Thread.sleep(1000);
			Thread.sleep(1000);
						
			pageObject.sendUser();
			Thread.sleep(5000);
			String pass = pageObject.getResult().toString();
			System.out.println(pass);
						
			if(pass.contains("Hasło jest za krótkie."))
		    {
		    	Assert.assertTrue(true);
		    }
		    else {
		    	Assert.assertTrue(false,"nie znaleziono oczekiwanego wyniku ");
		    }
		}
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
						
			if(email.contains("Wprowadzony adres e-mail jest nieprawidłowy."))
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
		}
}

// RECAPTCHA 
/*WebElement element = driver
.findElement(By.xpath("//*[@id=\"register\"]/div[2]/div/fieldset/dl/dd/div/div/div/iframe"));
((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);    // te dwie linie scroluja przegladarkę do miejsca gdzie jest captcha
Thread.sleep(500);

driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"register\"]/div[2]/div/fieldset/dl/dd/div/div/div/iframe"))); // ta linia przełącza nam focus na captche ponieważ captcha jest IFramem
Thread.sleep(500);
driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]")).click(); // klikamy w checkboxa
Thread.sleep(1000);
driver.switchTo().defaultContent();
Thread.sleep(1000);

	
	Component frame = null;
	JOptionPane.showMessageDialog(frame,
		    "Jeśli występuje powierdzenie obrazkami, wykonaj go ręcznie",
		    correctEmail, JOptionPane.PLAIN_MESSAGE);
	Thread.sleep(2000);
	driver.switchTo().defaultContent();
	Thread.sleep(2000);*/