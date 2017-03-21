package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterGroups;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.jcraft.jsch.JSchException;

import Pages.ApplyConfigurations;
import Pages.AdvancedConfigsPage;
import Pages.LoginPage;
import Pages.MainPage;
import Data.SSHUploader;
import Data.xmlData;

public class NetWorkTest {
	
	WebDriver driver;
	LoginPage objLogin;
	MainPage objMainPage;
	AdvancedConfigsPage objAdvDefPage;
	ApplyConfigurations objApplyConfigs;
	SSHUploader objSSH;
	xmlData objxml= new xmlData();
	String baseUrl,baseUrl2,IP,IP2;
	String ConfigXmlFile="FilesXML/NetWork.xml";

	@BeforeTest(alwaysRun=true)
	public void setup() throws ParserConfigurationException, SAXException, IOException{
		System.out.println("@BeforeTest: Setup");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String timeString=objxml.getxml(ConfigXmlFile,"implicitlyWait");
		long time=Integer.parseInt(timeString);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		baseUrl=objxml.getxml(ConfigXmlFile,"url");	
		baseUrl2=objxml.getxml(ConfigXmlFile,"url2");
		IP=objxml.getxml(ConfigXmlFile,"IP");
		IP2=objxml.getxml(ConfigXmlFile,"IP2");
	}
	
