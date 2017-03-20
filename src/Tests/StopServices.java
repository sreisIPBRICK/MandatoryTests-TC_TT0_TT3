package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class StopServices {
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
		
		//ACCESS TO URL 
		driver.get(baseUrl);
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));			    
		
		//STOP ALL SERVICES		
		objServicesPage.goToServicesPage();
		objServicesPage.StopService();
		objAdvDefPage.goToDefenitionsPage();
		objApplyConfigs.applyconfigWithDescription("@Test:StopServices");
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
		Thread.sleep(10000);
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
		System.out.println(objServicesPage.getState());
		Assert.assertTrue(objServicesPage.getState()
				.contains("0000000000000000000000000000000"));
		//System.out.println("Assert5");
		objMainPage.clickLogout();
	}/**/
}