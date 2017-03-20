package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy(name="Nome")
	WebElement userName;
	
	@FindBy(name="Password")
	WebElement password;
	
	@FindBy(name="OK")
	WebElement login;
	
	@FindBy(id="submit_lang")
	WebElement ok;
	
	@FindBy(id="horde_user")
	WebElement horde_user;
	
	@FindBy(id="horde_pass")
	WebElement horde_pass;
	
	@FindBy(id="login-button")
	WebElement loginbutton;
	
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	//Set user name in textbox
	public void setUserName(String strUserName){
		userName.sendKeys(strUserName);
		
	}
	//Set password in password textbox
	public void setPassword(String strPassword){
	password.sendKeys(strPassword);
	}
	//Click on login button
	public void clickLogin(){
			login.click();
	}
	
	public void clickOKoniportaldoc(){
		ok.click();
	}
	//Handle Warning message
	public void handleWarningMessage(){
		Boolean isPresent = driver.findElements(By.cssSelector(".titulosb")).size() > 0;
		if (isPresent==true) {
			//driver.findElement(By.cssSelector(".titulosb"));
			driver.findElement(By.name("entrar_ok")).click();
		}	
		
	}
	public void loginGroupware(String strUserName,String strPasword){
		horde_user.clear();
		horde_user.sendKeys(strUserName);
		horde_pass.clear();
		horde_pass.sendKeys(strPasword);
		loginbutton.click();
	}
	
	/*public void loginCafe(String strUserName,String strPasword){
		horde_user.clear();
		horde_user.sendKeys(strUserName);
		horde_pass.clear();
		horde_pass.sendKeys(strPasword);
		loginbutton.click();
	}*/

	public void loginToPage(String strUserName,String strPasword){
		//Fill user name
		this.setUserName(strUserName);
		//Fill password
		this.setPassword(strPasword);
		//Click Login button
		this.clickLogin();
				
	}
}
