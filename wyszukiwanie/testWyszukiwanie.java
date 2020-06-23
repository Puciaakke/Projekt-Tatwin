package wyszukiwanie;

import java.awt.Component;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.asm.Advice.Enter;


public class testWyszukiwanie {
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[1]/button/strong")
	WebElement news;
	
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[1]/div/ul/li[3]/a/span[1]")
	WebElement newsPolecane;
	
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[1]/div/ul/li[4]/a/span[1]")
	WebElement newsKomentowane;

	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[1]/div/ul/li[1]/a/span[1]") 
	WebElement newsAll;
	//*[@id="page"]/div/div/div[1]/div/div/div[1]/div/div[2]/button/strong
	//*[@id="page"]/div/div/div[3]/div/div/div[1]/div/div[2]/button/strong
	//*[@id="page"]/div/div/div[3]/div/div/div[1]/div/div[2]/button/strong
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[2]/button")
	WebElement category;
	
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[2]/div/div/div[3]/ul/li[6]/a/span[1]")
	WebElement categoryFree;
	
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[2]/div/div/div[3]/ul/li[5]/a/span[1]")
	WebElement categoryGive;
	//*[@id="page"]/div/div/div[3]/div/div/div[1]/div/div[3]/button/strong
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[3]/button")
	WebElement platform;
	
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[3]/div/div/div[3]/ul/li[8]/a/span[1]")
	WebElement platformPC;
	
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[3]/div/div/div[3]/ul/li[1]/a/span[1]")
	WebElement platformAndroid;
	
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[3]/div/div/div[1]/div/div[3]/div/div/div[4]/ul/li[7]/a/span[1]")
	WebElement platformXboxOne;
	
	@FindBy(xpath = "//*[@id=\"catapultCookie\"]")
	WebElement cookieAccept;
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[1]/div/div/div[2]/form/div/input")
	WebElement name;
	@FindBy (xpath = "//*[@id=\"page\"]/div/div/div[1]/div/div/div[2]/form/div/span/button/svg")
	WebElement buttonSearch;
	@FindBy(xpath = "//*[@id=\"page\"]/div/div/div[4]/main/header")
	WebElement resultText;
	
	public testWyszukiwanie(WebDriver driver){
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	public String getResult(){
		return resultText.getText();
	}
	
	public void gameName(String nameOfGame){
		name.sendKeys(nameOfGame);
		name.sendKeys(Keys.ENTER);
	}
	
	public void cookieAccept() {
		cookieAccept.click();
	}
	//NEWS
	public void news() {
		news.click();
	}
	public void newsPolecane() {
		newsPolecane.click();
	}
	public void newsKomentowane() {
		newsKomentowane.click();
	}
	// KATEGORIA
	public void category() {
		category.click();
	}
	public void categoryFree() {
		categoryFree.click();
	}
	public void categoryGive() {
		categoryGive.click();
	}
	// PLATFORMA
	public void platform() {
		platform.click();
	}
	public void platformPC() {
		platformPC.click();
	}
	public void platformAndoird() {
		platformAndroid.click();
	}
	public void platformXboxOne() {
		platformXboxOne.click();
	}
	public void button() {
		buttonSearch.click();
	}
	
}