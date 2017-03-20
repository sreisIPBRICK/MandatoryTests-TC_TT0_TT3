package Pages;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;

import Data.UploadFile;
import Data.xmlData;

public class DirectoryPage {
	
	WebDriver driver;
	MainPage objMainPage;
	AdvancedConfigsPage objAdvConfig;
	UploadFile objUploadFile;
	xmlData objxml= new xmlData();
	String ConfigXmlFile="FilesXML/AddUsers.xml";
	
	@FindBy(id="menuheader0")
	WebElement DirectoryMenu;		
	@FindBy(linkText="Users Management")
	WebElement UsersManagement;
	@FindBy(linkText="Machines Management")
	WebElement MachineManagement;
	@FindBy(linkText="Users list")
	WebElement UserList;
	@FindBy(linkText="Insert")
	WebElement Insert;
	@FindBy(linkText="Mass operations")
	WebElement Moperation;
	@FindBy(name="nome")
	WebElement nome;
	@FindBy(name="username")
	WebElement username;
	@FindBy(name="login")
	WebElement login; 
	@FindBy(name="server")
	WebElement server;
	@FindBy(name="area")
	WebElement Workarea;
	@FindBy(name="password")
	WebElement password;
	@FindBy(name="password2")
	WebElement password2;
	@FindBy(name="quota")
	WebElement quota;
	@FindBy(name="f_accao")
	WebElement InsertBtn;
	@FindBy(linkText="Teste")
	WebElement Test;
	@FindBy(linkText="TESTEM")
	WebElement ModifyUserTest;
	@FindBy(linkText="Modify")
	WebElement Modify;
	@FindBy(linkText="TESTED")
	WebElement DeleteUserTest;
	@FindBy(linkText="Delete")
	WebElement Delete;
	
	public DirectoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToMachineManagement(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");
		DirectoryMenu.click();
		MachineManagement.click();	
		objMainPage.switchToFrame("maquinas_ver_lista");
		Moperation.click();
	}
	
	public void goToUserListPage(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");
		DirectoryMenu.click();
		UsersManagement.click();
		UserList.click();

	}
	
	public void ClickMassOperation(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("utilizador_ver_lista");
		Moperation.click();
	}
	
	public void SelectDropDownValue(String value){
		Select option= new Select(Workarea);
		option.selectByVisibleText(value);

	}
	
	public boolean VerifyUser(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");
		DirectoryMenu.click();
		UsersManagement.click();
		UserList.click();
		objMainPage.switchToFrame("utilizador_ver_lista");
		return Test.isDisplayed();
		
	}
	
	public void AddIndividualUser() throws ParserConfigurationException, SAXException, IOException{
		
		String ConfigXmlFile="FilesXML/AddUsers.xml";
		int num = Integer.parseInt(objxml.getxml(ConfigXmlFile,"nUsers"));
		for(int i=0;i<num;i++){
		this.AddNewUser(objxml.getxml(ConfigXmlFile,"name"+i),
								objxml.getxml(ConfigXmlFile,"log"+i),
								objxml.getxml(ConfigXmlFile,"workarea"+i),
								objxml.getxml(ConfigXmlFile,"pw"+i),
								objxml.getxml(ConfigXmlFile,"pww"+i));
		
		}
	}
	
	public void AddNewUser(String name,String log,String workarea,String pw,String pw2){
			
			objMainPage= new MainPage(driver);
			objMainPage.switchToFrame("Default");
			DirectoryMenu.click();
			UsersManagement.click();
			UserList.click();
			objMainPage.switchToFrame("utilizador_ver_lista");
			Insert.click();
			
			nome.clear();
			nome.sendKeys(name);
			login.clear();
			login.sendKeys(log);
			this.SelectDropDownValue(workarea);
			password.clear();
			password.sendKeys(pw);
			password2.clear();
			password2.sendKeys(pw2);
			InsertBtn.click();
			driver.switchTo().alert().accept();
		
	}
	
	public void ModifyUser(String name,String workarea,String pw,String pw2){
		
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");		
		DirectoryMenu.click();
		UsersManagement.click();
		UserList.click();
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("utilizador_ver_lista");		
		ModifyUserTest.click();
		Modify.click();
		username.clear();
		username.sendKeys(name);
		this.SelectDropDownValue(workarea);
		password.clear();
		password.sendKeys(pw);
		password2.clear();
		password2.sendKeys(pw2);		
		InsertBtn.click();
		driver.switchTo().alert().accept();		
	}
	public void DeleteUser(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");		
		DirectoryMenu.click();
		UsersManagement.click();
		UserList.click();
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("utilizador_ver_lista");		
		DeleteUserTest.click();
		Delete.click();
		InsertBtn.click();
		driver.switchTo().alert().accept();
	}
}
