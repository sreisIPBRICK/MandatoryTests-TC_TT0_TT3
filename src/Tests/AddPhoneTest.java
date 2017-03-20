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
import Pages.DirectoryPage;
import Pages.GroupwarePage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.UCoIPpage;

public class AddPhoneTest {
	WebDriver driver;
	LoginPage objLogin;
	DirectoryPage objDirectory;
	MainPage objMainPage;
	AdvancedConfigsPage objAdvDefPage;
	ApplyConfigurations objApplyConfigs;
	UploadFile objUploadFile;
	GroupwarePage objGroupware;
	UCoIPpage objUCoIP;
	
	xmlData objxml= new xmlData();
	String baseUrl,baseUrl2;
	String ConfigXmlFile="FilesXML/AddPhone.xml";
	
	@BeforeTest (groups = {"AddPhone"})
	public void setup() throws ParserConfigurationException, SAXException, IOException{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String timeString=objxml.getxml(ConfigXmlFile,"implicitlyWait");
		long time=Integer.parseInt(timeString);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		baseUrl=objxml.getxml(ConfigXmlFile,"url");	
		baseUrl2=objxml.getxml(ConfigXmlFile,"url2");
	}
	
	@Test(groups={"AddPhone"},priority=1)
	public void AddPhonesTest() throws Exception{
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objUploadFile= new UploadFile();
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objDirectory.goToMachineManagement();
		objUploadFile.CVSUploadd(driver,objxml.getxml(ConfigXmlFile,"machineslist"));

		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();			
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
		
		/*
		driver.get(baseUrl2);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objDirectory.goToMachineManagement();
		objUploadFile.CVSUploadd(driver,objxml.getxml(ConfigXmlFile,"machineslist"));

		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();			
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		*/
	}
/*	
	@Test(groups={"AddPhone"},priority=2)
	public void ConfigOutBondRouteTest() throws Exception{
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objUploadFile= new UploadFile();
		objUCoIP= new UCoIPpage(driver);
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		
		objUCoIP.goToRouteManagement();
		objUCoIP.InsertOutboundRoute(objxml.getxml(ConfigXmlFile,"name"),objxml.getxml(ConfigXmlFile,"sipserver"),objxml.getxml(ConfigXmlFile,"Prefix"));
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();			
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
		
	}
*/

}

