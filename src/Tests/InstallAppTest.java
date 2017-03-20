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
import Pages.LoginPage;
import Pages.MainPage;
import Pages.ServicesPage;

public class InstallAppTest {
	WebDriver driver;
	LoginPage objLogin;
	MainPage objMainPage;
	UploadFile objUploadFile;
	ServicesPage objServicesPage;
	AdvancedConfigsPage objAdvDefPage;
	ApplyConfigurations objApplyConfigs;
	
	xmlData objxml= new xmlData();
	String baseUrl,baseUrl2;
	String ConfigXmlFile="FilesXML/InstallApp.xml";
	
	@BeforeTest(groups={"InstallApp"})
	public void setup() throws ParserConfigurationException, SAXException, IOException{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String timeString=objxml.getxml(ConfigXmlFile,"implicitlyWait");
		long time=Integer.parseInt(timeString);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		baseUrl=objxml.getxml(ConfigXmlFile,"url");
		baseUrl2=objxml.getxml(ConfigXmlFile,"url2");
	}
	@Test(groups={"InstallApp"},priority=0)
	public void AppInstallTest() throws Exception{		
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objUploadFile= new UploadFile();
		objApplyConfigs= new ApplyConfigurations(driver);
		objServicesPage=new ServicesPage(driver);
		

		driver.get(baseUrl);
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		
 
		objAdvDefPage.goToUpdatesPage();
		objAdvDefPage.clickInsert();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);
		
		objUploadFile.FileUpload(driver,objxml.getxml(ConfigXmlFile,"fileToUpload"));
		Thread.sleep(30000);
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);	
		
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));	
		System.out.println("Assert Successfully updated!");
		objMainPage.clickLogout();
	}
	
	@Test(groups={"InstallApp"},priority=1)
	public void VerifyInstalationTest() throws Exception{
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objUploadFile= new UploadFile();
		objApplyConfigs= new ApplyConfigurations(driver);
		objServicesPage=new ServicesPage(driver);
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));			    
		
		//ASSERT TO VERIFY IF THE UPDATE IS INTALLED
		objAdvDefPage.goToUpdatesPage();
		Assert.assertTrue(objAdvDefPage.getDadosOfupdate().contains("iPortalDoc v6.0"));
		System.out.println("Assert2");
		
		objMainPage.clickLogout();
		driver.get(baseUrl2);
	//	objLogin.clickOKoniportaldoc();
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username2"),objxml.getxml(ConfigXmlFile,"password2"));
		
	}
	
	@AfterMethod(groups={"InstallApp"})
	public void shutDownSelenium() {
		//objMainPage = new MainPage(driver);
		//objMainPage.clickLogout();
		driver.manage().deleteAllCookies();
	    //driver.quit();
	}
}
