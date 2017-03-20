package Tests;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.jcraft.jsch.JSchException;

import Data.SSHUploader;
import Data.UploadFile;
import Data.xmlData;
import Pages.AdvancedConfigsPage;
import Pages.ApplyConfigurations;
import Pages.DirectoryPage;
import Pages.MainPage;
import Pages.LoginPage;
import Pages.ServicesPage;

public class LoginTest {

	WebDriver driver;
	LoginPage objLogin;
	MainPage objMainPage;
	UploadFile objUploadFile;
	DirectoryPage objDirectory;
	AdvancedConfigsPage objAdvDefPage;
	ApplyConfigurations objApplyConfigs;
	ServicesPage objServicesPage;
	SSHUploader objSSH;
	
	xmlData objxml= new xmlData();
	String baseUrl,ip;
	String ConfigXmlFile="FilesXML/login.xml";
	
	@BeforeTest
	public void setup() throws ParserConfigurationException, SAXException, IOException{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String timeString=objxml.getxml(ConfigXmlFile,"implicitlyWait");
		long time=Integer.parseInt(timeString);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		baseUrl=objxml.getxml(ConfigXmlFile,"url");
		ip=objxml.getxml(ConfigXmlFile,"ip");
	}

	@Test(priority=0)
	public void LoginSuccessfully() throws InterruptedException, JSchException, IOException, ParserConfigurationException, SAXException{

		driver.get(baseUrl);
		objLogin = new LoginPage(driver);
		objSSH= new SSHUploader();
		//SSHUploader up = new SSHUploader();
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objServicesPage = new ServicesPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objMainPage = new MainPage(driver);
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));

		
		
		
		
		//System.out.println(objxml.ReadcsvFile());
	    //up.connect(ip);
	    
	    //expect -c "spawn /usr/bin/scp Sean_Lilly.zip adaptive@10.10.12.17:/opt/ams/epf_3_4/Sean_Lilly.zip; sleep 5; expect -re \"password\"; send \"ad\r\n\"; set timeout -1; expect -re \"100%\";"
	    //"expect -c 'spawn /bin/su sleep 5; expect \'Password:\'; send \'R0laBill\'; '"
	    //"expect -c 'spawn /bin/su ;sleep 10; expect -re \'Password:\'; send \'R0laBill\'; '"
	    //p.executeCommand("ifconfig"); 
	    
	    
	    //up.executeCommand("cd workspace; bash TestSSHconection.sh;");
	    //Assert.assertTrue(up.executeCommand("ifconfig").contains("172.31.3.48")); 
	    //up.disconnect();

	    
	    

		
	}
	@AfterMethod(alwaysRun=true)
	public void shutDownSelenium() {
		objMainPage = new MainPage(driver);
		objMainPage.clickLogout();
		driver.manage().deleteAllCookies();
	    //driver.quit();
	}
}
