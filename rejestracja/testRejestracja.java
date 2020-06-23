package rejestracja;

import java.awt.Component;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.asm.Advice.Enter;


public class testRejestracja {
	WebDriver driver;
	
	@FindBy(xpath = "//input[@id='agreed']")
	WebElement accept;
	
	@FindBy(xpath = "//input[@name='not_agreed']")
	WebElement notAccept;
	//*[@id="forumlist_collapse"]/div[1]/div[1]/div/ul[1]/li/dl/dt/div/a
	@FindBy(xpath = "//dl[@class='row-item']//a[contains(text(),'Promocje')]")
	WebElement promocje;

	@FindBy(xpath = "//fieldset[@class='fields2']//input[@id='username']")  //input[@id='username']
	WebElement usernameInput;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement emailInput;
	
	@FindBy(xpath = "//input[@id='new_password']")
	WebElement passwordInput;
	
	@FindBy(xpath = "//input[@id='password_confirm']")
	WebElement confirmPasswordInput;
	
	@FindBy(xpath = "//select[@id='pf_gender']")
	WebElement gender;
	
	@FindBy(xpath = "//option[contains(text(),'czyzna')]")
	WebElement male;
	
	@FindBy(xpath = "//*[@id=\"register\"]/div[1]/div/fieldset/dl[1]/dd")
	WebElement resultText;
	
	@FindBy(xpath = "//body[@id='phpbb']")
	WebElement robotAccept;
	
	@FindBy(xpath = "//div[@class='cc-compliance']")
	WebElement cookieAccept;
	// nie korzysta z image 
	@FindBy(xpath = "//*[@id=\\\"register\\\"]/div[1]/div/fieldset/dl[1]/dd")
	WebElement image;
	//*[@id="submit"]
	@FindBy(xpath = "//*[@id=\"submit\"]")
	WebElement sendUser;
	
	public testRejestracja(WebDriver driver){
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username){
		usernameInput.sendKeys(username);
	}
	public void enterEmail(String email) {
		emailInput.sendKeys(email);
	}
	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}
	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordInput.sendKeys(confirmPassword);
	}
	
	public void chooseGender() {
		gender.click();
		gender.sendKeys("m");
		gender.sendKeys(Keys.ENTER);
	}
	
	/*public void EnterMale() {
		male.sendKeys("m");
		male.sendKeys(Keys.ENTER);
	}*/
	
	public void acceptRobot() {
		robotAccept.click();
	}
	
	public void accept(){
		accept.click();
	}
	
	public void notAccept() {
		notAccept.click();
	}
	
	public String promocja() {
		return promocje.getText();
	}
	
	public String getResult(){
		return resultText.getText();
	}
	public void cookieAccept() {
		cookieAccept.click();
	}
	
	public void sendUser() {
		sendUser.click();
	}

	public String image() {
		// TODO Auto-generated method stub
		return null;
	}
}