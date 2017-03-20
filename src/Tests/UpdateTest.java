package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import Data.UploadFile;
import Data.xmlData;
import Pages.AdvancedConfigsPage;
import Pages.ApplyConfigurations;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.ServicesPage;

public class UpdateTest {
	WebDriver driver;
	LoginPage objLogin;
	MainPage objMainPage;
	UploadFile objUploadFile;
	ServicesPage objServicesPage;
	AdvancedConfigsPage objAdvDefPage;
	ApplyConfigurations objApplyConfigs;

	xmlData objxml= new xmlData();
	String baseUrl,baseUrl2;
	String ConfigXmlFile="FilesXML/Update.xml";
	
	@BeforeTest (alwaysRun=true)
	public void setup() throws ParserConfigurationException, SAXException, IOException{
		driver = new FirefoxDriver();
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		String timeString=objxml.getxml(ConfigXmlFile,"implicitlyWait");
		long time=Integer.parseInt(timeString);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		baseUrl=objxml.getxml(ConfigXmlFile,"url");
	}
	@Test(groups = {"StopServices"},priority=1)
	public void StopServicesTest() throws Exception{
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objUploadFile= new UploadFile();
		objApplyConfigs= new ApplyConfigurations(driver);
		objServicesPage=new ServicesPage(driver);
		
		//ACCESS TO URL AND HANDLE WARNING MESSAGE
		driver.get(baseUrl);
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));			    
		
		//STOP ALL SERVICES		
		objServicesPage.goToServicesPage();
		objServicesPage.StopService();
		objAdvDefPage.goToDefenitionsPage();
		objApplyConfigs.applyconfigWithDescription("@Test:StopServices");
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
		Thread.sleep(120000);
	}
	@Test(groups = {"VerifyServices"},priority=1)
	public void VerifyStopServicesTest() throws Exception{
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objUploadFile= new UploadFile();
		objApplyConfigs= new ApplyConfigurations(driver);
		objServicesPage=new ServicesPage(driver);
		
		//ACCESS TO URL AND HANDLE WARNING MESSAGE
		driver.get(baseUrl);
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));			    
		
		//STOP ALL SERVICES		
		objServicesPage.goToServicesPage();
		Assert.assertTrue(objServicesPage.getState()
				.contains("000000000000000000000000000000000"));
		//System.out.println("Assert5");
		objMainPage.clickLogout();
	}
	/*
		//UPLOAD FILE TO DO THE UPDATE
		objAdvDefPage.goToUpdatesPage();
		objAdvDefPage.clickInsert();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);
		objUploadFile.FileUpload(driver,objxml.getxml(ConfigXmlFile,"fileToUpload"));
		objApplyConfigs.goToApplyConfigsPage();
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.name("f_actualiza")));
		
		objApplyConfigs.applyConfigs();

		
		//driver.manage().timeouts().implicitlyWait(6, TimeUnit.MINUTES);		
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		System.out.println("Assert1");
		objMainPage.clickLogout();
	
		//ACCESS TO URL
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));			    
		
		//ASSERT TO VERIFY IF THE UPDATE IS INTALLED
		objAdvDefPage.goToUpdatesPage();
		Assert.assertTrue(objAdvDefPage.getDadosOfupdate().
				contains("update01-v6.2 - New improvements and functionalities."));
		System.out.println("Assert2");
		
		//UPLOAD FILE TO DO OTHER UPDATE
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);
		objAdvDefPage.goToUpdatesPage();
		objAdvDefPage.clickInsert();		
		objUploadFile.FileUpload(driver,objxml.getxml(ConfigXmlFile,"fileToUpload2"));
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);		
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		System.out.println("Assert3");
		objMainPage.clickLogout();
		
		//ACCESS TO URL
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));			    
		
		//ASSERT TO VERIFY IF THE UPDATE WAS INTALLED
		objAdvDefPage.goToUpdatesPage();		
		Assert.assertTrue(objAdvDefPage.getDadosOfupdate().
				contains("fix-update02-v6.2 - Fix for VoIP services."));
		System.out.println("Assert4");
		
		//CHECK IF ANY SERVICE WAS STARTED
		objServicesPage.goToServicesPage();
		Assert.assertTrue(objServicesPage.getState()
				.contains("000000000000000000000000000000000"));
		System.out.println("Assert5");
		
	}
	*/
	/*@AfterMethod(groups = {"VerifyServices"})
	public void shutDownSelenium() {
		objMainPage = new MainPage(driver);
		objMainPage.clickLogout();
		driver.manage().deleteAllCookies();
	    //driver.quit();
	}*/
}