	@Test(groups = {"ChangeIP"},priority=1)	
	public void ChangeIP() throws InterruptedException, ParserConfigurationException, SAXException, IOException, JSchException{
		System.out.println("@Test: ChangeIP");
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		//SSHUploader up = new SSHUploader();
		driver.get(baseUrl);
		
		/*up.connect(IP);
		up.executeCommand("echo '' > /var/log/syslog ; echo '' > /opt/system/log/apache/error.log");
	    up.disconnect();*/
		
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),
							 objxml.getxml(ConfigXmlFile,"password"));
		
		objAdvDefPage.goToDefenitionsPage();			
		objAdvDefPage.goToModifyEthXPage("0");			 
		objAdvDefPage.changeIP(						
				objxml.getxml(ConfigXmlFile,"ipp0"),	
				objxml.getxml(ConfigXmlFile,"ipp1"),	
				objxml.getxml(ConfigXmlFile,"ipp2"),
				objxml.getxml(ConfigXmlFile,"ipp3"));
		
		objAdvDefPage.goToDefenitionsPage();
		objApplyConfigs.applyconfigWithDescription("@Test:ChangeIP");
		
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		
		objMainPage.clickLogout();
		
		Thread.sleep(150000);
		
		
	}
	
	@Test(groups={"VerifyIP"},priority=2)
	public void VerifyIP() throws InterruptedException, ParserConfigurationException, SAXException, IOException, JSchException{
		System.out.println("@Test: Verify IP");
		SSHUploader up = new SSHUploader();
		up.connect(IP2);
		//; cat /opt/system/log/apache/error.log
		
		String cmd=up.executeCommand("hostname -i");

	    up.disconnect();
	    
	    Assert.assertTrue(cmd.contains(objxml.getxml(ConfigXmlFile,"ipp0")+"."+
	    							   objxml.getxml(ConfigXmlFile,"ipp1")+"."+
	    							   objxml.getxml(ConfigXmlFile,"ipp2")+"."+
	    							   objxml.getxml(ConfigXmlFile,"ipp3")));
	}
		
	@Test(groups = {"ChangeDomain"},priority=3)	
	public void ChangeDomain() throws InterruptedException, ParserConfigurationException, SAXException, IOException{
		System.out.println("@Test: Change Domain");
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		
		driver.get(baseUrl2);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),
				 			 objxml.getxml(ConfigXmlFile,"password"));
		
		objAdvDefPage.goToDefenitionsPage();			
		objAdvDefPage.goToModifyDomainDefenitions();
		objAdvDefPage.changeDomainAndName(
				objxml.getxml(ConfigXmlFile,"name2"),
				objxml.getxml(ConfigXmlFile,"domain2"));
		objAdvDefPage.goToDefenitionsPage();
		objApplyConfigs.applyConfigs();
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		
		Thread.sleep(150000);
		
	}
	
	@Test(groups={"VerifyDomain"},priority=4)
	public void VerifyDomain() throws InterruptedException, ParserConfigurationException, SAXException, IOException, JSchException{
		System.out.println("@Test: Verify Domain");
		SSHUploader up = new SSHUploader();
		up.connect(IP2);
		String cmd=up.executeCommand("hostname -f");
	    up.disconnect();
	    Assert.assertTrue(cmd.contains(objxml.getxml(ConfigXmlFile,"name2")+"."+
	    							   objxml.getxml(ConfigXmlFile,"domain2")));
	    
	}
	
	@Test(groups = {"ChangeGateway"},priority=5)	
	public void ChangeGateway() throws InterruptedException, ParserConfigurationException, SAXException, IOException{
		
		System.out.println("@Test: Change Gateway");
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		
		driver.get(baseUrl2);
		
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),
				 			 objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objAdvDefPage.goToModifyGateway();
		objAdvDefPage.chengeGateway(
				objxml.getxml(ConfigXmlFile,"eth"),
				objxml.getxml(ConfigXmlFile,"gw0"),
				objxml.getxml(ConfigXmlFile,"gw1"),
				objxml.getxml(ConfigXmlFile,"gw2"),
				objxml.getxml(ConfigXmlFile,"gw3"));
		
		objAdvDefPage.goToDefenitionsPage();
		objApplyConfigs.applyConfigs();
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
		
		Thread.sleep(150000);
	
	}
	
	@Test(groups={"VerifyGateway"},priority=6)
	public void VerifyGateway() throws InterruptedException, ParserConfigurationException, SAXException, IOException, JSchException{
		System.out.println("@Test: Verify Gateway");
		SSHUploader up = new SSHUploader();
		up.connect(IP2);

		String cmd=up.executeCommand("ip r | head -1 | awk '{print $3}'");
		
	    up.disconnect();
	    Assert.assertTrue(cmd.contains(objxml.getxml(ConfigXmlFile,"gw0")+"."+
				   objxml.getxml(ConfigXmlFile,"gw1")+"."+
				   objxml.getxml(ConfigXmlFile,"gw2")+"."+
				   objxml.getxml(ConfigXmlFile,"gw3")));
	}
	
	
	@Test(groups={"ALL"},priority=7)
	public void ALL() throws InterruptedException, ParserConfigurationException, SAXException, IOException, JSchException{
		
		System.out.println("@Test: Change All(IP,Domain,Gateway)");
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		SSHUploader up = new SSHUploader();
		//Thread.sleep(35000);
		Thread.sleep(90000);
		driver.get(baseUrl2);
		
		up.connect(IP2);
		up.executeCommand("echo '' > /var/log/syslog ; echo '' > /opt/system/log/apache/error.log");
	    up.disconnect();
		
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),
							 objxml.getxml(ConfigXmlFile,"password"));
		//CHANGE IP
		objAdvDefPage.goToDefenitionsPage();			
		objAdvDefPage.goToModifyEthXPage("0");			 
		objAdvDefPage.changeIP(						
				objxml.getxml(ConfigXmlFile,"ip0"),	
				objxml.getxml(ConfigXmlFile,"ip1"),	
				objxml.getxml(ConfigXmlFile,"ip2"),
				objxml.getxml(ConfigXmlFile,"ip3"));
		
		//CHANGE DOMAIN
		objAdvDefPage.goToDefenitionsPage();			
		objAdvDefPage.goToModifyDomainDefenitions();
		objAdvDefPage.changeDomainAndName(
				objxml.getxml(ConfigXmlFile,"name"),
				objxml.getxml(ConfigXmlFile,"domain"));
		//CHANGE GATEWAY
		objAdvDefPage.goToDefenitionsPage();
		objAdvDefPage.goToModifyGateway();
		objAdvDefPage.chengeGateway(
				objxml.getxml(ConfigXmlFile,"eth"),
				objxml.getxml(ConfigXmlFile,"gw0"),
				objxml.getxml(ConfigXmlFile,"gw1"),
				objxml.getxml(ConfigXmlFile,"gw2"),
				objxml.getxml(ConfigXmlFile,"gw3"));
		
		objAdvDefPage.goToDefenitionsPage();
		//objApplyConfigs.applyConfigs();
		objApplyConfigs.applyconfigWithDescription("@Test:ALL TOGETHER (IP,DOMAIN,GATEWAY)");
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		
		objMainPage.clickLogout();
		Thread.sleep(120000);
	}
	
	@Test(groups={"VerifyALL"},priority=8)
	public void VerifyALL() throws InterruptedException, ParserConfigurationException, SAXException, IOException, JSchException{
		
		System.out.println("@Test: Verify All(IP,Domain,Gateway)");
		SSHUploader up = new SSHUploader();
		System.out.println("ola");
		up.connect(IP);
		/*; cat /opt/system/log/apache/error.log*/
		String cmd0=up.executeCommand("hostname -i");
	
		Assert.assertTrue(cmd0.contains(IP));
		
	    String cmd1=up.executeCommand("hostname -f");
	    Assert.assertTrue(cmd1.contains(objxml.getxml(ConfigXmlFile,"name")+"."+
	    							   objxml.getxml(ConfigXmlFile,"domain")));
	    
	    String cmd=up.executeCommand("ip r | head -1 | awk '{print $3}'");
	    Assert.assertTrue(cmd.contains(objxml.getxml(ConfigXmlFile,"gw0")+"."+
				   objxml.getxml(ConfigXmlFile,"gw1")+"."+
				   objxml.getxml(ConfigXmlFile,"gw2")+"."+
				   objxml.getxml(ConfigXmlFile,"gw3")));
	    up.disconnect();
	}
	
	
	@AfterGroups(groups = {"ChangeDomain"})/*,alwaysRun=true*/  /*@AfterGroups("shopping")*/
	public void shutDownSelenium() {
		objMainPage = new MainPage(driver);
		objMainPage.clickLogout();
		driver.manage().deleteAllCookies();
	    //driver.quit();
	}

	
}
