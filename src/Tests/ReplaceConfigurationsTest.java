package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import Data.UploadFile;
import Data.xmlData;
import Pages.AdvancedConfigsPage;
import Pages.ApplyConfigurations;
import Pages.DirectoryPage;
import Pages.LoginPage;
import Pages.MainPage;

public class ReplaceConfigurationsTest {
	WebDriver driver;
	LoginPage objLogin;
	MainPage objMainPage;
	AdvancedConfigsPage objAdvDefPage;
	ApplyConfigurations objApplyConfigs;
	DirectoryPage objDirectory;
	UploadFile objUploadFile;
	
	xmlData objxml= new xmlData();
	String baseUrl;
	String ConfigXmlFile="FilesXML/login.xml";
	
	@BeforeTest(groups = {"ReplaceConfigs"})
	public void setup() throws ParserConfigurationException, SAXException, IOException{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String timeString=objxml.getxml(ConfigXmlFile,"implicitlyWait");
		long time=Integer.parseInt(timeString);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		baseUrl=objxml.getxml(ConfigXmlFile,"url");
	}
	@Test(groups = {"ReplaceConfigs"},priority=2)
	public void Adduser() throws ParserConfigurationException, SAXException, IOException{
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objUploadFile= new UploadFile();
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objDirectory.AddNewUser("Teste", "test", "Work Area 1", "123456", "123456");
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();			
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
	}
	
	@Test(groups = {"ReplaceConfigs"},priority=2)
	public void VerifyUserExist() throws ParserConfigurationException, SAXException, IOException{
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objUploadFile= new UploadFile();
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objDirectory.VerifyUser();
		objMainPage.clickLogout();
		
	}
	
	@Test(groups = {"ReplaceConfigs"},priority=2)	
	public void ReplaceConfigurations() throws InterruptedException, ParserConfigurationException, SAXException, IOException{
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		
		driver.get(baseUrl);
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));			   	
		
		objAdvDefPage.goToReplacePage();
		objAdvDefPage.ReplaceOtherDefaultConfigs();
		Thread.sleep(360000);
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
	}
	
	@Test(groups = {"ReplaceConfigs"},priority=2)	
	public void VerifyReplaceConfigurations() throws InterruptedException, ParserConfigurationException, SAXException, IOException{
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objUploadFile= new UploadFile();
		
		driver.get(baseUrl);				
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objDirectory.VerifyUser();
		Assert.assertFalse(objDirectory.VerifyUser());
		objMainPage.clickLogout();

	}
	@AfterMethod(alwaysRun=true)
	public void shutDownSelenium() {
		objMainPage = new MainPage(driver);
		//objMainPage.clickLogout();
		driver.manage().deleteAllCookies();
	    //driver.quit();
	}
}